package com.yequ.common.interfaces.outbond.upload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
@ApiModel("上传的文件信息")
@Data
public class FIleVO {

    @ApiModelProperty("文件名")
    String name;
    @ApiModelProperty("文件下载地址")
    String FileId;
    @ApiModelProperty("文件大小")
    String fileSize;
    @ApiModelProperty("文件类型")
    String FileType;

}
