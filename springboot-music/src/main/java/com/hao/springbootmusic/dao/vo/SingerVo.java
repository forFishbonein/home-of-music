/*
 * @Time : 2022/8/12 10:12
 * @Author : hao
 * @File : SingerVo.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingerVo {

    private String singerId;

    private String singerName;

    private List<String> singName;
}
