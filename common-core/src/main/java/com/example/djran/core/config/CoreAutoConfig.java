package com.example.djran.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MvcConfig.class,SwaggerConfig.class})
public class CoreAutoConfig {
}
