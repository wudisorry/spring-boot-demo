package com.example.demo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class CodeGenerator {

    @Test
    public void generateCode() {
        String packageName = "com.aispeech.mscp.cdc";
        generateByTables(false, packageName, "mobile_identity_member");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://10.12.6.49:3306/aispeech_cdc_recorder_dev?useUnicode=true&characterEncoding=utf-8&useSSL=false";

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL).setUrl(dbUrl).setUsername("dev").setPassword("aispeech").setDriverName("com.mysql.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();

        //修改替换成你需要的表名，多个表名传数组
        strategyConfig.setCapitalMode(true).setEntityLombokModel(true).setDbColumnUnderline(true).setNaming(NamingStrategy.underline_to_camel).setInclude(tableNames);

        config.setActiveRecord(false).setAuthor("bin.bi").setOutputDir("D:\\workspace_recorder\\cdc-recorder-mgr\\src\\main\\java").setFileOverride(true);

        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }

        new AutoGenerator().setGlobalConfig(config).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig().setParent(packageName).setController("controller").setEntity("entity").setMapper("service.dao")

                ).execute();
    }
}
