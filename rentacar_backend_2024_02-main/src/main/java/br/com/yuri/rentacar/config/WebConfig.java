package br.com.yuri.rentacar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8888", "http://www.yuri.com.br")
                .allowedMethods("GET","POST","PUT","DELETE"
                ,"OPTIONS","HEAD", "TRACE", "CONNECT");
    }
}
