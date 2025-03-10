package com.traini8.registry.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;


import java.time.Instant;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom Converter: Instant -> Long
        Converter<Instant, Long> instantToLong = new Converter<>() {
            @Override
            public Long convert(MappingContext<Instant, Long> context) {
                return (context.getSource() != null) ? context.getSource().toEpochMilli() : null;
            }
        };

        // Custom Converter: Long -> Instant
        Converter<Long, Instant> longToInstant = new Converter<>() {
            @Override
            public Instant convert(MappingContext<Long, Instant> context) {
                return (context.getSource() != null) ? Instant.ofEpochMilli(context.getSource()) : null;
            }
        };

        // Register Converters
        modelMapper.addConverter(instantToLong);
        modelMapper.addConverter(longToInstant);

        return modelMapper;
    }
}


