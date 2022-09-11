package com.yequ.common.domain.role.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.role.mapper.RoleUserRelationMapper;
import com.yequ.common.domain.role.service.RoleUserRelationService;
import com.yequ.common.domain.role.entity.RoleUserRelationEntity;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.login.RoleUserVO;
import com.yequ.common.utils.CommonConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class RoleUserRelationServiceImpl extends ServiceImpl<RoleUserRelationMapper, RoleUserRelationEntity> implements RoleUserRelationService {

    @Override
    public ResultDto<RoleUserVO> modfiyRoleUser(RoleUserVO roleUserVO) {
        ResultDto<RoleUserVO> resultDto = new ResultDto<RoleUserVO>();
        RoleUserRelationEntity roleUserRelationEntity = new RoleUserRelationEntity();
        BeanUtils.copyProperties(roleUserVO,roleUserRelationEntity);
        if(!super.saveOrUpdate(roleUserRelationEntity)){
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            resultDto.setMessage("修改角色失败");
            resultDto.setObj(roleUserVO);
        }else {
            BeanUtils.copyProperties(roleUserRelationEntity,roleUserVO);
            resultDto.setObj(roleUserVO);
        }
        return resultDto;
    }
}
