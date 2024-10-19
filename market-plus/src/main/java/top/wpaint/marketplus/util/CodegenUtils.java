package top.wpaint.marketplus.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Cleanup;
import top.wpaint.marketplus.common.constant.LogicConst;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CodegenUtils {
    //    private static final String SOURCE_DIR = "/home/wpan/Desktop";
    private static final String SOURCE_DIR = "/home/wpan/Documents/@ProjectSpace/@ByLanguage/@Java/java-market-plus/market-plus";
    private static final String BASE_PACKAGE = "top.wpaint.marketplus";
    private static final String TABLE_PREFIX = "wb_";

    //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
    private static final Integer JAVA_VERSION = 21;

    public static void main(String[] args) {
        @Cleanup HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:mysql://sh-cynosdbmysql-grp-09nvk3xa.sql.tencentcdb.com:22763/market_plus_db?characterEncoding=utf-8");
        dataSource.setUsername("wpanMarket");
        dataSource.setPassword("market123@wpan");

        GlobalConfig config = createGlobalConfig();

        // 生成
        new Generator(dataSource, config).generate();
    }

    public static GlobalConfig createGlobalConfig() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        globalConfig.getPackageConfig()
                .setSourceDir(SOURCE_DIR + "/src/main/java")
                .setBasePackage(BASE_PACKAGE)
                .setMapperXmlPath(SOURCE_DIR + "/src/main/resources/mapper");

        globalConfig.setTablePrefix(TABLE_PREFIX);

        // 实体类
        globalConfig.setEntityGenerateEnable(true);
//        globalConfig.setEntitySuperClass(BaseEntity.class);
        globalConfig.setEntityWithLombok(true);
        globalConfig.setEntityJdkVersion(JAVA_VERSION);


        globalConfig.setMapperAnnotation(true);
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.setMapperXmlGenerateEnable(true);


        globalConfig.setTableDefGenerateEnable(true);

        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceImplGenerateEnable(true);

        globalConfig.setEntityOverwriteEnable(true);
        globalConfig.setTableDefOverwriteEnable(true);
        globalConfig.setMapperOverwriteEnable(true);
        globalConfig.setMapperXmlOverwriteEnable(true);

        ArrayList<String> tableNames = new ArrayList<>();
        tableNames.add(TABLE_PREFIX + "auth");
        tableNames.add(TABLE_PREFIX + "role");
        tableNames.add(TABLE_PREFIX + "user");
        tableNames.add(TABLE_PREFIX + "user_auth");
//        tableNames.add(TABLE_PREFIX + "auth");
//        tableNames.add(TABLE_PREFIX + "auth");

        for (String table : tableNames) {
            globalConfig.setColumnConfig(
                    table,
                    ColumnConfig.builder().build()
                            .setColumnName("gmt_created")
                            .setOnInsertValue("now()"));

            globalConfig.setColumnConfig(
                    table,
                    ColumnConfig.builder().build()
                            .setColumnName("gmt_modified")
                            .setOnUpdateValue("now()"));

            globalConfig.setColumnConfig(
                    table,
                    ColumnConfig.builder().build()
                            .setColumnName("is_deleted")
                            .setLogicDelete(true)
                            .setOnInsertValue(LogicConst.NOT_DELETED));

            globalConfig.setColumnConfig(
                    table,
                    ColumnConfig.builder().build()
                            .setColumnName("is_enable")
                            .setOnInsertValue(LogicConst.DISABLE));
        }


        globalConfig.setAuthor("tecwds");
        globalConfig.setSince(LocalDateTimeUtil.format(
                LocalDateTime.now(),
                "yyyy-MM-dd"));
        return globalConfig;
    }
}
