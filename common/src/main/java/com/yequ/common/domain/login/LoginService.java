package com.yequ.common.domain.login;

import com.yequ.common.interfaces.outbound.dto.LoginResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.login.UserVO;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
public interface LoginService {

    LoginResultDto login(UserVO userVO);

    ResultDto logout(UserVO userVO);

}
