package com.yequ.common.domain.role.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.role.mapper.PermissionMapper;
import com.yequ.common.domain.role.service.PermissionService;
import com.yequ.common.domain.role.entity.PermissionEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.PermissionVO;
import com.yequ.common.interfaces.outbound.login.QueryPageVO;
import com.yequ.common.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    @Override
    public ResultDto<PermissionVO> modfiyPermission(PermissionVO PermissionVO) {
        return null;
    }

    @Override
    public ResultPageDto<PermissionVO> queryPermission(QueryPageVO<PermissionVO> queryPageVO) {
        PermissionVO  permissionVO = queryPageVO.getParamObj();
        Page<PermissionEntity> permissionEntityPage= new Page<>();
        BeanUtils.copyProperties(queryPageVO,permissionEntityPage);
        LambdaQueryWrapper<PermissionEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(!StringUtil.isEmpty(permissionVO.getDescription())) {
            lambdaQueryWrapper.like(PermissionEntity::getDescription,permissionVO.getDescription());
        }
        if(!StringUtil.isEmpty(permissionVO.getUrl())) {
            lambdaQueryWrapper.like(PermissionEntity::getUrl,permissionVO.getUrl());
        }
        permissionEntityPage =super.page(permissionEntityPage,lambdaQueryWrapper);
        ResultPageDto<PermissionVO> resultPageDto = new ResultPageDto<>();
        BeanUtils.copyProperties(permissionEntityPage,resultPageDto);
        return resultPageDto;
    }


}
