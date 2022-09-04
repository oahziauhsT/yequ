package com.yequ.common.interfaces.outbond.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yequ.common.interfaces.outbond.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zhaost
 * @create: 2022-09-02
 **/
@Data
@ApiModel(value = "RegisteredUserVO" , description = "用户注册信息")
public class RegisteredUserVO extends UserVO{

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String username;

    private String password;

    @ApiModelProperty(value = "性别 0:男 1:女")
    private int sex;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "工号")
    private String workId;

}
