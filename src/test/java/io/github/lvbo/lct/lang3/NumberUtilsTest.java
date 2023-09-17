package io.github.lvbo.lct.lang3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/**
 * @author lvbo created on 2023-08-21 10:09
 */
public class NumberUtilsTest {

    @Test
    public void testCompare() {
        assertEquals(NumberUtils.compare(1, 1), 0);
        assertEquals(NumberUtils.compare(1L, 1L), 0);
    }

    @Test
    public void testCreateNumber() {
        assertEquals(NumberUtils.createNumber("123456"), 123456);
    }

    @Test
    public void testIsDigits() {
        assertTrue(NumberUtils.isDigits("123456"));
    }

    @Test
    public void testMaxMin() {
        int[] array = {1, 2, 3, 4, 5, 6};
        assertEquals(NumberUtils.max(array), 6);
        assertEquals(NumberUtils.min(array), 1);
    }

}
