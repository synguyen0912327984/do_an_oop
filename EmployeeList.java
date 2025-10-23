import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public void removeEmployeeById(String id) {
        Employee del = findById(id);
        del.setAvailable(false);
    }

    public void displayAll() {
        for (Employee c : employees) {
            c.displayinfo();
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
