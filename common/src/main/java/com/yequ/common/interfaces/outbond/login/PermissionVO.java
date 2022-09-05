package com.yequ.common.interfaces.outbond.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("权限点")
public class PermissionVO {
    @ApiModelProperty("权限ID")
    Long id;
    @ApiModelProperty("权限点")
    String url;
    @ApiModelProperty("权限描述")
    String description;
}
