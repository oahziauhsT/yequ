package com.yequ.common.domain.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yequ.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Data
@TableName("sys_log_t")
public class LogEntity extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -2089296594963692303L;

    private String param;

    private String operation;

    private String desc_;

}
