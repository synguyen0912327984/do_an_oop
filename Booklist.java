import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list;  // danh sach
    private int n;                 // số lượng sách
    private static String List_BOOK = "books.txt"; // static cho ten file
    // Khoi tao
    public Booklist() {
        list = new ArrayList<>();
        n = 0;
    }
    // Doc file
     public void readFile() 
    {
        readFile(List_BOOK);
    }

    public void readFile(String filename) {
            try {
                File file = new File(filename);
                Scanner sc = new Scanner(file);

                n = sc.nextInt();
                sc.nextLine();

                list = new ArrayList<>();

                String bookID, Title, Author, Publisher;
                double Price;
                int Amount;

                for (int i = 0; i < n; i++) {
                    bookID = sc.nextLine();
                    Title = sc.nextLine();
                    Author = sc.nextLine();
                    Publisher = sc.nextLine();
                    Price = sc.nextDouble();
                    Amount = sc.nextInt();
                    sc.nextLine();

                    Book b = new Book(bookID, Title, Author, Publisher, Price, Amount);
                    list.add(b);
                }

                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("Cannot open file");
            }
        }

    // Xuat danh sach
    public void OutputList() {
        System.out.println("Các sách hiện tại là:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).toString());
        }
    }

    // So luong
    public int getN()
    {
        return n;
    }

    public void setN(int n)
    {
        this.n = n;
    }

    // Them sach
    public void AddBook() {

    }

    // Xoa sach theo ID
    public void DeleteBookByID() {

    }

    // Sua sach theo ID
    public void EditBookByID() {

    }

    // Tim sach theo ID
    public void FindBookByID() {

    }
}
