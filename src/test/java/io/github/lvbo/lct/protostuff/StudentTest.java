package io.github.lvbo.lct.protostuff;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author lvbo.lv created on 2023-03-17 10:08
 */
public class StudentTest {

    @Test
    public void testStudent() {
        Student stu1 = new Student("张三", 20);
        Student stu2 = new Student("李四", 21);
        List<Student> students = new ArrayList<>();
        students.add(stu1);
        students.add(stu2);
        School school = new School("西工大", students);
        //首先是序列化
        byte[] bytes = ProtostuffUtils.serialize(school);
        System.out.println("序列化后: "+ bytes.length);
        //然后是反序列化
        School group1 = ProtostuffUtils.deserialize(bytes, School.class);
        System.out.println("反序列化后: " + group1);
    }

}
