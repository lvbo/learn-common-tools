package io.github.lvbo.lct.simpleel.service;

import static junit.framework.TestCase.assertEquals;

import com.google.common.collect.Maps;
import io.github.lvbo.lct.simpleel.constants.LogicOperatorEnum;
import io.github.lvbo.lct.simpleel.constants.OperatorEnum;
import io.github.lvbo.lct.simpleel.constants.VariantEnum;
import io.github.lvbo.lct.simpleel.rule.Rule;
import java.util.Arrays;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lvbo created on 2023-03-24 23:25
 */
public class ExpressionServiceImplTest {

    private ExpressionService expressionService;


    @Before
    public void setUp() {
        expressionService = new ExpressionServiceImpl();
    }

    @Test
    public void testGenerate() {
        Rule rule1 = Rule.builder()
                .withLeftBracket(true)
                .withKey(VariantEnum.CHECK_IN.getVar())
                .withOperator(OperatorEnum.EQ.getCode())
                .withValue("2000-02-02 10:00:00")
                .withRightBracket(true)
                .withLogicOperator(LogicOperatorEnum.AND.getOperator())
                .build();

        Rule rule2 = Rule.builder()
                .withLeftBracket(true)
                .withKey(VariantEnum.CHECK_OUT.getVar())
                .withOperator(OperatorEnum.EQ.getCode())
                .withValue("2000-02-08 10:00:00")
                .withRightBracket(true).build();

        String generate = expressionService.generate(Arrays.asList(rule1, rule2));
        System.out.println(generate);
    }

    @Test
    public void testEval_1() {
        String exp = "@checkInDate == 20000202";
        Map<String, Object> ctx = Maps.newHashMap();
        ctx.put(VariantEnum.CHECK_IN.getVar(), 20000202);
        boolean eval = expressionService.eval(ctx, exp);
        assertEquals(true, eval);
    }

    @Test
    public void testEval_2() {
        String exp = "(@checkInDate == 20000202) && (@checkOutDate == 20000208)";
        Map<String, Object> ctx = Maps.newHashMap();
        ctx.put(VariantEnum.CHECK_IN.getVar(), 20000202);
        ctx.put(VariantEnum.CHECK_OUT.getVar(), 20000208);
        boolean eval = expressionService.eval(ctx, exp);
        assertEquals(true, eval);
    }
}