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
                    if(c.findByPhone(p) != null){
                        System.out.println("Welcome!");
                        do{
                            System.out.println("1. Search book");
                            System.out.println("2. Personal setting");
                            System.out.println("3. Redeem points");
                            System.out.println("0. Return");
                            select2 = sc.nextInt();
                            sc.nextLine();
                            switch(select2){
                                case 1:
                                    System.out.println("\nSearch by:");
                                    System.out.println("1. ID");
                                    System.out.println("2. Title");
                                    System.out.println("3. Author");
                                    System.out.println("4. Publisher");
                                    System.out.println("0. Cancel");
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
                                        System.out.println("1. Edit information");
                                        System.out.println("2. Delete account");
                                        System.out.println("0. Return");
                                        select3 = sc.nextInt();
                                        sc.nextLine();
                                        switch(select3){
                                            case 1:
                                                c.edit(c.findByPhone(p));
                                        }
                                    } while(select3 != 0);
                            }
                        } while(select2 != 0);
                    }
                    break;
                }
            }while(select != 0);
        }
}
