import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CustomerList implements IActions<Customer> {
    private ArrayList<Customer> customers;
    private static final String FILE_NAME = "Customers.txt";

    public CustomerList() {
        // Doc file va chuyen no thanh doi tuong roi dua vao danh sach (Customer list);
        // (Nguyen da lam)
        customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                add(Customer.fromString(line));
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

    @Override
    public void add(Customer c) {
        customers.add(c);
    }

    // Tim kiem

    @Override
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
            case 3:
                if (findAllByName(keyword) == null) {
                    System.out.println("Cannot find valid customer");
                    break;
                } else {
                    int i = 1;
                    for (Customer c : findAllByName(keyword)) {
                        System.out.println(i + ".");
                        c.displayinfo();
                        i++;
                    }
                }
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

    @Override
    public void removeById(String id) {
        Customer del = findById(id);
        del.setActive(false);
    }

    @Override
    public void edit(Customer a) {
        Scanner sc = new Scanner(System.in);
        System.out.println("EDIT:");
        System.out.print("1. Name\n2. Phone\n3. Address\nEnter: ");
        int keys = Integer.parseInt(sc.nextLine());
        String keyword;
        switch (keys) {
            case 1: // Name
                System.out.print("Enter new name: ");
                keyword = sc.nextLine();
                a.setName(keyword);
                System.out.println("Changed successfully!");
                break;
            case 2: // Phone
                System.out.print("Enter new phone number: ");
                keyword = sc.nextLine();
                if (!Person.isValidPhoneNumber(keyword)) {
                    System.out.println("Invalid phone number!\n");
                } else {
                    a.setPhoneNumber(keyword);
                    System.out.println("Changed successfully!");
                    break;
                }
            case 3: // Address
                System.out.print("Enter new address: ");
                keyword = sc.nextLine();
                a.setAddress(keyword);
                System.out.println("Changed successfully!");
                break;
            /*
             * case 4: //Active
             * System.out.println("Active (true/false): ");
             * keyword = sc.nextLine();
             * a.setActive(Boolean.parseBoolean(keyword));
             * System.out.println("Changed successfully!");
             * break;
             * case 5: //LoyaltyPoints
             * System.out.println("Enter loyalty points: ");
             * keyword = sc.nextLine();
             * a.setLoyaltyPoints(Integer.parseInt(keyword));
             * System.out.println("Changed successfully!");
             * break;
             */
            default:
                System.out.println("Cancelled.");
                break;
        }
        sc.close();
    }

    public void displayAll() {
        for (Customer c : customers) {
            if (c.isActive())
                c.displayinfo();
        }
    }

    public void editCustomer(String id, Scanner scanner) {
        Customer customerToEdit = this.findById(id);

        if (customerToEdit == null) {
            System.out.println("Customer not found with id: " + id);
            return;
        }

        System.out.println("Update customer information (press Enter to skip)");
        customerToEdit.displayinfo();

        System.out.print("Ten moi (" + customerToEdit.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            customerToEdit.setName(newName);
        }

        while (true) {
            System.out.print("New phone number (" + customerToEdit.getPhoneNumber() + "): ");
            String newPhone = scanner.nextLine();

            if (newPhone.isEmpty()) {
                break;
            }

            if (Person.isValidPhoneNumber(newPhone)) {
                customerToEdit.setPhoneNumber(newPhone);
                break;
            } else {
                System.out.println("Invalid phone number");
                System.out.println("Please re-enter or press Enter to skip");
            }
        }

        System.out.print("New address (" + customerToEdit.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            customerToEdit.setAddress(newAddress);
        }

        System.out.print("New reward points (" + customerToEdit.getLoyaltyPoints() + "): ");
        String newPointsStr = scanner.nextLine();
        if (!newPointsStr.isEmpty()) {
            try {
                int newPoints = Integer.parseInt(newPointsStr);
                customerToEdit.setLoyaltyPoints(newPoints);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Reward points were not changed");
            }
        }

        System.out.println("---Customer information updated successfully!---");
        customerToEdit.displayinfo();
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
