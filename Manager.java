import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Manager {

    public double calculateTotal(ArrayList<InvoiceDetail> ind, Booklist lb) {
        double allTotal = 0;
        for (InvoiceDetail st : ind) {
            allTotal += st.getQuantity() * lb.findByID(st.getIdBook()).getPrice();
        }
        return allTotal;
    }

    public static void PrintInvoice(EmployeeList le, CustomerList lc, ListInvoice ln, ListInvoiceDetails ld,
            Booklist lb, String id) {
        Invoice inv = ln.test(id);
        if (inv == null) {
            System.out.println(id + " is empty");
            return;
        }
        System.out.println(inv.getIdCustomer());
        ArrayList<InvoiceDetail> ind = ld.find(id);


        System.out.println("==============================================================");
        System.out.println("                       HÓA ĐƠN BÁN SÁCH                      ");
        System.out.println("==============================================================");
        System.out.println("IdInvoice : " + inv.getIdInvoice());
        System.out.println("Date      : " + inv.getTime());
        System.out.println("Customer  : " + lc.findById(inv.getIdCustomer()).getName());
        System.out.println("Staff     : " + le.findById(inv.getIdEmployee()).getName());
        System.out.println("--------------------------------------------------------------");

        System.out.printf("%-5s %-40s %-10s %-10s %-10s%n", "No", "Name", "Amount", "Price", "Total");
        System.out.println("--------------------------------------------------------------");

        double totalAll = 0;

        for (int i = 0; i < ind.size(); i++) {
            InvoiceDetail d = ind.get(i);
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
    }

    public static void addInvoice(EmployeeList le, CustomerList lc, ListInvoice ln, ListInvoiceDetails ld,
            Booklist lb) {
        Scanner sc = new Scanner(System.in);
        Invoice inv = new Invoice();

         // Generate new invoice ID
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
        inv.setIdInvoice(idInvoice);
        System.out.println(idInvoice);
        // Customer ID input
        String id;
        String Phone;
        boolean valid ;
        do {
            System.out.print("NumberPhone: ");
            Phone = sc.nextLine();
            valid = lc.findByPhone(Phone) != null;
            if (!valid)
                System.out.println("NumberPhone not found!");
        } while (!valid);
        id = lc.findByPhone(Phone).getId();
        inv.setIdCustomer(id);
        // Employee ID input
        do {
            System.out.print("idEmp: ");
            id = sc.nextLine();
            valid = le.findById(id) != null;
            if (!valid && 
                !(le.findById(id).getPosition().equalsIgnoreCase("Cashier") ||
                le.findById(id).getPosition().equalsIgnoreCase("Salesman") ||
                le.findById(id).getPosition().equalsIgnoreCase("Manager"))) {
                System.out.println("Employee ID invalid!");
}
        } while (!valid && 
            !(le.findById(id).getPosition().equalsIgnoreCase("Cashier") ||
            le.findById(id).getPosition().equalsIgnoreCase("Salesman") ||
            le.findById(id).getPosition().equalsIgnoreCase("Manager")));

        inv.setIdEmployee(id);
        inv.setTime(LocalDate.now());
        ln.addlist(inv);
        // add buy book
        int choice ;
        int quantity;
        String nameBook = new String();
        String continue1 = new String();
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<InvoiceDetail> listdentail = new ArrayList<>();
        Book book = new Book();
        boolean continue2;
        do{
            continue2 = true;
            do{
                System.out.print("NameBook:");
                nameBook= sc.nextLine();
                books = lb.findByTitle(nameBook);
                if(books.size()==0){
                    System.out.println("Namebook is empty");
                }else{
                    continue2=false;
                }
            }while(continue2);

            for(int i=0;i<books.size();i++){
                System.out.println("No"+(i+1));
                books.get(i).display();
            } 
            do{
                System.out.print("Your choice:");
                choice = sc.nextInt();
                if(choice>books.size()|| choice<=0) {
                    System.out.println("your choice is empty");
                }else if(books.get(choice-1).getAmount() == 0){
                    System.out.println("Sold out!");
                }
            }while(choice>books.size()|| choice<=0||books.get(choice-1).getAmount() == 0);
            book = books.get(choice-1);
            //quanity
            do{
                System.out.println("amouth book:" +lb.findByID(book.getbookID()).getAmount());
                System.out.print("Amouth to buy:");
                quantity = sc.nextInt();
                if(quantity > book.getAmount()){
                    System.out.println("Dont enoguh amouth to buy");
                }else if(quantity == 0){
                    System.out.println("Invalid amouth ");
                }
            }while(quantity > book.getAmount()|| quantity ==0);

            book.setAmount(book.getAmount()-quantity);

            InvoiceDetail dentail = new InvoiceDetail();
            dentail.setIdBook(book.getbookID());
            dentail.setQuantity(quantity);
            dentail.setIdInvoice(idInvoice);
            listdentail.add(dentail);

            sc.nextLine(); 
            System.out.print("Do you want to buy another book ?(y/n)");
            continue1 = sc.nextLine();
            
            

            
       
        }while (continue1.equalsIgnoreCase("y"));

        ln.test(inv.getIdInvoice()).displayInvoice();
        for(InvoiceDetail st:listdentail){
            ld.addlist(st);
        }

        
        PrintInvoice(le, lc, ln, ld, lb, inv.getIdInvoice());
        sc.close();
        
        // Save invoice and details
    //    
        
    }   


   public static void DailySalesReport(EmployeeList empList, CustomerList custList, ListInvoice invoiceList, 
                                    ListInvoiceDetails invoiceDetailsList, Booklist bookList) {
        Scanner sc = new Scanner(System.in);
        LocalDate date = null;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Nhập ngày hợp lệ
        while (true) {
            try {
                System.out.print("Enter date (dd/MM/yyyy): ");
                String input = sc.nextLine();
                date = LocalDate.parse(input, fmt);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("The date cannot be in the future!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date, please try again!");
            }
        }

        // Lấy danh sách hóa đơn trong ngày
        ArrayList<Invoice> inv = invoiceList.findtime(date);
        ArrayList<InvoiceDetail> ind = new ArrayList<>();
        for (Invoice st : inv) {
            ind.addAll(invoiceDetailsList.find(st.getIdInvoice()));
        }

        // Gom số lượng từng sách
        Map<String, Integer> bookSales = new HashMap<>();
        for (InvoiceDetail detail : ind) {
            String idBook = detail.getIdBook();
            int amount = detail.getQuantity();
            bookSales.put(idBook, bookSales.getOrDefault(idBook, 0) + amount);
        }

        // --- In báo cáo ---
        System.out.println("\n=== DAILY BOOK SALES REPORT (" + date.format(fmt) + ") ===");
        System.out.printf("%-10s %-40s %10s %15s %15s\n", "Book ID", "Book Title", "Qty", "Price", "Total");
        System.out.println("---------------------------------------------------------------------------------------------");

        double grandTotal = 0;

        for (Map.Entry<String, Integer> entry : bookSales.entrySet()) {
            Book b = bookList.findByID(entry.getKey());
            String title = (b != null) ? b.getTitle() : "Unknown";
            double price = (b != null) ? b.getPrice() : 0;
            int qty = entry.getValue();
            double total = price * qty;
            grandTotal += total;

            System.out.printf("%-10s %-40s %10d %15.2f %15.2f\n",
                    entry.getKey(), title, qty, price, total);
        }

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-40s %10s %15s %15.2f\n", "", "TOTAL", "", "", grandTotal);
        sc.close();
    }

    public static void CustomerSalesRp(EmployeeList empList, CustomerList custList, ListInvoice invoiceList, 
                                    ListInvoiceDetails invoiceDetailsList, Booklist bookList) {
        Scanner sc = new Scanner(System.in);
        String phoneCus;
        ArrayList<Invoice> inv;
        Customer cus;

        // Nhập và kiểm tra số điện thoại
        do {
            System.out.print("Phone Number: ");
            phoneCus = sc.nextLine();
            cus = custList.findByPhone(phoneCus);
            if (cus == null) {
                System.out.println("This phone number is empty or not found!");
            }
        } while (cus == null);

        // Lấy danh sách hóa đơn của khách hàng
        inv = invoiceList.findidCus(cus.getId());
        if (inv == null || inv.isEmpty()) {
            System.out.println("This customer has no invoices.");
            return;
        }

        System.out.println("Customer Information:");
        System.out.println(cus);

        System.out.println("\nThe Books that the customer has bought:");

        Map<String, Book> uniqueBooks = new HashMap<>();

        // Duyệt hóa đơn và chi tiết hóa đơn
        for (Invoice invoice : inv) {
            ArrayList<InvoiceDetail> details = invoiceDetailsList.find(invoice.getIdInvoice());
            if (details == null) continue;

            for (InvoiceDetail detail : details) {
                String bookId = detail.getIdBook();
                if (!uniqueBooks.containsKey(bookId)) {
                    Book book = bookList.findByID(bookId);
                    if (book != null) {
                        uniqueBooks.put(bookId, book);
                    }
                }
            }
        }

        // In danh sách sách (tránh trùng lặp)
        System.out.printf("%-5s %-10s %-30s\n", "No", "BookID", "Title");
        int index = 1;
        for (Book book : uniqueBooks.values()) {
            System.out.printf("%-5d %-10s %-30s\n", index++, book.getbookID(), book.getTitle());
        }

        sc.close();
    }


    

     

    

      

    public static void main(String[] args) {
        EmployeeList listemp = new EmployeeList();
        CustomerList listCus = new CustomerList();
        Booklist listb = new Booklist();
        ListInvoice listin = new ListInvoice();
        ListInvoiceDetails listdet = new ListInvoiceDetails();
        
        listin.readFile();
        listdet.readFile();
        
        // Example usage
    //    PrintInvoice(listemp, listCus, listin, listdet, listb, "HD100");
        //addInvoice(listemp, listCus, listin, listdet, listb);
       DailySalesReport(listemp, listCus, listin, listdet, listb);
        CustomerSalesRp(listemp, listCus, listin, listdet, listb);
    }
}
