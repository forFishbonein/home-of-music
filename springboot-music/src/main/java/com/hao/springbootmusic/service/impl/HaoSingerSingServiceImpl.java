package com.hao.springbootmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.springbootmusic.dao.pojo.HaoSinger;
import com.hao.springbootmusic.dao.pojo.HaoSingerSing;
import com.hao.springbootmusic.dao.mapper.HaoSingerSingMapper;
import com.hao.springbootmusic.dao.vo.SingerVo;
import com.hao.springbootmusic.service.HaoSingerService;
import com.hao.springbootmusic.service.HaoSingerSingService;


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
public class HaoSingerSingServiceImpl implements HaoSingerSingService {

    @Autowired
    HaoSingerService haoSingerService;

    @Autowired
    HaoSingerSingMapper haoSingerSingMapper;

    @Override
    public List<SingerVo> findSingerAndSing() {
        List<SingerVo> singerSings = new ArrayList<>();
        List<HaoSinger> singers = haoSingerService.findSingers();
        for (HaoSinger singer : singers) {
            List<String> singerNames = new ArrayList<>(); //注意作用域，只能在内部，不是大家共用的东西！
            SingerVo singerVo = new SingerVo(); //注意作用域，只能在内部，不是大家共用的东西！
            Integer singerId = singer.getSingerId();
            LambdaQueryWrapper<HaoSingerSing> queryWrapper = new LambdaQueryWrapper<>();
//            queryWrapper.("singName"); //好像用不了
            queryWrapper.eq(HaoSingerSing::getSingerId, singerId);
            List<HaoSingerSing> haoSingerSings = haoSingerSingMapper.selectList(queryWrapper);
            for (HaoSingerSing haoSingerSing : haoSingerSings) {
                String singName = haoSingerSing.getSingName();
                singerNames.add(singName);
            }
            singerVo.setSingName(singerNames);
            singerVo.setSingerName(singer.getSingerName());
            singerVo.setSingerId(String.valueOf(singer.getSingerId()));
            singerSings.add(singerVo);
        }
//        BeanUtils.copyProperties(singers,singerVo);
        return singerSings;
    }

//    @Test
//    public void test() {
//        HaoSingerSingServiceImpl haoSingerSingService = new HaoSingerSingServiceImpl();
//        System.out.println(haoSingerSingService.findSingerAndSing());
//    }
}
