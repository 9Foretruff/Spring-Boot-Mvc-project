package org.foretruff.collegemvcproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CollegeMvcProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeMvcProjectApplication.class, args);
    }

}
