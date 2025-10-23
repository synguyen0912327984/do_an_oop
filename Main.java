import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
/*     public static void PrintInvoice(EmployeeList le,CustomerList lc,ListInvoice ln,ListInvoiceDetails ld,Booklist lb,String id){
        Invoice inv = new Invoice();
        ArrayList<InvoiceDetail> ind = new ArrayList<>();
        ArrayList<Book> inb = new ArrayList<>();
        if(ln.test(id)==null){
            System.out.println(id+"is empty");
            return ;
        }
        
        for
        
        
        
        
    }*/ 

    public static void addCustomerold(EmployeeList le,CustomerList lc,ListInvoice ln,ListInvoiceDetails ld,Booklist lb){
        String idInvoice = new String();
        idInvoice="HD";
        int number =





        Scanner sc = new Scanner(System.in);
        boolean n = true;
        String id = new String();
        Invoice inv = new Invoice();
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
        
        
    }




    public static void main(String[] args) {
        EmployeeList listemp = new EmployeeList();
        CustomerList listCus = new CustomerList();
        Booklist listb = new Booklist();
        ListInvoice listin = new ListInvoice();
        ListInvoiceDetails listdet = new ListInvoiceDetails();
        listin.readFile();
        listdet.readFile();
        listb.displayAll();
        // in hoa don 
        addCustomerold(listemp, listCus, listin, listdet, listb);
        
        
        
    }
}
