package su.vistar.vetclinic.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import su.vistar.vetclinic.dao.mongo.FileStorageDao;
import su.vistar.vetclinic.dao.mongo.FileStorageDaoImpl;
import su.vistar.vetclinic.service.elasticsearch.PetRepository;
import su.vistar.vetclinic.service.elasticsearch.PetRepositoryServiceImpl;
import su.vistar.vetclinic.service.mongo.FileStorageServiceImpl;
import su.vistar.vetclinic.service.mongo.FileStorageService;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"su.vistar.vetclinic.dao", "su.vistar.vetclinic.entities", "su.vistar.vetclinic.service"},
               excludeFilters = @ComponentScan.Filter(
                       type = FilterType.ASSIGNABLE_TYPE,
                       value = {
                               FileStorageDao.class,
                               FileStorageDaoImpl.class,
                               FileStorageService.class,
                               FileStorageServiceImpl.class,
                               PetRepository.class,
                               PetRepositoryServiceImpl.class
                       }
                       )
)
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "su.vistar.vetclinic.entities" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }

}

