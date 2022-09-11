package com.yequ.common.domain.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yequ.common.domain.role.entity.RoleEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.QueryPageVO;
import com.yequ.common.interfaces.outbound.login.RoleVO;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
public interface RoleService extends IService<RoleEntity> {

    ResultDto<RoleVO> modfiyRole(RoleVO roleVO);

    ResultPageDto<RoleVO> queryRole (QueryPageVO<RoleVO> permissionVO);

}
