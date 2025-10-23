import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CustomerList {
    private ArrayList<Customer> customers;
    private static final String FILE_NAME = "Customers.txt";

    public CustomerList() {
        // Doc file va chuyen no thanh doi tuong roi dua vao danh sach (Customer list);
        // (Nguyen da lam)
        customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                addCustomer(Customer.fromString(line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        int maxId = 0;
        for (Customer cus : customers) {
            try {
                int idNum = Integer.parseInt(cus.getId().substring(1));
                maxId = (maxId > idNum) ? maxId : idNum;
            } catch (NumberFormatException e) {
                // Khong dung dinh dang thi bo qua
            }
        }
        Customer.setCustomerCounter(maxId + 1);

    }

    public ArrayList<Customer> getList() {
        return customers;
    }

    public void setList(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    // Tim kiem

    public void find(int keys, String keyword) {
        switch (keys) {
            case 1:
                if (findById(keyword) == null) {
                    System.out.println("Cannot find valid customer");
                    break;
                }
                findById(keyword).displayinfo();
                break;
            case 2:
                if (findByPhone(keyword) == null) {
                    System.out.println("Cannot find valid customer");
                    break;
                }
                findByPhone(keyword).displayinfo();
                break;
        }
    }

    public Customer findById(String id) {
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(id) && c.isActive()) {
                return c;
            }
        }
        return null;
    }

    public Customer findByPhone(String phone) {
        for (Customer c : customers) {
            if (c.getPhoneNumber().equalsIgnoreCase(phone) && c.isActive()) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Customer> findAllByName(String name) {
        ArrayList<Customer> results = new ArrayList<>();
        String lowerCaseName = name.toLowerCase();
        for (Customer c : customers) {
            if (c.getName().toLowerCase().contains(lowerCaseName) && c.isActive()) {
                results.add(c);
            }
        }
        return results;
    }

    public void removeCustomerById(String id) {
        Customer del = findById(id);
        del.setActive(false);
    }

    public void displayAll() {
        for (Customer c : customers) {
            if (c.isActive())
                c.displayinfo();
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer obj : customers) {
                bw.write(obj.toString());
                bw.newLine();
            }
            System.out.println("File saved successfully: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("file save failed: " + FILE_NAME);
        }
    }
}
