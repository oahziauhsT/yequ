package com.yequ.common.infrastructure.security.filter;

import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import com.yequ.common.utils.CommonConstant;
import com.yequ.common.utils.JWTUtil;
import com.yequ.common.utils.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-01
 **/
@Component
public class JWTSecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        checkToken(request,response,filterChain);
    }

    private void checkToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(CommonConstant.TOKEN_STR);
        if(!StringUtil.isEmpty(token)){
            Claims claims = JWTUtil.parseJWT(token);
            String username = claims.getSubject();
            //去缓存中拿用户权限点信息
            //临时代码：缓存还未加
            LoginUserVO loginUserVO = new LoginUserVO();
            UserVO userVO = new UserVO();
            userVO.setUsername(username);
            userVO.setPassword(username);
            List<String> list = new ArrayList<>();
            list.add("/admin/logout");
            //list.add("/admin/addUser");
            list.add("ROLE_admin");
            loginUserVO.setUserVO(userVO);
            loginUserVO.setPermissions(list);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userVO,null,loginUserVO.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }

}
