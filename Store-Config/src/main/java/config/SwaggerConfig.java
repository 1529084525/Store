package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * 配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /*  @Bean
    public Docket docketToken(){
        //配置全局的参数 如token
        Parameter token=new ParameterBuilder().name("token")
                .description("用户登录令牌")//备注
                //header请求头中 要用这个接受的话 接口参数需写@RequestHeader("token")String token
                //query请求体中 不用写RequestHeader
                .parameterType("header")
                //.required(true)//每一个接口都带上token  必须要token才能执行
                .modelRef(new ModelRef("String"))
                .build();
        List<Parameter> parameters=new ArrayList<>();
        parameters.add(token);
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters);
    }*/


    //配置API文档信息
    //API信息保存在Docket
    @Bean
    public Docket docketUser() {
        //Profiles.of7
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                //如果没有配置分组，默认是default。通过groupName()方法即可配置分组：
                //配置多个分组 写多个Bean即可
                .groupName("用户")

                //.ignoredParameterTypes(String.class)配置接口忽略参数

                //.enable(false)//是否启用Swagger 如果是false,Swagger将不能在浏览器中访问(一般用于项目上线后,不允许被暴露出去)

                // 通过.select()方法，去配置扫描接口
                .select()

                // RequestHandlerSelectors配置如何扫描接口
                // any()扫描所有  none()不扫描  basePackage()扫描指定路径包
                // withClassAnnotation(RestController.class)通过类上的注解扫描
                // withMethodAnnotation(GetMapping.class))通过方法扫描
                //.apis(RequestHandlerSelectors.basePackage("config.controller"))

                //通过path指定controller下的接口
                .paths(PathSelectors.any())
                .build();//通过apiInfo指定接口文档信息;//通过apiInfo指定接口文档信息
    }


    //通过这个类构建apiinfo  除了title  description 其他都不重要
    private ApiInfo apiInfo() {
        Contact contact = new Contact("汤童暄", "shadiao.com", "2398250143@qq.com");
        return new ApiInfo("沙雕接口文档",
                "这是一个描述",
                "V1.0",
                "http://localhost:8080",
                contact,
                "",
                "",
                new ArrayList<VendorExtension>());
    }


}
