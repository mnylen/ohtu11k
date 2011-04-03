package fi.helsinki.cs.oato.model;

import java.util.*;

import fi.helsinki.cs.oato.model.Course;
import org.joda.time.DateTime;

public class CourseTestFixtures {
    public static List<Course> threeConsecutiveCourses() {
        List<Course> ret = new ArrayList<Course>();
        ret.add(new Course("Course1",
                           new DateTime(2020, 1, 1, 0, 0, 0, 0),
                           new DateTime(2020, 2, 1, 0, 0, 0, 0)));
        ret.add(new Course("Course2",
                           new DateTime(2020, 2, 1, 0, 0, 0, 0),
                           new DateTime(2020, 3, 1, 0, 0, 0, 0)));
        ret.add(new Course("Course3",
                           new DateTime(2020, 3, 1, 0, 0, 0, 0),
                           new DateTime(2020, 4, 1, 0, 0, 0, 0)));
        return ret;
    }

    public static List<Course> twoEqualCourses() {
        List<Course> ret = new ArrayList<Course>();
        ret.add(new Course("Foobar Football Course",
                           new DateTime(1932, 1, 4, 0, 0, 0, 0),
                           new DateTime(1932, 1, 4, 0, 0, 0, 0)));
        ret.add(new Course("Foobar Football Course",
                           new DateTime(1932, 1, 4, 0, 0, 0, 0),
                           new DateTime(1932, 1, 4, 0, 0, 0, 0)));
        return ret;
    }

    public static List<Course> fourInequalCourses() {
        List<Course> ret = new ArrayList<Course>();
        ret.add(new Course("Introduction to FORTRAN",
                           new DateTime(1964, 1, 4, 0, 0, 0, 0),
                           new DateTime(1964, 1, 4, 0, 0, 0, 0)));
        ret.add(new Course("Introduction to FORTRON",
                           new DateTime(1964, 1, 4, 0, 0, 0, 0),
                           new DateTime(1964, 1, 4, 0, 0, 0, 0)));
        ret.add(new Course("Introduction to FORTRAN",
                           new DateTime(1964, 1, 3, 0, 0, 0, 0),
                           new DateTime(1964, 1, 4, 0, 0, 0, 0)));
        ret.add(new Course("Introduction to FORTRAN",
                           new DateTime(1964, 1, 4, 0, 0, 0, 0),
                           new DateTime(1964, 1, 3, 0, 0, 0, 0)));
        return ret;
    }
}