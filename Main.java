public class Main {
    public static void main(String[] args) {
        EmployeeList listemp = new EmployeeList();
        CustomerList listCus = new CustomerList();
        Booklist listb = new Booklist();
        listb.readFile();
        ListInvoice listin = new ListInvoice();
        ListInvoiceDetails listdet = new ListInvoiceDetails();
        listin.readFile();
        listdet.readFile();
        
        
        
    }
}
