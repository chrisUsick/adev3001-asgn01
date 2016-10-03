package ca.cu_dev;

/**
 * Created by chris on 03-Oct-16.
 */
public class EmployeeTest extends TestSuite {
    public EmployeeTest() {
        super(new Logger());
    }

    public static void main(String[] args) throws Exception {
        TestSuite suite = new EmployeeTest();
        suite.run();
    }

    @Test
    public void constructor() {
        it("initializes with an employee id", () -> {
            Employee e = new Employee(1);
            assertTrue(e.getEmployeeID() == 1);
            assertTrue(e.getFirstName() == null);
            assertTrue(e.getLastName() == null);
        });

        it("intializes will all values", () -> {
            Employee e = new Employee(1, "Bob", "Foo");
            assertTrue(e.getEmployeeID() == 1);
            assertTrue(e.getFirstName().equals("Bob"));
            assertTrue(e.getLastName().equals("Foo"));
        });
    }

    @Test
    public void properties() {
        it("gets id", () -> {
            Employee e = new Employee(1);
            assertTrue(e.getEmployeeID() == 1);
        });

        it("gets firstName", () -> {
            String fname = "fname";
            Employee e = new Employee(1, fname, null);
            assertTrue(e.getFirstName() == fname);
        });
        it("gets lastName", () -> {
            String lname = "lname";
            Employee e = new Employee(1, null, lname);
            assertTrue(e.getLastName() == lname);
        });
    }

    @Test
    public void compareTo() {
        it("compares 2 employees", () -> {
            Employee e1 = new Employee(1), e2 = new Employee(2);
            assertTrue(e1.compareTo(e2) > 0);
            assertTrue(e2.compareTo(e1) < 0);
            assertTrue(e1.compareTo(e1) == 0);
        });
    }
}
