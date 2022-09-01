package com.yequ.common.interfaces.outbond.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-31
 **/
@Data
@ApiModel(value = "请求返回基本信息")
public class BaseDto {

    @ApiModelProperty("状态码")
    private int code = 200;

    @ApiModelProperty("描述")
    private String message = "成功";

}
