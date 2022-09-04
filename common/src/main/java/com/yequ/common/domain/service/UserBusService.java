package com.yequ.common.domain.service;

import com.yequ.common.interfaces.outbond.dto.LoginResultDto;
import com.yequ.common.interfaces.outbond.dto.ResultDto;
import com.yequ.common.interfaces.outbond.login.UserVO;

/**
 * @author: zhaost
 * @create: 2022-09-01
 **/
public interface UserBusService {

    LoginResultDto login(UserVO userVO);

    ResultDto logout(UserVO userVO);

}
