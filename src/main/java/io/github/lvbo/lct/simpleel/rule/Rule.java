package io.github.lvbo.lct.simpleel.rule;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

/**
 * @author lvbo created on 2023-03-20 10:16
 */
@Getter
@Builder(setterPrefix = "with")
public class Rule implements Serializable {

    private final boolean leftBracket;
    private final String key;
    private final int operator;
    private final String value;
    private final boolean rightBracket;
    private final int logicOperator;

}
