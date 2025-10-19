package Java.project.team;

import java.util.*;

public class CustomerList {
    private List<Customer> customers;
    private static final String FILE_NAME = "Customers.txt";

    public CustomerList() {
        // Đọc file và chuyển nó thành đối tượng rồi đưa vào danh sách (Customer list);
    }

    public void addCustomer(Customer c) {
        customers.add(c);
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