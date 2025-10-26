import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EmployeeList implements IActions<Employee> {
    private ArrayList<Employee> employees;
    private static final String FILE_NAME = "Employees.txt";

    public EmployeeList() {
        // Doc file va chuyen no thanh doi tuong roi dua vao danh sach (Employee list);
        employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null)
                add(Employee.fromString(line));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int maxId = 0;
        for (Employee emp : employees) {
            try {
                int idNum = Integer.parseInt(emp.getId().substring(1));
                maxId = (maxId > idNum) ? maxId : idNum;
            } catch (NumberFormatException e) {
                // Khong dung dinh dang thi bo qua
            }
        }
        Employee.setEmloyeeCount(maxId + 1);
    }

    @Override
    public void add(Employee c) {
        employees.add(c);
    }

    @Override
    public void find(int keys, String keyword) {
        /*
         * System.out.println("1. ID");
         * System.out.println("2. Phone");
         * System.out.println("3. Name");
         * System.out.print("Enter search method: ");
         */
        switch (keys) {
            case 1:
                if (findById(keyword) == null) {
                    System.out.println("Cannot find valid employee");
                    break;
                }
                findById(keyword).displayinfo();
                break;
            case 2:
                if (findByPhone(keyword) == null) {
                    System.out.println("Cannot find valid employee");
                    break;
                }
                findByPhone(keyword).displayinfo();
                break;
            case 3:
                if (findAllByName(keyword) == null) {
                    System.out.println("Cannot find valid employee");
                    break;
                } else {
                    int i = 1;
                    for (Employee c : findAllByName(keyword)) {
                        System.out.println(i + ".");
                        c.displayinfo();
                        i++;
                    }
                }

        }
    }

    public Employee findById(String id) {
        for (Employee e : employees) {
            if (e.getId().equalsIgnoreCase(id) && e.isActive()) {
                return e;
            }
        }
        return null;
    }

    public Employee findByPhone(String phone) {
        for (Employee e : employees) {
            if (e.getPhoneNumber().equalsIgnoreCase(phone) && e.isActive()) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Employee> findAllByName(String name) {
        ArrayList<Employee> results = new ArrayList<>();
        String lowerCaseName = name.toLowerCase();
        for (Employee e : employees) {
            if (e.getName().toLowerCase().contains(lowerCaseName) && e.isActive()) {
                results.add(e);
            }
        }
        return results;
    }

    @Override
    public void removeById(String id) {
        Employee del = findById(id);
        del.setActive(false);
    }

    public void editEmployee(String id, Scanner scanner) {
        Employee employeeToEdit = this.findById(id);

        if (employeeToEdit == null) {
            System.out.println("Employee not found with id: " + id);
            return;
        }

        System.out.println("Edit employee information (press Enter to skip)");
        employeeToEdit.displayinfo();

        System.out.print("New name (" + employeeToEdit.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            employeeToEdit.setName(newName);
        }

        while (true) {
            System.out.print("New phone number (" + employeeToEdit.getPhoneNumber() + "): ");
            String newPhone = scanner.nextLine();

            if (newPhone.isEmpty()) {
                break;
            }

            if (Person.isValidPhoneNumber(newPhone)) {
                employeeToEdit.setPhoneNumber(newPhone);
                break;
            } else {
                System.out.println("Invalid phone number");
                System.out.println("Please re-enter or press Enter to skip");
            }
        }

        System.out.print("New address (" + employeeToEdit.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            employeeToEdit.setAddress(newAddress);
        }

        System.out.print("New position (" + employeeToEdit.getPosition() + "): ");
        String newPosition = scanner.nextLine();
        if (!newPosition.isEmpty()) {
            employeeToEdit.setPosition(newPosition);
        }

        System.out.print("New salary (" + employeeToEdit.getSalary() + "): ");
        String newSalaryStr = scanner.nextLine();
        if (!newSalaryStr.isEmpty()) {
            try {
                double newSalary = Double.parseDouble(newSalaryStr);
                employeeToEdit.setSalary(newSalary);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Salary was not changed");
            }
        }

        System.out.println("---Employee information updated successfully!---");
        employeeToEdit.displayinfo(); // Hiển thị thông tin mới
    }

    public void displayAll() {
        for (Employee e : employees) {
            if (e.isActive())
                e.displayinfo();
        }
    }

    @Override
    public void edit(Employee a) {
        Scanner sc = new Scanner(System.in);
        System.out.println("EDIT:");
        System.out.print("1. Name\n2. Phone\n3. Address\n4. Active\n 5. Position\n 6. Salary\nEnter: ");
        int keys = Integer.parseInt(sc.nextLine());
        String keyword;
        switch (keys) {
            case 1: // Name
                System.out.print("Enter new name: ");
                keyword = sc.nextLine();
                a.setName(keyword);
                System.out.println("Changed successfully!");
                break;
            case 2: // Phone
                System.out.print("Enter new phone number: ");
                keyword = sc.nextLine();
                if (!Person.isValidPhoneNumber(keyword)) {
                    System.out.println("Invalid phone number!\n");
                } else {
                    a.setPhoneNumber(keyword);
                    System.out.println("Changed successfully!");
                    break;
                }
            case 3: // Address
                System.out.print("Enter new address: ");
                keyword = sc.nextLine();
                a.setAddress(keyword);
                System.out.println("Changed successfully!");
                break;
            case 4: // Active
                System.out.println("Active (true/false): ");
                keyword = sc.nextLine();
                a.setActive(Boolean.parseBoolean(keyword));
                System.out.println("Changed successfully!");
                break;
            case 5: // Position
                System.out.print("1. Cashier\n2. Salesman\n3. Accountant\n4. Manager\nPosition: ");
                int c = sc.nextInt();
                if (c == 1)
                    keyword = "Cashier";
                else if (c == 2)
                    keyword = "Salesman";
                else if (c == 3)
                    keyword = "Accountant";
                else
                    keyword = "Manager";
                a.setPosition(keyword);
                System.out.println("Changed successfully!");
                break;
            case 6: // Salary
                System.out.print("Enter new salary: ");
                keyword = sc.nextLine();
                a.setSalary(Double.parseDouble(keyword));
                System.out.println("Changed successfully!");
                break;
            default:
                System.out.println("Cancelled.");
                break;
        }
        sc.close();
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee obj : employees) {
                bw.write(obj.toString());
                bw.newLine();
            }
            System.out.println("File saved successfully: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("file save failed: " + FILE_NAME);
        }
    }
}
