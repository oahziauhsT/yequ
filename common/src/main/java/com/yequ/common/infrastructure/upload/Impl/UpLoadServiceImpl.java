package com.yequ.common.infrastructure.upload.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yequ.common.domain.attach.service.AttachService;
import com.yequ.common.domain.attach.entity.AttachEntity;
import com.yequ.common.infrastructure.common.CommonProperties;
import com.yequ.common.infrastructure.upload.UpLoadService;
import com.yequ.common.interfaces.outbound.dto.ResultDto;
import com.yequ.common.interfaces.outbound.upload.FIleVO;
import com.yequ.common.utils.CommonConstant;
import com.yequ.common.utils.FileUtil;
import com.yequ.common.utils.ListUtil;
import com.yequ.common.utils.WebResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-09-08
 **/
@Component
public class UpLoadServiceImpl implements UpLoadService {

    @Autowired
    private AttachService attachServiceImpl;

    @Autowired
    private CommonProperties commonProperties;


    /**
     * 附件上传
     * @param files
     * @return
     */
    @Override
    public ResultDto<List<FIleVO>> upLoadFiles(MultipartFile[] files) {
        ResultDto<List<FIleVO>> resultDto = new ResultDto<>();
        //上传成功后返回附件对象集合
        List<FIleVO> fIleVOS = new ArrayList<>();
        //数据库插入的仓存模型数据
        List<AttachEntity> attachEntities = new ArrayList<>();
        if(null==files&&files.length==0){
            resultDto.setMessage("附件为空上传失败");
            resultDto.setCode(CommonConstant.STATUS_ERROR);
            return resultDto;
        }
        for(MultipartFile file:files ){
            FIleVO fIleVO = new FIleVO();
            AttachEntity attachEntity = saveFile(file);
            BeanUtils.copyProperties(attachEntity,fIleVO);
            fIleVOS.add(fIleVO);
            attachEntities.add(attachEntity);
        }
        attachServiceImpl.saveBatch(attachEntities);
        resultDto.setObj(fIleVOS);
        return resultDto;

    }

    /**
     * 附件下载
     * @param response
     * @param fileIds
     * @throws IOException
     */
    @Override
    public void downLoadFiles(HttpServletResponse response, List<String> fileIds) throws IOException {

        response.reset();
        //查询附件真实地址
        LambdaQueryWrapper<AttachEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(AttachEntity::getFileId,fileIds);
        List<AttachEntity> attachEntities = attachServiceImpl.list(lambdaQueryWrapper);
        //判断是多附件还是单附件
        if(!ListUtil.isEmpty(attachEntities)&&attachEntities.size()>1){
            filesDownLoad(attachEntities,response);
        }else{
            fileDownLoad(attachEntities.get(0),response);
        }


    }


    AttachEntity saveFile(MultipartFile file){
        //附件保存时无业务含义的名称
        String uuid = UUID.randomUUID().toString().replace("-","");
        AttachEntity attachEntity = new AttachEntity();
        //获取上传文件的源文件名称
        String oldName = file.getOriginalFilename();
        //附件唯一标识
        attachEntity.setFileId(uuid);
        //附件大小
        attachEntity.setFileSize(FileUtil.getFileSize(file.getSize()));
        //附件的原始名称
        attachEntity.setName(oldName);
        //附件类型
        attachEntity.setFileType(file.getContentType());
        //存储地址
        String realPath = commonProperties.getHostPath();
        //判断远程地盘文件夹是否存在，如不存在就需要创建
        File folder = FileUtil.mkdir(realPath);
        //获取文件新名称 uuid+后缀
        String newName = uuid + oldName.substring(oldName.lastIndexOf("."));
        attachEntity.setFilePath(folder.getPath().replace(commonProperties.getHostPath(),"")+"\\"+newName);
        //判断目录是否存在，不存在则创建目录
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            //文件保存
            file.transferTo(new File(folder, newName));
            //打印上传文件的url
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachEntity;
    }

    /**
     * 单文件下载
     * @param attachEntitie 单个文件对象
     * @param response
     */
    void fileDownLoad(AttachEntity attachEntitie,HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream;charset=utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            //设置字符编码
            response.setCharacterEncoding("utf-8");
            //设置下载文件类型及文件名称
            response.setHeader(
                    "Content-disposition",
                    "attachment; filename="+ URLEncoder.encode(attachEntitie.getName(),"utf-8"));
            //获取文件流
            bis = new BufferedInputStream(new FileInputStream(commonProperties.getHostPath()+attachEntitie.getFilePath()));
            // 输出流
            bos = new BufferedOutputStream(response.getOutputStream());
            //声明每次取多大的字节
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = bis.read(buff)) > 0) {
                bos.write(buff, 0, len);
            }
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bis != null){
                bis.close();
            }
            if(bos != null){
                bos.close();
            }
        }
    }
    /**
     * 多文件下载
     * @param attachEntities
     * @param response
     */
    void filesDownLoad(List<AttachEntity> attachEntities,HttpServletResponse response){
        //声明对文件下载的压缩包名称
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyyMMddHHmmss");
        //压缩文件全名
        String zipFileName = formatter.format(new Date())+".zip";
        //临时文件路径
        String strZipPathTemp = commonProperties.getHostPath()+"\\"+zipFileName;
        //压缩文件流
        ZipOutputStream zipStream = null;
        //文件流
        FileInputStream zipSource = null;
        //输入缓冲流
        BufferedInputStream bufferStream = null;
        //创建压缩文件
        File zipFile = new File(strZipPathTemp);
        try{
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for(AttachEntity attachEntity: attachEntities){
                //对文件名进行编码，防止中文乱码
                String realFileName = URLDecoder.decode(attachEntity.getName(),"UTF-8");
                //文件路径
                String realFilePath = URLDecoder.decode(commonProperties.getHostPath()+attachEntity.getFilePath(),"UTF-8");
                //读要现在的文件
                File file = new File(realFilePath);
                if(file.exists()){
                    //将需要压缩的文件格式化为输入流
                    zipSource = new FileInputStream(file);
                    //在压缩目录中文件的名字
                    ZipEntry zipEntry = new ZipEntry(realFileName);
                    //定位该压缩条目位置，开始写入文件到压缩包中
                    zipStream.putNextEntry(zipEntry);
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                    int read = 0;
                    byte[] buf = new byte[1024 * 10];
                    while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1){
                        zipStream.write(buf, 0, read);
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            try{
                if(null != bufferStream){
                    bufferStream.close();
                }
                if(null != zipStream){
                    zipStream.flush();
                    zipStream.close();
                }
                if(null != zipSource) {
                    zipSource.close();
                }
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }

        if(zipFile.exists()){
            downZip(response,zipFileName,strZipPathTemp);
            zipFile.delete();
        }
    }

    private void downZip(HttpServletResponse response,String filename,String path ) {
        if (filename != null) {
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            try {
                File file = new File(path);
                if (file.exists()) {
                    //设置Headers
                    response.setHeader("Content-Type", "application/octet-stream");
                    //设置下载的文件的名称-该方式已解决中文乱码问题
                    response.setHeader("Content-Disposition", "attachment;filename=" + filename);
                    is = new FileInputStream(file);
                    bs = new BufferedInputStream(is);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = bs.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                } else {
                    WebResponseUtils.renderResponse(response, "资源不存在");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (bs != null) {
                        bs.close();
                    }
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
