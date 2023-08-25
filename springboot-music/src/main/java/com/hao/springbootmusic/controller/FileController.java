/*
 * @Time : 2022/8/12 15:14
 * @Author : hao
 * @File : FileController.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.controller;

import com.hao.springbootmusic.dao.mapper.HaoMusicMapper;
import com.hao.springbootmusic.dao.pojo.HaoMusic;
import com.hao.springbootmusic.service.FileService;
import com.hao.springbootmusic.service.ThreadService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private HaoMusicMapper haoMusicMapper;

    @GetMapping("download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileService.loadFileAsResource(fileName);

        int id = Integer.parseInt(fileName.substring(0, fileName.length()-4));
        HaoMusic music= haoMusicMapper.selectById(id);
        threadService.updateViewCount(haoMusicMapper,music);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            logger.error("无法获取文件类型！", e);
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

