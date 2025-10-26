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
            System.out.println("==========================");
            System.out.print("Enter: ");

            select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 0:
                    e.saveToFile();
                    c.saveToFile();
                    bl.saveToFile();
                    ln.saveFile();
                    ld.savefile();
                    System.out.println("GOODBYE!");
                    break;

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
                            select2 = sc.nextInt();
                            sc.nextLine();
                            switch(select2){
                                case 1:
                                System.out.println("==========================");
                                    System.out.println("Search by:");
                                    System.out.println("1. ID");
                                    System.out.println("2. Title");
                                    System.out.println("3. Author");
                                    System.out.println("4. Publisher");
                                    System.out.println("0. Cancel");
                                    System.out.println("==========================");
                                    System.out.print("Enter: ");
                                    int type = sc.nextInt();
                                    sc.nextLine();
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
                                        }
                                        String search = sc.nextLine();
                                        bl.find(type, search);
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
                                        select3 = sc.nextInt();
                                        sc.nextLine();
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
                                        System.out.println("==========================");
                                        System.out.println("Enter: ");
                                        select4 = sc.nextInt();
                                        sc.nextLine();
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
                                        }
                                    }while(select4 != 0);
                                    break;
                                case 0:
                                    System.out.println("Returning...");
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

                    case 2:
                        break;
                }
            }while(select != 0);
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

