package fi.helsinki.cs.oato.model;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {
    @Test
    public void testChronologicalOrdering() {
        assertFalse(CourseTestFixtures.threeConsecutiveCourses().get(0).equals(CourseTestFixtures.threeConsecutiveCourses().get(1)));
        assertFalse(CourseTestFixtures.threeConsecutiveCourses().get(0).equals(CourseTestFixtures.threeConsecutiveCourses().get(2)));
        assertFalse(CourseTestFixtures.threeConsecutiveCourses().get(1).equals(CourseTestFixtures.threeConsecutiveCourses().get(2)));
        assertFalse(CourseTestFixtures.threeConsecutiveCourses().get(1).equals(CourseTestFixtures.threeConsecutiveCourses().get(0)));
        assertFalse(CourseTestFixtures.threeConsecutiveCourses().get(2).equals(CourseTestFixtures.threeConsecutiveCourses().get(1)));
        assertFalse(CourseTestFixtures.threeConsecutiveCourses().get(2).equals(CourseTestFixtures.threeConsecutiveCourses().get(0)));

        Course[] ordered = CourseTestFixtures.threeConsecutiveCourses().toArray(new Course[0]);
        Course[] sortedCourses = Arrays.copyOf(ordered, ordered.length);
        Arrays.sort(sortedCourses);
        assertArrayEquals(ordered, sortedCourses);

        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(0).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(1)) < 0);
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(1).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(2)) < 0);
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(0).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(2)) < 0);
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(2).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(1)) > 0);
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(1).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(0)) > 0);
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(2).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(0)) > 0);
        
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(0).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(0)) == 0);
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(1).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(1)) == 0);
        assertTrue(CourseTestFixtures.threeConsecutiveCourses().get(2).compareTo(CourseTestFixtures.threeConsecutiveCourses().get(2)) == 0);
    }

    @Test
    public void testEqualityOperator() {
        assertTrue(CourseTestFixtures.twoEqualCourses().get(0).equals(CourseTestFixtures.twoEqualCourses().get(1)));
        assertTrue(CourseTestFixtures.twoEqualCourses().get(1).equals(CourseTestFixtures.twoEqualCourses().get(0)));
        assertTrue(CourseTestFixtures.twoEqualCourses().get(1).equals(CourseTestFixtures.twoEqualCourses().get(1)));
        assertTrue(CourseTestFixtures.twoEqualCourses().get(0).equals(CourseTestFixtures.twoEqualCourses().get(0)));
    }

    @Test
    public void testInequalityOperator() {
        assertTrue(CourseTestFixtures.fourInequalCourses().get(0).equals(CourseTestFixtures.fourInequalCourses().get(0)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(0).equals(CourseTestFixtures.fourInequalCourses().get(1)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(0).equals(CourseTestFixtures.fourInequalCourses().get(2)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(0).equals(CourseTestFixtures.fourInequalCourses().get(3)));
        
        assertFalse(CourseTestFixtures.fourInequalCourses().get(1).equals(CourseTestFixtures.fourInequalCourses().get(0)));
        assertTrue(CourseTestFixtures.fourInequalCourses().get(1).equals(CourseTestFixtures.fourInequalCourses().get(1)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(1).equals(CourseTestFixtures.fourInequalCourses().get(2)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(1).equals(CourseTestFixtures.fourInequalCourses().get(3)));

        assertFalse(CourseTestFixtures.fourInequalCourses().get(2).equals(CourseTestFixtures.fourInequalCourses().get(0)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(2).equals(CourseTestFixtures.fourInequalCourses().get(1)));
        assertTrue(CourseTestFixtures.fourInequalCourses().get(2).equals(CourseTestFixtures.fourInequalCourses().get(2)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(2).equals(CourseTestFixtures.fourInequalCourses().get(3)));
        
        assertFalse(CourseTestFixtures.fourInequalCourses().get(3).equals(CourseTestFixtures.fourInequalCourses().get(0)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(3).equals(CourseTestFixtures.fourInequalCourses().get(1)));
        assertFalse(CourseTestFixtures.fourInequalCourses().get(3).equals(CourseTestFixtures.fourInequalCourses().get(2)));
        assertTrue(CourseTestFixtures.fourInequalCourses().get(3).equals(CourseTestFixtures.fourInequalCourses().get(3)));
    }
}