package Java.project.team;
import java.util.*;
public class Booklist {
    private Book[] list;
    private int n; // số lượng sách
    //Khởi tạo
    public Booklist()
    {
        list = new Book[100];
        n = 0; 
    };
    //Nhập danh sách
    public void InputList(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số lượng sách:");
        n = sc.nextInt();
        for(int i = 0;i<n;i++){
            list[i] = new Book(); //khởi tạo
            list[i].Input(); //nhập
        }
    }
    //xuất danh sách
    public void OutputList(){
        System.out.println("Các sách hiện tại là:");
        for(int i = 0; i < n;i++){
            System.out.print(list[i].toString()); 
        }
    }
    //Thêm sách
    public void AddBook(){

    }
    //Xóa sách theo ID
    public void DeleteBookByID(){

    }
    //Sửa sách theo ID
    public void EditBookByID(){

    }
    //Tìm sách theo ID
    public void FindBookByID(){

    }
}
