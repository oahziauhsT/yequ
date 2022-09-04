package com.yequ.common.infrastructure.security.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yequ.common.infrastructure.security.filter.SnowFlake;
import com.yequ.common.interfaces.outbond.login.LoginUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitBaseEntityHandler implements MetaObjectHandler {

    @Autowired
    private SnowFlake snowFlake;
    @Override
    public void insertFill(MetaObject metaObject) {
        long id = snowFlake.nextId();
        this.fillStrategy(metaObject,"id",id);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUserVO loginUserVO =(LoginUserVO)usernamePasswordAuthenticationToken.getPrincipal();
        this.fillStrategy(metaObject,"createBy",loginUserVO.getId());
        this.fillStrategy(metaObject,"createDate",new Date());
        this.fillStrategy(metaObject,"updateBy",loginUserVO.getId());
        this.fillStrategy(metaObject,"updateDate",new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUserVO loginUserVO =(LoginUserVO)usernamePasswordAuthenticationToken.getPrincipal();
        this.fillStrategy(metaObject,"updateBy",loginUserVO.getId());
        this.fillStrategy(metaObject,"updateDate",new Date());
    }
}
