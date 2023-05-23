package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.Message;
import edu.whu.newspace.mapper.MessageMapper;
import edu.whu.newspace.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
