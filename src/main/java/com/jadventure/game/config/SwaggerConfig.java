package com.jadventure.game.config;

/**
 * @author zgn
 * @Description: SwaggerConfig
 * @date 2022/1/10 0010
 */

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2//开启和关闭Swagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) // 注意此处改动,需要将SWAGGER_2改成OAS_30
                .apiInfo(apiInfo()).select()
                //.apis(RequestHandlerSelectors.basePackage("com.zfast.yugioh.controller")) // 扫描包
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 扫描类注解
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //扫描方法注解
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("游戏王java版")
                .description("swagger2接口文档")
//                .contact(new Contact("碧海燕鱼", "#", "654195681@qq.com"))
                .version("0.0.1")
                .build();
    }
}
