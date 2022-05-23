package com.kodilla.convertertask.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class SlashConverterConfig {
    @Bean
    public HttpMessageConverter<Object> slashConverter(){
        return new SlashConverter();
    }
}
