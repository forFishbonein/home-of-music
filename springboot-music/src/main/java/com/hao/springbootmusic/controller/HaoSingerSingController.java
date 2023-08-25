package com.hao.springbootmusic.controller;


import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.dao.vo.SingerVo;
import com.hao.springbootmusic.service.HaoSingerSingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/singers")
public class HaoSingerSingController {
    @Autowired
    private HaoSingerSingService haoSingerSingService;

    @PostMapping
    public Result singerSings() {
        List<SingerVo> singerAndSing = haoSingerSingService.findSingerAndSing();
        return Result.success(singerAndSing);
    }
}

