package com.yequ.common.interfaces.outbond.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = -3257010800472015028L;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String code;

}
