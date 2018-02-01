package su.vistar.vetclinic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.core.env.Environment;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Владислав on 10.03.2017.
 */
@Configuration
@EnableTransactionManagement
@org.springframework.context.annotation.PropertySource("classpath:application.properties")
@ComponentScan({"su.vistar.vetclinic.dao.mongo", "su.vistar.vetclinic.service.mongo"})
public class MongoConfiguration extends AbstractMongoConfiguration  {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.database");
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(env.getProperty("mongo.host"), Integer.valueOf(env.getProperty("mongo.port")));
    }

    @Override
    protected String getMappingBasePackage() {
        return "su.vistar.vetclinic.entities";
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate
                = new MongoTemplate(new MongoClient(env.getProperty("mongo.host"), Integer.valueOf(env.getProperty("mongo.port"))), env.getProperty("mongo.database"));
        return mongoTemplate;
    }

}
