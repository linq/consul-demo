package com.linq.config.consul;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;

/**
 * ConsulPropertySource
 *
 * @author LinQ
 * @version 2015-08-27
 */
public class ConsulPropertySource extends MapPropertySource {
    private DynamicConfiguration configuration;

    public ConsulPropertySource(String name, ConsulInfo consulInfo) {
        super(name, new HashMap<String, Object>());

        DynamicConfiguration configuration = new DynamicConfiguration(
                new ConsulConfigurationSource(consulInfo), new FixedDelayPollingScheduler());
        ConfigurationManager.install(configuration);
        this.configuration = configuration;
    }

    @Override
    public Object getProperty(String name) {
        return configuration.getProperty(name);
    }
}
