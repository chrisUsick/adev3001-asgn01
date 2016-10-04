package ca.cu_dev;

/**
 * Employee - Contains data of an employee
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
public class Employee implements Comparable<Employee>{
    private int employeeID;
    private String firstName;
    private String lastName;

    /**
     * constructs a new employee object with only an employee id
     * @param employeeID
     */
    public Employee(int employeeID) {
        this.employeeID = employeeID;
    }
    public Employee(int employeeID, String firstName, String lastName) {
        this(employeeID);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * gets the employee id
     * @return the id
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * gets the first name
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * creates a string representation of an employee
     * @return string containing employee id, first name, and last name
     */
    @Override
    public String toString() {
        return String.format("%d %s %s", employeeID, firstName, lastName);
    }

    /**
     * Compares this employee to another employee by comparing employee ids
     * @param o the other employee to compare to
     * @return  1 if the other is less than self, 0 if equal and -1 if other is greater
     */
    @Override
    public int compareTo(Employee o) {
        int result = 0;

        if (o.getEmployeeID() < employeeID) {
            result = 1;
        } else if (o.getEmployeeID() > employeeID) {
            result = -1;
        }
        return result;
    }
}
