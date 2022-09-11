package com.yequ.common.domain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yequ.common.domain.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity findUserByName(String name);

}
