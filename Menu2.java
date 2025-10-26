import java.util.ArrayList;
import java.util.Scanner;

public class Menu2 {
    private static Scanner sc = new Scanner(System.in);
    private static EmployeeList e = new EmployeeList();
    private static CustomerList c = new CustomerList();
    private static Booklist bl = new Booklist();
    private static ListInvoice ln = new ListInvoice();
    private static ListInvoiceDetails ld = new ListInvoiceDetails();

    public static void main(String[] args){
        ln.readFile();
        ld.readFile();
        int select;
        do {
            System.out.println("\n====== MENU ======");
            System.out.println("Who are you?");
            System.out.println("1. Customer");
            System.out.println("2. Employee");
            System.out.println("3. Management");
            System.out.println("0. Exit");
            System.out.println("==========================");
            System.out.print("Enter: ");

            select = readIntInput();
            switch (select) {
                case 0:
                    e.saveToFile();
                    c.saveToFile();
                    bl.saveToFile();
                    ln.saveFile();
                    ld.savefile();
                    System.out.println("GOODBYE!");
                    break;

                //Customer

                case 1:
                    int select2;
                    System.out.print("Enter your phone number: ");
                    String p = sc.nextLine();
                    if(Person.isValidPhoneNumber(p) && c.findByPhone(p) == null){
                        System.out.println("Phone number doesn't exist in database. Create new account?");
                        System.out.print("y/n: ");
                        String temp = sc.nextLine();
                        if(temp.equalsIgnoreCase("y")){
                            addCustomerFromInput(sc, c);
                        }
                        else{
                            System.out.println("Returning...");
                        }
                    }
                    else if(Person.isValidPhoneNumber(p) && c.findByPhone(p) != null && c.findByPhone(p).isActive()){
                        System.out.println("Welcome!");
                        do{
                            System.out.println("==========================");
                            System.out.println("1. Search book");
                            System.out.println("2. Personal setting");
                            System.out.println("3. Redeem points");
                            System.out.println("0. Return");
                            System.out.println("==========================");
                            System.out.print("Enter: ");
                            select2 = readIntInput();
                            switch(select2){
                                case 1:
                                    int type;
                                    do{
                                        System.out.println("==========================");
                                        System.out.println("Search by:");
                                        System.out.println("1. ID");
                                        System.out.println("2. Title");
                                        System.out.println("3. Author");
                                        System.out.println("4. Publisher");
                                        System.out.println("0. Cancel");
                                        System.out.println("==========================");
                                        System.out.print("Enter: ");
                                        type = readIntInput();
                                        if(type < 1 || type > 4) System.out.println("Cancelled.");
                                        else{
                                            switch(type){
                                                case 1:
                                                    System.out.println("Enter ID: ");
                                                    break;
                                                case 2:
                                                    System.out.println("Enter Title: ");
                                                    break;
                                                case 3:
                                                    System.out.println("Enter Author: ");
                                                    break;
                                                case 4:
                                                    System.out.println("Enter Publisher: ");
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice!");
                                                    break;
                                            }
                                            String search = sc.nextLine();
                                            ArrayList<Book> tempbl = bl.find(type, search);
                                            if(tempbl.isEmpty()){
                                                System.out.println("Returning...");
                                            }
                                            else{
                                                System.out.print("Would you like to buy? y/n: ");
                                                String temp;
                                                do{
                                                    temp = sc.nextLine();
                                                    if(temp.equalsIgnoreCase("y")){
                                                        c.findByPhone(p).Buy(e, bl, ln, ld, sc);
                                                    }
                                                    else System.out.println("Returning...");
                                                }while(temp.equalsIgnoreCase("y"));
                                            }
                                        }
                                    }while(type != 0);
                                    break;
                                case 2:
                                    int select3;
                                    do{
                                        System.out.println("==========================");
                                        System.out.println("1. Edit information");
                                        System.out.println("2. Delete account");
                                        System.out.println("0. Return");
                                        System.out.println("==========================");
                                        System.out.print("Enter: ");
                                        select3 = readIntInput();
                                        switch(select3){
                                            case 1:
                                                c.edit(c.findByPhone(p), sc);
                                                System.out.print("Done!");
                                                break;
                                            case 2:
                                                System.out.print("Are you sure? y/n: ");
                                                String temp = sc.nextLine();
                                                if(temp.equalsIgnoreCase("y")) {
                                                    c.findByPhone(p).setActive(false);
                                                    System.out.print("Done!");
                                                }
                                                else System.out.println("Cancelled.");
                                                break;
                                            case 0:
                                                System.out.println("Returning...");
                                                break;
                                            default:
                                                System.out.println("Invalid choice!");
                                                break;
                                        }
                                    } while(select3 != 0);
                                    break;
                                case 3:
                                    int select4;
                                    do{
                                        System.out.println("==========================");
                                        System.out.println("1. 25% discount: 30LP");
                                        System.out.println("2. 50% discount: 50LP");
                                        System.out.println("3. Select book: 80LP");
                                        System.out.println("0. Return");
                                        System.out.println("Your LP: " + c.findByPhone(p).getLoyaltyPoints());
                                        System.out.println("==========================");
                                        System.out.println("Enter: ");
                                        select4 = readIntInput();
                                        String temp;
                                        switch(select4){
                                            case 1:
                                                System.out.print("Are you sure? y/n: ");
                                                temp = sc.nextLine();
                                                if(temp.equalsIgnoreCase("y"))
                                                    c.findByPhone(p).redeemPoints(30);
                                                else System.out.println("Cancelled.");
                                                break;
                                            case 2:
                                                System.out.print("Are you sure? y/n: ");
                                                temp = sc.nextLine();
                                                if(temp.equalsIgnoreCase("y"))
                                                    c.findByPhone(p).redeemPoints(50);
                                                else System.out.println("Cancelled.");
                                                break;
                                            case 3:
                                                System.out.print("Are you sure? y/n: ");
                                                temp = sc.nextLine();
                                                if(temp.equalsIgnoreCase("y"))
                                                    c.findByPhone(p).redeemPoints(80);
                                                else System.out.println("Cancelled.");
                                                break;
                                            case 0:
                                                System.out.println("Returning...");
                                                break;
                                            default:
                                                System.out.println("Invalid choice!");
                                                break;
                                        }
                                    }while(select4 != 0);
                                    break;
                                case 0:
                                    System.out.println("Returning...");
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                                    break;
                            }
                        } while(select2 != 0);
                    }
                    else if(c.findByPhone(p) == null){
                        System.out.println("Invalid phone number. Returning...");
                    }
                    else{
                        System.out.println("Account doesn't exist!");
                    }
                    break;
                    
                    //Employee

                    case 2:
                        int selectE;
                        System.out.print("Enter your ID: ");
                        String ID = sc.nextLine();
                        if(e.findById(ID) == null){
                            System.out.println("ID doesn't exist!");
                        }
                        else if(e.findById(ID) != null && e.findById(ID).isActive()){
                            System.out.println("Welcome!");
                            Employee tempE = e.findById(ID);
                            do{
                                System.out.println("==========================");
                                System.out.println("1. Books management");
                                System.out.println("2. Personal setting");
                                System.out.println("0. Return");
                                System.out.println("==========================");
                                System.out.print("Enter: ");
                                selectE = readIntInput();
                                switch(selectE){
                                    case 1:
                                        int selectE2;
                                        do{
                                            System.out.println("==========================");
                                            System.out.println("1. Add book");
                                            System.out.println("2. Remove book");
                                            System.out.println("3. Update book");
                                            System.out.println("4. Delete history");
                                            System.out.println("0. Cancel");
                                            System.out.println("==========================");
                                            System.out.print("Enter: ");
                                            selectE2 = readIntInput();
                                            switch(selectE2){
                                                case 1:
                                                    Book tempB = new Book();
                                                    bl.AddBook(tempB, sc);
                                                    break;
                                                case 2:

                                                    break;
                                                case 3:
                                                    break;
                                                case 4:
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice!");
                                                    break;
                                            }
                                        }while(selectE2 != 0);
                                        break;
                                    case 2:
                                        int selectE3;
                                        do{
                                            System.out.println("==========================");
                                            System.out.println("1. Edit information");
                                            System.out.println("0. Return");
                                            System.out.println("==========================");
                                            System.out.print("Enter: ");
                                            selectE3 = readIntInput();
                                            switch(selectE3){
                                                case 1:
                                                    e.edit(tempE, sc);
                                                    System.out.print("Done!");
                                                    break;
                                                case 0:
                                                    System.out.println("Returning...");
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice!");
                                                    break;
                                            }
                                        } while(selectE3 != 0);
                                        break;
                                    default:
                                        System.out.println("Invalid choice!");
                                        break;
                                }
                            }while(selectE != 0);
                        }
                        break;

                    //Management

                    case 3:
                        System.out.print("Enter password: ");
                        String temp = sc.nextLine();
                        if(temp.equalsIgnoreCase("DoanOOP"))
                            mainMenu();
                        else System.out.println("Wrong password. Returning...");
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            }while(select != 0);
        }

        public static void mainMenu() {
        int select;
        do {
            System.out.println("\n===========================");
            System.out.println("====== MAIN MANAGEMENT MENU =====");
            System.out.println("===========================");
            System.out.println("1. Customer Management");
            System.out.println("2. Employee Management");
            System.out.println("0. Exit and Save Program");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            select = readIntInput();

            switch (select) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 0:
                    System.out.println("Saving data to file...");
                    e.saveToFile();
                    c.saveToFile();
                    System.out.println("GOODBYE!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        } while (select != 0);
    }

    public static void employeeMenu() {
        int select;
        do {
            System.out.println("\n--- EMPLOYEE MANAGEMENT MENU ---");
            System.out.println("1. Add new employee");
            System.out.println("2. Edit employee information (by ID)");
            System.out.println("3. Fire employee (by ID)");
            System.out.println("4. Search for employee");
            System.out.println("5. Print entire employee list");
            System.out.println("0. Back to Main Menu");
            System.out.println("--------------------------------");
            System.out.print("Enter choice: ");

            select = readIntInput();

            switch (select) {
                case 1:
                    addEmployeeFromInput(sc, e);
                    break;
                case 2:
                    System.out.print("Enter employee ID to edit: ");
                    String editEmpId = sc.nextLine();
                    e.editEmployee(editEmpId, sc);
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    findEmployeeMenu();
                    break;
                case 5:
                    System.out.println("\n--- Full Employee List ---");
                    e.displayAll();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (select != 0);
    }

    public static void findCustomerMenu() {
        int select;
        do {
            System.out.println("\n--- Search Customer ---");
            System.out.println("1. Find by ID");
            System.out.println("2. Find by Name");
            System.out.println("3. Find by Phone Number");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            select = readIntInput();
            String keyword;

            switch (select) {
                case 1:
                    System.out.print("Enter customer ID to find: ");
                    keyword = sc.nextLine();
                    Customer rsCId = c.findById(keyword);
                    if (rsCId != null) {
                        System.out.println("Customer found:");
                        rsCId.displayinfo();
                    } else {
                        System.out.println("Customer not found with ID: " + keyword);
                    }
                    break;
                case 2:
                    System.out.print("Enter customer name to find: ");
                    keyword = sc.nextLine();
                    ArrayList<Customer> rsCName = c.findAllByName(keyword);
                    if (!rsCName.isEmpty()) {
                        System.out.println("Found " + rsCName.size() + " results:");
                        for (Customer cus : rsCName) {
                            cus.displayinfo();
                        }
                    } else {
                        System.out.println("No matching names found");
                    }
                    break;
                case 3:
                    System.out.print("Enter customer phone number to find: ");
                    keyword = sc.nextLine();
                    Customer rsCPhone = c.findByPhone(keyword);
                    if (rsCPhone != null) {
                        System.out.println("Customer found:");
                        rsCPhone.displayinfo();
                    } else {
                        System.out.println("Phone number not found");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (select != 0);
    }

    public static void findEmployeeMenu() {
        int select;
        do {
            System.out.println("\n--- Search Employee ---");
            System.out.println("1. Find by ID");
            System.out.println("2. Find by Name");
            System.out.println("3. Find by Phone Number");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            select = readIntInput();
            String keyword;

            switch (select) {
                case 1:
                    System.out.print("Enter employee ID to find: ");
                    keyword = sc.nextLine();
                    Employee rsEId = e.findById(keyword);
                    if (rsEId != null) {
                        System.out.println("Employee found:");
                        rsEId.displayinfo();
                    } else {
                        System.out.println("Employee not found with ID: " + keyword);
                    }
                    break;
                case 2:
                    System.out.print("Enter employee name to find: ");
                    keyword = sc.nextLine();
                    ArrayList<Employee> rsEName = e.findAllByName(keyword);
                    if (!rsEName.isEmpty()) {
                        System.out.println("Found " + rsEName.size() + " results:");
                        for (Employee emp : rsEName) {
                            emp.displayinfo();
                        }
                    } else {
                        System.out.println("No matching names found");
                    }
                    break;
                case 3:
                    System.out.print("Enter employee phone number to find: ");
                    keyword = sc.nextLine();
                    Employee rsEPhone = e.findByPhone(keyword);
                    if (rsEPhone != null) {
                        System.out.println("Employee found:");
                        rsEPhone.displayinfo();
                    } else {
                        System.out.println("Phone number not found");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (select != 0);
    }

    public static void customerMenu() {
        int select;
        do {
            System.out.println("\n--- CUSTOMER MANAGEMENT MENU ---");
            System.out.println("1. Add new customer");
            System.out.println("2. Edit customer information (by ID)");
            System.out.println("3. Delete customer (by ID)");
            System.out.println("4. Search for customer");
            System.out.println("5. Print entire customer list");
            System.out.println("0. Back to Main Menu");
            System.out.println("---------------------------------");
            System.out.print("Enter choice: ");

            select = readIntInput();

            switch (select) {
                case 1:
                    addCustomerFromInput(sc, c);
                    break;
                case 2:
                    System.out.print("Enter customer ID to edit: ");
                    String editCusId = sc.nextLine();
                    c.editCustomer(editCusId, sc);
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    findCustomerMenu();
                    break;
                case 5:
                    System.out.println("\n--- Full Customer List ---");
                    c.displayAll();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (select != 0);
    }

    private static void deleteEmployee() {
        System.out.print("Enter employee ID to fire: ");
        String delEmpId = sc.nextLine();
        Employee empToDel = e.findById(delEmpId);

        if (empToDel == null) {
            System.out.println("Employee not found with ID: " + delEmpId);
        } else {
            System.out.println("Found employee: " + empToDel.getName());
            System.out.print("Are you sure you want to fire? (y/n): ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                e.removeById(delEmpId);
                System.out.println("Employee fired successfully.");
            } else {
                System.out.println("Fire operation canceled.");
            }
        }
    }

    public static int readIntInput() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter numbers only!");
            return -1;
        }
    }

    public static void addEmployeeFromInput(Scanner sc, EmployeeList e) {
        System.out.println("--- Add new employee ---");
        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        String phone;
        while (true) {
            System.out.print("Enter phone number: ");
            phone = sc.nextLine();
            if (Person.isValidPhoneNumber(phone)) {
                break;
            } else {
                System.out.println("Invalid phone number! Must be 10 digits and start with '0'. Please re-enter.");
            }
        }

        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter position: ");
        String position = sc.nextLine();

        double salary = 0;
        while (true) {
            try {
                System.out.print("Enter salary: ");
                salary = Double.parseDouble(sc.nextLine());
                if (salary > 0) {
                    break;
                } else {
                    System.out.println("Salary must be a positive number.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        Employee newEmployee = new Employee(name, phone, address, position, salary);

        e.add(newEmployee);
        System.out.println("Successfully added new employee with ID: " + newEmployee.getId());
        newEmployee.displayinfo();
    }

        private static void deleteCustomer() {
            System.out.print("Enter customer ID to delete: ");
            String delCusId = sc.nextLine();
            Customer custToDel = c.findById(delCusId);

            if (custToDel == null) {
                System.out.println("Customer not found with ID: " + delCusId);
            } else {
                System.out.println("Found customer: " + custToDel.getName());
                System.out.print("Are you sure you want to delete? (y/n): ");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    c.removeById(delCusId);
                    System.out.println("Customer deleted successfully.");
                } else {
                    System.out.println("Delete operation canceled.");
                }
            }
        }

        public static void addCustomerFromInput(Scanner sc, CustomerList c) {
        System.out.println("--- Create new customer ---");
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        String phone;
        while (true) {
            System.out.print("Enter phone number: ");
            phone = sc.nextLine();
            if (Person.isValidPhoneNumber(phone)) {
                break;
            }
            else if(Person.isValidPhoneNumber(phone) && c.findByPhone(phone) != null){
                System.out.println("Phone number already exist.");
            }
            else {
                System.out.println("Invalid phone number! Must be 10 digits and start with '0'. Please re-enter.");
            }
        }

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        Customer newCustomer = new Customer(name, phone, address, 0);

        c.add(newCustomer);
        System.out.println("Successfully created new customer with ID: " + newCustomer.getId());
        newCustomer.displayinfo();
    }
}

