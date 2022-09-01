package com.yequ.common.interfaces.outbond.dto;

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
@ApiModel(value = "返回的结果集")
public class ResoultDto<T> extends BaseDto{

    @ApiModelProperty(value = "返回的业务数据")
    private T obj;

}
