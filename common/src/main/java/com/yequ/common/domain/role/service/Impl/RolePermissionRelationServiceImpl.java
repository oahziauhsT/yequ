package com.yequ.common.domain.role.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.role.entity.PermissionEntity;
import com.yequ.common.domain.role.mapper.RolePermissionRelationMapper;
import com.yequ.common.domain.role.service.PermissionService;
import com.yequ.common.domain.role.service.RolePermissionRelationService;
import com.yequ.common.domain.role.entity.RolePermissionRelationEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.login.RolePermissionVO;
import com.yequ.common.utils.CommonConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class RolePermissionRelationServiceImpl extends ServiceImpl<RolePermissionRelationMapper, RolePermissionRelationEntity> implements RolePermissionRelationService {

    @Autowired
    private PermissionService permissionService;

    @Override
    public ResultDto<RolePermissionVO> modfiyRolePermission(RolePermissionVO rolePermissionVO) {
        //todo 1.判断权限点ID是否存在，不存在新增
        //todo 2.将权限点ID与校色ID绑定入库
        ResultDto<RolePermissionVO> resultDto =  new ResultDto<RolePermissionVO>();
        RolePermissionRelationEntity rolePermissionRelationEntity = new RolePermissionRelationEntity();
        BeanUtils.copyProperties(rolePermissionVO,rolePermissionRelationEntity);
        if(rolePermissionVO.getPermissionId()==null){
            PermissionEntity permissionEntity = new PermissionEntity();
            permissionEntity.setUrl(rolePermissionVO.getUrl());
            permissionEntity.setDescription(rolePermissionVO.getDescription());
            permissionService.save(permissionEntity);
            rolePermissionRelationEntity.setPermissionId(permissionEntity.getId());
        }
        if(!super.saveOrUpdate(rolePermissionRelationEntity)){
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            resultDto.setMessage("修改用户失败");
            resultDto.setObj(rolePermissionVO);
        }else {
            BeanUtils.copyProperties(rolePermissionRelationEntity, rolePermissionVO);
            resultDto.setObj(rolePermissionVO);
        }
        return resultDto;
    }
}
