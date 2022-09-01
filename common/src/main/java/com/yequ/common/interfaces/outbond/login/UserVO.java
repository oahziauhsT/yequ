package com.yequ.common.interfaces.outbond.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-31
 **/
@Data
@ApiModel(value = "UserVO",description = "用户登录信息")
public class UserVO {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

}
