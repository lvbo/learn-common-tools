package io.github.lvbo.lct.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import io.github.lvbo.lct.protobuf.StudentOuterClass.Student;
import io.github.lvbo.lct.protobuf.StudentOuterClass.Student.Builder;
import java.util.Arrays;
import org.junit.Test;

/**
 * @author lvbo created on 2023-03-16 10:09
 */
public class StudentTest {

    @Test
    public void testStudentCreate() {
        Student build = Student.newBuilder()
                .setName("erdai")
                .setAge(18)
                .setEmail("erdai666@qq.com")
                .addAllCourse(Arrays.asList("Math", "English", "Computer"))
                .build();
        System.out.println(build);
    }

    @Test
    public void testStudentSerializeAndDeserialize() throws InvalidProtocolBufferException {
        // 1、构造对象
        Student build = Student.newBuilder()
                .setName("erdai")
                .setAge(18)
                .setEmail("erdai666@qq.com")
                .addAllCourse(Arrays.asList("Math", "English", "Computer"))
                .build();
        // 2、序列化并返回一个包含其原始字节的字节数组
        byte[] byteArray = build.toByteArray();
        // 3、反序列化从字节数组中解析消息
        Student student1 = StudentOuterClass.Student.parseFrom(byteArray);
        System.out.println(student1);
    }

    @Test
    public void testProtobufToJson() throws InvalidProtocolBufferException {
        // 1、构建 Protobuf 对象
        Student build = Student.newBuilder()
                .setName("erdai")
                .setAge(18)
                .setEmail("erdai666@qq.com")
                .addAllCourse(Arrays.asList("Math", "English", "Computer"))
                .build();
        // 2、Protobuf 转 Json
        String print = JsonFormat.printer().print(build);
        // 3、打印 json
        System.out.println(print);
    }

    @Test
    public void testJsonToProtobuf() throws InvalidProtocolBufferException {
        // 1、构建 Protobuf 对象
        Student build = Student.newBuilder()
                .setName("erdai")
                .setAge(18)
                .setEmail("erdai666@qq.com")
                .addAllCourse(Arrays.asList("Math", "English", "Computer"))
                .build();
        // 2、Protobuf 转 Json
        String print = JsonFormat.printer().print(build);
        // 3、Json 转 Protobuf 对象
        Builder builder1 = Student.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(print, builder1);
        // 4、打印 Protobuf 对象
        System.out.println(builder1.build());
    }


}
