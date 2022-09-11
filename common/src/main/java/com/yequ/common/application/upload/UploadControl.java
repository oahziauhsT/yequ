package com.yequ.common.application.upload;

import com.yequ.common.infrastructure.upload.UpLoadService;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.upload.FIleVO;
import com.yequ.common.interfaces.outbound.upload.IUpLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
@RestController
@RequestMapping("/common")
public class UploadControl implements IUpLoad {


    @Autowired
    private UpLoadService upLoadServiceImpl;
    @Override
    @PostMapping(value ="/upLoadFiles",headers={"content-type=multipart/form-data"})
    public ResultDto<List<FIleVO>> upLoadFiles(@RequestPart("files") MultipartFile[] files) {
        ResultDto<List<FIleVO>> resultDto = new ResultDto<>();
        resultDto= upLoadServiceImpl.upLoadFiles(files);
        return resultDto;
    }

    @Override
    @PostMapping("/downLoadFiles")
    public void downLoadFiles(HttpServletResponse response, @RequestBody List<String> fileIds) throws IOException {
        upLoadServiceImpl.downLoadFiles(response,fileIds);
    }
}