package com.gusuran.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(DeferredResult.class, ResponseEntity.class)
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder().name("channelId").parameterType("header").modelRef(new ModelRef("string"))
                                .required(true).defaultValue("").description("client's channel id").build(),
                        new ParameterBuilder().name("username").parameterType("header").modelRef(new ModelRef("string"))
                                .required(false).description("client's username").build(),
                        new ParameterBuilder().name("requestId").parameterType("header").modelRef(new ModelRef("string"))
                                .required(true).defaultValue("").description("unique id per request").build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST API with Swagger")
                .description("Sample Authentication Service")
                .build();
    }
}
