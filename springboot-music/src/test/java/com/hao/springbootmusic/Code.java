/*
 * @Time : 2022/8/11 21:02
 * @Author : hao
 * @File : Code.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class Code {
    public static void main(String[] args) {
        // 一、构建一个 代码自动生成器 对象
        // new一个代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 二、进行各种配置
        //1、全局配置
        GlobalConfig gc = new GlobalConfig(); //全局配置对象
        String projectPath = System.getProperty("user.dir"); //获取用户的目录（即当前项目的根目录）
        gc.setOutputDir(projectPath + "/src/main/java"); //自动生成的代码输出的位置
        gc.setAuthor("hao"); //配置作者名，会在上面添加注释
        gc.setOpen(false); //是否在代码生成完毕后打开windows资源管理器
        gc.setFileOverride(false);  //是否覆盖原来的代码
        gc.setServiceName("%sService"); //去调Service层接口名字的I前缀，不需要加这个
        gc.setIdType(IdType.ASSIGN_ID);  //3.3.0版本以后不用ID_WORKER了
        gc.setDateType(DateType.ONLY_DATE); //日期类型，仅仅只是时间
        gc.setSwagger2(true); //自动配置swagger文档
        mpg.setGlobalConfig(gc); //丢入代码生成器对象msg中

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig(); //数据源配置对象
        dsc.setUrl("jdbc:mysql://localhost:3306/music?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL); //数据库类型
        mpg.setDataSource(dsc); //丢入代码生成器对象msg中

        //3、生成的包的配置
        PackageConfig pc = new PackageConfig(); //包配置对象
        pc.setModuleName("main"); //设置子级项目模块名
        pc.setParent("com.hao.springbootmusic"); //设置父级包路径
        pc.setEntity("dao"); //子级包名
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc); //丢入代码生成器对象msg中

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig(); //策略配置对象
        strategy.setInclude("hao_music","hao_singer","hao_singer_sing"); //设置要映射的表名 ——> 这个是最重点的地方
        strategy.setNaming(NamingStrategy.underline_to_camel); //包名映射规则：下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); //字段映射规则：下划线转驼峰命名
        strategy.setEntityLombokModel(true);  //自动使用lombok
        //逻辑删除配置
        strategy.setLogicDeleteFieldName("deleted"); //开启逻辑删除，设置字段名字
        //自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills); //放入策略配置对象中
        //乐观锁配置
        strategy.setVersionFieldName("version"); //开启乐观锁
        strategy.setRestControllerStyle(true); //开启controller使用ResultFul风格
        strategy.setControllerMappingHyphenStyle(true); //开启连接下划线风格：localhost:8080/hello_id_2
        mpg.setStrategy(strategy); //丢入代码生成器对象msg中

        //三、执行代码构造器
        mpg.execute();
    }
}
