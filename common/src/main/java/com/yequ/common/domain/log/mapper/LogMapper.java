package com.yequ.common.domain.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yequ.common.domain.log.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Mapper
public interface LogMapper extends BaseMapper<LogEntity> {
}
