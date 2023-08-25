package com.hao.springbootmusic.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="HaoSingerSing对象", description="")
public class HaoSingerSing implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    private String singName;

    private Integer singerId;


}
