package com.yequ.common.interfaces.outbond.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zhaost
 * @create: 2022-09-01
 **/
@Data
@ApiModel("登录返回对象")
public class LoginResultDto extends BaseDto{

    @ApiModelProperty("认证通过的有效token")
    private String token;

}
