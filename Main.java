import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

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
            System.out.println(lb.findByID(ind.get(i)));
        }
        
        
        
        
    } 

    public static void addCustomerold(EmployeeList le,CustomerList lc,ListInvoice ln,ListInvoiceDetails ld,Booklist lb){
        String idInvoice = new String();
        idInvoice="HD";
        





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
        listb.readFile();
        // in hoa don 
        PrintInvoice(listemp, listCus, listin, listdet, listb, "HD011");
    //    addCustomerold(listemp, listCus, listin, listdet, listb);
        
        
        
    }
}
