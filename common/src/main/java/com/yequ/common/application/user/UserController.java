package com.yequ.common.application.user;

import com.yequ.common.domain.role.service.PermissionService;
import com.yequ.common.domain.role.service.RolePermissionRelationService;
import com.yequ.common.domain.role.service.RoleService;
import com.yequ.common.domain.role.service.RoleUserRelationService;
import com.yequ.common.domain.user.service.UserService;
import com.yequ.common.infrastructure.aspect.LogAnnotation;
import com.yequ.common.interfaces.outbound.user.IUser;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@RestController
@RequestMapping("/admin")
public class UserController implements IUser {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleUserRelationService roleUserRelationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @Override
    @PostMapping("/modfiyUser")
    @PreAuthorize(" hasAnyAuthority('/admin/addUser') || hasRole('admin')")
    @LogAnnotation
    public ResultDto<RegisteredUserVO> modfiyUser(@RequestBody RegisteredUserVO vo) {
        return userService.modfiyUser(vo);
    }

    @Override
    @PostMapping("/modfiyRole")
    @LogAnnotation
    public ResultDto<RoleVO> modfiyRole(@RequestBody RoleVO roleVO) {
        return roleService.modfiyRole(roleVO);
    }

    @Override
    @PostMapping("/modfiyRoleUser")
    @LogAnnotation
    public ResultDto<RoleUserVO> modfiyRoleUser(@RequestBody RoleUserVO roleUserVO) {
        return roleUserRelationService.modfiyRoleUser(roleUserVO);
    }

    @Override
    @PostMapping("/queryUser")
    @LogAnnotation
    public ResultPageDto<RegisteredUserVO> queryUser(@RequestBody QueryPageVO<RegisteredUserVO> vo) {
        return userService.queryUser(vo);
    }

    @Override
    @PostMapping("/queryRole")
    @LogAnnotation
    public ResultPageDto<RoleVO> queryRole(@RequestBody QueryPageVO<RoleVO> roleVO) {
        return roleService.queryRole(roleVO);
    }

    @Override
    @PostMapping("/queryPermission")
    @LogAnnotation
    public ResultPageDto<PermissionVO> queryPermission(@RequestBody QueryPageVO<PermissionVO> permissionVO) {
        return permissionService.queryPermission(permissionVO);
    }

    @Override
    @PostMapping("/modfiyRolePermission")
    @LogAnnotation
    public ResultDto<RolePermissionVO> modfiyRolePermission(@RequestBody RolePermissionVO rolePermissionVO) {
        return rolePermissionRelationService.modfiyRolePermission(rolePermissionVO);
    }

    @Override
    @PostMapping("/modfiyPermission")
    @LogAnnotation
    public ResultDto<PermissionVO> modfiyPermission(@RequestBody PermissionVO permissionVO) {
        return permissionService.modfiyPermission(permissionVO);
    }
}
