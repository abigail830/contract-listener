package com.github.abigail830.contractlistener.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jenkins")
public class JenkinsProperties {

    private String url;

    private boolean skip;

}
