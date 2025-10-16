package Java.project.team;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    //Book
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

    @Override
        public String toString()
        {
            return "Mã sách: " + bookID
                    + "\nTên sách:" + Title 
                    + "\nTác giả:" + Author
                    + "\nNhà xuất bản:" + Publisher
                    + "\nGiá:" + Price
                    + "\nSố lượng:" + Amount;
        }
        
    }
    //Booklist
    public class Booklist {
        private ArrayList<Book> list;  // danh sach
        private int n;                 // số lượng sách
        private static String List_BOOK = "books.txt"; // static cho ten file
        // Khởi tạo
        public Booklist() {
            list = new ArrayList<>();
            n = 0;
        }
        // Đọc file
        public void readFile() 
        {
            readFile(List_BOOK);
        }

        public void readFile(String filename) 
        {
            try{
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                n = nextInt();
                list = new ArrayList<Book>(n);
                for(int i = 0; i < n; i++){
                    list.get(i).bookID = sc.nextLine();
                    list.get(i).Title = sc.nextLine();
                    list.get(i).Author = sc.nextLine();
                    list.get(i).Publisher = sc.nextLine();;
                    list.get(i).Price = sc.nextDouble();
                    list.get(i).Amount = sc.nextInt();
                }
            } catch(FileNotFoundException e) {
                System.out.println("Cannot open file");
            }
        }

        // Xuất danh sách
        public void OutputList() {
            System.out.println("Các sách hiện tại là:");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).toString());
            }
        }

        // Thêm sách
        public void AddBook() {

        }

        // Xóa sách theo ID
        public void DeleteBookByID() {

        }

        // Sửa sách theo ID
        public void EditBookByID() {

        }

        // Tìm sách theo ID
        public void FindBookByID() {
            
        }
    }

    public void main(String[] args){
        
    }
}
