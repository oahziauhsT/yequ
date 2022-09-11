package com.yequ.common.domain.attach.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yequ.common.base.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
@Data
@TableName("sys_attach_t")
public class AttachEntity extends BaseEntity {

    /**
     * 文件下载地址
     */
    private String fileId;

    /**
     * oss地址
     */
    private String filePath;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件大小
     */
    private  String fileSize;

    /**
     * 文件类型
     */
    private  String fileType;

    /**
     * 文件描述
     */
    private  String description;
}
