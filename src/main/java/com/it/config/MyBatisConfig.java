    package com.it.config;

    import com.alibaba.druid.pool.DruidDataSource;
    import com.github.pagehelper.PageInterceptor;
    import org.apache.ibatis.plugin.Interceptor;
    import org.mybatis.spring.SqlSessionFactoryBean;
    import org.mybatis.spring.annotation.MapperScan;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.PropertySource;

    import javax.sql.DataSource;
    import java.util.Properties;

    //@Configuration
    @PropertySource("classpath:db.properties")
    @MapperScan("com.it.dao")  //扫描Mapper
    public class MyBatisConfig {

        @Value("${mysql.url}")
        private String url;
        @Value("${mysql.driver}")
        private String driverClassName;
        @Value("${mysql.username}")
        private String username;
        @Value("${mysql.password}")
        private String password;

        @Bean
        public DataSource dataSource(){
            DruidDataSource dataSource = new DruidDataSource();

            dataSource.setUrl(url);
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            return dataSource;
        }

//        分页
        @Bean
        public PageInterceptor pageInterceptor(){
            PageInterceptor pageInterceptor = new PageInterceptor();

            Properties properties = new Properties();
            properties.setProperty("value","true");
            pageInterceptor.setProperties(properties);
            return pageInterceptor;
        }

        @Autowired
        private PageInterceptor pageInterceptor;

        @Bean
        public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage("com.it.pojo");

            sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});

            return sqlSessionFactoryBean;
        }

//        @Bean
//        public MapperScannerConfigurer mapperScannerConfigurer(){
//            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//            mapperScannerConfigurer.setBasePackage("com.it.dao");
//            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//            return mapperScannerConfigurer;
//        }
    }
