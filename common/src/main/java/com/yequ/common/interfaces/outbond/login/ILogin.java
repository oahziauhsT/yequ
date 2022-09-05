package com.yequ.common.interfaces.outbond.login;

import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultPageDto;
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

    @ApiOperation("/admin/queryUser")
    ResultPageDto<RegisteredUserVO> queryUser (QueryPageVO<RegisteredUserVO> vo);

    @ApiOperation("/admin/queryRole")
    ResultPageDto<RoleVO> queryRole (QueryPageVO<RoleVO> roleVO);

    @ApiOperation("/admin/queryPermission")
    ResultPageDto<PermissionVO> queryPermission (QueryPageVO<PermissionVO> permissionVO);

    @ApiOperation("/admin/modfiyRolePermission")
    ResultDto<RolePermissionVO> modfiyRolePermission (RolePermissionVO rolePermissionVO);

    @ApiOperation("/admin/modfiyPermission")
    ResultDto<PermissionVO> modfiyPermission(PermissionVO PermissionVO);


}
