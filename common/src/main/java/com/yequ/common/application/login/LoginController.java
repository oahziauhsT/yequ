package com.yequ.common.application.login;

import com.yequ.common.domain.service.UserBusService;
import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import com.yequ.common.interfaces.outbond.login.ILogin;
import com.yequ.common.interfaces.outbond.login.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultDto<String> logout(@RequestBody UserVO vo) {
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.setObj(vo.toString());
        return resultDto;
    }
}
