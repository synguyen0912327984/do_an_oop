import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public double calculateTotal(ArrayList<InvoiceDetail> ind, Booklist lb) {
        double allTotal = 0;
        for (InvoiceDetail st : ind) {
            allTotal += st.getQuantity() * lb.findByID(st.getIdBook()).getPrice();
        }
        return allTotal;
    }

    public static void PrintInvoice(EmployeeList le, CustomerList lc, ListInvoice ln, ListInvoiceDetails ld, Booklist lb, String id) {
        Invoice inv = ln.test(id);
        if (inv == null) {
            System.out.println(id + " is empty");
            return;
        }

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
            if (b == null) continue; // safety check
            double total = d.getQuantity() * b.getPrice();
            totalAll += total;

            System.out.printf("%-5d %-40s %-10d %-10.2f %-10.2f%n",
                    (i + 1), b.getTitle(), d.getQuantity(), b.getPrice(), total);
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-55s %-10s %-10.2f VND%n", "", "AllTotal:", totalAll);
        System.out.println("==============================================================");
    }

    public static void addCustomerOld(EmployeeList le, CustomerList lc, ListInvoice ln, ListInvoiceDetails ld, Booklist lb) {
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

        // Customer ID input
        String id;
        boolean valid;
        do {
            System.out.print("idCus: ");
            id = sc.nextLine();
            valid = lc.findById(id) != null;
            if (!valid) System.out.println("Customer ID not found!");
        } while (!valid);
        inv.setIdCustomer(id);

        // Employee ID input
        do {
            System.out.print("idEmp: ");
            id = sc.nextLine();
            valid = le.findById(id) != null;
            if (!valid) System.out.println("Employee ID not found!");
        } while (!valid);
        inv.setIdEmployee(id);

        inv.setTime(LocalDate.now());
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();

        // Add books
        boolean continueAdding = true;
        while (continueAdding) {
            String idBook;
            int quantity;
            Book book;

            // Book ID input
            do {
                System.out.print("IdBook: ");
                idBook = sc.nextLine();
                book = lb.findByID(idBook);
                if (book == null) System.out.println("Book ID not found!");
            } while (book == null);
            System.out.println(book.getAmount());
            // Quantity input
            do {
                System.out.print("Quantity: ");
                quantity = sc.nextInt();
                sc.nextLine(); // consume newline
                if (quantity > book.getAmount()) {
                    System.out.println("Not enough stock! Available: " + book.getAmount());
                }
            } while (quantity > book.getAmount());

            // Add invoice detail
            InvoiceDetail detail = new InvoiceDetail();
            detail.setIdInvoice(idInvoice);
            detail.setIdBook(idBook);
            detail.setQuantity(quantity);
            invoiceDetails.add(detail);

            // Update stock
            book.setAmount(book.getAmount() - quantity);

            // Continue?
            System.out.print("Add another book? (y/n): ");
            String choice = sc.nextLine();
            continueAdding = choice.equalsIgnoreCase("y");
        }

        // Save invoice and details
        ArrayList<InvoiceDetail> Newden = new ArrayList<>();
        

        System.out.println("Invoice " + idInvoice + " created successfully!");
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
    //    PrintInvoice(listemp, listCus, listin, listdet, listb, "HD011");
        addCustomerOld(listemp, listCus, listin, listdet, listb);
    }
}
