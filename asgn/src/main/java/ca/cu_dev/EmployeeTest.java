package ca.cu_dev;

/**
 * EmployeeTest - Test class for Employee. Includes a main method so it can be run standalone or
 * called programmatically
 *
 * <pre>
 *
 * Assignment: #1
 * Course: ADEV-3001
 * Date Created: October 03, 2016
 *
 * Revision Log
 * Who          When    Reason
 * --------- ---------- ----------------------------------
 *
 * </pre>
 *
 * @author Chris Usick
 * @version 1.0
 *
 */
public class EmployeeTest extends TestSuite {
    /**
     * default constructor. sets the name and logger
     */
    public EmployeeTest() {
        super(new Logger());
        setName("Employees");
    }

    /**
     * Execute this test
     * @param args          command line arguments
     * @throws Exception    If test execution throws an exception the application exists
     */
    public static void main(String[] args) throws Exception {
        TestSuite suite = new EmployeeTest();
        suite.run();
    }

    /**
     * test the constructor
     */
    @Test
    public void testConstructor() {
        runTest("initializes with an employee id", () -> {
            Employee employee = new Employee(1);
            assertTrue(employee.getEmployeeID() == 1);
            assertTrue(employee.getFirstName() == null);
            assertTrue(employee.getLastName() == null);
        });

        runTest("intializes will all values", () -> {
            Employee employee = new Employee(1, "Bob", "Foo");
            assertTrue(employee.getEmployeeID() == 1);
            assertTrue(employee.getFirstName().equals("Bob"));
            assertTrue(employee.getLastName().equals("Foo"));
        });
    }

    /**
     * test getting and setting properties of the employee object
     */
    @Test
    public void testProperties() {
        runTest("gets id", () -> {
            Employee employee = new Employee(1);
            assertTrue(employee.getEmployeeID() == 1);
        });

        runTest("gets firstName", () -> {
            String fname = "fname";
            Employee employee = new Employee(1, fname, null);
            assertTrue(employee.getFirstName() == fname);
        });
        runTest("gets lastName", () -> {
            String lname = "lname";
            Employee employee = new Employee(1, null, lname);
            assertTrue(employee.getLastName() == lname);
        });
    }

    /**
     * tests comparing employees
     */
    @Test
    public void compareTo() {
        runTest("compares 2 employees", () -> {
            Employee employee1 = new Employee(1), employee2 = new Employee(2);
            assertTrue(employee1.compareTo(employee2) < 0);
            assertTrue(employee2.compareTo(employee1) > 0);
            assertTrue(employee1.compareTo(employee1) == 0);
        });
    }

    /**
     * tests converting to string
     */
    @Test
    public void testToString() {
        runTest("formats the string representation", () -> {
            Employee employee = new Employee(123, "Bob", "Tom");
            assertTrue("123 Bob Tom".equals(employee.toString()));
        });
    }
}
