package ca.cu_dev;

/**
 * Created by chris on 03-Oct-16.
 */
public class Employee implements Comparable<Employee>{
    private int employeeID;
    private String firstName;
    private String lastName;

    public Employee(int employeeID) {
        this.employeeID = employeeID;
    }
    public Employee(int employeeID, String firstName, String lastName) {
        this(employeeID);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", employeeID, firstName, lastName);
    }

    @Override
    public int compareTo(Employee o) {
        int result = 0;

        if (o.getEmployeeID() > employeeID) {
            result = 1;
        } else if (o.getEmployeeID() < employeeID) {
            result = -1;
        }
        return result;
    }
}
