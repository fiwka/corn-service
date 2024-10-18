package xyz.fiwka.cornservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Corn Service", description = "Сервис для обработки запросов обжарки и поступления зерна", version = "v1"))
public class CornServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornServiceApplication.class, args);
    }

}
