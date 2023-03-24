package io.github.lvbo.lct.simpleel.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogicOperatorEnum {
    /**
     * 且
     */
    AND(200),
    /**
     * 或
     */
    OR(201);
    private final int operator;
}
