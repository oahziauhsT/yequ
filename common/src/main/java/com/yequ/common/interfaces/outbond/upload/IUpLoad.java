package com.yequ.common.interfaces.outbond.upload;

import com.yequ.common.interfaces.outbond.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
@Api(tags = "通用附件上传下载接口")
public interface IUpLoad {

    @ApiOperation(value = "附件上传")
    ResultDto<List<FIleVO>> upLoadFiles(MultipartFile[] files);

    @ApiOperation(value = "附件下载")
    void  downLoadFiles(HttpServletResponse response, List<String> fileIds) throws IOException;

}
