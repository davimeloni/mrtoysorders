package com.dc.javafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootifulFxApplication {

    public static void main(String[] args) {
//        SpringApplication.run(JavafxApplication.class, args);
        Application.launch(JavafxApplication.class, args);
    }
}
