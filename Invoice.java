import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private String idInvoice;     // Mã hóa đơn
    private String idCustomer;    // Mã khách hàng
    private String idEmployee;    // Mã nhân viên
    private LocalDate time;       // Ngày lập hóa đơn

    public Invoice(String idInvoice, String idCustomer, String idEmployee, LocalDate time) {
        this.idInvoice = idInvoice;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.time = time;
    }

    public String getIdInvoice() {
        return idInvoice;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public LocalDate getTime() {
        return time;
    }

    public void displayInvoice() {
        System.out.println("Invoice ID: " + idInvoice);
        System.out.println("Customer ID: " + idCustomer);
        System.out.println("Employee ID: " + idEmployee);
        System.out.print("Date: ");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(time.format(fmt));
    }
}
