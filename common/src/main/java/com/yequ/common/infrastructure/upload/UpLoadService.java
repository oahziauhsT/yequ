package com.yequ.common.infrastructure.upload;

import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.upload.FIleVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
public interface UpLoadService {

    ResultDto<List<FIleVO>> upLoadFiles(MultipartFile[] files);

    void  downLoadFiles(HttpServletResponse response, List<String> fileIds) throws IOException;
}

