package Java.project.team;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list;  // danh sach
    private int n;                 // số lượng sách
    private static String List_BOOK = "books.txt"; // static cho ten file
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
        try{
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            setN(sc.nextInt());
            list = new ArrayList<Book>(n);
            for(int i = 0; i < n; i++){
                setbookID(sc.nextLine());
                
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

    // So luong
    public int getN()
    {
        return n;
    }

    public void setN(int n)
    {
        this.n = n;
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
