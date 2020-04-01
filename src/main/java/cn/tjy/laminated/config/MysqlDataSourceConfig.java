package cn.tjy.laminated.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Create by self on 2020/3/12 10:31
 *
 * @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。「多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean
 * @MapperScan 扫描 Mapper 接口并容器管理，包路径精确到 label，为了和下面 cluster 数据源做到精确区分
 * @Value 获取全局配置文件 application.properties 的 kv 配置,并自动装配sqlSessionFactoryRef 表示定义了 key ，表示一个唯一 SqlSessionFactory 实例
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {

    // 精确到 label 目录，以便跟其他数据源隔离
    static final String PACKAGE = "cn.tjy.laminated.dao.mysql";
    private static final String MAPPER_LOCATION = "classpath:mapper/mysql/*/*Mapper.xml";
    private static final String ENTITY_PACKAGE = "cn.tjy.laminated.pojo.mysql";

    /**
     * 数据库连接配置
     */
    @Value("${mysql.datasource.url}")
    private String url;
    @Value("${mysql.datasource.username}")
    private String user;
    @Value("${mysql.datasource.password}")
    private String password;
    @Value("${mysql.datasource.driverClassName}")
    private String driverClass;
    /**
     * druid配置
     */
    @Value("${spring.datasource.druid.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.druid.minIdle}")
    private Integer minIdle;
    @Value("${spring.datasource.druid.initialSize}")
    private Integer initialSize;
    @Value("${spring.datasource.druid.maxWait}")
    private Long maxWait;
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.druid.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.druid.testWhileIdle}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.druid.testOnBorrow}")
    private Boolean testOnReturn;
    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource labelDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        //连接池配置
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setConnectionProperties(connectionProperties);

        return dataSource;
    }

    //事务管理
    @Bean("mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(labelDataSource());
        return dataSourceTransactionManager;
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
        factoryBean.setTypeAliasesPackage(ENTITY_PACKAGE);
        return factoryBean.getObject();
    }

    @ConfigurationProperties(prefix = "mysql.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public PlatformTransactionManager labellTransactionManager(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        return new DataSourceTransactionManager(mysqlDataSource);
    }
}

