package io.github.lvbo.lct.lang3;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

/**
 * @author lvbo created on 2023-08-22 09:53
 */
public class TripleTest {

    private static ImmutablePair<String, String> immutablePair = new ImmutablePair<>("leftElement", "rightElement");

    @Test
    public void testMutablePair() {
        MutablePair mutablePair = new MutablePair<>("leftElement", "rightElement");
        assertEquals(mutablePair.getLeft(), "leftElement");
        assertEquals(mutablePair.getRight(), "rightElement");
        mutablePair.setLeft("newLeftElement");
        assertEquals(mutablePair.getLeft(), "newLeftElement");
    }

    @Test
    public void testImmutablePair() {
        assertEquals(immutablePair.getLeft(), "leftElement");
        assertEquals(immutablePair.getRight(), "rightElement");

        ImmutablePair<String, String> of = ImmutablePair.of("leftElement", "rightElement");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenCalledSetValue_thenThrowUnsupportedOperationException() {
        immutablePair.setValue("newValue");
    }

    @Test
    public void testTriple() {
        Triple<String, String, String> triple = Triple.of("leftElement", "middleElement", "rightElement");
        assertEquals(triple.getLeft(), "leftElement");
        assertEquals(triple.getMiddle(), "middleElement");
        assertEquals(triple.getRight(), "rightElement");
    }
}
