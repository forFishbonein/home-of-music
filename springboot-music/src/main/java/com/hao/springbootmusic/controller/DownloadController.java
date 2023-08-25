/*
 * @Time : 2022/8/12 14:37
 * @Author : hao
 * @File : DownloadController.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.controller;

import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.dao.vo.params.PageParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

//@Controller
//public class DownloadController {
//
//    @GetMapping("/download/{id}")
//    public String downloads(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") String id) throws Exception{
//        //要下载的图片地址
////        String path = request.getServletContext().getRealPath("/upload"); //从指定文件夹下下载
//        String path = "D:\\javaweb\\webproject\\springboot-music\\src\\main\\resources\\static\\upload";
//        String fileName = "yiyu.mp3"; //只需要修改文件名即可！
//
//        //1、设置response 响应头
//        response.reset(); //设置页面不缓存,清空buffer
//        response.setCharacterEncoding("UTF-8"); //字符编码
//        response.setContentType("multipart/form-data"); //二进制传输数据
//        //设置响应头
//        response.setHeader("Content-Disposition",
//                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
//
//        File file = new File(path, fileName);
//        //2、 读取文件--输入流 ：先读到程序里面
//        InputStream input = new FileInputStream(file);
//        //3、 写出文件--输出流 ：再写出去
//        OutputStream out = response.getOutputStream();
//
//        byte[] buff = new byte[1024];
//        int index = 0;
//        //4、执行 写出操作
//        while ((index = input.read(buff)) != -1) {
//            out.write(buff, 0, index);
//            out.flush();
//        }
//        out.close();
//        input.close();
//        return null;
//    }
//}
