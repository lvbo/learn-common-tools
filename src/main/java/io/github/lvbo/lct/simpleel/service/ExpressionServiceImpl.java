package io.github.lvbo.lct.simpleel.service;

import com.alibaba.simpleEL.compile.JdkCompiler;
import com.alibaba.simpleEL.eval.DefaultExpressEvalService;
import io.github.lvbo.lct.simpleel.constants.LogicOperatorEnum;
import io.github.lvbo.lct.simpleel.constants.VariantEnum;
import io.github.lvbo.lct.simpleel.factory.OperatorFactory;
import io.github.lvbo.lct.simpleel.rule.Rule;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExpressionServiceImpl implements ExpressionService {
    
    private final OperatorFactory operatorFactory = new OperatorFactory();
    
    public final DefaultExpressEvalService service = new DefaultExpressEvalService();
    
    static {
        final var fields = JdkCompiler.class.getDeclaredFields();
        final String javaVersion = System.getProperty("java.version");
        for (final var field : fields) {
            if ("javaVersion".equals(field.getName())) {
                field.setAccessible(true);
                try {
                    // 1.8以及下，已处理，此处只处理1.8以上
                    // 以下仅11做过测试，其它版本需要测试
                    if (javaVersion.startsWith("9.")) {
                        field.set(null, "9");
                    } else if (javaVersion.startsWith("10.")) {
                        field.set(null, "10");
                    } else if (javaVersion.startsWith("11.")) {
                        field.set(null, "11");
                    } else if (javaVersion.startsWith("12.")) {
                        field.set(null, "12");
                    } else if (javaVersion.startsWith("13.")) {
                        field.set(null, "13");
                    } else if (javaVersion.startsWith("14.")) {
                        field.set(null, "14");
                    } else if (javaVersion.startsWith("15.")) {
                        field.set(null, "15");
                    } else if (javaVersion.startsWith("16.")) {
                        field.set(null, "16");
                    }
                } catch (final Exception e) {
                    throw new RuntimeException("javaVersion修改失败", e);
                }
                
                break;
            }
        }
    }
    
    public ExpressionServiceImpl() {
        Arrays.stream(VariantEnum.values()).forEach(v -> this.service.regsiterVariant(v.getClazz(), v.getVar()));
        
    }
    
    @Override
    public String generate(final List<Rule> list) {
        try {
            final var builder = new StringBuilder(256);
            
            for (var i = 0; i < list.size(); i++) {
                final var rule = list.get(i);
                
                if (rule.isLeftBracket()) {
                    builder.append("(");
                }
                
                final String exp = this.operatorFactory.generate(rule);
                builder.append(exp);
                
                if (rule.isRightBracket()) {
                    builder.append(")");
                }
                
                if (i < list.size() - 1) {
                    if (rule.getLogicOperator() == LogicOperatorEnum.AND.getOperator()) {
                        builder.append(" && ");
                    } else if (rule.getLogicOperator() == LogicOperatorEnum.OR.getOperator()) {
                        builder.append(" || ");
                    }
                }
            }
            
            final var exp = builder.toString();

            return exp;
        } catch (final Exception e) {
            throw e;
        }
    }
    
    @Override
    public boolean eval(final Map<String, Object> ctx, final String exp) {
        try {
            return (Boolean) this.service.eval(ctx, exp);
        } catch (final Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean evalException(final Map<String, Object> ctx, final String exp) {
        return (Boolean) this.service.eval(ctx, exp);
    }
}
