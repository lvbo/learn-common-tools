package io.github.lvbo.lct.lang3;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author lvbo created on 2023-08-15 10:03
 */
public class BuilderBean {

    private Integer id;
    private String name;

    public BuilderBean() {
    }

    public BuilderBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
