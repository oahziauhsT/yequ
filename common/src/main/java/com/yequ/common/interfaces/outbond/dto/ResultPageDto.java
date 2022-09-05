package com.yequ.common.interfaces.outbond.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-05
 **/
@Data
public class ResultPageDto<T> {


    @ApiModelProperty("总数")
    private long total = 0;

    @ApiModelProperty("每页显示条数，默认 10")
    private  long size = 10;

    @ApiModelProperty("当前页")
    private long current = 1;

    @ApiModelProperty("业务查询结果")
    private List<T> records;

}
