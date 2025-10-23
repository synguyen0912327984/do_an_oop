import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

<<<<<<< Updated upstream
    public static double calculatetototal(ArrayList<InvoiceDetail> ind,Booklist lb){
        double Alltotal=0;
        for(InvoiceDetail st:ind){
            Alltotal+=st.getQuantity()*lb.findByID(st.getIdBook()).getPrice();
        }
        return Alltotal;
    }

    public static void PrintInvoice(EmployeeList le, CustomerList lc, ListInvoice ln, ListInvoiceDetails ld, Booklist lb, String id) {
=======
    public double calculatetototal(ArrayList<InvoiceDetail> ind,Booklist lb){
        double Alltotal=0;
        for(InvoiceDetail st:ind){
            Alltotal+=st.getQuantity()*lb.findByID(st.getIdBook()).getPrice();
        }
        return Alltotal;
    }

    public static void PrintInvoice(EmployeeList le,CustomerList lc,ListInvoice ln,ListInvoiceDetails ld,Booklist lb,String id){
        Invoice inv = new Invoice();
        ArrayList<InvoiceDetail> ind = new ArrayList<>();
        if(ln.test(id)==null){
            System.out.println(id+"is empty");
            return ;
        }
        inv=ln.test(id);
        ind=ld.find(id);    
        System.out.println("Customer:"+lc.findById(inv.getIdCustomer()).getName());
        System.out.println("Staff:"+le.findById(inv.getIdEmployee()).getName());
        for(int i=0;i<ind.size();i++){
            System.out.println(lb.findByID(ind.get(i).getIdBook()).);
        }
>>>>>>> Stashed changes
        
        
        if (ln.test(id) == null) {
            System.out.println("❌ Hóa đơn " + id + " không tồn tại.");
            return;
        }

        Invoice inv = ln.test(id);
        ArrayList<InvoiceDetail> ind = ld.find(id);

        
        System.out.println("==============================================================");
        System.out.println("                       HÓA ĐƠN BÁN SÁCH                      ");
        System.out.println("==============================================================");
        System.out.println("IdInvoice: " + inv.getIdInvoice());
        System.out.println("Date   : " + inv.getTime());
        System.out.println("Customer : " + lc.findById(inv.getIdCustomer()).getName());
        System.out.println("Staff  : " + le.findById(inv.getIdEmployee()).getName());
        System.out.println("--------------------------------------------------------------");
        

        // 🔹 In tiêu đề bảng
        System.out.printf("%-5s %-40s %-10s %-10s %-10s%n",
            "No", "Name", "Amount", "Price", "Total");
        System.out.println("--------------------------------------------------------------");

        double totalAll = 0;
        
        for (int i = 0; i < ind.size(); i++) {
            InvoiceDetail d = ind.get(i);
            Book b = lb.findByID(d.getIdBook());
            double total = d.getQuantity() * b.getPrice();
            totalAll += total;

            System.out.printf("%-5d %-40s %-10d %-10.2f %-10.2f%n",
                (i + 1), b.getTitle(), d.getQuantity(), b.getPrice(), total);
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-55s %-10s %-10.2f VND%n", "", "AllToTal:", totalAll);
        System.out.println("==============================================================");
    }


    public static void addCustomerold(EmployeeList le,CustomerList lc,ListInvoice ln,ListInvoiceDetails ld,Booklist lb){
=======
    } 

    public static void addCustomerold(EmployeeList le,CustomerList lc,ListInvoice ln,ListInvoiceDetails ld,Booklist lb){
        String idInvoice = new String();
        idInvoice="HD";
        





>>>>>>> Stashed changes
        Scanner sc = new Scanner(System.in);
        boolean n = true;
        String id = new String();
        Invoice inv = new Invoice();
        String idInvoice = new String();
        ArrayList<Book> ib = new ArrayList<>();
        int number=ln.getQuantity()+1;
        do{
            idInvoice="HD"+idInvoice+number;
            number++;
        }while(ln.test(idInvoice)!=null);
        System.out.println(idInvoice);
        
        inv.setIdInvoice(idInvoice);





        do{
            System.out.print("idCus:");
            id=sc.nextLine();
            if(lc.findById(id)==null){
                System.out.println("id is empty");
            }else{
                n =false;
            }
        }while(n);
        inv.setIdCustomer(id);
        inv.setTime(LocalDate.now());
        do{
            System.out.print("idEmp:");
            id=sc.nextLine();
            if(le.findById(id)==null){
                System.out.println("id is empty");
            }else{
                n=true;
            }
        }while(!n);
        inv.setIdCustomer(id);
            boolean n1;
            String idbook = new String();
            int quantity;
            boolean choice=true;
            
        do{
            n1=true;
            do {
                System.out.println("Idbook:");
                id=sc.nextLine();
                if(lb.findByID(idbook)==null){
                    System.out.println("id empty");
                }else{
                    n1=false;
                }
            } while (n1);
            do {
                System.out.print("quantity:");
                quantity=sc.nextInt();
                if(quantity<lb.findByID(idbook).getAmount()){
                    System.out.println("Dont enough to buy");
                }
            } while (n1);
            System.out.println("1.continue");
            System.out.println("correct");
            
        }while(choice);
        
        
    }




    public static void main(String[] args) {
        EmployeeList listemp = new EmployeeList();
        CustomerList listCus = new CustomerList();
        Booklist listb = new Booklist();
        ListInvoice listin = new ListInvoice();
        ListInvoiceDetails listdet = new ListInvoiceDetails();
        listin.readFile();
        listdet.readFile();
        // in hoa don 


        PrintInvoice(listemp, listCus, listin, listdet, listb, "HD011");
        addCustomerold(listemp, listCus, listin, listdet, listb);

        
        
        
    }
}
