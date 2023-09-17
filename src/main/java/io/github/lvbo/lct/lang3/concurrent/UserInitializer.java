package io.github.lvbo.lct.lang3.concurrent;

import org.apache.commons.lang3.concurrent.LazyInitializer;

/**
 * @author lvbo created on 2023-08-21 10:17
 */
public class UserInitializer extends LazyInitializer<User> {

    @Override
    protected User initialize() {
        return new User("test", 23, "test address");
    }
}