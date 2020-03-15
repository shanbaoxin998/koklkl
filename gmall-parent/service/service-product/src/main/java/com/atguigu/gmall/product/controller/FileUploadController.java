package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import org.apache.commons.io.FilenameUtils;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

/**
 * @author Zhangjun
 * @create 2020-03-14 19:41
 */

@RestController
@RequestMapping("admin/product")
public class FileUploadController {

    @Value("${fileServer.url}")
    private String fileUrl;

    // :action="'http://api.gmall.com/admin/product/fileUpload'"
    @RequestMapping("fileUpload")
    public Result<String> fileUpload(MultipartFile file) throws Exception{//看了半天 感觉没问题啊  我昨天开始找的：
        String configFile = this.getClass().getResource("/tracker.conf").getFile();
        String path = null;

        if (file != null){
            // 初始化
            ClientGlobal.init(configFile);
            // 创建trackerClient
            TrackerClient trackerClient = new TrackerClient();
            // 获取trackerService
            TrackerServer trackerServer = trackerClient.getConnection();
            // 创建storageClient1
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
            path = storageClient1.upload_appender_file1(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            System.out.println(fileUrl + path);
        }
        System.out.println(fileUrl + path);
        return Result.ok(fileUrl+path);
    }

}


