package com.yequ.common.domain.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yequ.common.domain.role.entity.RoleUserRelationEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.login.RoleUserVO;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
public interface RoleUserRelationService extends IService<RoleUserRelationEntity> {

    ResultDto<RoleUserVO> modfiyRoleUser(RoleUserVO roleUserVO);

}
