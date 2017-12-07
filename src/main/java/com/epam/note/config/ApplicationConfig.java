package com.epam.note.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.epam.note.config", "com.epam.note.dao", "com.epam.note.services.impl",
        "com.epam.note.init"})
public class ApplicationConfig {

}
