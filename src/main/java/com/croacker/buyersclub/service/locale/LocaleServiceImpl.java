package com.croacker.buyersclub.service.locale;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class LocaleServiceImpl implements LocaleService{

    private final MessageSource messageSource;

    private static final String DEFAULT_LANGUAGE_CODE = "ru";

    @Override
    public String getString(String key, String languageCode) {
        var locale = new Locale(languageCode);
        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String getString(String key) {
        var locale = new Locale(DEFAULT_LANGUAGE_CODE);
        return messageSource.getMessage(key, null, locale);
    }

}
