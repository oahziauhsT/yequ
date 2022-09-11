package com.yequ.common.infrastructure.mq;

import com.alibaba.fastjson.JSONObject;
import com.yequ.common.domain.log.service.LogService;
import com.yequ.common.domain.log.entity.LogEntity;
import com.yequ.common.utils.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Component
@Slf4j
public class ConsumerMessageListener {

    @Autowired
    private LogService logService;

    @KafkaListener(groupId = CommonConstant.YEQU_LOG_GROUP,topics = CommonConstant.YEQU_LOG)
    public void onConsumerLog(ConsumerRecord<?,?> record){
        log.info("消费消息-------"+"topic:"+record.topic()+",value:"+record.value());
        LogEntity logVO = new LogEntity();
        logVO = JSONObject.parseObject(record.value().toString(),LogEntity.class);
        logService.save(logVO);
    }

}
