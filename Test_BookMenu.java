import java.util.Scanner;

public class Test_BookMenu {
       private static Scanner sc = new Scanner(System.in);
       private static Booklist lb = new Booklist();
       private static int choice;
    public static void mainMenu() {

        do {
            System.out.println("\n===== MENU QUAN LY SACH =====");
            System.out.println("1. Them sach moi");
            System.out.println("2. Hien thi tat ca sach");
            System.out.println("3. Xoa sach");
            System.out.println("4. Cap nhat thong tin sach");
            System.out.println("5. Tim kiem sach");
            System.out.println("6. Thong ke");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");

            // Kiem tra dau vao hop le
            while (!sc.hasNextInt()) {
                System.out.print("Vui long nhap so: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // Xoa ky tu Enter

            switch (choice) {
                case 1:
                    Book newBook = new Book();
                    lb.AddBook(newBook);
                    break;

                case 2:
                    lb.displayAll();
                    break;

                case 3:
                    DeletedBooksMenu();
                    break;
                case 4:
                    lb.updateBook();
                    break;  
                case 5:
                    SearchBookMenu();
                    break;
                case 6:
                    AnalysMenu();
                    break;
                case 0:
                    System.out.println("Dang thoat chuong trinh...");
                    lb.saveToFile();
                    break;

                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai!");
            }

        } while (choice != 0);

        sc.close();
    }
    public static void DeletedBooksMenu() {
    int delChoice;

    do {
        System.out.println("\n===== MENU XOA SACH =====");
        System.out.println("1. Xoa sach");
        System.out.println("2. Hien thi sach da xoa");
        System.out.println("0. Quay lai menu chinh");
        System.out.print("Nhap lua chon: ");

        // Kiem tra dau vao hop le
        while (!sc.hasNextInt()) {
            System.out.print("Vui long nhap so: ");
            sc.next(); // Xoa dau vao sai
        }

        delChoice = sc.nextInt();
        sc.nextLine(); // Xoa ky tu Enter

        switch (delChoice) {
            case 1:
                lb.removeBook();
                // Sau khi xoa, quay lai menu xoa
                break;

            case 2:
                lb.displayDeletedBooks();
                break;

            case 0:
                System.out.println("Dang quay lai menu chinh...");
                return; // Thoat khoi menu xoa
            default:
                System.out.println("Lua chon khong hop le, vui long nhap lai!");
        }

    } while (true); // Luon quay lai menu xoa sau moi thao tac
}

    
    public static void SearchBookMenu() {
        System.out.println("1.Tim kiem theo ID");
        System.out.println("2.Tim kiem theo Title");
        System.out.println("3.Tim kiem theo Author");
        System.out.println("4.Tim kiem theo Publisher");
        System.out.print("Nhap lua chon: ");
        if(sc.hasNextInt()) {
            int searchChoice = sc.nextInt();
            sc.nextLine(); // Xoa ky tu Enter
            System.out.print("Nhap thong tin can tim: ");
            String searchInfo = sc.nextLine();
            lb.find(searchChoice, searchInfo);
        } else {
            System.out.println("Vui long nhap so!");
            sc.next(); // Xoa dau vao khong hop le
        }
    }
    public static void AnalysMenu() {
        System.out.println("1.Thong ke sach co so luong > x");
        System.out.println("2.Thong ke sach theo so luong ban chay nhat");
        System.out.println("3.Thong ke toan bo so luong sach hien co trong kho");
        System.out.print("Nhap lua chon: ");
        if(sc.hasNextInt()) {
            int analysChoice = sc.nextInt();
            sc.nextLine(); // Xoa ky tu Enter
            switch (analysChoice) {
                case 1:
                    lb.booksGreaterThan();
                    break;
                case 2:
                    System.out.println("Chua hoan thanh chuc nang nay!");
                    break;
                    case 3:
                    lb.totalBooks();
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai!");
            }
        } else {
            System.out.println("Vui long nhap so!");
            sc.next(); // Xoa dau vao khong hop le
        }
    }
    public static void main(String[] args) {
        mainMenu();
    }
    
}