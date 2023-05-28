package edu.whu.newspace.service;

import edu.whu.newspace.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
public interface IMessageService extends IService<Message> {
    /**
     * 查询用户所有推送消息
     * @param userId    用户id
     * @param pageNum   当前页号
     * @param pageSize  页面大小
     * @return          消息列表
     */
    List<Message> queryUserMessages(Long userId, int pageNum, int pageSize);

    /**
     * 查询未被标记的消息推送
     * @param userId    用户id
     * @param pageNum   当前页号
     * @param pageSize  页面大小
     * @return          消息列表
     */
    List<Message> queryUserUncheckedMessages(Long userId, int pageNum, int pageSize);
    /**
     * 标记消息为已读
     * @param messageId 消息id
     * @return          操作结果
     */
    boolean checkMessages(Long messageId);

    /**
     * 添加消息
     * @param message   待添加消息
     * @return          操作结果
     */
    boolean addMessages(Message message);

    /**
     * 删除消息
     * @param messageId  待删除消息id
     * @return          操作结果
     */
    boolean deleteMessage(Long messageId);

}
