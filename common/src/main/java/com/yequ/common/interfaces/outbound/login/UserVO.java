package com.yequ.common.interfaces.outbound.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-31
 **/
@Data
@ApiModel(value = "UserVO",description = "用户登录信息")
public class UserVO implements Serializable {

    private static final long serialVersionUID = -953965699051334911L;

    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

}
