import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static EmployeeList e = new EmployeeList();
    private static CustomerList c = new CustomerList();

    public static void main(String[] args) {
        mainMenu();
        sc.close();
    }

    public static void mainMenu() {
        int select;
        do {
            System.out.println("\n===========================");
            System.out.println("====== MENU QUAN LY CHINH =====");
            System.out.println("===========================");
            System.out.println("1. Quan ly Khach hang");
            System.out.println("2. Quan ly Nhan vien");
            System.out.println("0. Thoat va Luu chuong trinh");
            System.out.println("===========================");
            System.out.print("Nhap lua chon cua ban: ");

            select = readIntInput();

            switch (select) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 0:
                    System.out.println("Dang luu du lieu vao file...");
                    e.saveToFile();
                    c.saveToFile();
                    System.out.println("TAM BIET!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai!");
                    break;
            }
        } while (select != 0);
    }

    public static void customerMenu() {
        int select;
        do {
            System.out.println("\n--- MENU QUAN LY KHACH HANG ---");
            System.out.println("1. Them khach hang moi");
            System.out.println("2. Chinh sua thong tin khach hang (theo ID)");
            System.out.println("3. Xoa khach hang (theo ID)");
            System.out.println("4. Tim kiem khach hang");
            System.out.println("5. In toan bo danh sach khach hang");
            System.out.println("0. Quay lai Menu Chinh");
            System.out.println("---------------------------------");
            System.out.print("Nhap lua chon: ");

            select = readIntInput();

            switch (select) {
                case 1:
                    addCustomerFromInput(sc, c);
                    break;
                case 2:
                    System.out.print("Nhap ID khach hang can chinh sua: ");
                    String editCusId = sc.nextLine();
                    c.editCustomer(editCusId, sc);
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    findCustomerMenu();
                    break;
                case 5:
                    System.out.println("\n--- Danh sach toan bo khach hang ---");
                    c.displayAll();
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        } while (select != 0);
    }

    public static void employeeMenu() {
        int select;
        do {
            System.out.println("\n--- MENU QUAN LY NHAN VIEN ---");
            System.out.println("1. Them nhan vien moi");
            System.out.println("2. Chinh sua thong tin nhan vien (theo ID)");
            System.out.println("3. Sa thai nhan vien (theo ID)");
            System.out.println("4. Tim kiem nhan vien");
            System.out.println("5. In toan bo danh sach nhan vien");
            System.out.println("0. Quay lai Menu Chinh");
            System.out.println("--------------------------------");
            System.out.print("Nhap lua chon: ");

            select = readIntInput();

            switch (select) {
                case 1:
                    addEmployeeFromInput(sc, e);
                    break;
                case 2:
                    System.out.print("Nhap ID nhan vien can chinh sua: ");
                    String editEmpId = sc.nextLine();
                    e.editEmployee(editEmpId, sc);
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    findEmployeeMenu();
                    break;
                case 5:
                    System.out.println("\n--- Danh sach toan bo nhan vien ---");
                    e.displayAll();
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        } while (select != 0);
    }

    public static void findCustomerMenu() {
        int select;
        do {
            System.out.println("\n--- Tim kiem khach hang ---");
            System.out.println("1. Tim theo ID");
            System.out.println("2. Tim theo Ten");
            System.out.println("3. Tim theo So Dien Thoai");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");

            select = readIntInput();
            String keyword;

            switch (select) {
                case 1:
                    System.out.print("Nhap ID khach hang can tim: ");
                    keyword = sc.nextLine();
                    Customer rsCId = c.findById(keyword);
                    if (rsCId != null) {
                        System.out.println("Tim thay khach hang:");
                        rsCId.displayinfo();
                    } else {
                        System.out.println("Khong tim thay khach hang voi ID: " + keyword);
                    }
                    break;
                case 2:
                    System.out.print("Nhap ten khach hang can tim: ");
                    keyword = sc.nextLine();
                    ArrayList<Customer> rsCName = c.findAllByName(keyword);
                    if (!rsCName.isEmpty()) {
                        System.out.println("Tim thay " + rsCName.size() + " ket qua:");
                        for (Customer cus : rsCName) {
                            cus.displayinfo();
                        }
                    } else {
                        System.out.println("Khong tim thay ten nao hop le");
                    }
                    break;
                case 3:
                    System.out.print("Nhap so dien thoai khach hang can tim: ");
                    keyword = sc.nextLine();
                    Customer rsCPhone = c.findByPhone(keyword);
                    if (rsCPhone != null) {
                        System.out.println("Tim thay khach hang:");
                        rsCPhone.displayinfo();
                    } else {
                        System.out.println("Khong tim thay so dien thoai");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        } while (select != 0);
    }

    public static void findEmployeeMenu() {
        int select;
        do {
            System.out.println("\n--- Tim kiem nhan vien ---");
            System.out.println("1. Tim theo ID");
            System.out.println("2. Tim theo Ten");
            System.out.println("3. Tim theo So Dien Thoai");
            System.out.println("0. Quay lai");
            System.out.print("Nhap lua chon: ");

            select = readIntInput();
            String keyword;

            switch (select) {
                case 1:
                    System.out.print("Nhap ID nhan vien can tim: ");
                    keyword = sc.nextLine();
                    Employee rsEId = e.findById(keyword);
                    if (rsEId != null) {
                        System.out.println("Tim thay nhan vien:");
                        rsEId.displayinfo();
                    } else {
                        System.out.println("Khong tim thay nhan vien voi ID: " + keyword);
                    }
                    break;
                case 2:
                    System.out.print("Nhap ten nhan vien can tim: ");
                    keyword = sc.nextLine();
                    ArrayList<Employee> rsEName = e.findAllByName(keyword);
                    if (!rsEName.isEmpty()) {
                        System.out.println("Tim thay " + rsEName.size() + " ket qua:");
                        for (Employee emp : rsEName) {
                            emp.displayinfo();
                        }
                    } else {
                        System.out.println("Khong tim thay ten nao hop le");
                    }
                    break;
                case 3:
                    System.out.print("Nhap so dien thoai nhan vien can tim: ");
                    keyword = sc.nextLine();
                    Employee rsEPhone = e.findByPhone(keyword);
                    if (rsEPhone != null) {
                        System.out.println("Tim thay nhan vien:");
                        rsEPhone.displayinfo();
                    } else {
                        System.out.println("Khong tim thay so dien thoai");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        } while (select != 0);
    }

    private static void deleteCustomer() {
        System.out.print("Nhap ID khach hang can xoa: ");
        String delCusId = sc.nextLine();
        Customer custToDel = c.findById(delCusId);

        if (custToDel == null) {
            System.out.println("Khong tim thay khach hang voi ID: " + delCusId);
        } else {
            System.out.println("Tim thay khach hang: " + custToDel.getName());
            System.out.print("Ban co chac chan muon xoa? (y/n): ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                c.removeById(delCusId);
                System.out.println("Da xoa khach hang thanh cong.");
            } else {
                System.out.println("Da huy thao tac xoa.");
            }
        }
    }

    private static void deleteEmployee() {
        System.out.print("Nhap ID nhan vien can sa thai: ");
        String delEmpId = sc.nextLine();
        Employee empToDel = e.findById(delEmpId);

        if (empToDel == null) {
            System.out.println("Khong tim thay nhan vien voi ID: " + delEmpId);
        } else {
            System.out.println("Tim thay nhan vien: " + empToDel.getName());
            System.out.print("Ban co chac chan muon sa thai? (y/n): ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                e.removeById(delEmpId);
                System.out.println("Da sa thai nhan vien thanh cong.");
            } else {
                System.out.println("Da huy thao tac sa thai.");
            }
        }
    }

    private static int readIntInput() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Loi: Vui long chi nhap so!");
            return -1;
        }
    }

    public static void addCustomerFromInput(Scanner sc, CustomerList c) {
        System.out.println("--- Tao khach hang moi ---");
        System.out.print("Nhap ten khach hang: ");
        String name = sc.nextLine();

        String phone;
        while (true) {
            System.out.print("Nhap SDT: ");
            phone = sc.nextLine();
            if (Person.isValidPhoneNumber(phone)) {
                break;
            } else {
                System.out.println("SDT khong hop le! Phai co 10 so va bat dau bang '0'. Vui long nhap lai.");
            }
        }

        System.out.print("Nhap dia chi: ");
        String address = sc.nextLine();

        Customer newCustomer = new Customer(name, phone, address, 0);

        c.add(newCustomer);
        System.out.println("Da tao khach hang moi thanh cong voi ID: " + newCustomer.getId());
        newCustomer.displayinfo();
    }

    public static void addEmployeeFromInput(Scanner sc, EmployeeList e) {
        System.out.println("--- Them nhan vien moi ---");
        System.out.print("Nhap ten nhan vien: ");
        String name = sc.nextLine();

        String phone;
        while (true) {
            System.out.print("Nhap SDT: ");
            phone = sc.nextLine();
            if (Person.isValidPhoneNumber(phone)) {
                break;
            } else {
                System.out.println("SDT khong hop le! Phai co 10 so va bat dau bang '0'. Vui long nhap lai.");
            }
        }

        System.out.print("Nhap dia chi: ");
        String address = sc.nextLine();
        System.out.print("Nhap chuc vu: ");
        String position = sc.nextLine();

        double salary = 0;
        while (true) {
            try {
                System.out.print("Nhap luong: ");
                salary = Double.parseDouble(sc.nextLine());
                if (salary > 0) {
                    break;
                } else {
                    System.out.println("Luong phai la so duong.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Nhap lieu khong hop le! Vui long nhap so.");
            }
        }

        Employee newEmployee = new Employee(name, phone, address, position, salary);

        e.add(newEmployee);
        System.out.println("Da them nhan vien moi thanh cong voi ID: " + newEmployee.getId());
        newEmployee.displayinfo();
    }
}