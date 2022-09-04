package com.yequ.common.interfaces.outbond.login;

import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-30
 **/
@Api("登录接口")
public interface ILogin {

    @ApiOperation("/admin/login")
    LoginResultDto login (UserVO vo);

    @ApiOperation("/admin/logout")
    ResultDto<String> logout (UserVO vo);

    @ApiOperation("/admin/modfiyUser")
    ResultDto<RegisteredUserVO> modfiyUser (RegisteredUserVO vo);

    @ApiOperation("/admin/modfiyRole")
    ResultDto<RoleVO> modfiyRole(RoleVO roleVO);

    @ApiOperation("/admin/modfiyRoleUser")
    ResultDto<RoleUserVO> modfiyRoleUser(RoleUserVO roleUserVO);

}
