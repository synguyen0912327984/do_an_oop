import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListInvoice {
    private ArrayList<Invoice> list = new ArrayList<>();

    public ListInvoice() {}

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

    // ✅ Đọc dữ liệu từ file Invoice.txt
    public void readFile() {
        ArrayList<Invoice> tempList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Invoice.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 4) {
                    String idInvoice = arr[0].trim();
                    String idCustomer = arr[1].trim();
                    String idEmployee = arr[2].trim(); 
                    String dateStr = arr[3].trim();
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

    
}
