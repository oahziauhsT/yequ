package com.yequ.common.domain.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yequ.common.domain.log.entity.LogEntity;
import com.yequ.common.domain.log.service.LogService;
import com.yequ.common.domain.log.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {
}
