//package com.example.cart.Configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.Tag;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//@EnableAutoConfiguration
//@EnableSwagger2
//public class SwaggerConfig {
//    private static final Set<String> PROTOCOLS = new HashSet<>(Arrays.asList("http","https"));
//    @Value("localhost:8099")
//    private String hostUrl;
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).protocols(PROTOCOLS).host(hostUrl)
//                .securitySchemes(Collections.singletonList(apiKey()))
//                .tags(new Tag("cart management","cartmanagement api"))
//                .groupName("cart Management").select().apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    public ApiInfo apiInfo(){
//        return new ApiInfoBuilder().title("cart management").description("Mobility Solution Space Controllers").build();
//    }
//
//    public ApiKey apiKey(){
//        return new ApiKey("Authorization","apikey","header");
//    }
//
//}
