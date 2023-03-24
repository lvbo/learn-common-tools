package io.github.lvbo.lct.simpleel.service;

import io.github.lvbo.lct.simpleel.rule.Rule;
import java.util.List;
import java.util.Map;

public interface ExpressionService {
    
    String generate(List<Rule> list);
    
    boolean eval(Map<String, Object> ctx, String exp);
    
    boolean evalException(Map<String, Object> ctx, String exp);
}
