import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class EmployeeList {
    private ArrayList<Employee> employees;
    private static final String FILE_NAME = "Employees.txt";

    public EmployeeList() {
        // Doc file va chuyen no thanh doi tuong roi dua vao danh sach (Employee list);
        employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null)
                addEmployee(Employee.fromString(line));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        // Ghi vao file theo dang chuoi dau vao (toString)
    }
}
