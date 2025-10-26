import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Booklist {
    private ArrayList<Book> list;
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
    public void find(int type, String keyword) {
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
            System.out.println("Khong tim thay sach phu hop.");
        } else {
            System.out.println("Ket qua tim thay: ");
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
         // Nhap Price
    do {
        System.out.print("Nhap Price: ");
        String priceInput = sc.nextLine().trim();
        try {
            double price = Double.parseDouble(priceInput);
            if (price < 0) {
                System.out.println("Price khong duoc am. Vui long nhap lai.");
                valid = false;
            } else {
                book.setPrice(price);
                valid = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Gia tri Price phai la so. Vui long nhap lai.");
            valid = false;
        }
    } while (!valid);

    // Nhap Amount
    do {
        System.out.print("Nhap Amount: ");
        String amountInput = sc.nextLine().trim();
        try {
            int amount = Integer.parseInt(amountInput);
            if (amount < 0) {
                System.out.println("Amount khong duoc am. Vui long nhap lai.");
                valid = false;
            } else {
                book.setAmount(amount);
                valid = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Gia tri Amount phai la so nguyen. Vui long nhap lai.");
            valid = false;
        }
    } while (!valid);
}

    //Phuong thuc them sach
    public void AddBook(Book book) {
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
        System.out.println("Book ID da duoc tao: " + book.getbookID());

        inputBookInfo(book);
        list.add(book);
        book.setStatus(true); 
        System.out.println("Da them sach thanh cong!");
    }
    
    

    public void removeBook() {
    while (true) {
        System.out.print("Nhap ID sach can xoa: ");
        String bookID = sc.nextLine().trim();

        // Kiem tra ID khong duoc de trong
        if (bookID.isEmpty()) {
            System.out.println("ID sach khong duoc de trong. Vui long nhap lai!");
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
                    System.out.println("Sach nay da bi danh dau xoa truoc do.");
                    break;
                }

                // Danh dau xoa mem
                b.setStatus(false);
                System.out.println("Da xoa sach co ID: " + bookID);
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay sach co ID: " + bookID);
        }

        // Hoi nguoi dung co muon xoa tiep khong
        System.out.print("Ban co muon xoa tiep khong? (Y/N): ");
        String ans = sc.nextLine().trim();
        if (ans.equalsIgnoreCase("N")) {
            break;
        }
    }
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
            while (true){
            System.out.print("Nhap ID sach can sua: ");
            String bookID_Fix = sc.nextLine(); // nhap id sach can sua
            if(bookID_Fix.isEmpty()){
                System.out.println("ID sach khong duoc de trong.");
                break;
            }
            
            for (Book i : list) {
                boolean found = false;
                if(i.isActive()==true){
                    if (i.getbookID().equalsIgnoreCase(bookID_Fix)) {
                        inputBookInfo(i); // Update sach
                        System.out.println("Da cap nhat sach co ID: " + bookID_Fix);
                        found = true;
                        return;
                }
            }
                else if (!i.isActive()){
                System.out.println("Sach nay da bi xoa, khong the sua.");
                break;
                }
                if (!found){ 
                System.out.println("Khong tim thay sach co ID: " + bookID_Fix);
                break;
            }
        }

            
            
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
        boolean found = false;
        for (Book b : list) {
            if (b.isActive() && b.getAmount() > x) {
                if (!found) {
                    System.out.println("\n===== SACH CO SO LUONG > " + x + " =====");
                    found = true;
                }
                b.display();
                System.out.println("----------");
                found = true;
            }
        }
        if(!found){
            System.out.println("Khong co sach nao co so luong > " + x);
        }
    }
    
    // Phuong thuc cap nhat file
   public void saveToFile() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
        for (Book b : list) {
            bw.write(b.toString());
            bw.newLine();
        }
        System.out.println("File books.txt da duoc cap nhat thanh cong.");
    } catch (IOException e) {
        System.err.println("Loi ghi file: " + e.getMessage());
    }
}
    
}
