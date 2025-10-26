import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

class Customer extends Person {
    private static int customerCount = 0;
    private int loyaltyPoints;

    public Customer(String name, String phoneNumber, String address, int loyaltyPoints) {
        super(null, name, phoneNumber, address);
        this.id = String.format("C%03d", customerCount++);
        /*
         * Dinh dang chuoi voi chuoi dinh dang C + customnerCount
         * %03d do dai toi thieu la 3 neu khong du thi chen 0 vao dau
         */
        this.loyaltyPoints = loyaltyPoints;
    }

    private Customer(String id, String name, String phoneNumber, String address, int loyaltyPoints, boolean active) {
        super(id, name, phoneNumber, address);
        this.loyaltyPoints = loyaltyPoints;
        this.active = active;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public void displayinfo() {
        System.out.println("=====================================");
        super.displayinfo();
        System.out.println("LoyaltyPoints   : " + loyaltyPoints);
        System.out.println("=====================================");
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phoneNumber + "," + address + "," + loyaltyPoints + "," + active;
    }

    public void Buy(EmployeeList E, Booklist lb, ListInvoice ln, ListInvoiceDetails ld, Scanner sc) {
        int number = ln.getQuantity() + 1;
        String idInvoice;
        do {
            if(number<100){
                idInvoice = "HD0" + number;
                number++;
            }else{
                idInvoice ="HD" + number;
                number++;
            }
        } while (ln.test(idInvoice) != null);
        


            Invoice inv = new Invoice();
            String temp3;
            inv.setIdInvoice(idInvoice);
            inv.setIdCustomer(id);
            do{
                int n = (int)(Math.random() * (50)) + 1;
                temp3 = String.format("E%04d", n);
                if(E.findById(temp3).getPosition().equalsIgnoreCase("Cashier") && E.findById(temp3).isActive())
                    inv.setIdEmployee(temp3);
            }while(!E.findById(temp3).getPosition().equalsIgnoreCase("Cashier") || E.findById(temp3).isActive() == false);
            inv.setTime(LocalDate.now());
            ln.addlist(inv);
            String temp2;
            int flag = 1;
            int flag2 = 1;
            int flag3 = 1;
            int temp1;
            Book a;
            do{
                flag2 = 1;
                System.out.print("Enter book ID: ");
                String temp0 = sc.nextLine();
                do{
                    if(lb.findByID(temp0) != null){
                        flag2 = 0;
                        System.out.print("Enter the amount you want to buy: ");
                        temp1 = Menu2.readIntInput();
                        a = lb.findByID(temp0);
                        flag3 = 1;
                        if (a.getAmount() >= temp1) {
                            flag3 = 0;
                            System.out.print("Are you sure? y/n: ");
                            temp2 = sc.nextLine();
                            if (temp2.equalsIgnoreCase("y")) {
                                System.out.println("Successfully purchased!");
                                a.setAmount(a.getAmount() - temp1);
                                addLoyaltyPoints((int)(a.getPrice() * temp1 / 10000));
                                InvoiceDetail ind = new InvoiceDetail(idInvoice, a.getbookID(), temp1);
                                flag = 0;
                                ld.addlist(ind);
                                System.out.println("Continue to buy? y/n: ");
                                temp2 = sc.nextLine();
                                if(temp2.equalsIgnoreCase("y"))
                                    flag2 = 1;
                            } 
                            else {
                                System.out.println("Cancelled.");
                                if(flag == 1) ln.removelist(inv);
                            }
                        }
                        else System.out.println("Only " + a.getAmount() + " items are available. Please adjust your quantity.");
                        
                    }
                    else System.out.println("Cannot find book with ID: " + temp0);
                }while(flag3 == 1);
            }while(flag2 == 1);
                System.out.println("Print invoice? y/n: ");
                String temp = sc.nextLine();
                if(temp.equalsIgnoreCase("y")){
                    ArrayList<InvoiceDetail> temp4 = ld.find(idInvoice);
                    System.out.println("==============================================================");
                    System.out.println("                       I N V O i C E                      ");
                    System.out.println("==============================================================");
                    System.out.println("IdInvoice : " + inv.getIdInvoice());
                    System.out.println("Date      : " + inv.getTime());
                    System.out.println("Customer  : " + getName());
                    System.out.println("Staff     : " + E.findById(temp3).getName());
                    System.out.println("--------------------------------------------------------------");

                    System.out.printf("%-5s %-40s %-10s %-10s %-10s%n", "No", "Name", "Amount", "Price", "Total");
                    System.out.println("--------------------------------------------------------------");

                    double totalAll = 0;

                    for (int i = 0; i < temp4.size(); i++) {
                        InvoiceDetail d = temp4.get(i);
                        Book b = lb.findByID(d.getIdBook());
                        if (b == null)
                            continue; // safety check
                        double total = d.getQuantity() * b.getPrice();
                        totalAll += total;

                        System.out.printf("%-5d %-40s %-10d %-10.2f %-10.2f%n",
                                (i + 1), b.getTitle(), d.getQuantity(), b.getPrice(), total);
                }
                System.out.println("--------------------------------------------------------------");
                System.out.printf("%-55s %-10s %-10.2f VND%n", "", "AllTotal:", totalAll);
                System.out.println("==============================================================");
                System.out.println("Process Done! Continue to buy? y/n: ");
            }
            else System.out.println("Process Done! Continue to buy? y/n: ");
    }

    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
            System.out.println(points + " Added to account! Total: " + loyaltyPoints);
        } else
            System.out.println("Invalid points!");
    }

    public void redeemPoints(int points) {
        if (points > 0 && points <= loyaltyPoints) {
            this.loyaltyPoints -= points;
            System.out.println(points + " points redeemed successfully! Remain: " + loyaltyPoints);
        } else
            System.out.println("Not enough points to redeem");
    }

    /*
     * Doc file(.txt)
     * File co dang : id,name,phonenumber,address,loyaltypoints
     */
    public static Customer fromString(String line) {
        String p[] = line.split(",");
        if (p.length == 6) {
            Customer c = new Customer(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]), Boolean.parseBoolean(p[5]));
            return c;
        }
        return null;
    }

    public static void setCustomerCounter(int value) {
        customerCount = value;
    }

}
