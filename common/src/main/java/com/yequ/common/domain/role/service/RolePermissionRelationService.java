package com.yequ.common.domain.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yequ.common.domain.role.entity.RolePermissionRelationEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.login.RolePermissionVO;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
public interface RolePermissionRelationService extends IService<RolePermissionRelationEntity> {

    ResultDto<RolePermissionVO> modfiyRolePermission(RolePermissionVO rolePermissionVO);

}
