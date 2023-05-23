package edu.whu.newspace;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author Newspace
 * @version 1.0
 * @description 代码生成器, 将数据库实体转换为Java实体代码
 * @date 2023/5/23 10:03
 */
public class CodeGenerator {
    private static final String SERVICE_NAME = "Newspace";
    private static final String USERNAME = "root";
    private static final String PASSWD = "j1101714214";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/toutiao?serverTimezone=UTC";

    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(DRIVER);
        dataSourceConfig.setUrl(MYSQL_URL);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWD);
        autoGenerator.setDataSource(dataSourceConfig);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setAuthor("Newspace");
        globalConfig.setFileOverride(true);
        globalConfig.setMapperName("%sMapper");
        globalConfig.setIdType(IdType.ASSIGN_ID);
        autoGenerator.setGlobalConfig(globalConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("edu.whu.newspace");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapper");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setTablePrefix("tt_");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
