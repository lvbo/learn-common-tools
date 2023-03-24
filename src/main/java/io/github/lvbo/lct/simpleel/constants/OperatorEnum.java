package io.github.lvbo.lct.simpleel.constants;

import io.github.lvbo.lct.simpleel.operator.CommonOperatorGenerator;
import io.github.lvbo.lct.simpleel.operator.InOperatorGenerator;
import io.github.lvbo.lct.simpleel.operator.NotInOperatorGenerator;
import io.github.lvbo.lct.simpleel.operator.OperatorGenerator;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperatorEnum {

    EQ("==", 10),
    NOT_EQ("!=", 11),
    GT(">", 13),
    LT("<", 12),
    GE(">=", 15),
    LE("<=", 14),
    IN("in", 51, new InOperatorGenerator()),
    NOT_IN("notIn", 52, new NotInOperatorGenerator());

    private final String operator;
    private final int code;
    private final OperatorGenerator operatorGenerator;
    
    OperatorEnum(final String operator, final int code) {
        this.operator = operator;
        this.code = code;
        this.operatorGenerator = new CommonOperatorGenerator();
    }
    
    private static final Map<Integer, OperatorEnum> MAP = Arrays.stream(OperatorEnum.values())
            .collect(Collectors.toMap(OperatorEnum::getCode, o -> o));

    public static OperatorEnum getOperatorEnum(final int operator) {
        return MAP.get(operator);
    }
}
