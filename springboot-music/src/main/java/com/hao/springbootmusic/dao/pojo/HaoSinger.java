package com.hao.springbootmusic.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class HaoSinger implements Serializable {

    @TableId(value = "singer_id", type = IdType.ASSIGN_ID)
    private Integer singerId;

    private String singerName;

}
