package com.yequ.common.domain.service.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yequ.common.base.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Data
@TableName("sys_role_user_relation_t")
public class RoleUserRelationEntity extends BaseEntity {

    private Long userId;

    private Long roleId;

}
