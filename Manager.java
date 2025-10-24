import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


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
            idInvoice = "HD" + number;
            number++;
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
            if (!valid || 
                !(le.findById(id).getPosition().equalsIgnoreCase("Cashier") ||
                le.findById(id).getPosition().equalsIgnoreCase("Salesman") ||
                le.findById(id).getPosition().equalsIgnoreCase("Manager"))) {
                System.out.println("Employee ID invalid!");
}
        } while (!valid || 
            !(le.findById(id).getPosition().equalsIgnoreCase("Cashier") ||
            le.findById(id).getPosition().equalsIgnoreCase("Salesman") ||
            le.findById(id).getPosition().equalsIgnoreCase("Manager")));


        inv.setTime(LocalDate.now());
        ln.addlist(inv);
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
        // add buy book
        String nameBook = new String();
        String continue1 = new String();
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book();
        boolean continue2;
        do{
            System.out.print("NameBook:");
            nameBook= sc.nextLine()
        }while(continue2)  
        
       

        // Save invoice and details
    //    ArrayList<InvoiceDetail> Newden = new ArrayList<>();
        
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
        // PrintInvoice(listemp, listCus, listin, listdet, listb, "HD011");
         addInvoice(listemp, listCus, listin, listdet, listb);
    }
}
