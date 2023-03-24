package io.github.lvbo.lct.simpleel.operator;

import io.github.lvbo.lct.simpleel.constants.OperatorEnum;
import io.github.lvbo.lct.simpleel.constants.VariantEnum;

public class CommonOperatorGenerator implements OperatorGenerator {
    
    @Override
    public String generate(final VariantEnum variant, final OperatorEnum operator, final String value) {
        return String.format("@%s %s %s", variant.getVar(), operator.getOperator(), value);
    }
}
    
    
