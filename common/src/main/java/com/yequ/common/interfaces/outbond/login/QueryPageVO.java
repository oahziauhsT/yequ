package com.yequ.common.interfaces.outbond.login;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.yequ.common.interfaces.outbond.dto.ResultPageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class QueryPageVO<T>  extends ResultPageDto<T> {

    @ApiModelProperty("查询业务参数")
    private T paramObj;

    @ApiModelProperty("查询排序字段")
   private  List<OrderItem> orders = new ArrayList<>();

}
