package io.github.lvbo.lct.simpleel.factory;

import io.github.lvbo.lct.simpleel.constants.OperatorEnum;
import io.github.lvbo.lct.simpleel.constants.VariantEnum;
import io.github.lvbo.lct.simpleel.rule.Rule;

public class OperatorFactory {
    
    public final String generate(final Rule rule) {
        final var operator = OperatorEnum.getOperatorEnum(rule.getOperator());
        if (operator == null) {
            throw new RuntimeException("operator does not exist" );
        }
        
        final var var = VariantEnum.getVariantEnum(rule.getKey());
        if (var == null) {
            throw new RuntimeException("variant does not exist, rule");
        }
        
        return operator.getOperatorGenerator()
                .generate(var, operator, var.getTransformer().transform(rule.getValue()));
    }
}
