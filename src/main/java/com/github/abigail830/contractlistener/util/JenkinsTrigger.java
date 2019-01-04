package com.github.abigail830.contractlistener.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class JenkinsTrigger {

    private static final Logger logger = LoggerFactory.getLogger(JenkinsTrigger.class);

    public static final String JENKINS_URL="http://jenkins.saraqian.cn/job/mock-server-sample/build?token=ThisIsAToken";


    public static void build(){
        logger.info("Trigger Jenkins build for mock-server-sample");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(JENKINS_URL, null, String.class);

        HttpStatus status = response.getStatusCode();
        if(status.is2xxSuccessful())
            logger.info("JenkinsTrigger.build() with status: {}", status);
        else
            logger.warn("JenkinsTrigger.build() fail with status: {}", status);

    }


}
