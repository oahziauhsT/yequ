package com.yequ.common.interfaces.outbond.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("角色与权限挂载")
public class RolePermissionVO {

    @ApiModelProperty("关联ID")
    @JsonSerialize(using= ToStringSerializer.class)
    Long id;
    @ApiModelProperty("角色ID")
    Long roleId;
    @ApiModelProperty("权限ID")
    Long permissionId;
    @ApiModelProperty("权限点")
    String url;
    @ApiModelProperty("权限描述")
    String description;
}
