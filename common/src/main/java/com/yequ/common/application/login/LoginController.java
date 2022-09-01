package com.yequ.common.application.login;

import com.yequ.common.interfaces.outbond.dto.ResoultDto;
import com.yequ.common.interfaces.outbond.login.ILogin;
import com.yequ.common.interfaces.outbond.login.UserVO;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-30
 **/
@RestController
@RequestMapping("/admin")
public class LoginController implements ILogin {

    @Override
    @PostMapping("/login")
    public ResoultDto<String> login(@RequestBody UserVO vo) {
        ResoultDto<String> resoultDto = new ResoultDto<>();
        resoultDto.setObj(vo.toString());
        return resoultDto;
    }

    @Override
    @PostMapping("/logout")
    public ResoultDto<String> logout(UserVO vo) {
        ResoultDto<String> resoultDto = new ResoultDto<>();
        resoultDto.setObj(vo.toString());
        return resoultDto;
    }
}
