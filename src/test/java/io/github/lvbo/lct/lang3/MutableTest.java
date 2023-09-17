package io.github.lvbo.lct.lang3;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.Test;

/**
 * @author lvbo created on 2023-08-22 09:47
 */
public class MutableTest {

    @Test
    public void testMutableObject() {
        MutableObject mutableObject = new MutableObject("Initial value");

        assertEquals(mutableObject.getValue(), "Initial value");

        mutableObject.setValue("Another value");
        assertEquals(mutableObject.getValue(), "Another value");

        assertEquals(mutableObject.toString(), "Another value");
    }

}
