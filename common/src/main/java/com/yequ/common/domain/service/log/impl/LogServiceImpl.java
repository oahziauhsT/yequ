package com.yequ.common.domain.service.log.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.service.log.LogService;
import com.yequ.common.domain.service.log.entity.LogEntity;
import com.yequ.common.domain.service.log.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {
}
