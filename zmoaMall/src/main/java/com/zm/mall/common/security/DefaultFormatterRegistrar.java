package com.zm.mall.common.security;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

/**
 * Created by liyanshuai on 2016/11/8.
 */
public class DefaultFormatterRegistrar implements FormatterRegistrar {
    public DefaultFormatterRegistrar() {
    }

    public void registerFormatters(FormatterRegistry registry) {
        if(registry instanceof ConversionService) {
            ConversionService service = (ConversionService)registry;
            registry.addConverterFactory(new StringToEnumConverterFactory(service));
        }

    }
}
