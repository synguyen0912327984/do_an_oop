package oop;
import java.util.*;
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
    //Nhap thong tin
    public void Input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã sách: ");
        bookID = sc.nextLine();

        System.out.print("Nhập tên sách: ");
        Title = sc.nextLine();

        System.out.print("Nhập tác giả: ");
        Author = sc.nextLine();

        System.out.print("Nhập nhà xuất bản: ");
        Publisher = sc.nextLine();

        System.out.print("Nhập giá: ");
        Price = sc.nextDouble();

        System.out.print("Nhập số lượng: ");
        Amount = sc.nextInt();
    }
    // In thong tin
    public void Output()
    {
        System.out.println( "Mã sách: " + bookID
                            + "\n Tên sách:" + Title 
                            + "\n Tác giả:" + Author
                            + "\n Nhà xuất bản:" + Publisher
                            + "\n Giá:" + Price
                            + "\n Số lượng:" + Amount);
    }
    
}


