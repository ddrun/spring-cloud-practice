package com.example.djran.core.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(new ApiInfoBuilder()
            .title("服务接口文档")
            .termsOfServiceUrl("http://api.epsoft.com")
            .version("2.0")
            .contact(new Contact("djran",
                    "https://ddrun.github.io/",
                    "d.djran@gmail.com"))
            .build())
            .select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .build();
    }

//    @Bean
//    public Docket createSwaggerDocketByGroup1(){
//        Docket docket=new Docket(DocumentationType.SWAGGER_2)
//                .groupName("大汗接口")
//                .select()
//                .paths(regex("/bpo/.*")).build()
//                .apiInfo(apiInfo);
//        return docket;
//    }
//
//    @Bean
//    public Docket createSwaggerDocketByGroup2(){
//        Docket docket=new Docket(DocumentationType.SWAGGER_2)
//                .groupName("天正接口")
//                .select()
//                .paths(regex("/post/.*")).build()
//                .apiInfo(apiInfo);
//        return docket;
//    }
//    @Bean
//    public Docket createSwaggerDocketByGroup3(){
//        Docket docket=new Docket(DocumentationType.SWAGGER_2)
//                .groupName("内容接口")
//                .select()
//                .paths(regex("/site/.*|/face/.*")).build()
//                .apiInfo(apiInfo);
//        return docket;
//    }
}


