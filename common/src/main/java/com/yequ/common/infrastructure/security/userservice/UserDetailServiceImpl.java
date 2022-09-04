package com.yequ.common.infrastructure.security.userservice;

import com.yequ.common.domain.service.user.UserService;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: zhaost
 * @create: 2022-09-01
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserVO loginUserVO = userService.queryUserByName(username);
        return loginUserVO;
    }
}
