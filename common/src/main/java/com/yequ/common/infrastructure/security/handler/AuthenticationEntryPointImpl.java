package com.yequ.common.infrastructure.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.yequ.common.interfaces.outbond.dto.BaseDto;
import com.yequ.common.utils.WebResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhaost
 * @create: 2022-09-02
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        BaseDto baseDto = new BaseDto();
        baseDto.setCode(HttpStatus.UNAUTHORIZED.value());
        baseDto.setMessage("认证失败,请重新登录");
        WebResponseUtils.renderResponse(httpServletResponse, JSONObject.toJSONString(baseDto));
    }
}
