package com.yequ.common.application.login;

import com.yequ.common.domain.login.LoginService;
import com.yequ.common.infrastructure.aspect.LogAnnotation;
import com.yequ.common.interfaces.outbound.login.*;
import com.yequ.common.interfaces.outbound.dto.LoginResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private LoginService loginService;

    @Override
    @PostMapping("/login")
    @LogAnnotation
    public LoginResultDto login(@RequestBody UserVO vo) {
        LoginResultDto loginResultDto = loginService.login(vo);
        return loginResultDto;
    }

    @Override
    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('/admin/logout')")
    @LogAnnotation
    public ResultDto logout(@RequestBody UserVO vo) {
        return loginService.logout(vo);
    }

}
