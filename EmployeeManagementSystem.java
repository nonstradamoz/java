import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class EmployeeManagementSystem extends JFrame {
    private ArrayList<EmployeeData> employees; // List to store employee data

    EmployeeManagementSystem() {
        ImageIcon image1 = new ImageIcon("icon1.jpg");
        this.setIconImage(image1.getImage());

        this.setTitle("Employee Management System");
        this.getContentPane().setBackground(Color.white);
        this.setLayout(null);

        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(80, 30, 1000, 60);
        heading.setFont(new Font("sans serif", Font.PLAIN, 40));
        heading.setForeground(Color.BLACK);
        this.add(heading);

        JButton clickhere = new JButton("Click here to view employees");
        clickhere.setBounds(400, 400, 300, 70);
        clickhere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display employee details in a new frame
                displayEmployeeDetails();
            }
        });
        this.add(clickhere);

        // Sample employee data
        employees = new ArrayList<>();
        employees.add(new EmployeeData("John Doe", 30, "Software Engineer", 50000));
        employees.add(new EmployeeData("Jane Smith", 28, "Data Analyst", 45000));

        this.setSize(1170, 650);
        this.setLocation(200, 50);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void displayEmployeeDetails() {
        JFrame detailsFrame = new EmployeeDetailsFrame(employees);
        detailsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeManagementSystem();
    }

    // EmployeeData class to represent employee information
    static class EmployeeData {
        private String name;
        private int age;
        private String position;
        private double salary;

        public EmployeeData(String name, int age, String position, double salary) {
            this.name = name;
            this.age = age;
            this.position = position;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getPosition() {
            return position;
        }

        public double getSalary() {
            return salary;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
}

class EmployeeDetailsFrame extends JFrame {
    private ArrayList<EmployeeManagementSystem.EmployeeData> employees;
    private JTable employeeTable;

    EmployeeDetailsFrame(ArrayList<EmployeeManagementSystem.EmployeeData> employees) {
        this.employees = employees;

        this.setTitle("Employee Details");
        this.setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Age", "Position", "Salary"};
        Object[][] data = new Object[employees.size()][4];

        for (int i = 0; i < employees.size(); i++) {
            EmployeeManagementSystem.EmployeeData employee = employees.get(i);
            data[i][0] = employee.getName();
            data[i][1] = employee.getAge();
            data[i][2] = employee.getPosition();
            data[i][3] = employee.getSalary();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        employeeTable = new JTable(model);
        employeeTable.setFont(new Font("sans serif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> addEmployee());
        buttonPanel.add(addButton);

        JButton editButton = new JButton("Edit Employee");
        editButton.addActionListener(e -> editEmployee());
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(e -> deleteEmployee());
        buttonPanel.add(deleteButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
    }

    private void refreshEmployeeDetails() {
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        model.setRowCount(0);

        for (EmployeeManagementSystem.EmployeeData employee : employees) {
            model.addRow(new Object[]{employee.getName(), employee.getAge(), employee.getPosition(), employee.getSalary()});
        }
    }

    private void addEmployee() {
        String name = JOptionPane.showInputDialog(this, "Enter Employee Name:");
        if (name != null && !name.isEmpty()) {
            String ageStr = JOptionPane.showInputDialog(this, "Enter Employee Age:");
            if (ageStr != null && !ageStr.isEmpty()) {
                int age = Integer.parseInt(ageStr);

                String position = JOptionPane.showInputDialog(this, "Enter Employee Position:");

                String salaryStr = JOptionPane.showInputDialog(this, "Enter Employee Salary:");
                if (salaryStr != null && !salaryStr.isEmpty()) {
                    double salary = Double.parseDouble(salaryStr);

                    employees.add(new EmployeeManagementSystem.EmployeeData(name, age, position, salary));
                    refreshEmployeeDetails();
                }
            }
        }
    }

    private void editEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            EmployeeManagementSystem.EmployeeData selectedEmployee = employees.get(selectedRow);
            String name = JOptionPane.showInputDialog(this, "Enter Employee Name:", selectedEmployee.getName());
            if (name != null && !name.isEmpty()) {
                String ageStr = JOptionPane.showInputDialog(this, "Enter Employee Age:", selectedEmployee.getAge());
                if (ageStr != null && !ageStr.isEmpty()) {
                    int age = Integer.parseInt(ageStr);

                    String position = JOptionPane.showInputDialog(this, "Enter Employee Position:", selectedEmployee.getPosition());

                    String salaryStr = JOptionPane.showInputDialog(this, "Enter Employee Salary:", selectedEmployee.getSalary());
                    if (salaryStr != null && !salaryStr.isEmpty()) {
                        double salary = Double.parseDouble(salaryStr);

                        selectedEmployee.setName(name);
                        selectedEmployee.setAge(age);
                        selectedEmployee.setPosition(position);
                        selectedEmployee.setSalary(salary);
                        refreshEmployeeDetails();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit.", "Edit Employee", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                employees.remove(selectedRow);
                refreshEmployeeDetails();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.", "Delete Employee", JOptionPane.WARNING_MESSAGE);
        }
    }
}
