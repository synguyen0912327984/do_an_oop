import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "books.txt";
    // Khoi tao
    public Booklist() {
        list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                list.add(Book.fromString(line));// neu sua lai thanh AddBook thi se bi dinh Scanner, ket trong vong lap trong ham Addbook
            }

        } catch (Exception e) {
            e.printStackTrace();
        }}
    public Booklist(ArrayList<Book> list){
        this.list = list;
    }

    // Get va set
    public int getQuantity(){
        return list.size();
    }
    public ArrayList<Book> getList() {
        return list;
    }
    public void setList(ArrayList<Book> list) {
        this.list = list;
    }

    

    public void displayAll() {
        for (Book hd : list) {
            if(hd.isActive()){
            hd.display();
            System.out.println("----------");
            }
        }
    }

    // Phuong thuc tim kiem
    public void find(String type, String keyword) {
        ArrayList<Book> result = new ArrayList<>();
        
        switch (type.toLowerCase()) { 
            case "id":
                Book found = findByID(keyword);
                if (found != null) result.add(found); // do kieu du lieu tra ve la Book 
                break;
            case "title":
                result = findByTitle(keyword);
                break;
            case "author":
                result = findByAuthor(keyword);
                break;
            case "publisher":
                result = findByPublisher(keyword);
                break;
        }
    
        if (result.isEmpty()) {
            System.out.println("Khong tim thay sach phu hop.");
        } else {
            System.out.println("Ket qua tim thay:");
            for (Book b : result) {
                b.display();
            }
        }
    }

    // Tim theo ID
    public Book findByID(String bookID) {
        for (Book b : list) {
            if (b.getbookID().equalsIgnoreCase(bookID)) {
                return b;
            }
        }
        return null;
    }

    // Tim theo title
    public ArrayList<Book> findByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    // Tim theo author
    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }
    //Tim theo publisher
    public ArrayList<Book> findByPublisher(String publisher) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getPublisher().toLowerCase().contains(publisher.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }
    //Phuong thuc nhap thong tin sach 
    public void inputBookInfo(Book book){
        
    //nhap title
        boolean valid;
        do
        {
            System.out.print("Nhap title: ");
            String title_test;
            title_test = sc.nextLine();
            valid = !title_test.isEmpty();
            if(valid)
                book.setTitle(title_test);
            else
                System.out.println("Title khong duoc de trong. Vui long nhap lai.");
        }while(!valid);
    //Nhap Author    
        do
        {
            System.out.print("Nhap Author: ");
            String Author_test;
            Author_test = sc.nextLine();
            valid = !Author_test.isEmpty();
            if(valid)
                book.setAuthor(Author_test);
            else
                System.out.println("Author khong duoc de trong. Vui long nhap lai.");
        }while(!valid);
        //Nhap Publisher
        do{
            System.out.print("Nhap Publisher: ");
            String Publisher_test;
            Publisher_test = sc.nextLine();
            valid = !Publisher_test.isEmpty();
            if(valid)
                book.setPublisher(Publisher_test);
            else
                System.out.println("Publisher khong duoc de trong. Vui long nhap lai.");
        }while(!valid);
        //Nhap Price
        do{
            System.out.print("Nhap Price: ");
            String Price_input = sc.nextLine();
            valid = !Price_input.isEmpty();
            if(valid)
                book.setPrice(Double.parseDouble(Price_input));
            else
                System.out.println("Price khong duoc de trong. Vui long nhap lai.");

            if((Double.parseDouble(Price_input))<0)
                System.out.println("Price khong duoc nhap gia tri am. Vui long nhap lai");
            
            
        }while(!valid);
        do{
            System.out.print("Nhap Amount: ");
            String Amount_input = sc.nextLine();
            valid = !Amount_input.isEmpty();
            if(valid)
                book.setAmount(Integer.parseInt(Amount_input));
            else
                System.out.println("Amount khong duoc de trong. Vui long nhap lai.");

            if((Integer.parseInt(Amount_input))<0)
                System.out.println("Amount khong duoc nhap gia tri am. Vui long nhap lai");
            
            
        }while(!valid);
    }
    //Phuong thuc them sach
    public void AddBook(Book book) {
        //Tao ID cho book
        
        int number = getQuantity()+1;
        String test_idBook;
        do{
            test_idBook = "B0" + number;
            number++;
        }
        while (findByID(test_idBook) != null);
        book.setbookID(test_idBook);
        System.out.println("Book ID da duoc tao: " + book.getbookID());

        inputBookInfo(book);
        list.add(book);
        book.setStatus(true); 
        System.out.println("Da them sach thanh cong!");
    }
    
    

    // Phuong thuc xoa sach
    public boolean removeBook(String bookID) 
    {
    for (Book b : list) {
        if (b.getbookID().equalsIgnoreCase(bookID)) {
            if (!b.isActive()) {
                System.out.println("Sach nay da bi danh dau xoa truoc do.");
                return false;
            }
            b.setStatus(false); // danh dau xoa
            
            System.out.println("Da xoa sach co ID:" + bookID); // chi xoa mem
            return true;
        }
    }
    System.out.println("Khong tim thay sach co ID: " + bookID);
    return false;
    }
    
    //phuong thuc in ra danh sach sach da xoa
    public void displayDeletedBooks(){
        System.out.println("Danh sach sach da bi xoa:");
        for (Book b : list) {
            if (!b.isActive()) {
                b.display();
                System.out.println("----------");
            }
        }
    }
    // Phuong thuc sua sach
public void updateBook() {
    System.out.print("Nhap ID sach can sua: ");
    String bookID_Fix = sc.nextLine(); // nhap id sach can sua

    boolean found = false;
    for (Book i : list) {
        if (i.getbookID().equalsIgnoreCase(bookID_Fix)) {
            inputBookInfo(i); // Update sach
            System.out.println("Da cap nhat sach co ID: " + bookID_Fix);
            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Khong tim thay sach co ID: " + bookID_Fix);
    }
}
            
    //Phuong thuc thong ke tong so sach hien co
    public void totalBooks() {
        int total = 0;
        for (Book b : list) {
            if (b.isActive()) {
                total += b.getAmount();
            }
        }
        System.out.println("Tong so sach hien co trong kho: " + total);
    }
    //Phuong thuc thong ke cac sach co so luong > x
    public void booksGreaterThan(){
        System.out.println("Nhap so luong x: ");
        int x = sc.nextInt();
        System.out.println("Cac sach co so luong > " + x + ":");
        for (Book b : list) {
            if (b.isActive() && b.getAmount() > x) {
                b.display();
                System.out.println("----------");
            }
        }
    }
    // Phuong thuc cap nhat file
   public void saveToFile() 
   {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
        for (Book b : list) {
            bw.write(b.getbookID() + "," +
                     b.getTitle() + "," +
                     b.getAuthor() + "," +
                     b.getPublisher() + "," +
                     b.getPrice() + "," +
                     b.getAmount());
            bw.newLine();
        }
        System.out.println("File books.txt da duoc cap nhat.");
    } catch (IOException e) {
        System.err.println("Loi ghi file: " + e.getMessage());
    }
    }
    
}
