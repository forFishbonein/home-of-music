/*
 * @Time : 2022/8/12 15:13
 * @Author : hao
 * @File : FileProperties.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {
    private String docDir;
}

