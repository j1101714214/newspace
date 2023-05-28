package edu.whu.newspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.newspace.constant.MessageConstant;
import edu.whu.newspace.entity.Message;
import edu.whu.newspace.mapper.MessageMapper;
import edu.whu.newspace.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public List<Message> queryUserMessages(Long userId, int pageNum, int pageSize) {
        return messageMapper.selectPage(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Message>().eq(Message::getToId, userId)
        ).getRecords();
    }

    @Override
    public List<Message> queryUserUncheckedMessages(Long userId, int pageNum, int pageSize) {
        return messageMapper.selectPage(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<Message>().eq(Message::getToId, userId)
                        .eq(Message::getHasRead, MessageConstant.HAS_READ)
        ).getRecords();
    }

    @Override
    public boolean checkMessages(Long messageId) {
        LambdaUpdateWrapper<Message> luw = new LambdaUpdateWrapper<>();
        luw.eq(Message::getId, messageId).set(Message::getHasRead, MessageConstant.HAS_READ);
        return messageMapper.update(null, luw) == 1;
    }

    @Override
    public boolean addMessages(Message message) {
        return messageMapper.insert(message) == 1;
    }

    @Override
    public boolean deleteMessage(Long messageId) {
        return messageMapper.deleteById(messageId) == 1;
    }
}
