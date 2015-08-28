package com.linq.config;

import com.linq.config.consul.ConsulInfo;
import com.linq.config.consul.ConsulPropertySource;
import com.linq.constants.Constants;
import consul.Consul;
import consul.ConsulException;
import consul.KeyValue;
import consul.ServiceProvider;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * ConsulConfig
 *
 * @author LinQ
 * @version 2015-08-27
 */
@Configuration
public class ConsulConfig {

    @Autowired
    private ConfigurableEnvironment env;

    @PostConstruct
    public void init() throws ConsulException, IOException {
        env.getPropertySources().addFirst(new ConsulPropertySource("consul", getConsulInfo()));
    }

    public ConsulInfo getConsulInfo() throws ConsulException, IOException {
        String host = env.getProperty(Constants.CONSUL_HOST);
        int port = NumberUtils.toInt(env.getProperty(Constants.CONSUL_PORT), 8500);
        Consul consul = new Consul(host, port);
        String service = env.getProperty(Constants.CONSUL_SERVICE);
        String tag = env.getProperty(Constants.CONSUL_TAG);
        consul.agent().register(new ServiceProvider("id",
                service, 8302,
                new String[]{tag}));
        KeyValue keyValue = new KeyValue(consul);
        String keyName = String.format("service/%s/%s/config", service, tag);
        return new ConsulInfo(consul, keyName, keyValue);
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
