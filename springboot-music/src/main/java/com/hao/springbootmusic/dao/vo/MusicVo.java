/*
 * @Time : 2022/8/8 16:46
 * @Author : hao
 * @File : ArticleVo.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.vo;

import lombok.Data;

import java.util.List;

//注：Vo数据：就相当于是和页面交互的数据
//也就是说返回给页面的数据不应该和数据库的pojo进行耦合，应该单独定义，自身针对性对pojo地做一些删除和增加
@Data
public class MusicVo {

    private String id;

    private String title;

    private Integer loadCounts;

    private Integer weight;

    private String createDate;

    private String singer;

}

