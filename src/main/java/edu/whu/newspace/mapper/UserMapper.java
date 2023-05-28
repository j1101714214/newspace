package edu.whu.newspace.mapper;

import edu.whu.newspace.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
