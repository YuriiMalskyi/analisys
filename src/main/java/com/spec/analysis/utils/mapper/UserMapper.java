package com.spec.analysis.utils.mapper;

import com.spec.analysis.dto.request.LoginRequest;
import com.spec.analysis.dto.request.RegisterRequest;
import com.spec.analysis.entity.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class UserMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(User.class, RegisterRequest.class)
                .byDefault()
                .register();

        factory.classMap(User.class, LoginRequest.class)
                .byDefault()
                .register();

        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        factory.getConverterFactory()
                .registerConverter(new PassThroughConverter(LocalDateTime.class));
    }

}
