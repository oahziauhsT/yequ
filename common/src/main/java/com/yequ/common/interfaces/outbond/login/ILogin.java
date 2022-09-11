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
@Api(tags = "登录接口")
public interface ILogin {

    @ApiOperation(value = "/admin/login",notes = "用户登录")
    LoginResultDto login (UserVO vo);

    @ApiOperation(value = "/admin/logout",notes = "用户退出")
    ResultDto<String> logout (UserVO vo);

    @ApiOperation(value = "/admin/modfiyUser",notes = "修改用户")
    ResultDto<RegisteredUserVO> modfiyUser (RegisteredUserVO vo);

    @ApiOperation(value = "/admin/modfiyRole",notes = "修改角色")
    ResultDto<RoleVO> modfiyRole(RoleVO roleVO);

    @ApiOperation(value = "/admin/modfiyRoleUser",notes = "修改角色用户关系")
    ResultDto<RoleUserVO> modfiyRoleUser(RoleUserVO roleUserVO);

    @ApiOperation(value = "/admin/queryUser",notes = "查询用户")
    ResultPageDto<RegisteredUserVO> queryUser (QueryPageVO<RegisteredUserVO> vo);

    @ApiOperation(value = "/admin/queryRole",notes = "查询角色")
    ResultPageDto<RoleVO> queryRole (QueryPageVO<RoleVO> roleVO);

    @ApiOperation(value = "/admin/queryPermission",notes = "查询权限")
    ResultPageDto<PermissionVO> queryPermission (QueryPageVO<PermissionVO> permissionVO);

    @ApiOperation(value = "/admin/modfiyRolePermission",notes = "修改角色权限关系")
    ResultDto<RolePermissionVO> modfiyRolePermission (RolePermissionVO rolePermissionVO);

    @ApiOperation(value = "/admin/modfiyPermission",notes = "修改权限")
    ResultDto<PermissionVO> modfiyPermission(PermissionVO PermissionVO);


}
