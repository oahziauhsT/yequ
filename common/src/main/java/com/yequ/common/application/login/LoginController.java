package com.yequ.common.application.login;

import com.yequ.common.domain.service.UserBusService;
import com.yequ.common.interfaces.outbond.login.RoleUserVO;
import com.yequ.common.interfaces.outbond.login.RoleVO;
import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import com.yequ.common.interfaces.outbond.login.ILogin;
import com.yequ.common.interfaces.outbond.login.RegisteredUserVO;
import com.yequ.common.interfaces.outbond.login.UserVO;
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
}
