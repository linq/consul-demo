package com.linq.config.consul;

import consul.Consul;
import consul.KeyValue;

/**
 * ConsulInfo
 *
 * @author LinQ
 * @version 2015-08-27
 */
public class ConsulInfo {
    private Consul consul;
    private String keyName;
    private KeyValue keyValue;

    public ConsulInfo(Consul consul, String keyName, KeyValue keyValue) {
        this.consul = consul;
        this.keyName = keyName;
        this.keyValue = keyValue;
    }

    public Consul getConsul() {
        return consul;
    }

    public void setConsul(Consul consul) {
        this.consul = consul;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public KeyValue getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(KeyValue keyValue) {
        this.keyValue = keyValue;
    }
}
