package com.hao.springbootmusic.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

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

@EqualsAndHashCode(callSuper = false)
@ApiModel(value="HaoMusic对象", description="")
@Data
public class HaoMusic implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Integer weight;

    private String title;

    private Integer loadCounts;

    private String singer;

    private String createDate; //不能用Date！！！！

}
