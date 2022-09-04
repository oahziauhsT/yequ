package com.yequ.common.domain.service.user.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.service.user.UserService;
import com.yequ.common.domain.service.user.entity.UserEntity;
import com.yequ.common.domain.service.user.mapper.UserMapper;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public LoginUserVO queryUserByName(String name) {
        UserEntity userEntity = baseMapper.findUserByName(name);
        UserVO userVO = new UserVO();
        userVO.setUsername(userEntity.getUsername());
        userVO.setPassword(userEntity.getPassword());
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setUserVO(userVO);
        List<String> list = new ArrayList<>();
        list.add("/admin/logout");
        loginUserVO.setPermissions(list);
        return loginUserVO;
    }
}
