package com.ehc.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class SampleApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(SampleApiApplication.class, args);
  }

  @Bean
  public Docket newsApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("greetings")
        .apiInfo(apiInfo())
        .select()
        .paths(regex("/api/v1/.*"))
        .build()
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET,
            newArrayList(
                new ResponseMessageBuilder().code(500).message("Failure").responseModel(new ModelRef("Error")).build(),
                new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
                new ResponseMessageBuilder().code(403).message("Forbidden").build(),
                new ResponseMessageBuilder().code(404).message("Not Found").build()
            ))
        .globalResponseMessage(RequestMethod.POST,
            newArrayList(
                new ResponseMessageBuilder().code(500).message("Failure").build(),
                new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
                new ResponseMessageBuilder().code(403).message("Forbidden").build(),
                new ResponseMessageBuilder().code(404).message("Not Found").build()
            )).globalResponseMessage(RequestMethod.PUT,
            newArrayList(
                new ResponseMessageBuilder().code(500).message("Failure").build(),
                new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
                new ResponseMessageBuilder().code(403).message("Forbidden").build(),
                new ResponseMessageBuilder().code(404).message("Not Found").build()
            )).securitySchemes(newArrayList(apiKey()));
  }

  private ApiKey apiKey() {
    return new ApiKey("abc1245fsdfsd41ds5fgds54525sd", "api_key", "header");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Rest Document")
        .description("Generating RestFull Webservice Document through swagger")
//        .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
        .contact("example@projectdomain.com")
//        .license("Apache License Version 2.0")
//        .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
        .version("1.0")
        .build();

  }
}
