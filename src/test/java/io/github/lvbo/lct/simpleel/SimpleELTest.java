package io.github.lvbo.lct.simpleel;

import com.alibaba.simpleEL.compile.JdkCompiler;
import com.alibaba.simpleEL.eval.DefaultExpressEvalService;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lvbo.lv created on 2023-03-18 23:22
 */
public class SimpleELTest {

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

    @Test
    public void testSimpleEL() {
        DefaultExpressEvalService service = new DefaultExpressEvalService();
        // 注册两个类型为Integer的变量
        service.regsiterVariant(Integer.class, "a", "b");

        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 3);
        ctx.put("b", 4);

        Assert.assertEquals(7, service.eval(ctx, "@a + @b"));
        Assert.assertEquals(true, service.eval(ctx, "@a < @b"));

        //再执行一次，参数不一样
        Map<String, Object> context = new HashMap<>();
        context.put("a", 16);
        context.put("b", 4);
        Assert.assertEquals(20, service.eval(context, "@a + @b"));
    }

}
