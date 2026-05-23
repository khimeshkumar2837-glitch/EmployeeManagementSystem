import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class Employee implements Serializable {
    private String id;
    private String name;
    private String department;
    private String position;
    private double salary;
    private Date joinDate;

    public Employee(String id, String name, String department, String position, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.joinDate = new Date();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public Date getJoinDate() { return joinDate; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "ID: " + id + ", Name: " + name + ", Department: " + department +
               ", Position: " + position + ", Salary: ₹" + salary +
               ", Joined: " + sdf.format(joinDate);
    }
}

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees = new ArrayList<>();
    private HashMap<String, Employee> employeeMap = new HashMap<>();
    private static final String DATA_FILE = "employees.dat";
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem();
        system.run();
    }

    private void run() {
        while (true) {
            System.out.println("\\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addEmployee() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();

        System.out.print("Enter Position: ");
        String pos = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        Employee emp = new Employee(id, name, dept, pos, salary);
        employees.add(emp);
        employeeMap.put(id, emp);

        saveEmployees();
        System.out.println("Employee Added Successfully!");
    }

    private void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No Employees Found!");
            return;
        }

        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    private void saveEmployees() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }
}
