import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list = new ArrayList<>();
    private static final String FILE_NAME = "books.txt";
    // Khoi tao
    public Booklist() {
        list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                AddBook(Book.fromString(line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }}
    public Booklist(ArrayList<Book> list){
        this.list = list;
    }

    // Get va set
    public ArrayList<Book> getList() {
        return list;
    }
    public void setList(ArrayList<Book> list) {
        this.list = list;
    }

    

    public void displayAll() {
        for (Book hd : list) {
            hd.display();
            System.out.println("----------");
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
    //Phuong thuc them sach
    public void AddBook(Book book) {
    if (book == null) {
        System.out.println("Sach khong hop le (null).");
        return;
    }
    if (book.getbookID() == null || book.getbookID().isEmpty()) {
        System.out.println("Ma sach khong duoc de trong.");
        return;
    }
    if (book.getPrice() < 0 || book.getAmount() < 0) {
        System.out.println("Gia hoac so luong khong hop le.");
        return;
    }

    for (Book b : list) {
        if (b.getbookID().equalsIgnoreCase(book.getbookID())) {
            System.out.println("ID da ton tai. Khong the them sach moi.");
            return;
        }
    }
    list.add(book);
}
    // Phuong thuc xoa sach
    public void removeBook(String bookID) {
        boolean removed = list.removeIf(b -> b.getbookID().equalsIgnoreCase(bookID));
        if (removed) {
            System.out.println("Da xoa sach co ID: " + bookID);
            
        } else {
            System.out.println("Khong tim thay sach co ID: " + bookID);
        }
    }
    // Phuong thuc sua sach
    public void updateBook(String bookID, Book updatedBook) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getbookID().equalsIgnoreCase(bookID)) {
                list.set(i, updatedBook); //thay the phan tu tai vi tri i bang updatedBook
                System.out.println("Da cap nhat sach co ID: " + bookID);
                
                return;
            }
        }
        System.out.println("Khong tim thay sach co ID: " + bookID);
    }
    //Phuong thuc thong ke tong so sach hien co
    public void totalBooks() {

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
