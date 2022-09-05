package com.yequ.common.application.login;

import com.yequ.common.domain.service.UserBusService;
import com.yequ.common.interfaces.outbond.dto.ResultPageDto;
import com.yequ.common.interfaces.outbond.login.*;
import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-30
 **/
@RestController
@RequestMapping("/admin")
public class LoginController implements ILogin {

    @Autowired
    private UserBusService userBusService;

    @Override
    @PostMapping("/login")
    public LoginResultDto login(@RequestBody UserVO vo) {
        LoginResultDto loginResultDto = userBusService.login(vo);
        return loginResultDto;
    }

    @Override
    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('/admin/logout')")
    public ResultDto logout(@RequestBody UserVO vo) {
        return userBusService.logout(vo);
    }

    @Override
    @PostMapping("/modfiyUser")
    @PreAuthorize(" hasAnyAuthority('/admin/addUser') || hasRole('admin')")
    public ResultDto<RegisteredUserVO> modfiyUser(@RequestBody RegisteredUserVO vo) {
        return userBusService.modfiyUser(vo);
    }

    @Override
    @PostMapping("/modfiyRole")
    public ResultDto<RoleVO> modfiyRole(@RequestBody RoleVO roleVO) {
        return userBusService.modfiyRole(roleVO);
    }

    @Override
    @PostMapping("/modfiyRoleUser")
    public ResultDto<RoleUserVO> modfiyRoleUser(@RequestBody RoleUserVO roleUserVO) {
        return userBusService.modfiyRoleUser(roleUserVO);
    }

    @Override
    @PostMapping("/queryUser")
    public ResultPageDto<RegisteredUserVO> queryUser(@RequestBody QueryPageVO<RegisteredUserVO> vo) {
        return userBusService.queryUser(vo);
    }

    @Override
    public ResultPageDto<RoleVO> queryRole(QueryPageVO<RoleVO> roleVO) {
        return userBusService.queryRole(roleVO);
    }

    @Override
    public ResultPageDto<PermissionVO> queryPermission(QueryPageVO<PermissionVO> permissionVO) {
        return userBusService.queryPermission(permissionVO);
    }

    @Override
    public ResultDto<RolePermissionVO> modfiyRolePermission(RolePermissionVO rolePermissionVO) {
        return userBusService.modfiyRolePermission(rolePermissionVO);
    }

    @Override
    public ResultDto<PermissionVO> modfiyPermission(PermissionVO permissionVO) {
        return userBusService.modfiyPermission(permissionVO);
    }

}
