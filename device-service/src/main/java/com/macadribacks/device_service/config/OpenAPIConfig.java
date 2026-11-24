package com.macadribacks.device_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI userServiceApiDocs() {
        return new OpenAPI()
                .info(new Info()
                        .title("Device Service API")
                        .description("Device service API for Home Energy Tracker")
                        .contact(getContact())
                        .license(getLicence())
                        .version("1.0.0"));
    }

    private static License getLicence() {
        License license = new License();
        license.setName("Creative Commons Attribution-NonCommercial 4.0 International License");
        license.setUrl("https://creativecommons.org/licenses/by-nc/4.0/");
        return license;
    }

    private static Contact getContact() {
        Contact contact = new Contact();
        contact.setUrl("https://github.com/adrianC56");
        contact.setEmail("adriancrespog3@gmail.com");
        return contact;
    }
}
