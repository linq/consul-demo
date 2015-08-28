package com.linq.config.consul;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import org.apache.http.util.TextUtils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ConsulConfigurationSource
 *
 * @author LinQ
 * @version 2015-08-27
 */
public class ConsulConfigurationSource implements PolledConfigurationSource {

    private ConsulInfo consulInfo;

    public ConsulConfigurationSource(ConsulInfo consulInfo) {
        this.consulInfo = consulInfo;
    }

    @Override
    public PollResult poll(boolean initial, Object checkPoint) throws Exception {
        String str = consulInfo.getKeyValue().get(consulInfo.getKeyName());
        Map<String, Object> map = new HashMap<String, Object>();
        if (TextUtils.isBlank(str)) {
            return PollResult.createFull(map);
        } else {
            Properties properties = new Properties();
            properties.load(new StringReader(str));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                map.put(entry.getKey().toString(), entry.getValue());
            }

            return PollResult.createFull(map);
        }
    }
}
