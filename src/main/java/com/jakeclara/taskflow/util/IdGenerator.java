package com.jakeclara.taskflow.util;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    
    // member variable for unique id generation
    private final AtomicLong currentId;

    // constructor
    public IdGenerator(@Value("${id.generator.start-value:1}") long startValue) {
        this.currentId = new AtomicLong(startValue);
    }

    // method to generate unique ID
    public String generateUniqueID() {
        return String.valueOf(currentId.getAndIncrement());
    }
}
