package com.datacollector.configuration;

import com.datacollector.services.ILineService;
import com.datacollector.services.IValidator;
import com.datacollector.services.LineServiceImpl;
import com.datacollector.services.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Bean
    public IValidator getIValidator() {
        return new ValidationService();
    }

    @Bean
    public ILineService getILineService() {
        return new LineServiceImpl();
    }

    @PostConstruct
    public void  startup(){
        log.info("applications stated");
    }
}
