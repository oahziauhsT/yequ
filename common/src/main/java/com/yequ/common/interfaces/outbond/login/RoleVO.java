package com.yequ.common.interfaces.outbond.login;

import lombok.Data;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Data
public class RoleVO {

    private Long id;

    private String name;

    private String code;

}
