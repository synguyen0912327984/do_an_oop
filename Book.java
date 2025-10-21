public class Book {
    private String bookID;
    private String Title;
    private String Author;
    private String Publisher;
    private double Price;
    private int Amount;

    // Khoi tao
    public Book() {}
    public Book(String bookID,String Title,String Author,String Publisher,double Price,int Amount){
        this.bookID = bookID;
        this.Title = Title;
        this.Author = Author;
        this.Publisher = Publisher;
        this.Price = Price;
        this.Amount = Amount;
    }

    // Get va set
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
        return bookID + "," + Title + "," + Author + "," + Publisher + "," + Price + "," + Amount;
    }
    
}