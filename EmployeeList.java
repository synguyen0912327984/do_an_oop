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
        /*System.out.println("1. ID");
        System.out.println("2. Phone");
        System.out.println("3. Name");
        System.out.print("Enter search method: ");*/
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
                if(findAllByName(keyword) == null){
                        System.out.println("Cannot find valid employee");
                        break;
                }
                else{
                    int i = 1;
                    for(Employee c : findAllByName(keyword)){
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
