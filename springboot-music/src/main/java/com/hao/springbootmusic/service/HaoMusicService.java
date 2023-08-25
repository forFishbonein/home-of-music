package com.hao.springbootmusic.service;

import com.hao.springbootmusic.dao.pojo.HaoMusic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.springbootmusic.dao.vo.MusicVo;
import com.hao.springbootmusic.dao.vo.params.PageParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
public interface HaoMusicService{

    List<MusicVo> listMusic(PageParams pageParams);
}
