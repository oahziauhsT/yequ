package com.yequ.common.domain.service.Impl;

import com.yequ.common.domain.service.UserBusService;
import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import com.yequ.common.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: zhaost
 * @create: 2022-09-01
 **/
@Service
public class UserBusServiceImpl implements UserBusService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public LoginResultDto login(UserVO userVO) {
        LoginResultDto loginResultDto = new LoginResultDto();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userVO.getUsername(),userVO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("认证失败");
        }
        LoginUserVO loginUserVO = (LoginUserVO) authenticate.getPrincipal();
        String username = loginUserVO.getUsername();
        //登录账号生成token 将token返回请求方 作为下次访问的凭证
        String token = JWTUtil.createJWT(username);
        loginResultDto.setToken(token);
        return loginResultDto;
    }

    @Override
    public LoginResultDto logout(UserVO userVO) {
        return null;
    }

}
