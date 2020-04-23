package com.example.telegramcarbot;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(converter -> {
            if (converter instanceof AbstractHttpMessageConverter) {
                ((AbstractHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
                if (converter instanceof StringHttpMessageConverter) {
                    ((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
                }
            } else if (converter instanceof FormHttpMessageConverter) {
                ((FormHttpMessageConverter) converter).setCharset(StandardCharsets.UTF_8);
                ((FormHttpMessageConverter) converter).setMultipartCharset(StandardCharsets.UTF_8);
            }
        });
    }
}