package com.yequ.common.infrastructure.security.userservice;

import com.yequ.common.domain.service.user.Impl.UserServiceImpl;
import com.yequ.common.domain.service.user.UserService;
import com.yequ.common.domain.service.user.entity.UserEntity;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaost
 * @create: 2022-09-01
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户信息
        UserEntity userEntity = userServiceImpl.getBaseMapper().findUserByName(username);
        LoginUserVO loginUserVO = new LoginUserVO();
        UserVO userVO = new UserVO();
        userVO.setPassword(userEntity.getPassword());
        userVO.setUsername(userEntity.getUsername());
        loginUserVO.setId(userEntity.getId());
        loginUserVO.setUserVO(userVO);
        //获取用户权限点
        List<String> permissions = new ArrayList<>();
        permissions.add("/admin/login");
        permissions.add("/admin/logout");
        permissions.add("ROLE_admin");
        loginUserVO.setPermissions(permissions);
        return loginUserVO;
    }
}
