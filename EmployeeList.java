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

    public void addEmployee(Employee c) {
        employees.add(c);
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

    public void removeEmployeeById(String id) {
        Employee del = findById(id);
        del.setActive(false);
    }

    public void displayAll() {
        for (Employee e : employees) {
            if (e.isActive())
                e.displayinfo();
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
