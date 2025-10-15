package Java.project.team;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list;  // danh sach
    private int n;                 // số lượng sách
    private static String List_BOOK = "books.txt"; // statuc cho ten file
    // Khởi tạo
    public Booklist() {
        list = new ArrayList<>();
        n = 0;
    }
    //đọc file
     public void readFile() 
    {
        readFile(List_BOOK);
    }

    public void readFile(String filename) 
    {
        
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
