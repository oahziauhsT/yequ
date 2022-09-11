package com.yequ.common.infrastructure.mq;

import com.alibaba.fastjson.JSONObject;
import com.yequ.common.utils.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-11
 **/
@Component
@Slf4j
public class ProductMessageHandler {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(Object data){
        log.info("生产消息:"+ JSONObject.toJSONString(data));
        kafkaTemplate.send(CommonConstant.YEQU_LOG,JSONObject.toJSONString(data));
    }
}
