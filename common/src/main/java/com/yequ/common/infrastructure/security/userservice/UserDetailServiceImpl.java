package com.yequ.common.infrastructure.security.userservice;

import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import com.yequ.common.utils.CommonConstant;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserVO loginUserVO = new LoginUserVO();
        UserVO userVO = new UserVO();
        userVO.setUsername(username);
        userVO.setPassword(CommonConstant.PASS_WORD);
        List<String> list = new ArrayList<>();
        list.add("/admin/logout");
        loginUserVO.setUserVO(userVO);
        loginUserVO.setPermissions(list);
        return loginUserVO;
    }
}
