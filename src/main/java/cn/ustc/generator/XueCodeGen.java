package cn.ustc.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class XueCodeGen {
    public static void main(String[] args) {
        //需要先构造一个代码生成器对象
        AutoGenerator generator = new AutoGenerator();
        //下面开始配置策略
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前路径
        String propertyPath = System.getProperty("user.dir");
        //生成输出路径
        gc.setOutputDir(propertyPath+"/src/main/java");
        //生成作者姓名
        gc.setAuthor("glor");
        //不打开文件管理器
        gc.setOpen(false);
        //是否覆盖原生成文件
        gc.setFileOverride(false);
        //设置service去除I前缀
        gc.setServiceName("%Service");
        //设置id类型
        gc.setIdType(IdType.ID_WORKER);
        //设置日期类型
        gc.setDateType(DateType.ONLY_DATE);
        //配置swagger
        gc.setSwagger2(true);
        //将以上配置丢进全局配置
        generator.setGlobalConfig(gc);

        //2. 设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/manage?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        //数据库类型
        dsc.setDbType(DbType.MYSQL);
        //将配置丢进去
        generator.setDataSource(dsc);

        //3.包的配置
        PackageConfig pc = new PackageConfig();
        //以下两行会生成 com.ustc.test 包
        pc.setModuleName("test");
        pc.setParent("com.ustc");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setController("controller");
        pc.setService("service");
        //将配置丢进去
        generator.setPackageInfo(pc);


        //4.策略配置，如乐观锁等
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //重点：要映射的表名
        strategy.setInclude("user");
        //实体类命名策略：驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //列名命名策略：驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //设置lombok
        strategy.setEntityLombokModel(true);
        //设置api风格
        strategy.setRestControllerStyle(true);
        //还有很多，若自动填充，逻辑删除等，看文档
        //下面就是自动配置时间
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        //乐观锁
        strategy.setVersionFieldName("version");
        //连接下划线命名 localhost/hell0_id_2
        strategy.setControllerMappingHyphenStyle(true);

        //丢进配置
        generator.setStrategy(strategy);

        //最后。。执行
        generator.execute();
    }
}
