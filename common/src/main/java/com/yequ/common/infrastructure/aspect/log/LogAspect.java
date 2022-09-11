package com.yequ.common.infrastructure.aspect.log;

import com.alibaba.fastjson.JSONObject;
import com.yequ.common.domain.log.entity.LogEntity;
import com.yequ.common.infrastructure.mq.ProductMessageHandler;
import com.yequ.common.interfaces.outbound.login.LoginUserVO;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Component
@Aspect
public class LogAspect {

    @Autowired
    private ProductMessageHandler productMessageHandler;

    private int MAX_LENGTH = 500;
    private String ANONYMOUS_USER = "anonymousUser";

    @Pointcut(value = "@annotation(com.yequ.common.infrastructure.aspect.LogAnnotation)")
    public void annotationPointCut(){}

    @Around("annotationPointCut()")
    Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{

        //获取实现类的接口
        Class<?>[] class_ = joinPoint.getTarget().getClass().getInterfaces();
        //获取当前方法名
        String methodName = joinPoint.getSignature().getName();
        //获取当前传参
        Object[] argument = joinPoint.getArgs();

        //获取当前类
        Class<?> classTarget = joinPoint.getTarget().getClass();
        //获取当前传参的类型
        Class<?>[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method obeMethod = classTarget.getMethod(methodName,par);
        obeMethod = class_[0].getMethod(methodName,par);

        ApiOperation apiOperation = obeMethod.getAnnotation(ApiOperation.class);

        LogEntity logEntity = new LogEntity();
        logEntity.setOperation(apiOperation.value());
        logEntity.setDesc_(apiOperation.notes());
        String str = JSONObject.toJSONString(argument);
        str = str.length()>MAX_LENGTH ? str.substring(0,MAX_LENGTH-3)+"....":str;
        logEntity.setParam(str);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().toString().contains(ANONYMOUS_USER)){
            logEntity.setCreateBy(0L);
        }else {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
            LoginUserVO loginUserVO = (LoginUserVO) usernamePasswordAuthenticationToken.getPrincipal();
            logEntity.setCreateBy(loginUserVO.getId());
        }
        logEntity.setCreateDate(new Date());

        productMessageHandler.sendLog(logEntity);
        return joinPoint.proceed();
    }

}
