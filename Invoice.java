import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private String idInvoice;
    private String idCustomer;
    private String nameCustomer;
    private LocalDate time; 

    public Invoice(String idInvoice, String idCustomer, String nameCustomer, LocalDate time) {
        this.idInvoice = idInvoice;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.time = time;
    }

    
    public String getIdInvoice() {
        return idInvoice;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public LocalDate getTime() {
        return time;
    }

    public void displayInvoice() {
        System.out.println("Invoice ID: " + idInvoice);
        System.out.println("Customer ID: " + idCustomer);
        System.out.println("Customer Name: " + nameCustomer);
        System.out.print("Date: ");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(time.format(fmt));
    }
}
