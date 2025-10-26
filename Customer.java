import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

class Customer extends Person {
    private static int customerCount = 0;
    private int loyaltyPoints;

    public Customer(String name, String phoneNumber, String address, int loyaltyPoints) {
        super(null, name, phoneNumber, address);
        this.id = String.format("C%04d", customerCount++);
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

    public int Buy(EmployeeList E, Booklist lb, ListInvoice ln, ListInvoiceDetails ld, Scanner sc) {
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
                if (temp0.contains(" ")) {
                    System.out.println("Book ID cannot contain spaces. Try again.");
                    continue;
                }
                do{
                    if(lb.findByID(temp0) != null){
                        flag2 = 0;
                        System.out.print("Enter the amount you want to buy: ");
                        temp1 = Menu2.readIntInput();
                        a = lb.findByID(temp0);
                        flag3 = 1;
                        if (a.getAmount() >= temp1 && temp1 > 0) {
                            flag3 = 0;
                            System.out.print("Are you sure you want to buy" +  " \"" + lb.findByID(temp0).getTitle() + "\"? y/n: ");
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
                                if(temp2.equalsIgnoreCase("y")) {
                                    int type;
                                    do {
                                        System.out.println("==========================");
                                        System.out.println("Search by:");
                                        System.out.println("1. ID");
                                        System.out.println("2. Title");
                                        System.out.println("3. Author");
                                        System.out.println("4. Publisher");
                                        System.out.println("0. Cancel");
                                        System.out.println("==========================");
                                        System.out.print("Enter: ");
                                        type = Menu2.readIntInput();
                                        if (type < 1 || type > 4) {
                                            System.out.println("Cancelled.");
                                            flag2 = 0;
                                            break;
                                        }
                                        else {
                                            switch (type) {
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
                                            ArrayList<Book> tempbl = lb.find(type, search);
                                            if (!tempbl.isEmpty()) {
                                                type = 0;
                                            }
                                            flag2 = 1;
                                        }
                                    }while(type != 0);
                                }
                            } 
                            else {
                                System.out.println("Cancelled.");
                                if(flag == 1) ln.removelist(inv);
                            }
                        }
                        else if(temp1 <= 0){
                            System.out.println("Amount cannot below or equal 0. Try again.");
                        }
                        else
                        System.out.println(
                                "Only " + a.getAmount() + " items are available. Please adjust your quantity.");
                    }
                    else {
                        System.out.println("Cannot find book with ID: " + temp0);
                        break;
                    }
                } while (flag3 == 1);
            } while (flag2 == 1);
        ArrayList<InvoiceDetail> currentInvoiceDetails = ld.find(idInvoice);
        if (currentInvoiceDetails.isEmpty()) {
            System.out.println("No items purchased. Cancelling invoice...");
            ln.removelist(inv);
            return 0;
        }
        double totalAll = 0;
        for (InvoiceDetail d : currentInvoiceDetails) {
            Book b = lb.findByID(d.getIdBook());
            if (b != null) {
                totalAll += d.getQuantity() * b.getPrice();
            }
        }
        double discountAmount = 0;
        double finalTotal = totalAll;
        int currentPoints = this.getLoyaltyPoints();
        int flag4 = 1;
        do {
            System.out.println("\n--------------------------------------------------------------");
            System.out.printf("Subtotal: %,.0f VND%n", totalAll);
            System.out.println("You have " + currentPoints + " reward points.");
            System.out.println("Do you want to use your points to redeem rewards?");
            System.out.println("1. 25% discount on your bill (Costs 30 points)");
            System.out.println("2. 50% discount on your bill (Costs 50 points)");
            System.out.println("3. Get 1 most expensive book for free (20 points required)");
            System.out.println("0. No, thanks.");
            System.out.print("Enter your choice: ");
            int redeemChoice = 0;
            try {
                redeemChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. No discount applied.");
            }

            switch (redeemChoice) {
                case 1:
                    if (this.redeemPoints(30)) {
                        discountAmount = totalAll * 0.25;
                        System.out.println("25% DISCOUNT APPLIED!");
                    } else {
                        System.out.println("You don't have enough points. Please try again");
                    }
                    flag4 = 0;
                    break;
                case 2:
                    if (this.redeemPoints(50)) {
                        discountAmount = totalAll * 0.50;
                        System.out.println("50% DISCOUNT APPLIED!");
                    } else {
                        System.out.println("You don't have enough points. Please try again");
                    }
                    flag4 = 0;
                    break;
                case 3:
                    if (this.redeemPoints(20)) {
                        System.out.println("Please select which book you want for free:");
                        System.out.println("--------------------------------------------------------------");
                        System.out.printf("%-5s %-40s %-10s%n", "No.", "Name", "Price (per 1 unit)");
                        for (int i = 0; i < currentInvoiceDetails.size(); i++) {
                            InvoiceDetail d = currentInvoiceDetails.get(i);
                            Book b = lb.findByID(d.getIdBook());
                            if (b != null) {
                                System.out.printf("%-5d %-40s %,.0f VND%n",
                                        (i + 1), b.getTitle(), b.getPrice());
                            }
                        }
                        System.out.println("--------------------------------------------------------------");
                        System.out.print("Enter the 'No.' of the book you want (e.g., 1, 2...): ");

                        int selectedBookIndex = -1;
                        try {
                            selectedBookIndex = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                        }
                        if (selectedBookIndex > 0 && selectedBookIndex <= currentInvoiceDetails.size()) {

                            InvoiceDetail selectedDetail = currentInvoiceDetails.get(selectedBookIndex - 1);
                            Book selectedBook = lb.findByID(selectedDetail.getIdBook());

                            if (selectedBook != null) {
                                discountAmount = selectedBook.getPrice();
                                System.out.printf("'%s' applied as free book! (Worth %,.0f VND)%n",
                                        selectedBook.getTitle(), discountAmount);
                            } else {
                                System.out.println("Error finding selected book. No discount applied.");
                                this.addLoyaltyPoints(20);
                                System.out.println("Points returned.");
                            }
                        } else {
                            System.out.println("Invalid selection. No discount applied.");
                            this.addLoyaltyPoints(20);
                            System.out.println("Points returned.");
                        }
                        flag4 = 0;
                    } else {
                        System.out.println("You don't have enough points. Please try again");
                    }
                    break;
                default:
                    System.out.println("Offer not applied.");
                    flag4 = 0;
                    break;
            }
        } while (flag4 == 1);

        finalTotal = totalAll - discountAmount;
        System.out.println("--------------------------------------------------------------");
        System.out.println("Print invoice? y/n: ");
        String temp = sc.nextLine();
        if (temp.equalsIgnoreCase("y")) {
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
            for (int i = 0; i < currentInvoiceDetails.size(); i++) {
                InvoiceDetail d = currentInvoiceDetails.get(i);
                Book b = lb.findByID(d.getIdBook());
                if (b == null)
                    continue;
                double total = d.getQuantity() * b.getPrice();
                System.out.printf("%-5d %-40s %-10d %-10.2f %-10.2f%n",
                        (i + 1), b.getTitle(), d.getQuantity(), b.getPrice(), total);
            }
            System.out.println("--------------------------------------------------------------");
            System.out.printf("%-66s %,.0f VND%n", "Subtotal:", totalAll);
            if (discountAmount > 0) {
                System.out.printf("%-66s -%,.0f VND%n", "Discount:", discountAmount);
            }
            System.out.printf("%-66s %,.0f VND%n", "FINAL TOTAL:", finalTotal);
            System.out.println("==============================================================");
            System.out.println("Process Done! Continue to buy? y/n: ");
        } else
            System.out.println("Process Done! Continue to buy? y/n: ");
        return 1;
    }

    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
            System.out.println(points + " Point added to account! Total: " + loyaltyPoints);
        } else
            System.out.println("Invalid points!");
    }

    public boolean redeemPoints(int points) {
        if (points > 0 && points <= loyaltyPoints) {
            this.loyaltyPoints -= points;
            System.out.println(points + " points redeemed successfully! Remain: " + loyaltyPoints);
            return true;
        } else {
            System.out.println("Not enough points to redeem");
            return false;
        }
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
