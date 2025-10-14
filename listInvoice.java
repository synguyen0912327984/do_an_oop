import java.io.BufferedReader;
import java.io.FileReader;
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
            System.out.println();
        }
    }

    public ArrayList<Invoice> readFile() {
        ArrayList<Invoice> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Invoice.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 4) {
                    String[] arrdate = arr[3].split("/");
                    int day = Integer.parseInt(arrdate[0]);
                    int month = Integer.parseInt(arrdate[1]);
                    int year = Integer.parseInt(arrdate[2]);

                    Invoice.Date date = new Invoice.Date(day, month, year);
                    Invoice invoice = new Invoice(arr[0], arr[1], arr[2], date);
                    list.add(invoice);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.list = list;
        return list;
    }
}
