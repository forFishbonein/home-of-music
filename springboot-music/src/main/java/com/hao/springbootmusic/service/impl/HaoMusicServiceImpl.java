package com.hao.springbootmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.springbootmusic.dao.pojo.HaoMusic;
import com.hao.springbootmusic.dao.mapper.HaoMusicMapper;
import com.hao.springbootmusic.dao.vo.MusicVo;
import com.hao.springbootmusic.dao.vo.params.PageParams;
import com.hao.springbootmusic.service.HaoMusicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
@Service
public class HaoMusicServiceImpl implements HaoMusicService {

    @Autowired
    private HaoMusicMapper haoMusicMapper;

    @Override
    public List<MusicVo> listMusic(PageParams pageParams) {
        Page<HaoMusic> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        IPage<HaoMusic> musicIPage = haoMusicMapper.listMusic(page);
        List<HaoMusic> records = musicIPage.getRecords();
        List<MusicVo> musicVoList = copyList(records);

        return musicVoList;

    }

    private List<MusicVo> copyList(List<HaoMusic> records) {

        List<MusicVo> musicVoList = new ArrayList<>();
        for (HaoMusic record : records) {
            MusicVo MusicVo = copy(record); //一个一个地转换
            musicVoList.add(MusicVo);
        }
        return musicVoList;
    }

    private MusicVo copy(HaoMusic haoMusic) {

        MusicVo musicVo = new MusicVo();
        musicVo.setId(String.valueOf(haoMusic.getId()));
        BeanUtils.copyProperties(haoMusic, musicVo);

        return musicVo;
    }

}
