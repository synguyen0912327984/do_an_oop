import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list = new ArrayList<>();

    // Khoi tao
    public Booklist() {}
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

    public void readFile() {
        ArrayList<Book> tempList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 6) {
                    String bookID = arr[0].trim();
                    String title = arr[1].trim();
                    String author = arr[2].trim();
                    String publisher = arr[3].trim();
                    double price = Double.parseDouble(arr[4].trim());
                    int amount = Integer.parseInt(arr[5].trim());

                    Book book = new Book(bookID, title, author, publisher, price, amount);
                    tempList.add(book);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.list = tempList;
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
                result = findByID(keyword);
                break;
            case "title":
                result = findByTitle(keyword);
                break;
            case "author":
                result = findByAuthor(keyword);
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
    public ArrayList<Book> findByID(String bookID) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getbookID().equalsIgnoreCase(bookID)) {
                result.add(b);
            }
        }
        return result;
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
    //Phuong thuc them sach
    public ArrayList<Book> AddBook(Book book) {
        for (Book b : list) 
        {
        if (b.getbookID().equalsIgnoreCase(book.getbookID())) 
            {
            System.out.println("ID da ton tai. Khong the them sach moi."); //Rang buoc ID sach la duy nhat
                return;
            }
        }
        list.add(book);
        return list;
    }
    // Phuong thuc xoa sach
    public void removeBook(String bookID) {
        boolean removed = list.removeIf(b -> b.getbookID().equalsIgnoreCase(bookID));
        if (removed) {
            System.out.println("Da xoa sach co ID: " + bookID);
            saveToFile();
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
                saveToFile();
                return;
            }
        }
        System.out.println("Khong tim thay sach co ID: " + bookID);
    }
    // Phuong thuc cap nhat file
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter("books.txt")) {
            for (Book b : list) {
                pw.println(b.getbookID() + "," + b.getTitle() + "," + b.getAuthor() + "," +
                           b.getPublisher() + "," + b.getPrice() + "," + b.getAmount());
            }
            System.out.println("File books.txt da duoc cap nhat.");
        } catch (Exception e) {
            System.err.println("Loi ghi file: " + e.getMessage());
        }
    }
}
