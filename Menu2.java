import java.util.Scanner;

public class Menu2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        EmployeeList e = new EmployeeList();
        CustomerList c = new CustomerList();
        Booklist bl = new Booklist();

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
                    System.out.println("GOODBYE!");
                    break;

                case 1:
                    int select2;
                    System.out.print("Enter your phone number: ");
                    String p = sc.nextLine();
                    if(Person.isValidPhoneNumber(p) && c.findByPhone(p) != null && c.findByPhone(p).isActive() != false){
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
                                    System.out.println("\nSearch by:");
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
                                        String search = sc.nextLine();
                                        bl.find(type, search);
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
                                                c.edit(c.findByPhone(p));
                                                System.out.print("Done!");
                                                break;
                                            case 2:
                                                c.findByPhone(p).setActive(false);
                                                System.out.print("Done!");
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
                                        switch(select4){
                                            case 1:
                                                c.findByPhone(p).redeemPoints(30);
                                                break;
                                            case 2:
                                                c.findByPhone(p).redeemPoints(50);
                                                break;
                                            case 3:
                                                c.findByPhone(p).redeemPoints(80);
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
                    else if(!c.findByPhone(p).isActive()){
                        System.out.println("Account doesn't exist!");
                    }
                    else if(Person.isValidPhoneNumber(p) && c.findByPhone(p) == null){
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
                    else{
                        System.out.println("Invalid phone number. Returning...");
                    }
                    break;

                    case 2:
                        break;
                }
            }while(select != 0);
        }

        public static void addCustomerFromInput(Scanner sc, CustomerList c) {
        System.out.println("--- Create new customer ---");
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        String phone;
        while (true) {
            System.out.print("Enter phone number: ");
            phone = sc.nextLine();
            if (Person.isValidPhoneNumber(phone)) {
                break;
            } else {
                System.out.println("Invalid phone number! (10 nums and start with 0).");
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

