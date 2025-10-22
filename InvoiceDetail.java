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

    // Getter và Setter
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

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }

    public void display(){
        System.out.printf("%-15s %-15s %-10d%n",idInvoice,idBook,quantity);
    }
}
