package com.github.abigail830.contractlistener.util;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JenkinsTriggerTest {

    String url = "http://ec2-52-81-45-194.cn-north-1.compute.amazonaws.com.cn:8088/job/mock-server/build?token=ThisIsAToken";
    @Test
    public void build() {
        JenkinsTrigger.build(url);
    }

}