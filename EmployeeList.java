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
    public void edit(Employee a, Scanner sc) {
        System.out.println("EDIT:");
        System.out.print("1. Name\n2. Phone\n3. Address\nEnter: ");
        int keys = Menu2.readIntInput();
        boolean valid;
        switch (keys) {
            case 1: // Name
                do{
                    System.out.print("Enter new name: ");
                    String Name_test;
                    Name_test = sc.nextLine();
                    valid = !Name_test.isEmpty();
                    if(valid)
                        a.setName(Name_test);
                    else
                        System.out.println("Name cannot be empty. Try again.");
                }while(!valid);
                System.out.println("Changed successfully!");
                break;
            case 2: // Phone
                do{
                    System.out.print("Enter new phone number: ");
                    String Phone_test;
                    Phone_test = sc.nextLine();
                    valid = Person.isValidPhoneNumber(Phone_test);
                    if (valid) {
                        a.setPhoneNumber(Phone_test);
                    } else {
                        System.out.println("Invalid phone number");
                        System.out.println("Please re-enter or press Enter to skip");
                    }
                }while(!valid);
                System.out.println("Changed successfully!");
                break;
            case 3: // Address
                do{
                    System.out.print("Enter new address: ");
                    String Address_test;
                    Address_test = sc.nextLine();
                    valid = !Address_test.isEmpty();
                    if(valid)
                        a.setAddress(Address_test);
                    else
                        System.out.println("Title cannot be empty. Try again.");
                }while(!valid);
                System.out.println("Changed successfully!");
                break;
            default:
                System.out.println("Cancelled.");
                break;
        }
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
