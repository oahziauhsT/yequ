package com.yequ.common.interfaces.outbound.dto;

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
public class ResultDto<T> extends BaseDto{

    @ApiModelProperty(value = "返回的业务数据")
    private T obj;

}
