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

    public ArrayList<Employee> getActiveCashiers() {
        ArrayList<Employee> activeCashiers = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPosition().equalsIgnoreCase("Cashier") && emp.isActive()) {
                activeCashiers.add(emp);
            }
        }
        return activeCashiers;
    }

    @Override
    public void add(Employee c) {
        employees.add(c);
    }

    @Override
    public void find(int keys, String keyword) {
        switch (keys) {
            case 1:
                Employee empById = findById(keyword);
                if (empById == null) {
                    System.out.println("Cannot find valid employee");
                    break;
                }
                empById.displayinfo();
                break;
            case 2:
                Employee empByPhone = findByPhone(keyword);
                if (empByPhone == null) {
                    System.out.println("Cannot find valid employee");
                    break;
                }
                empByPhone.displayinfo();
                break;
            case 3:
                ArrayList<Employee> results = findAllByName(keyword);
                if (results.isEmpty()) {
                    System.out.println("Cannot find valid employee");
                    break;
                } else {
                    int i = 1;
                    for (Employee c : results) {
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
        if (del != null) {
            del.setActive(false);
            System.out.println("Employee " + id + " has been successfully deactivated.");
        } else {
            System.out.println("Error: Cannot find employee with ID " + id + " to remove.");
        }
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
            Employee checkForEmployee = findByPhone(newPhone);
            if (checkForEmployee != null) {
                System.out.println(
                        "Phone number already exist.Please enter another phone number or press Enter to skip.");
                continue;
            }
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

        int newPos;
        do {
            System.out.println("New position (" + employeeToEdit.getPosition() + "): ");
            System.out.println("1.Cashier \n2.Manager \n3.Salesman \n4.Accountant");
            newPos = Menu.readIntInput();
        } while (newPos == -1);
        String newPosition = "";
        switch (newPos) {
            case 1:
                newPosition = "Cashier";
                employeeToEdit.setPosition(newPosition);
                break;
            case 2:
                newPosition = "Manager";
                employeeToEdit.setPosition(newPosition);
                break;
            case 3:
                newPosition = "Salesman";
                employeeToEdit.setPosition(newPosition);
                break;
            case 4:
                newPosition = "Accountant";
                employeeToEdit.setPosition(newPosition);
                break;
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
    public void edit(Employee e, Scanner sc) {
        e.displayinfo();
        int value;
        do {
            System.out.println("1.Name \n2.Phone \n3.address \n0.Exit: ");
            System.out.println("Enter: ");
            value = Menu.readIntInput();
            if (value == 0) {
                System.out.println("Exiting...");
                return;
            }
            if (value > 3 || value < 1) {
                value = -1;
                System.out.println("Please choose an option from 1 to 3.");
            }
        } while (value == -1);
        switch (value) {
            case 1:
                System.out.print("New name (" + e.getName() + "): ");
                String newName = sc.nextLine();
                if (!newName.isEmpty()) {
                    e.setName(newName);
                }
                break;
            case 2:
                while (true) {
                    System.out.print("New phone number (" + e.getPhoneNumber() + "): ");
                    String newPhone = sc.nextLine();
                    Employee checkForEmployee = findByPhone(newPhone);
                    if (checkForEmployee != null) {
                        System.out.println(
                                "Phone number already exist.Please enter another phone number or press Enter to skip.");
                        continue;
                    }
                    if (newPhone.isEmpty()) {
                        break;
                    }

                    if (Person.isValidPhoneNumber(newPhone)) {
                        e.setPhoneNumber(newPhone);
                        break;
                    } else {
                        System.out.println("Invalid phone number");
                        System.out.println("Please re-enter or press Enter to skip");
                    }
                }
                break;
            case 3:
                System.out.print("New address (" + e.getAddress() + "): ");
                String newAddress = sc.nextLine();
                if (!newAddress.isEmpty()) {
                    e.setAddress(newAddress);
                }
                break;
        }

        System.out.println("---Employee information updated successfully!---");
        e.displayinfo();
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
