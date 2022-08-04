package com.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private List<String> nodes;
}