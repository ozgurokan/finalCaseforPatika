package com.ozgurokanozdal.paticars.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    // burada constructor oluşturmayı unutmuşum burayı düzelt....jwt muhabbetinden sonra tabi ki dikkat dağılmasın
    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
