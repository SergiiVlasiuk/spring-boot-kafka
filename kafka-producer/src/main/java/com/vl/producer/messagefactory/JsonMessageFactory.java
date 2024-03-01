package com.vl.producer.messagefactory;

import com.vl.producer.model.MyModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
@Profile("experimental-json")
public class JsonMessageFactory implements MessageFactory {

    @Override
    public MyModel generateMessage() {
        return MyModel.builder()
                .id(UUID.randomUUID())
                .name("name-" + randomAlphabetic(10))
                .description("description-" + randomAlphabetic(10))
                .build();
    }
}
