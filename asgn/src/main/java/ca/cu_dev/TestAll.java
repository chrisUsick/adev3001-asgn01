package ca.cu_dev;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chris on 03-Oct-16.
 */
public class TestAll {
    public static void main(String[] args) throws Exception {
        ArrayList<TestSuite> suites = new ArrayList<TestSuite>(Arrays.asList(
                new EmployeeTest(),
                new LinkedListTest(),
                new NodeTest()));
        for (TestSuite suite: suites) {
            suite.run();
        }
    }
}
