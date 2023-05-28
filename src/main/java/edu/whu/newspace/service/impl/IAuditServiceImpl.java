package edu.whu.newspace.service.impl;

import edu.whu.newspace.constant.BlogsStatus;
import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.service.IAuditService;
import edu.whu.newspace.service.IBlogsService;
import edu.whu.newspace.util.SensitiveFilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Newspace
 * @version 1.0
 * @description 审计控制器
 * @date 2023/5/24 16:53
 */
@Service
@Transactional
public class IAuditServiceImpl implements IAuditService {
    @Autowired
    private IBlogsService blogsService;

    @Override
    public void auditBlogs(Long id) {
        Blogs blogs = blogsService.queryBlogById(id);
        if(SensitiveFilterUtils.toolgoodWords(blogs.getContent())) {
            // 存在敏感内容, 将状态修改为reject.
            blogsService.updateBlogStatus(id, BlogsStatus.REJECT_AUDIT_CODE);
        }
    }
}
