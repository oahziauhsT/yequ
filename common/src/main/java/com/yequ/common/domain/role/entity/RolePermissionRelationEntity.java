package com.yequ.common.domain.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yequ.common.base.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Data
@TableName("sys_role_permission_relation_t")
public class RolePermissionRelationEntity extends BaseEntity {

    private Long roleId;

    private Long permissionId;

}
