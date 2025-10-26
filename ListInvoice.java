import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;

public class ListInvoice {
    private ArrayList<Invoice> list = new ArrayList<>();

    public ListInvoice() {}

    public int getQuantity(){
        return list.size();
    }

    public ListInvoice(ArrayList<Invoice> list) {
        this.list = list;
    }

    public ArrayList<Invoice> getList() {
        return list;
    }

    
    public void displayAll() {
        if (list.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }

        for (Invoice hd : list) {
            hd.displayInvoice();
            System.out.println("-----------------------------");
        }
    }

    public void readFile() {
        ArrayList<Invoice> tempList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Invoice.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 4) {
                    String idInvoice = arr[0];
                    String idCustomer = arr[1];
                    String idEmployee = arr[2]; 
                    String dateStr = arr[3];
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(dateStr, fmt);

                    Invoice invoice = new Invoice(idInvoice, idCustomer, idEmployee, date);
                    tempList.add(invoice);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.list = tempList;
    }

    public Invoice test(String id){
        for(Invoice st:list){
            if(id.equalsIgnoreCase(st.getIdInvoice())) return st;
        }
        return null;
    }

    public void addlist(Invoice l1){
        list.add(l1);
    }

    public void removelist(Invoice l1){
        list.remove(l1);
    }

    public void saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Invoice.txt"))) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Invoice invoice : list) {
                String line = invoice.getIdInvoice() + "," 
                            + invoice.getIdCustomer() + "," 
                            + invoice.getIdEmployee() + "," 
                            + invoice.getTime().format(fmt);
                bw.write(line);
                bw.newLine();
            }

            System.out.println("File saved successfully: Invoice.txt");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
        
    public ArrayList<Invoice> findtime(LocalDate time) {
        ArrayList<Invoice> result = new ArrayList<>();
        for (Invoice inv : list) {
            if (inv.getTime().isEqual(time)) {
                result.add(inv);
            }
        }
        return result;
    }

    public ArrayList<Invoice> findidCus(String id){
        ArrayList<Invoice> result = new ArrayList<>();
        for (Invoice inv : list) {
            if (inv.getIdCustomer().equals(id)) {
                result.add(inv);
            }
        }
        return result;
    }
    
}
