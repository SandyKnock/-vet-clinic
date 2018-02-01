package su.vistar.vetclinic.configuration;



import javax.annotation.Resource;


import org.elasticsearch.client.Client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Владислав on 14.03.2017.
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableElasticsearchRepositories(basePackages = "su.vistar.vetclinic.service.elasticsearch")
public class ElasticsearchConfiguration {

    @Resource
    private Environment environment;

    @Bean
    public Client client() throws UnknownHostException {
        String host_name = environment.getProperty("elasticsearch.host");
        int port = Integer.parseInt(environment.getProperty("elasticsearch.port"));
        Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true) .build();
        TransportClient client = new TransportClient.Builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host_name),port));
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }

}
