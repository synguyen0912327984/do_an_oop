
//Day la munu cho phan Customer va Employee

import java.util.ArrayList;
import java.util.Scanner;

public class TestFunc {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeList e = new EmployeeList();
        CustomerList c = new CustomerList();

        int select;
        do {
            System.out.println("\n====== MENU Quan Ly ======");
            System.out.println("1. Tao khach hang moi");
            System.out.println("2. Tao nhan vien moi");
            System.out.println("3. In danh sach khach hang");
            System.out.println("4. In danh sach nhan vien");
            System.out.println("5. Tim kiem khach hang theo id");
            System.out.println("6. Tim kien nhan vien theo id");
            System.out.println("7. Tim kiem khach hang theo ten");
            System.out.println("8. Tim kiem nhan vien theo ten");
            System.out.println("9. Tim kiem khach hang theo so dien thoai");
            System.out.println("10. Tim kiem nhan vien cho so dien thoai");
            System.out.println("11. Xoa khach hang trong cua hang");
            System.out.println("12.Sa thai nhan vien trong cua hang");
            System.out.println("0. Thoat");
            System.out.println("==========================");
            System.out.print(" Nhap su lua chon cua ban: ");

            select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 0:
                    e.saveToFile();
                    c.saveToFile();
                    System.out.println("TAM BIET!");
                    break;

                case 1:
                    System.out.println("Nhap thong tin khach hang moi:");
                    addCustomerFromInput(sc, c);
                    break;

                case 2:
                    System.out.println("Nhap thong tin nhan vien moi:");
                    addEmployeeFromInput(sc, e);
                    break;

                case 3:
                    System.out.println("\n Danh sach khach hang:");
                    c.displayAll();
                    break;

                case 4:
                    System.out.println("\n Danh sách nhan vien:");
                    e.displayAll();
                    break;

                case 5:
                    System.out.print("Nhap id khach hang can tim: ");
                    String customerId = sc.nextLine();
                    Customer rsCId = c.findById(customerId);
                    if (rsCId != null) {
                        System.out.println("Tim thay khach hang:");
                        rsCId.displayinfo();
                    } else {
                        System.out.println("Khong tim thay khach hang voi ID: " + customerId);
                    }
                    break;

                case 6:
                    System.out.print("Nhap id nhan vien can tim: ");
                    String employeeId = sc.nextLine();
                    Employee rsEId = e.findById(employeeId);
                    if (rsEId != null) {
                        System.out.println("Tim thay khach hang:");
                        rsEId.displayinfo();
                    } else {
                        System.out.println("Khong tim thay khach hang voi ID: " + employeeId);
                    }
                    break;
                case 7:
                    System.out.println("Nhap ten khach hang can tim");
                    String customerName = sc.nextLine();
                    ArrayList<Customer> rsCName = c.findAllByName(customerName);
                    for (Customer cus : rsCName) {
                        cus.displayinfo();
                    }
                    break;
                case 8:
                    System.out.println("Nhap ten nhan vien can tim");
                    String employeeName = sc.nextLine();
                    ArrayList<Employee> rsEName = e.findAllByName(employeeName);
                    if (rsEName.isEmpty()) {
                        for (Employee emp : rsEName) {
                            emp.displayinfo();
                        }
                    } else {
                        System.out.println("Khong tim thay ten nao hop le");
                    }
                    break;
                case 9:
                    System.out.println("Nhap so dien thoai khach hang can tim");
                    String customerPhone = sc.nextLine();
                    Customer rsCPhone = c.findByPhone(customerPhone);
                    if (rsCPhone != null) {
                        rsCPhone.displayinfo();
                    } else {
                        System.out.println("Khong tim thay so dien thoai");
                    }
                    break;
                case 10:
                    System.out.println("Nhap so dien thoai nhan vien can tim");
                    String employeePhone = sc.nextLine();
                    Employee rsEPhone = e.findByPhone(employeePhone);
                    if (rsEPhone != null) {
                        rsEPhone.displayinfo();
                    } else {
                        System.out.println("Khong tim thay so dien thoai");
                    }
                    break;
                case 11:
                    System.out.println();
                    break;
                case 12:
                    break;
                default:
                    System.out.println("Lua chon khong hop le , vui long thu lai!");
                    break;
            }
        } while (select != 0);
        sc.close();
    }

    public static void addCustomerFromInput(Scanner sc, CustomerList c) {
        System.out.println("--- Tao khach hang moi ---");
        System.out.print("Nhap ten khach hang: ");
        String name = sc.nextLine();
        System.out.print("Nhap SDT: ");
        String phone = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        String address = sc.nextLine();

        Customer newCustomer = new Customer(name, phone, address, 0);

        c.add(newCustomer);
        System.out.println("Da tao khach hang moi thanh cong voi ID: " + newCustomer.getId());
    }

    public static void addEmployeeFromInput(Scanner sc, EmployeeList e) {
        System.out.println("--- Them nhan vien moi ---");
        System.out.print("Nhap ten nhan vien: ");
        String name = sc.nextLine();
        System.out.print("Nhap SDT: ");
        String phone = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        String address = sc.nextLine();
        System.out.println("Nhap chuc vu: ");
        String position = sc.nextLine();
        System.out.println("Nhap luong: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        Employee newEmployee = new Employee(name, phone, address, position, salary);

        e.add(newEmployee);
        System.out.println("Da them nhan vien moi thanh cong voi ID: " + newEmployee.getId());
    }
}
