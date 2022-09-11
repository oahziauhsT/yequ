package com.yequ.common.domain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yequ.common.domain.user.entity.UserEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.LoginUserVO;
import com.yequ.common.interfaces.outbound.login.QueryPageVO;
import com.yequ.common.interfaces.outbound.login.RegisteredUserVO;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
public interface UserService extends IService<UserEntity> {

    LoginUserVO queryUserByName(String name);

    ResultDto<RegisteredUserVO> modfiyUser(RegisteredUserVO vo);

    ResultPageDto<RegisteredUserVO> queryUser(QueryPageVO<RegisteredUserVO> vo);



}
