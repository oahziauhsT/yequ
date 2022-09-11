package com.yequ.common.domain.role.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.role.mapper.RoleMapper;
import com.yequ.common.domain.role.service.RoleService;
import com.yequ.common.domain.role.entity.RoleEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.dto.ResultPageDto;
import com.yequ.common.interfaces.outbound.login.QueryPageVO;
import com.yequ.common.interfaces.outbound.login.RoleVO;
import com.yequ.common.utils.CommonConstant;
import com.yequ.common.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {


    @Override
    public ResultPageDto<RoleVO> queryRole(QueryPageVO<RoleVO> queryPageVO) {
        RoleVO  roleVO = queryPageVO.getParamObj();
        Page<RoleEntity> roleEntityPage= new Page<>();
        BeanUtils.copyProperties(queryPageVO,roleEntityPage);
        LambdaQueryWrapper<RoleEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(!StringUtil.isEmpty(roleVO.getName())) {
            lambdaQueryWrapper.like(RoleEntity::getName,roleVO.getName());
        }
        if(!StringUtil.isEmpty(roleVO.getCode())) {
            lambdaQueryWrapper.like(RoleEntity::getCode,roleVO.getCode());
        }
        roleEntityPage =super.page(roleEntityPage,lambdaQueryWrapper);
        ResultPageDto<RoleVO> resultPageDto = new ResultPageDto<>();
        BeanUtils.copyProperties(roleEntityPage,resultPageDto);
        return resultPageDto;
    }
    @Override
    public ResultDto<RoleVO> modfiyRole(RoleVO roleVO) {
        ResultDto<RoleVO> resultDto = new ResultDto<RoleVO>();
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleVO,roleEntity);
        if(!super.saveOrUpdate(roleEntity)){
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            resultDto.setMessage("修改角色失败");
            resultDto.setObj(roleVO);
        }else {
            BeanUtils.copyProperties(roleEntity,roleVO);
            resultDto.setObj(roleVO);
        }
        return resultDto;
    }

}
