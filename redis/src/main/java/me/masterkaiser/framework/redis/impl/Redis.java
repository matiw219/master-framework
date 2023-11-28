package me.masterkaiser.framework.redis.impl;

import eu.okaeri.configs.OkaeriConfig;
import me.masterkaiser.framework.yaml.YamlConfig;
import org.springframework.stereotype.Component;

@Component
@YamlConfig(fileName = "redis")
public class Redis extends OkaeriConfig {
    public String address = "redis://{host}:{port}";
    public int database = 1;
    public String password = "";
}
