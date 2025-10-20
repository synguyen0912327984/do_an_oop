public class InvoiceDetail {
    private String idInvoice;   
    private String idBook;       
    private String nameBook;     
    private int quantity;        
    private double price;        
    private double total;        

    
    public InvoiceDetail() {}

    
    public InvoiceDetail(String idInvoice, String idBook, String nameBook, int quantity, double price) {
        this.idInvoice = idInvoice;
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price; // Tự động tính tổng tiền
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

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.total = this.quantity * this.price; // Cập nhật lại tổng tiền
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.total = this.quantity * this.price; // Cập nhật lại tổng tiền
    }

    public double getTotal() {
        return total;
    }

    // Hàm hiển thị thông tin chi tiết hóa đơn
    public void displayDetail() {
        System.out.println("Invoice ID: " + idInvoice);
        System.out.println("Book ID: " + idBook);
        System.out.println("Book Name: " + nameBook);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: " + price);
        System.out.println("Total: " + total);
        System.out.println("---------------------------");
    }
}
