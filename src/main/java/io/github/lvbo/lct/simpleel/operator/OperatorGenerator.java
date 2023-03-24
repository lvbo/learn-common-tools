package io.github.lvbo.lct.simpleel.operator;

import io.github.lvbo.lct.simpleel.constants.OperatorEnum;
import io.github.lvbo.lct.simpleel.constants.VariantEnum;

public interface OperatorGenerator {
    
    /**
     * 生成局部表达式
     *
     * @param variant
     *         变量
     * @param operator
     *         操作符
     * @param value
     *         值
     *
     * @return 表达式
     */
    String generate(VariantEnum variant, OperatorEnum operator, String value);
    
}
