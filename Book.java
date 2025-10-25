public class Book {
    private String bookID;
    private String Title;
    private String Author;
    private String Publisher;
    private double Price;
    private int Amount;
    private boolean status; //trang thai sach con hoat dong hay khong

    // Khoi tao
    public Book() {}
    public Book(String bookID,String Title,String Author,String Publisher,double Price,int Amount,boolean status){
        this.bookID = bookID;
        this.Title = Title;
        this.Author = Author;
        this.Publisher = Publisher;
        this.Price = Price;
        this.Amount = Amount;
        this.status = true; //mac dinh la con hoat dong
    }

    // Get va set
    public boolean isActive() { //ho tro ham xoa 
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getbookID() //Mã Sách
    {
        return bookID;
    }
    public void setbookID(String bookID)
    {
        this.bookID = bookID;
    }
    public String getTitle() //Tên sách
    {
        return Title;
    }
    public void setTitle(String Title)
    {
        this.Title = Title;
    }
    public String getAuthor()//Tác giả
    {
        return Author;
    }
    public void setAuthor(String Author)
    {
        this.Author = Author;
    }
    public String getPublisher()// NXB
    {
        return Publisher;
    }
    public void setPublisher(String Publisher)
    {
        this.Publisher = Publisher;
    }
    public double getPrice()// Giá
    {
        return Price;
    }
    public void setPrice(double Price)
    {
        this.Price = Price;
    }
    public int getAmount()//Số lượng
    {
        return Amount;
    }
    public void setAmount(int Amount)
    {
        this.Amount = Amount;
    }


    public void display() {
        System.out.println("=====================================");
        System.out.println("Book ID     : " + bookID);
        System.out.println("Title       : " + Title);
        System.out.println("Author      : " + Author);
        System.out.println("Publisher   : " + Publisher);
        System.out.println("Price       : " + String.format("%,.0f VND", Price));
        System.out.println("Quantity    : " + Amount);
        System.out.println("=====================================");
    }

    @Override
    public String toString() {
        return bookID + "," + Title + "," + Author + "," + Publisher + "," + Price + "," + Amount + "," + status;
    }
    public static Book fromString(String line) {
    String[] p = line.split(";");
    if (p.length == 7) { 
        try {
            String bookID = p[0];
            String title = p[1];
            String author = p[2];
            String publisher = p[3];
            double price = Double.parseDouble(p[4]);
            int amount = Integer.parseInt(p[5]);
            boolean status = Boolean.parseBoolean(p[6]);
            return new Book(bookID, title, author, publisher, price, amount, status);
        } catch (NumberFormatException e) {
            System.err.println("Loi dinh dang du lieu:" + e.getMessage());
        }
    } else {
        System.err.println("Dong du lieu khong hop le:" + line);
    }
    return null;
}

    
}