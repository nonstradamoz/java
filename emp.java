import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Employee { // define base class
    String name; // instance variables(outside  a method inside a class)
    String id;
    double salary;

    Employee(String name, String id, double salary) { // employee constructor, creating instance of the employee class
        this.name = name; // assigns the value of name parameter to name instance variable of object created
        this.id = id;
        this.salary = salary;
    }

    String getName() { //allows you to retrieve the value of the name attribute from an Employee object. 
        return name;
    }

    String getId() { // retrieve the value of the id attribute of an Employee object.
        return id;
    }

    double getSalary() { //returns the value of the salary attribute of an Employee object.
        return salary;
    }

    void displayDetails() {   //method
        System.out.println("Name: " + name); // print out the details
        System.out.println("ID: " + id);
        System.out.println("Salary: $" + salary);
    }
}

class Manager extends Employee { // child class, inheritance Manager inherits the attributes and methods of Employee and can also have its own attributes and methods.
    String department; // declares an instance variable which will store the information.

    Manager(String name, String id, double salary, String department) { // constructor of manager class
        super(name, id, salary); // intializes the inherited attributes
        this.department = department; // intializes the attribute 
    }

    String getDepartment() { // returns the department value to that manager.
        return department;
    }
                          // run time polymorphism, method overriding
    void displayDetails() { // overridden to provide additional information about the manager.
        super.displayDetails(); // calls the display details of the main class, it first prints the basic employee details inherited from the employee class
        System.out.println("Department: " + department);
    }
}

class Developer extends Employee { // inheritance
    String programmingLanguage; // declares an instance variable, which stores the info

    Developer(String name, String id, double salary, String programmingLanguage) { // constructor of developer class
        super(name, id, salary); // intializes the inherited attributes from main class using super
        this.programmingLanguage = programmingLanguage; // intilizes attribute
    }

    String getProgrammingLanguage() {
        return programmingLanguage; // returns the value
    }

    void displayDetails() {
        super.displayDetails(); // diplay the details of the super class
        System.out.println("Programming Language: " + programmingLanguage);
    }
}


public class emp { // main class
    private static List<Employee> employees = new ArrayList<>(); // uses array list to store instances of the employee class,  static keyword means that this field is shared among all instances of the class.
    private static Scanner scanner = new Scanner(System.in); // reading user input

    public static void main(String[] args) { // main method
        try { // try for exception handling
            int choice;
            do { // do-while loop begins
                System.out.println("\nEmployee Management System Menu:");
                System.out.println("1. Add Employee");
                System.out.println("2. Delete Employee");
                System.out.println("3. Edit Employee");
                System.out.println("4. View Employees");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        deleteEmployee();
                        break;
                    case 3:
                        editEmployee();
                        break;
                    case 4:
                        viewEmployees();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5); // end otherwise the loop continues until the user select case 5
        } catch (Exception e) { // end of try block , catches any error that may occur during execution
            System.out.println("An error occurred: " + e.getMessage());
        } finally {   //closing the scanner object
            scanner.close();
        }
    }

    private static void addEmployee() {
        System.out.print("Enter employee type (1 for Manager, 2 for Developer): ");
        int employeeType = scanner.nextInt();// enter the type of employee and reads the input
        scanner.nextLine(); // to ensure every line is read

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        if (employeeType == 1) { //if true the user wants to add a manager
            System.out.print("Enter manager department: ");
            String department = scanner.nextLine();
            Manager manager = new Manager(name, id, salary, department); // creates an object 
            employees.add(manager); // add newly created object
            System.out.println("\nManager added successfully.");
        } else if (employeeType == 2) { // if true add developer
            System.out.print("Enter programming language: ");
            String programmingLanguage = scanner.nextLine();
            Developer developer = new Developer(name, id, salary, programmingLanguage);// creating an object
            employees.add(developer);// adds newly created object to employees list
            System.out.println("\nDeveloper added successfully.");
        } else {
            System.out.println("\nInvalid employee type.");
        }
    }

    //In summary, this code block is a part of the addEmployee() method and is responsible for adding either a Manager or a
    // Developer to the employees list based on the user's input for the employeeType. It prompts the user for relevant information and creates the appropriate object, 
    //adding it to the list. If the entered employeeType is neither 1 nor 2, it prints an error message indicating an invalid input.
    
    
    private static void deleteEmployee() { // declares a private static method 
        System.out.print("Enter the ID of the employee to delete: ");
        String id = scanner.nextLine();
        Employee employeeToDelete = null; // declares an object and intializes it with null.
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) { // checks whether the id of employee matches the provided id for deletion
                employeeToDelete = employee; // if found the matching employee is assigned to varaible and then the loop exits using break.
                break;
            }
        }
        if (employeeToDelete != null) { // if matching employee was found, id exists .
            employees.remove(employeeToDelete); //then removed using remove() method
            System.out.println("\nEmployee deleted successfully.");
        } else {
            System.out.println("\nEmployee with ID " + id + " not found."); // if id doesnt exist error msg will be shown
        }
    }

    //In summary, the deleteEmployee() method prompts the user for an employee ID to delete, 
    //searches the list of employees for a matching ID, removes the corresponding employee if found, 
    //and provides appropriate feedback to the us


    private static void editEmployee() {
        System.out.print("Enter the ID of the employee to edit: ");
        String id = scanner.nextLine(); // object created 
        Employee employeeToEdit = null;//and intilaized with null
        for (Employee employee : employees) { // checks the id match
            if (employee.getId().equals(id)) { //if found 
                employeeToEdit = employee; // the matchong employee is assigned to a variable
                break; // the loop is immediately exited 
            }
        }
        if (employeeToEdit != null) { // matching employee found the block of code will execute
            System.out.print("Enter new employee name: ");
            String newName = scanner.nextLine(); // enter a new name and read input as string
            System.out.print("Enter new employee salary: ");
            double newSalary = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            if (employeeToEdit instanceof Developer) { // this checks whether employeeToEdit(the reference variable that refers to obj of employee class) is an instance of the developer class
                System.out.print("Enter new programming language: ");
                String newLanguage = scanner.nextLine();
                ((Developer) employeeToEdit).programmingLanguage = newLanguage;
            }

            employeeToEdit.name = newName; // updates new name and salary
            employeeToEdit.salary = newSalary;

            System.out.println("\nEmployee details updated successfully.");
        } else {
            System.out.println("\nEmployee with ID " + id + " not found."); // no matching employee found
        }
    }

    //In summary, the editEmployee() method prompts the user for an employee ID to edit, 
    //searches the list of employees for a matching ID, allows the user to update the name and 
    //salary of the employee, and optionally updates the programming language for developers. 
    //It provides appropriate feedback to the user based on the outcome of the editing process.


    private static void viewEmployees() {
        System.out.println("\nEmployee List:"); // output with list of employees
        for (Employee employee : employees) {
            employee.displayDetails(); // printing the details
            System.out.println();
        }
    }
}
//In summary, the viewEmployees() method prints a list of all employees in the employees list. 
//It iterates through the list, calls the displayDetails() method for each employee, and adds
// an empty line to separate the details of different employees.

//methods like addEmployee(), deleteEmployee(), editEmployee(), viewEmployees(), and main() are all declared as private static. 
//This is because they are utility methods that are only intended to be used within the emp class itself.