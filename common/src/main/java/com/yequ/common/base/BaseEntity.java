package com.yequ.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-03
 **/
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 82550430753999230L;

    /**
     * 数据库唯一标识
     */
    Long id;

    /**
     * 创建者
     */
    String  createBy;
    /**
     * 创建时间
     */
    Date createDate;
    /**
     * 更新者
     */
    String  updateBy;

    /**
     * 更新时间
     */
    Date   updateDate;

    /**
     * 是否有效(0:有效，1：删除)
     */
    Integer   enable;
    /**
     * 租户编码
     */
    String  tenant;



}
