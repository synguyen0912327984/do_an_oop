import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list;
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
    public ArrayList<Book> find(int type, String keyword) {
        ArrayList<Book> result = new ArrayList<>();
        
        switch (type) { 
            case 1:
                Book found = findByID(keyword);
                if (found != null) result.add(found); // do kieu du lieu tra ve la Book 
                break;
            case 2:
                result = findByTitle(keyword);
                break;
            case 3:
                result = findByAuthor(keyword);
                break;
            case 4:
                result = findByPublisher(keyword);
                break;
        }
        if (result.isEmpty()) {
            System.out.println("No matching books found(or book may have been deleted).");
        } else {
            System.out.println("Search results:");
            for (Book b : result) {
                b.display();
            }
        }
        return result;
    }

    // Tim theo ID
    public Book findByID(String bookID) {
        for (Book b : list) {
            if (b.getbookID().equalsIgnoreCase(bookID) && b.isActive()) {
                return b;
            }
        }
        return null;
    }

    // Tim theo title
   public ArrayList<Book> findByTitle(String title) {
    ArrayList<Book> result = new ArrayList<>();
    for (Book b : list) {
        if (b.getTitle().toLowerCase().contains(title.toLowerCase()) && b.isActive()) {
            result.add(b);
        }
    }
    return result;
}


    // Tim theo author
    public ArrayList<Book> findByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())&& b.isActive()) {
                result.add(b);
            }
        }
        return result;
    }
    //Tim theo publisher
    public ArrayList<Book> findByPublisher(String publisher) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : list) {
            if (b.getPublisher().toLowerCase().contains(publisher.toLowerCase())&& b.isActive()) {
                result.add(b);
            }
        }
        return result;
    }
    //Phuong thuc nhap thong tin sach 
    public void inputBookInfo(Book book, Scanner sc){
        
    //nhap title
        boolean valid;
        do
        {
            System.out.print("Enter title: ");
            String title_test;
            title_test = sc.nextLine();
            valid = !title_test.isEmpty();
            if(valid)
                book.setTitle(title_test);
            else
                System.out.println("Title cannot be empty. Try again.");
        }while(!valid);
    //Nhap Author    
        do
        {
            System.out.print("Enter Author: ");
            String Author_test;
            Author_test = sc.nextLine();
            valid = !Author_test.isEmpty();
            if(valid)
                book.setAuthor(Author_test);
            else
                System.out.println("Author cannot be empty. Try again.");
        }while(!valid);
        //Nhap Publisher
        do{
            System.out.print("Enter Publisher: ");
            String Publisher_test;
            Publisher_test = sc.nextLine();
            valid = !Publisher_test.isEmpty();
            if(valid)
                book.setPublisher(Publisher_test);
            else
                System.out.println("Publisher cannot be empty. Try again.");
        }while(!valid);
         // Nhap Price
    do {
        System.out.print("Enter Price: ");
        String priceInput = sc.nextLine().trim();
        try {
            double price = Double.parseDouble(priceInput);
            if (price < 0) {
                System.out.println("Price cannot be empty. Try again.");
                valid = false;
            } else {
                book.setPrice(price);
                valid = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Price must be a number. Try again.");
            valid = false;
        }
    } while (!valid);

    // Nhap Amount
    do {
        System.out.print("Enter Amount: ");
        String amountInput = sc.nextLine().trim();
        try {
            int amount = Integer.parseInt(amountInput);
            if (amount < 0) {
                System.out.println("Amount cannot be negative. Try again.");
                valid = false;
            } else {
                book.setAmount(amount);
                valid = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Amount must be integer. Try again.");
            valid = false;
        }
    } while (!valid);
}

    //Phuong thuc them sach
    public void AddBook(Book book, Scanner sc) {
        //Tao ID cho book
        
        int number = getQuantity()+1;
        String test_idBook;
        do{ 
            if(number<100){
                test_idBook = "B0" + number;
                number++;
            }
            else{
                test_idBook = "B" + number;
                number++;
            }
        }
        while (findByID(test_idBook) != null);
        book.setbookID(test_idBook);
        System.out.println("Book's ID has been created: " + book.getbookID());

        inputBookInfo(book, sc);
        list.add(book);
        book.setStatus(true); 
        System.out.println("Book added!");
    }
    
    

    public void removeBook(Scanner sc) {
    while (true) {
        System.out.print("Enter Book's ID to delete: ");
        String bookID = sc.nextLine().trim();

        // Kiem tra ID khong duoc de trong
        if (bookID.isEmpty()) {
            System.out.println("ID cannot be empty. Try again");
            continue;
        }

        boolean found = false;

        for (Book b : list) {
            // Bo qua cac phan tu null neu co
            if (b == null) continue;

            if (b.getbookID().equalsIgnoreCase(bookID)) {
                found = true;

                // Kiem tra sach da bi danh dau xoa truoc do
                if (!b.isActive()) {
                    System.out.println("This book has already been marked as deleted.");
                    break;
                }

                // Danh dau xoa mem
                b.setStatus(false);
                System.out.println("Book with ID " + bookID + " has been deleted.");
                break;
            }
        }

        if (!found) {
            System.out.println("No book found with ID: " + bookID);
        }
        // Hoi nguoi dung co muon xoa tiep khong
        System.out.print("Do you want to delete another one? (Y/N): ");
        String ans = sc.nextLine().trim();
        if (ans.equalsIgnoreCase("N")) {
            break;
        }
  
    }
}

    //phuong thuc in ra danh sach sach da xoa
    public void displayDeletedBooks(){
        System.out.println("List of deleted books:");
        for (Book b : list) {
            if (!b.isActive()) {
                b.display();
                System.out.println("----------");
            }
        }
    }
    // Phuong thuc sua sach
    public void updateBook(Scanner sc) {
            while (true){
                System.out.print("Enter Book's ID to edit: ");
                String bookID_Fix = sc.nextLine(); // nhap id sach can sua
                if(bookID_Fix.isEmpty()){
                    System.out.println("ID cannot be blank.");
                    break;
                }
                
                for (Book i : list) {
                    boolean found = false;
                    if(i.isActive()==true){
                        if (i.getbookID().equalsIgnoreCase(bookID_Fix)) {
                            edit(i, sc); // Update sach
                            System.out.println("Updated books with ID: " + bookID_Fix);
                            found = true;
                            return;
                        }
                    }
                    else if (!i.isActive()){
                        System.out.println("Books are deleted. Cannot be changed.");
                        break;
                    }
                    if (!found){ 
                        System.out.println("Cannot find books with ID: " + bookID_Fix);
                        break;
                    }
                }
            }
        }

    public void edit(Book a, Scanner sc) {
        if(a == null){
            System.out.println("Book not found.");
            return;
        }
        if(a.isActive() == false){
        System.out.println("Cannot edit a deleted book.");
        return;
        }
        System.out.println("EDIT:");
        System.out.print("1. Title\n2. Author\n3. Publisher\n4. Price\n5. Amount\nEnter: ");
        int keys = Menu2.readIntInput();
        boolean valid;
        switch (keys) {
            case 1: // Title
                do{
                    System.out.print("Enter title: ");
                    String title_test;
                    title_test = sc.nextLine();
                    valid = !title_test.isEmpty();
                    if(valid)
                        a.setTitle(title_test);
                    else
                        System.out.println("Title cannot be empty. Try again.");
                }while(!valid);
                System.out.println("Changed successfully!");
                break;
            case 2: // Author
                do{
                    System.out.print("Enter Author: ");
                    String Author_test;
                    Author_test = sc.nextLine();
                    valid = !Author_test.isEmpty();
                    if(valid)
                        a.setAuthor(Author_test);
                    else
                        System.out.println("Author cannot be empty. Try again.");
                }while(!valid);
                System.out.println("Changed successfully!");
                break;
            case 3: // Publisher
                do{
                    System.out.print("Enter new publisher: ");
                    String Publisher_test;
                    Publisher_test = sc.nextLine();
                    valid = !Publisher_test.isEmpty();
                    if(valid)
                        a.setPublisher(Publisher_test);
                    else
                        System.out.println("Publisher cannot be empty. Try again.");
                }while(!valid);
                System.out.println("Changed successfully!");
                break;
            case 4: // Price
                do {
                    System.out.print("Enter new price: ");
                    String priceInput = sc.nextLine().trim();
                    try {
                        double price = Double.parseDouble(priceInput);
                        if (price < 0) {
                            System.out.println("Price cannot be empty. Try again.");
                            valid = false;
                        } else {
                            a.setPrice(price);
                            valid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Price must be a number. Try again.");
                        valid = false;
                    }
                } while (!valid);
                System.out.println("Changed successfully!");
                break;
            case 5: // Amount
                do {
                    System.out.print("Enter new amount: ");
                    String amountInput = sc.nextLine().trim();
                    try {
                        int amount = Integer.parseInt(amountInput);
                        if (amount < 0) {
                            System.out.println("Amount cannot be negative. Try again.");
                            valid = false;
                        } else {
                            a.setAmount(amount);
                            valid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Amount must be integer. Try again.");
                        valid = false;
                    }
                } while (!valid);
                System.out.println("Changed successfully!");
                break;
            default:
                System.out.println("Cancelled.");
                break;
        }
            a.display();
    }

        
    
            
    //Phuong thuc thong ke tong so sach hien co
    public void totalBooks() {
        int total = 0;
        for (Book b : list) {
            if (b.isActive()) {
                total += b.getAmount();
            }
        }
        System.out.println("Total number of books currently in stock: " + total);
    }
    //Phuong thuc thong ke cac sach co so luong > x
    public void booksGreaterThan(Scanner sc){
        System.out.println("Enter quantity x: ");
        int x = Menu2.readIntInput();
        if(x <= 0) {
            System.out.println("x cannot below or equal 0.");
            return;
        }
        boolean found = false;
        for (Book b : list) {
            if (b.isActive() && b.getAmount() > x) {
                if (!found) {
                    System.out.println("\n===== BOOKS WITH QUANTITY > " + x + " =====");
                    found = true;
                }
                b.display();
                System.out.println("----------");
                found = true;
            }
        }
        if(!found){
            System.out.println("No books found with quantity greater than " + x);
        }
    }
    
    // Phuong thuc cap nhat file
   public void saveToFile() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
        for (Book b : list) {
            bw.write(b.toString());
            bw.newLine();
        }
        System.out.println("File saved successfully: books.txt ");
    } catch (IOException e) {
        System.err.println("Error: " + e.getMessage());
    }
}
    
}
