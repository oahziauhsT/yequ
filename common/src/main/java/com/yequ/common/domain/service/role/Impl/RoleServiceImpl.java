package com.yequ.common.domain.service.role.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.service.role.RoleService;
import com.yequ.common.domain.service.role.entity.RoleEntity;
import com.yequ.common.domain.service.role.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
}
