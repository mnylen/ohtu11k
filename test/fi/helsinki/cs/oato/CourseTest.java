package fi.helsinki.cs.oato;

import java.util.*;
import org.junit.Test;

import static fi.helsinki.cs.oato.CourseTestFixtures.*;

import static org.junit.Assert.*;

public class CourseTest {
    @Test
    public void testChronologicalOrdering() {
        assertFalse(threeConsecutiveCourses().get(0).equals(threeConsecutiveCourses().get(1)));
        assertFalse(threeConsecutiveCourses().get(0).equals(threeConsecutiveCourses().get(2)));
        assertFalse(threeConsecutiveCourses().get(1).equals(threeConsecutiveCourses().get(2)));
        assertFalse(threeConsecutiveCourses().get(1).equals(threeConsecutiveCourses().get(0)));
        assertFalse(threeConsecutiveCourses().get(2).equals(threeConsecutiveCourses().get(1)));
        assertFalse(threeConsecutiveCourses().get(2).equals(threeConsecutiveCourses().get(0)));

        Course[] ordered = threeConsecutiveCourses().toArray(new Course[0]);
        Course[] sortedCourses = Arrays.copyOf(ordered, ordered.length);
        Arrays.sort(sortedCourses);
        assertArrayEquals(ordered, sortedCourses);

        assertTrue(threeConsecutiveCourses().get(0).compareTo(threeConsecutiveCourses().get(1)) < 0);
        assertTrue(threeConsecutiveCourses().get(1).compareTo(threeConsecutiveCourses().get(2)) < 0);
        assertTrue(threeConsecutiveCourses().get(0).compareTo(threeConsecutiveCourses().get(2)) < 0);
        assertTrue(threeConsecutiveCourses().get(2).compareTo(threeConsecutiveCourses().get(1)) > 0);
        assertTrue(threeConsecutiveCourses().get(1).compareTo(threeConsecutiveCourses().get(0)) > 0);
        assertTrue(threeConsecutiveCourses().get(2).compareTo(threeConsecutiveCourses().get(0)) > 0);
        
        assertTrue(threeConsecutiveCourses().get(0).compareTo(threeConsecutiveCourses().get(0)) == 0);
        assertTrue(threeConsecutiveCourses().get(1).compareTo(threeConsecutiveCourses().get(1)) == 0);
        assertTrue(threeConsecutiveCourses().get(2).compareTo(threeConsecutiveCourses().get(2)) == 0);
    }

    @Test
    public void testEqualityOperator() {
        assertTrue(twoEqualCourses().get(0).equals(twoEqualCourses().get(1)));
        assertTrue(twoEqualCourses().get(1).equals(twoEqualCourses().get(0)));
        assertTrue(twoEqualCourses().get(1).equals(twoEqualCourses().get(1)));
        assertTrue(twoEqualCourses().get(0).equals(twoEqualCourses().get(0)));
    }

    @Test
    public void testInequalityOperator() {
        assertTrue(fourInequalCourses().get(0).equals(fourInequalCourses().get(0)));
        assertFalse(fourInequalCourses().get(0).equals(fourInequalCourses().get(1)));
        assertFalse(fourInequalCourses().get(0).equals(fourInequalCourses().get(2)));
        assertFalse(fourInequalCourses().get(0).equals(fourInequalCourses().get(3)));
        
        assertFalse(fourInequalCourses().get(1).equals(fourInequalCourses().get(0)));
        assertTrue(fourInequalCourses().get(1).equals(fourInequalCourses().get(1)));
        assertFalse(fourInequalCourses().get(1).equals(fourInequalCourses().get(2)));
        assertFalse(fourInequalCourses().get(1).equals(fourInequalCourses().get(3)));

        assertFalse(fourInequalCourses().get(2).equals(fourInequalCourses().get(0)));
        assertFalse(fourInequalCourses().get(2).equals(fourInequalCourses().get(1)));
        assertTrue(fourInequalCourses().get(2).equals(fourInequalCourses().get(2)));
        assertFalse(fourInequalCourses().get(2).equals(fourInequalCourses().get(3)));
        
        assertFalse(fourInequalCourses().get(3).equals(fourInequalCourses().get(0)));
        assertFalse(fourInequalCourses().get(3).equals(fourInequalCourses().get(1)));
        assertFalse(fourInequalCourses().get(3).equals(fourInequalCourses().get(2)));
        assertTrue(fourInequalCourses().get(3).equals(fourInequalCourses().get(3)));
    }
}