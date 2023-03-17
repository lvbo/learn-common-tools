package io.github.lvbo.lct.protobuf;

import java.io.IOException;
import org.junit.Test;

/**
 * @author lvbo created on 2023-03-02 23:25
 */
public class PersonTest {

    @Test
    public void testPerson() throws IOException {
        Person john =
                Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhones(
                                Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(Person.PhoneType.HOME))
                        .build();
        byte[] bytes = john.toByteArray();
        Person person = Person.parseFrom(bytes);
        System.out.println(person);
    }
}
