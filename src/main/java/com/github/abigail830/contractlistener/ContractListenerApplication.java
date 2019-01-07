package com.github.abigail830.contractlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Configuration
@EnableSwagger2
@EnableMongoAuditing
@EnableConfigurationProperties
public class ContractListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractListenerApplication.class, args);
	}

	@Bean
	public Docket createRestApi() {
		List<Parameter> pars = new ArrayList<>();
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.github.abigail830.contractlistener.controller"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Contract Listener Service")
				.description("Contract Listener Service")
				.contact(new Contact("SaraQian", null, "abigail830@163.com"))
				.version("1.0")// 版本号
				.build();
	}

}

