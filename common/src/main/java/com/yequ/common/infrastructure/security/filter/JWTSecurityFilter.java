package com.yequ.common.infrastructure.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import com.yequ.common.utils.CommonConstant;
import com.yequ.common.utils.JWTUtil;
import com.yequ.common.utils.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import java.util.Objects;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-01
 **/
@Component
public class JWTSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        checkToken(request,response,filterChain);
    }

    private void checkToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(CommonConstant.TOKEN_STR);
        if(!StringUtil.isEmpty(token)){
            Claims claims = JWTUtil.parseJWT(token);
            String username = claims.getSubject();

            //临时代码：缓存还未加
            /*UserVO userVO = new UserVO();
            userVO.setUsername(username);
            userVO.setPassword(username);
            List<String> list = new ArrayList<>();
            list.add("/admin/logout");
            //list.add("/admin/addUser");
            list.add("ROLE_admin1");
            loginUserVO.setUserVO(userVO);
            loginUserVO.setPermissions(list);*/

            //去缓存中拿用户权限点信息
            LoginUserVO loginUserVO = new LoginUserVO();
            String s = stringRedisTemplate.opsForValue().get(username);
            if(Objects.nonNull(s)){
                //第一种方式 把字符串转为jsonObje 然后赋值给LoginUserVO
            /*JSONObject jsonObject = JSON.parseObject(s);
            loginUserVO.setUserVO(JSON.parseObject(jsonObject.get("userVO").toString(),UserVO.class));
            loginUserVO.setId(jsonObject.getLong("id"));
            loginUserVO.setPermissions(JSON.parseObject(jsonObject.get("permissions").toString(),List.class));*/

                //第二种
                loginUserVO = JSONObject.parseObject(s,LoginUserVO.class);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserVO.getUserVO(),null,loginUserVO.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request,response);
    }

}
