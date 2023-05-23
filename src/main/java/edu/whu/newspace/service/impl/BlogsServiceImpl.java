package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.Blogs;
import edu.whu.newspace.mapper.BlogsMapper;
import edu.whu.newspace.service.IBlogsService;
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
public class BlogsServiceImpl extends ServiceImpl<BlogsMapper, Blogs> implements IBlogsService {

}
