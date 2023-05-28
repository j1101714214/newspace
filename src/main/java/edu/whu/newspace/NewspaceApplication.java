package edu.whu.newspace;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Newspace
 * @version 1.0
 * @description Newspace启动项
 * @date 2023/5/23 9:45
 */
@SpringBootApplication
@EnableSwagger2
public class NewspaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewspaceApplication.class, args);
    }

}
