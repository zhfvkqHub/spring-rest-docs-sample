package com.raonsecure.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Configuration
public class MessageSourceConfig {
    @Value("${spring.messages.default-locale}")
    private String localeStringForProperties;

    @Value("${spring.messages.errorBasename}")
    private String errorBasename;

    @Bean
    public ReloadableResourceBundleMessageSource errorMessageSource(MessageSourceProperties properties) {
        return createCustomMessageSource(errorBasename, properties);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    private ReloadableResourceBundleMessageSource createCustomMessageSource(String basename, MessageSourceProperties properties) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        if (StringUtils.hasText(basename)) {
            messageSource.setBasenames(basename);
        }

        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding()
                    .name());
        }

        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }

        Locale.setDefault(getAvailableLocale(localeStringForProperties));
        messageSource.setDefaultLocale(Locale.getDefault());

        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());

        return messageSource;
    }

    private Locale getAvailableLocale(String localeString) {
        Optional<Locale> optionalLocale = convertLocaleStringToLocale(localeString);
        return optionalLocale.get();
    }

    private boolean isAvailableLocale(Locale inputLocale) {
        return Arrays.asList(Locale.getAvailableLocales())
                .contains(inputLocale);
    }

    private Optional<Locale> convertLocaleStringToLocale(String localeString) {
        try {
            return Optional.ofNullable(StringUtils.parseLocaleString(localeString));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Bean
    public MessageSourceAccessor errorMessageSourceAccessor(
            @Qualifier("errorMessageSource") ReloadableResourceBundleMessageSource errorMessageSource
    ) {
        return new MessageSourceAccessor(errorMessageSource);
    }}
