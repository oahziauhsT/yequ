package com.yequ.common.domain.service;

import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultPageDto;
import com.yequ.common.interfaces.outbond.login.*;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: zhaost
 * @create: 2022-09-01
 **/
public interface UserBusService {

    LoginResultDto login(UserVO userVO);

    ResultDto logout(UserVO userVO);

    ResultDto<RegisteredUserVO> modfiyUser(RegisteredUserVO vo);

    ResultDto<RoleVO> modfiyRole(RoleVO roleVO);

    ResultDto<RoleUserVO> modfiyRoleUser(RoleUserVO roleUserVO);

    ResultPageDto<RegisteredUserVO> queryUser( QueryPageVO<RegisteredUserVO> vo);

    ResultPageDto<PermissionVO> queryPermission (QueryPageVO<PermissionVO> permissionVO);

    ResultPageDto<RoleVO> queryRole (QueryPageVO<RoleVO> permissionVO);

    ResultDto<RolePermissionVO> modfiyRolePermission(RolePermissionVO rolePermissionVO);

    ResultDto<PermissionVO> modfiyPermission(PermissionVO PermissionVO);



}
