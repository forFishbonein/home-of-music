package com.hao.springbootmusic;

import com.hao.springbootmusic.dao.pojo.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileProperties.class})
public class SpringbootMusicApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootMusicApplication.class, args);
    }

}
