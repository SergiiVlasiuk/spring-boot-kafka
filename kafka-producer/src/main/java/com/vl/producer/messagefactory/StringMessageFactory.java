package com.vl.producer.messagefactory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
@Profile("experimental-string")
public class StringMessageFactory implements MessageFactory {

    @Override
    public String generateMessage() {
        return randomAlphabetic(30);
    }
}
