package com.github.abigail830.contractlistener.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JenkinsTrigger {

    private static final Logger logger = LoggerFactory.getLogger(JenkinsTrigger.class);

    public static final String JENKINS_URL="http://jenkins.saraqian.cn/job/mock-server-sample/build?token=ThisIsAToken";


    public static void build(){
        logger.info("Trigger Jenkins build for mock-server-sample");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(JENKINS_URL, null, String.class);
        HttpStatus status = response.getStatusCode();
        logger.info("JenkinsTrigger.build() with status: {}", status);

    }


}
