package com.dac.spring.processor;


import com.dac.spring.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User, User> {
    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public User process(User user) throws Exception {
        String firstName = user.getFirstName().toUpperCase();
        String lastName = user.getLastName().toUpperCase();

        final User transformedUser = new User(firstName, lastName);

        log.info("Converting (" + user + ") into (" + transformedUser + ")");

        return transformedUser;
    }
}
