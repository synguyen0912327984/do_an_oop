import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ListInvoiceDetails {
    private ArrayList<InvoiceDetail> list = new ArrayList<>();


    public ListInvoiceDetails() {}


    public ListInvoiceDetails(ArrayList<InvoiceDetail> list) {
        this.list = list;
    }


    public ArrayList<InvoiceDetail> getList() {
        return list;
    }

    public void displayAll() {
        if (list.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        for (InvoiceDetail ct : list) {
            ct.displayDetail();
        }
    }

    public void readFile() {
        ArrayList<InvoiceDetail> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("InvoiceDetail.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Mỗi dòng trong file có định dạng: idInvoice,idBook,nameBook,quantity,price
                String[] arr = line.split(",");
                if (arr.length == 5) {
                    String idInvoice = arr[0].trim();
                    String idBook = arr[1].trim();
                    String nameBook = arr[2].trim();
                    int quantity = Integer.parseInt(arr[3].trim());
                    double price = Double.parseDouble(arr[4].trim());

                    InvoiceDetail detail = new InvoiceDetail(idInvoice, idBook, nameBook, quantity, price);
                    list.add(detail);
                }
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi đọc file InvoiceDetail.txt: " + e.getMessage());
        }

        this.list = list;
    }

    public double getTotalByInvoiceId(String idInvoice) {
        double total = 0;
        for (InvoiceDetail ct : list) {
            if (ct.getIdInvoice().equalsIgnoreCase(idInvoice)) {
                total += ct.getTotal();
            }
        }
        return total;
    }

    
    
}
