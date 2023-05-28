package edu.whu.newspace.controller.scheduler;

import edu.whu.newspace.constant.BlogsStatus;
import edu.whu.newspace.constant.MessageConstant;
import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.entity.Message;
import edu.whu.newspace.service.IAuditService;
import edu.whu.newspace.service.IBlogsService;
import edu.whu.newspace.service.IFriendsService;
import edu.whu.newspace.service.IMessageService;
import edu.whu.newspace.util.SensitiveFilterUtils;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Newspace
 * @version 1.0
 * @description 定时审计任务, 每个5分钟审核20条记录
 * @date 2023/5/25 15:01
 */
@Component
public class AuditScheduler {
    @Autowired
    private IAuditService auditService;

    @Autowired
    private IBlogsService blogsService;

    @Autowired
    private IFriendsService friendsService;
    @Autowired
    private IMessageService messageService;



    /**
     * 每五分钟自动检查待处理的关键字
     * @throws InterruptedException
     */
    // 每5分钟执行一次
    @Scheduled(cron = "0 0/5 * * * ?")
    public void auditBlogs() throws InterruptedException {
        // 查询等待审核的文章
        int DEFAULT_TASKS = 20;
        List<Blogs> blogs = blogsService.queryBlogsByStatus(DEFAULT_TASKS, BlogsStatus.WAITING_FOR_AUDIT_CODE);

        int processors = Runtime.getRuntime().availableProcessors();
        int size = blogs.size();
        if(size <= 0) {
            // 无待处理任务
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        blogs.forEach(blog -> {
            executorService.execute(() -> {
                try {
                    auditService.auditBlogs(blog.getId());
                    notifyFriends(blog);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        countDownLatch.await(2, TimeUnit.MINUTES);
    }

    /**
     * 在审核完毕所有的敏感词内容之后向好友发送通知
     * @param blogs     博客信息
     * @throws InterruptedException
     */
    private void notifyFriends(Blogs blogs) throws InterruptedException {
        int page = 1;
        int size = 10;
        Long userId = blogs.getUserId();
        String brief = blogs.getContent().substring(0, Math.min(100, blogs.getContent().length()));
        List<Long> followees;
        while((followees = friendsService.getFollowees(userId, page, size)).size() > 0) {
            // 还有好友未通知
            CountDownLatch countDownLatch = new CountDownLatch(size);
            ExecutorService executorService = Executors.newFixedThreadPool(size);
            followees.forEach(followee -> {
                executorService.execute(() -> {
                    try{
                        Message message = new Message();
                        message.setFromId(userId);
                        message.setToId(followee);
                        message.setContent(brief);
                        message.setHasRead(MessageConstant.NOT_READ);
                        message.setCreatedDate(LocalDateTime.now());
                        message.setConversationId("");
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            });
            countDownLatch.await(60, TimeUnit.SECONDS);
        }
    }
}
