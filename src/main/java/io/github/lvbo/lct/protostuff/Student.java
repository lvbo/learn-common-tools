package io.github.lvbo.lct.protostuff;

import io.protostuff.Tag;

/**
 * @author lvbo.lv created on 2023-03-17 09:57
 */
public class Student {

    @Tag(1)
    private String name;
    @Tag(2)
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
