package io.github.lvbo.lct.lang3;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Test;

/**
 * @author lvbo created on 2023-08-15 10:02
 */
public class BuilderTest {

    @Test
    public void testToString() {
        BuilderBean builderBean = new BuilderBean(1, "张三");
        System.out.println(builderBean);
    }

    @Test
    public void testDiffBuilder() {
        Person a = new Person("张三", 22, false);
        Person b = new Person("李四", 22, true);

        DiffResult result = a.diff(b);
        System.out.println("result -> " + result);
    }

    @Test
    public void testHashCodeBuilder() {
        int hashcode = new HashCodeBuilder(17, 37)
                .append("John")
                .append("john@domain.com")
                .toHashCode();
        assertEquals(hashcode, 1269178828);
    }
}
