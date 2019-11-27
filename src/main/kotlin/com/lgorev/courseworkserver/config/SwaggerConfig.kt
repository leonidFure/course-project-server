package com.lgorev.courseworkserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig

@Bean
fun apiDocket(): Docket {
    return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
}

private fun apiInfo(): ApiInfo {
    return ApiInfo(
            "Course work APIs",
            "Some custom description of API.",
            "API TOS",
            "Terms of service",
            Contact("Leonid Gorev", "", "leonmore8@gamil.com"),
            "License of API", "API license URL", Collections.emptyList())
}