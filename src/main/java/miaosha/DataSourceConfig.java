package miaosha;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  多数据库DataSource 配置文件信息
 *  @author
 *  @date 2018/11/21
 */
@Configuration
@MapperScan(basePackages="miaosha", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    @Bean(name = "dataSourceLabel")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.datasource-label")
    public DataSource dataSourceFace() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceLabel") DataSource dataSourceTest)
            throws Exception {
        List<Resource> list = new ArrayList<>();
        Resource[] r1 = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml");
        list.addAll(Arrays.asList(r1));
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceTest);
        factoryBean.setMapperLocations(list.toArray(new Resource[] {}));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("dataSourceLabel") DataSource dataSourceFace)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(dataSourceFace));
    }

    @Bean
    public PlatformTransactionManager prodTransactionManager(@Qualifier("dataSourceLabel") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
