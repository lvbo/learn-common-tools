package io.github.lvbo.lct.simpleel.operator;

import com.google.common.base.Splitter;
import io.github.lvbo.lct.simpleel.constants.OperatorEnum;
import io.github.lvbo.lct.simpleel.constants.VariantEnum;

public class NotInOperatorGenerator implements OperatorGenerator {
    
    private static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();
    @Override
    public String generate(final VariantEnum variant, final OperatorEnum operator, final String value) {
        final var list = SPLITTER.splitToList(value);
        final var builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            final var tmp = String.format("!@%s.contains(\"%s\")", variant.getVar(), list.get(i));
            builder.append(tmp);
            if (i < list.size() - 1) {
                builder.append(" && ");
            }
        }
    
        if (list.size() == 1) {
            return String.format("(@%s == null || %s)", variant.getVar(), builder.toString());
        
        } else {
            return String.format("(@%s == null || (%s))", variant.getVar(), builder.toString());
        }
    
    }
}
