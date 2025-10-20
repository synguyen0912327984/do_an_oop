package Java.project.team;

import java.util.*;

public class EmployeeList {
    private List<Employee> employees;
    private static final String FILE_NAME = "Employees.txt";

    public EmployeeList() {
        // Đọc file và chuyển nó thành đối tượng rồi đưa vào danh sách (Employee list);
    }

    public void addEmployee(Employee c) {
        employees.add(c);
    }

    public Employee findById(String id) {
        for (Employee c : employees) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public Employee findByPhone(String phone) {
        for (Employee c : employees) {
            if (c.getPhoneNumber().equalsIgnoreCase(phone)) {
                return c;
            }
        }
        return null;
    }

    public void displayAll() {
        for (Employee c : employees) {
            c.displayinfo();
        }
    }

    public void saveToFile() {
        // Ghi vào file theo dạng chuỗi dựa vào (toString)
    }
}