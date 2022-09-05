package com.yequ.common.interfaces.outbond.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Data
public class RoleUserVO {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private Long roleId;

    private Long userId;

}
