package com.iedaas.checklistreport;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChecklistReportConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuthorizationFilter authorizationFilter() throws Exception {
        return new AuthorizationFilter();
    }
}
