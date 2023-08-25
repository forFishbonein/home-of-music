package com.hao.springbootmusic.controller;


import com.hao.springbootmusic.dao.vo.MusicVo;
import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.dao.vo.params.PageParams;
import com.hao.springbootmusic.service.HaoMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/musics")
public class HaoMusicController {

    @Autowired HaoMusicService haoMusicService;

    @PostMapping
    public Result musics(@RequestBody PageParams pageParams) {

        //ArticleVo 页面接收的数据
        List<MusicVo> musics = haoMusicService.listMusic(pageParams);
        return Result.success(musics);
    }

}

