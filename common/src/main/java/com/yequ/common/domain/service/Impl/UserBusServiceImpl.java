package com.yequ.common.domain.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.yequ.common.domain.service.UserBusService;
import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import com.yequ.common.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginResultDto login(UserVO userVO) {
        //登录信息存入UsernamePasswordAuthenticationToken 进入AuthenticationManager进行校验
        LoginResultDto loginResultDto = new LoginResultDto();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userVO.getUsername(),userVO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("认证失败");
        }
        LoginUserVO loginUserVO = (LoginUserVO) authenticate.getPrincipal();
        String username = loginUserVO.getUsername();
        //loginUserVO存入缓存
        stringRedisTemplate.opsForValue().set(username, JSONObject.toJSONString(loginUserVO));
        //登录账号生成token 将token返回请求方 作为下次访问的凭证
        String token = JWTUtil.createJWT(username);
        loginResultDto.setToken(token);
        return loginResultDto;
    }

    @Override
    public ResultDto logout(UserVO userVO) {
        //登录信息存入UsernamePasswordAuthenticationToken 进入AuthenticationManager进行校验
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userVO = (UserVO) authentication.getPrincipal();
        String username = userVO.getUsername();
        stringRedisTemplate.delete(username);
        ResultDto dto = new ResultDto();
        dto.setMessage("退出成功");
        return dto;
    }

}
