package io.github.lvbo.lct.protostuff;

import io.protostuff.Tag;
import java.util.List;

/**
 * @author lvbo.lv created on 2023-03-17 09:59
 */
public class School {

    @Tag(1)
    private String schoolName;
    @Tag(2)
    private List<Student> students;

    public School(String schoolName, List<Student> students) {
        this.schoolName = schoolName;
        this.students = students;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolName='" + schoolName + '\'' +
                ", students=" + students +
                '}';
    }
}
