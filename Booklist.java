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
    public ArrayList<Book> getList()
    {
        return list;
    }
    public void setList(ArrayList<Book> list)
    {
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
        this.list=tempList;
    }

    public void displayAll(){
        for(Book hd: list){
            hd.display();
            System.out.println("----------");
        }
    }
    //phuong thuc tim kiem
    public void find(String type, String keyword) {
        ArrayList<Book> result = new ArrayList<>();
        
        switch (type.toLowerCase()) { 
            case "id":
                result = findByID(keyword);
                break;
            case "title":
                result = findByTitle(keyword);
                break;
        }
    
    if (result.isEmpty()) {
            System.out.println("❗ Không tìm thấy sách phù hợp.");
        } else {
            System.out.println("✅ Kết quả tìm thấy:");
            for (Book b : result) {
                b.display();
            }
        }
    }
    //tim theo ID
    public ArrayList<Book> findByID(String bookID) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getbookID().equalsIgnoreCase(bookID)) {
                result.add(b);
            }
        }
        return result;
    }
    //tim theo title
    public ArrayList<Book> findByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }
    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }
    public ArrayList<Book> AddBook(Book book) {
        list.add(book);
        return list;
    }
    public void removeBook(String bookID) {
        boolean removed = list.removeIf(b -> b.getbookID().equalsIgnoreCase(bookID));
        if (removed) {
            System.out.println("✅ Đã xóa sách có ID: " + bookID);
            saveToFile();
        } else {
            System.out.println("❗ Không tìm thấy sách có ID: " + bookID);
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter("books.txt")) {
            for (Book b : list) {
                pw.println(b.getbookID() + "," + b.getTitle() + "," + b.getAuthor() + "," +
                           b.getPublisher() + "," + b.getPrice() + "," + b.getAmount());
            }
            System.out.println("💾 File books.txt đã được cập nhật.");
        } catch (Exception e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }
}

