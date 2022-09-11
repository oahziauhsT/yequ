package com.yequ.common.domain.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yequ.common.domain.role.entity.PermissionEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.PermissionVO;
import com.yequ.common.interfaces.outbound.login.QueryPageVO;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
public interface PermissionService extends IService<PermissionEntity> {

    ResultPageDto<PermissionVO> queryPermission (QueryPageVO<PermissionVO> permissionVO);

    ResultDto<PermissionVO> modfiyPermission(PermissionVO PermissionVO);

}
