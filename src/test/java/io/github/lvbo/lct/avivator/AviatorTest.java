package io.github.lvbo.lct.avivator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import io.github.lvbo.lct.aviator.AddFunction;
import io.github.lvbo.lct.aviator.Foo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @author lvbo created on 2023-03-25 14:53
 */
public class AviatorTest {

    @Test
    public void test1() {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }

    @Test
    public void testSayHello() {
        String yourname = "Hello";
        Map env = new HashMap();
        env.put("yourname", yourname);
        String result = (String) AviatorEvaluator.execute(" 'hello ' + yourname ", env);
        System.out.println(result);

        Object executeResult = AviatorEvaluator.execute(" 'a\"b' ");
        System.out.println(executeResult);

        executeResult = AviatorEvaluator.execute(" \"a\'b\" ");
        System.out.println(executeResult);

        executeResult = AviatorEvaluator.execute(" 'hello '+3 ");
        System.out.println(executeResult);

        executeResult = AviatorEvaluator.execute(" 'hello '+ unknow ");
        System.out.println(executeResult);
    }

    @Test
    public void testExec() {
        String myname="dennis";
        Object execResult = AviatorEvaluator.exec(" 'hello ' + yourname ", myname);
        System.out.println(execResult);
    }

    @Test
    public void testInvokeFunction() {
        Object executeResult = AviatorEvaluator.execute("string.length('hello')");
        System.out.println(executeResult);
    }

    @Test
    public void testCustomFunction() {
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1,2)"));
        System.out.println(AviatorEvaluator.execute("add(add(1,2),100)"));
    }

    @Test
    public void testCompile() {
        String expression = "a-(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);

        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);

        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);
    }

    @Test
    public void testCollection() {
        final List<String> list = new ArrayList();
        list.add("hello");
        list.add(" world");

        final int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 3;

        final Map<String, Date> map = new HashMap<>();
        map.put("date", new Date());

        Map<String, Object> env = new HashMap<>();
        env.put("list", list);
        env.put("array", array);
        env.put("mmap", map);

        System.out.println(AviatorEvaluator.execute(
                "list[0]+list[1]+'\narray[0]+array[1]+array[2]='+(array[0]+array[1]+array[2]) +' \ntoday is '+mmap.date ", env));
    }

    @Test
    public void testTernaryOperator() {
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100);
        env.put("b", 200);
        System.out.println(AviatorEvaluator.execute("a>b ? 'a is greater than b' : 'a is less than b'", env));
    }

    @Test
    public void testRegularExpression() {
        Map<String, Object> env = new HashMap<>();
        env.put("a", "hello");
        env.put("b", "world");
        System.out.println(AviatorEvaluator.execute("a =~ /h.*/ && b =~ /w.*/", env));
    }

    @Test
    public void testVariable() {
        Foo foo = new Foo(100, 3.14f, new Date());
        Map env = new HashMap();
        env.put("foo", foo);

        String result =
                (String) AviatorEvaluator.execute(
                        " '[foo i='+ foo.i + ' f='+foo.f+' year='+(foo.date.year+1900)+ ' month='+foo.date.month +']' ",
                        env);
        System.out.println(result);
    }

    @Test
    public void testNil() {
        System.out.println(AviatorEvaluator.execute("nil == nil"));
        System.out.println(AviatorEvaluator.execute(" 3> nil"));
        System.out.println(AviatorEvaluator.execute(" true!= nil"));
        System.out.println(AviatorEvaluator.execute(" ' '>nil "));
        System.out.println(AviatorEvaluator.execute(" a==nil "));
    }

    @Test
    public void testDate() {
        Map env = new HashMap();
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        env.put("date", date);
        env.put("dateStr", dateStr);

        Boolean result = (Boolean) AviatorEvaluator.execute("date==dateStr", env);
        System.out.println(result);

        result = (Boolean) AviatorEvaluator.execute("date > '2009-12-20 00:00:00:00' ", env);
        System.out.println(result);

        result = (Boolean) AviatorEvaluator.execute("date < '2200-12-20 00:00:00:00' ", env);
        System.out.println(result);

        result = (Boolean) AviatorEvaluator.execute("date == date", env);
        System.out.println(result);
    }

    @Test
    public void testSeq() {
        Map<String, Object> env = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(100);
        list.add(-100);
        env.put("list", list);

        // 求长度
        System.out.println(AviatorEvaluator.execute("count(list)", env));
        // 求和
        System.out.println(AviatorEvaluator.execute("reduce(list,+,0)", env));
        // 过滤
        System.out.println(AviatorEvaluator.execute("filter(list,seq.gt(0))", env));
        // 判断元素在不在集合里
        System.out.println(AviatorEvaluator.execute("include(list,100)", env));
        // 排序
        System.out.println(AviatorEvaluator.execute("sort(list)", env));
        // 遍历整个集合
        System.out.println(AviatorEvaluator.execute("map(list,println)", env));
    }
}
