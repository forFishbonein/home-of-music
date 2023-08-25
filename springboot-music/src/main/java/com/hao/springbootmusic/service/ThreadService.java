/*
 * @Time : 2022/8/12 17:35
 * @Author : hao
 * @File : ThreadService.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.springbootmusic.dao.mapper.HaoMusicMapper;
import com.hao.springbootmusic.dao.pojo.HaoMusic;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    @Async("taskExecutor")
    public void updateViewCount(HaoMusicMapper musicMapper, HaoMusic music){

        HaoMusic musicUpdate = new HaoMusic();
        musicUpdate.setLoadCounts(music.getLoadCounts() + 1);
        LambdaQueryWrapper<HaoMusic> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HaoMusic::getId,music.getId());
        queryWrapper.eq(HaoMusic::getLoadCounts,music.getLoadCounts());
        musicMapper.update(musicUpdate,queryWrapper);
        try {
            //睡眠3秒 证明不会影响主线程的使用
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
