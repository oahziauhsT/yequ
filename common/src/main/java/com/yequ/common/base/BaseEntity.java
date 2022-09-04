package com.yequ.common.base;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @TableField(fill = FieldFill.INSERT)
    Long id;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    Long  createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    Date createDate;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Long  updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Date   updateDate;

    /**
     * 是否有效(0:有效，1：删除)
     */
    Integer   enable;
    /**
     * 租户编码
     */
    @TableField(fill = FieldFill.INSERT)
    String  tenant;



}
