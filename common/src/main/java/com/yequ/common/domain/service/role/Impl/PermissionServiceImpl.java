package com.yequ.common.domain.service.role.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.service.role.PermissionService;
import com.yequ.common.domain.service.role.entity.PermissionEntity;
import com.yequ.common.domain.service.role.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-04
 **/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {
}
