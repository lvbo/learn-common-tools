package io.github.lvbo.lct.lang3;

import io.github.lvbo.lct.lang3.concurrent.User;
import io.github.lvbo.lct.lang3.concurrent.UserInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.junit.Test;

/**
 * @author lvbo created on 2023-08-21 10:19
 */
public class LazyInitializerTest {

    @Test
    public void test() throws ConcurrentException {
        UserInitializer userInitializer = new UserInitializer();
        User user = userInitializer.get();
    }
}
