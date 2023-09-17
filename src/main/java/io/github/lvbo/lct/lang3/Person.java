package io.github.lvbo.lct.lang3;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author lvbo created on 2023-08-15 10:14
 */
public class Person implements Diffable<Person>  {
    private final String name;
    private final int age;
    private final boolean smoker;

    public Person(String name, int age, boolean smoker) {
        this.name = name;
        this.age = age;
        this.smoker = smoker;
    }

    public DiffResult diff(Person obj) {
        // No need for null check, as NullPointerException correct if obj is null
        return new DiffBuilder(this, obj, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", this.name, obj.name)
                .append("age", this.age, obj.age)
                .append("smoker", this.smoker, obj.smoker)
                .build();
    }
}
