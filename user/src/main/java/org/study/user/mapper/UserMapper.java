package org.study.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.study.bean.bean.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
