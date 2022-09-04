package com.yequ.common.domain.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yequ.common.domain.service.user.entity.UserEntity;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
public interface UserService extends IService<UserEntity> {

    LoginUserVO queryUserByName(String name);

}
