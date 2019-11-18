package com.spec.analysis.utils.mapper;

import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ConfigurableMapper {

//    @Override
//    protected void configure(MapperFactory factory) {
//
//        factory.classMap(User.class, UserResponse.class)
//                .byDefault()
//                .register();
//
//        factory.classMap(User.class, RegisterRequest.class)
//                .byDefault()
//                .register();
//
//        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
//        factory.getConverterFactory()
//                .registerConverter(new PassThroughConverter(LocalDateTime.class));
//    }

}
