public class Invoice {

    // ✅ static nested class
    public static class Date {
        private int day;
        private int month;
        private int year;

        public Date(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public void display() {
            System.out.println(day + "/" + month + "/" + year);
        }
    }

    private String idInvoice;
    private String idCustomer;
    private String nameCustomer;
    private Date time;

    public Invoice(String idInvoice, String idCustomer, String nameCustomer, Date time) {
        this.idInvoice = idInvoice;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.time = time;
    }

    public void displayInvoice() {
        System.out.println("Invoice ID: " + idInvoice);
        System.out.println("Customer ID: " + idCustomer);
        System.out.println("Customer Name: " + nameCustomer);
        System.out.print("Date: ");
        time.display();
    }
}
