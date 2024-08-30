package model;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dictionaries.IPathEnum;
import dictionaries.ServiceEnum;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ConfigQA {
    private final Map<IPathEnum, String> serviceDataMap = new HashMap<>();

    private volatile static ConfigQA instance;

    public static ConfigQA getInstance() {
        if (instance == null) {
            instance = new ConfigQA();
        }
        return instance;
    }

    private ConfigQA() {
        Config config = ConfigFactory.parseResources("config.conf");
        String port, host;
        for (ServiceEnum value : ServiceEnum.values()) {
            Config conf = config.getConfig("service")
                    .getConfig(value.name().toLowerCase());
            host = conf.getString("host");
            port = conf.getString("port");
            Config pathsConf = conf.getConfig("path");
            for (IPathEnum iPathEnum : value.getPathEnumList()) {
                String path = pathsConf.getString(iPathEnum.name().toLowerCase());
                serviceDataMap.put(iPathEnum, generateFullPath(host, port, path));
            }
        }
    }

    public String generateFullPath(String host, String port, String path) {
        return "http://" + host + ":" + port + "/" + path;
    }
}