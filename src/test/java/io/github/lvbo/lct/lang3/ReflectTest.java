package io.github.lvbo.lct.lang3;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;

/**
 * @author lvbo created on 2023-07-18 10:23
 */
public class ReflectTest {

    /**
     * 反射获取对象实例属性的值
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException {
        ReflectTest ReflectTest = new ReflectTest();

        // 原生写法
        Field abcField = ReflectTest.getClass().getDeclaredField("abc");
        // 设置访问级别，如果private属性不设置则访问会报错
        abcField.setAccessible(true);
        String value = (String) abcField.get(ReflectTest);
        System.out.println(value);

        // commons写法 方法名如果不含Declared会向父类上一直查找
        String value2 = (String) FieldUtils.readDeclaredField(ReflectTest, "abc", true);
        System.out.println(value2);
        // 反射获取类静态属性的值
        String value3 = (String) FieldUtils.readStaticField(ReflectTest.class, "sAbc", true);
        System.out.println(value3);
        // 反射设置对象属性值
        FieldUtils.writeField(ReflectTest, "abc", "newValue", true);
        // 反射设置类静态属性的值
        FieldUtils.writeStaticField(ReflectTest.class, "sAbc", "newStaticValue", true);
    }

    /**
     * 获取被Test注解标识的方法
     */
    @Test
    public void testGetAnnotation() {
        //原生写法
        List<Method> annotatedMethods = new ArrayList<Method>();
        for (Method method : ReflectTest.class.getMethods()) {
            if (method.getAnnotation(Test.class) != null) {
                annotatedMethods.add(method);
            }
        }
        System.out.println(annotatedMethods);

        // commons写法
        Method[] methods = MethodUtils.getMethodsWithAnnotation(ReflectTest.class, Test.class);
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    /**
     * 方法调用
     */
    @Test
    public void testInvokeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 调用函数"testMethod"
        ReflectTest ReflectTest = new ReflectTest();
        // 原生写法
        Method testMethod = ReflectTest.getClass().getDeclaredMethod("testGetAnnotation");
        testMethod.setAccessible(true); // 设置访问级别，如果private函数不设置则调用会报错
        testMethod.invoke(ReflectTest);
        System.out.println("------------------------");

        // commons写法
        MethodUtils.invokeExactMethod(ReflectTest, "testGetAnnotation");
        System.out.println("------------------------");

        // ---------- 类似方法 ----------
        // 调用static方法
        MethodUtils.invokeExactStaticMethod(ReflectDemo.class, "test", "testParam");
    }
}
