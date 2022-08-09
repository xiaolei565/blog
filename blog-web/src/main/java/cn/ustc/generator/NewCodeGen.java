package cn.ustc.generator;

import cn.ustc.blog.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import lombok.val;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * 新版本代码生成器
 */

public class NewCodeGen {
    public static void main(String[] args) {
        /**数据库配置**/
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","123456")
                .dbQuery(new MySqlQuery())
                .build();
        /**创建代码生成器对象**/
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);
        /**定义代码生成位置*/
        String property = System.getProperty("user.dir");
        /**全局配置*/
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .fileOverride() //覆盖已生成文件
                .outputDir(property + "/blog-web/src/main/java")
                .author("xiaolei565")
                .enableSwagger()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .build();
        generator.global(globalConfig);
        /**包管理*/
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("cn.ustc")
                .moduleName("blog")
                .entity("entity")
                .controller("controller")
                .service("service")
                .serviceImpl("service.Impl")
                .mapper("mapper")
                .xml("mapper.xml")//这个位置后面要移动到resources下面
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/leixue/Documents/CODE/blog/blog-web/src/main/resources/mapper"))
                .build();
        generator.packageInfo(packageConfig);
        /**模板配置*/
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();
        generator.template(templateConfig);

        /**策略配置*/
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode() //开启大写命名
                .enableSkipView() //跳过视图
                .disableSqlFilter() //禁用sql过滤器
//                .likeTable(new LikeTable("USER"))
                .addInclude("m_blog") //需要构造的表名
//                .addTablePrefix("t_", "c_")
//                .addFieldSuffix("_flag")
                /*   .entityBuilder()
                   .controllerBuilder()
                   .mapperBuilder()
                   .serviceBuilder()*/
                .build();
        strategyConfig.entityBuilder()
                .enableLombok()//开启lombok模式
                .superClass(BaseEntity.class)//设置继承父类
                .addSuperEntityColumns("id","created","updated","statu")//设置继承字段
                .versionColumnName("version") //乐观锁字段
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                .addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE))
                .build();
        strategyConfig.controllerBuilder()
                .enableRestStyle()
                .formatFileName("%sController")
                .build();
        strategyConfig.serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImp")
                .build();
        strategyConfig
                .mapperBuilder()
                .superClass(BaseMapper.class)
                .enableMapperAnnotation()
                .enableBaseResultMap()
                .enableBaseColumnList()
//                .cache(MyMapperCache.class)
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper")
                .build();
        generator.strategy(strategyConfig);
        generator.execute();

    }

}
