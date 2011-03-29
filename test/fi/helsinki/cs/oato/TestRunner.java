package fi.helsinki.cs.oato;

import org.junit.runner.JUnitCore;

public class TestRunner {
    static final String[] tests = { "CsvScheduleReaderTest", "CsvScheduleWriterTest", "EventTest",
                                    "EventIteratorTest", "ScheduleTest" };
    
    public static void main(String args[]) {
        String pckg = TestRunner.class.getPackage().getName();

        String testPaths[] = new String[tests.length];
        for (int i=0; i<tests.length; i++) {
            testPaths[i] = pckg.concat(".").concat(tests[i]);
        }

        JUnitCore core = new JUnitCore();
        core.main(testPaths);
    }
}