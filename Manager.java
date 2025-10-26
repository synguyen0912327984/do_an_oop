import java.util.ArrayList;
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
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
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
        
        
        // Save invoice and details
    //    
        
    }   


   public static void DailySalesReport(EmployeeList empList, CustomerList custList, ListInvoice invoiceList, 
                                    ListInvoiceDetails invoiceDetailsList, Booklist bookList) {
    Scanner sc = new Scanner(System.in);
    LocalDate date = null;
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    while (true) {
        try {
            System.out.print("Enter date (dd/MM/yyyy): ");
            String input = sc.nextLine();
            date = LocalDate.parse(input, fmt);
            if (date.isAfter(LocalDate.now())) {
                System.out.println(" The date cannot be in the future!");
                continue;
            }
            break;
        } catch (Exception e) {
            System.out.println(" Invalid date, please try again!");
        }
    }

    ArrayList<Invoice> inv = new ArrayList<>();
    inv = invoiceList.findtime(date);
    ArrayList<InvoiceDetail> ind = new ArrayList<>();
    Book b = new Book();
    ArrayList<Book> listb = new ArrayList<>();
    for(Invoice st:inv){
        st.displayInvoice();
        ind.addAll(invoiceDetailsList.find(st.getIdInvoice()));
        
    }
    System.out.println("Daily book sales report");
    System.out.printf("");
    for(Invoice st: ind){
        
    }
    
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
    }
}
