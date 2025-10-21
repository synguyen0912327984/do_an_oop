import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CustomerList {
    private ArrayList<Customer> customers;
    private static final String FILE_NAME = "Customers.txt";

    public CustomerList() {
        // Doc file va chuyen no thanh doi tuong roi dua vao danh sach (Customer list); (Nguyen da lam)
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null)
                addCustomer(Customer.fromString(line));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    //Tim kiem

    public void find(int keys, String keyword) {
        switch (keys) { 
            case 1:
                if(findById(keyword) == null){
                    System.out.println("Cannot find valid customer");
                    break;
                }
                findById(keyword).displayinfo();
                break;
            case 2:
                if(findByPhone(keyword) == null){
                    System.out.println("Cannot find valid customer");
                    break;
                }
                findByPhone(keyword).displayinfo();;
                break;
        }
    }

    public Customer findById(String id) {
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public Customer findByPhone(String phone) {
        for (Customer c : customers) {
            if (c.getPhoneNumber().equalsIgnoreCase(phone)) {
                return c;
            }
        }
        return null;
    }

    public void displayAll() {
        for (Customer c : customers) {
            c.displayinfo();
        }
    }

    public void saveToFile() {
        // Ghi vào file theo dạng chuỗi dựa vào (toString)
    }
}
