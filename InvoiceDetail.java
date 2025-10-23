public class InvoiceDetail {
    private String idInvoice;   
    private String idBook;           
    private int quantity;                      

    public InvoiceDetail() {}

    public InvoiceDetail(String idInvoice, String idBook, int quantity) {
        this.idInvoice = idInvoice;
        this.idBook = idBook;
        this.quantity = quantity;
    }

    // Getters và Setters
    public String getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void display() {
        System.out.printf("%-15s %-15s %-10d%n", idInvoice, idBook, quantity);
    }

    @Override
    public String toString() {
        return idInvoice + "," + idBook + "," + quantity;
    }

    public static InvoiceDetail fromString(String line) {
        String[] p = line.split(",");
        if (p.length == 3) {
            return new InvoiceDetail(p[0], p[1], Integer.parseInt(p[2]));
        }
        return null;
    }
}
