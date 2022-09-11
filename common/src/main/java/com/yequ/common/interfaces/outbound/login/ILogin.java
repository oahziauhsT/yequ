package com.yequ.common.interfaces.outbound.login;

import com.yequ.common.interfaces.outbound.dto.LoginResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-30
 **/
@Api(tags = "用户登录以及退出相关服务")
public interface ILogin {

    @ApiOperation(value = "用户登录",notes = "/admin/login")
    LoginResultDto login (UserVO vo);

    @ApiOperation(value = "用户退出",notes = "/admin/logout")
    ResultDto<String> logout (UserVO vo);



}
