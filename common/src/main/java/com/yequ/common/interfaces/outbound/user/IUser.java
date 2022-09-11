package com.yequ.common.interfaces.outbound.user;

import com.yequ.common.interfaces.outbound.dto.LoginResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Api(tags = "用户基本信息以及权限相关服务")
public interface IUser {

    @ApiOperation(value = "修改用户",notes = "/admin/modfiyUser")
    ResultDto<RegisteredUserVO> modfiyUser (RegisteredUserVO vo);

    @ApiOperation(value = "修改角色",notes = "/admin/modfiyRole")
    ResultDto<RoleVO> modfiyRole(RoleVO roleVO);

    @ApiOperation(value = "修改角色用户关系",notes = "/admin/modfiyRoleUser")
    ResultDto<RoleUserVO> modfiyRoleUser(RoleUserVO roleUserVO);

    @ApiOperation(value = "查询用户",notes = "/admin/queryUser")
    ResultPageDto<RegisteredUserVO> queryUser (QueryPageVO<RegisteredUserVO> vo);

    @ApiOperation(value = "查询角色",notes = "/admin/queryRole")
    ResultPageDto<RoleVO> queryRole (QueryPageVO<RoleVO> roleVO);

    @ApiOperation(value = "查询权限",notes = "/admin/queryPermission")
    ResultPageDto<PermissionVO> queryPermission (QueryPageVO<PermissionVO> permissionVO);

    @ApiOperation(value = "修改角色权限关系",notes = "/admin/modfiyRolePermission")
    ResultDto<RolePermissionVO> modfiyRolePermission (RolePermissionVO rolePermissionVO);

    @ApiOperation(value = "修改权限",notes = "/admin/modfiyPermission")
    ResultDto<PermissionVO> modfiyPermission(PermissionVO PermissionVO);
}
