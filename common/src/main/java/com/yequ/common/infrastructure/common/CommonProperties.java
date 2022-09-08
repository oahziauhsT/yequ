package com.yequ.common.infrastructure.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
@Component
@ConfigurationProperties(prefix = "yequ.common")
@Data
public class CommonProperties {

    private String hostPath;
}
