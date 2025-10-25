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
            ct.display();
        }
    }

    public void readFile() {
        ArrayList<InvoiceDetail> tempList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("InvoiceDetail.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 3) { // file có 3 phần: mã hóa đơn, mã sách, số lượng
                    String idInvoice = arr[0];
                    String idBook = arr[1];
                    int quantity = Integer.parseInt(arr[2]);

                    InvoiceDetail detail = new InvoiceDetail(idInvoice, idBook, quantity);
                    tempList.add(detail);
                }
            }

            this.list = tempList;

        } catch (Exception e) {
            System.err.println("Lỗi khi đọc file InvoiceDetail.txt: " + e.getMessage());
        }

    }
    public ArrayList<InvoiceDetail> find(String id){
        ArrayList<InvoiceDetail> l = new ArrayList<>(); 
        for(InvoiceDetail st: list){
            if(id.equalsIgnoreCase(st.getIdInvoice())) {
                l.add(st);
            }
        }
        return l;
    }


    public void addlist(InvoiceDetail ln){
        list.add(ln);
    }

    public void savefile() {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("InvoiceDetail.txt"))) {
            for (InvoiceDetail detail : list) {
                // Ghi theo định dạng: idInvoice,idBook,quantity
                bw.write(detail.getIdInvoice() + "," + detail.getIdBook() + "," + detail.getQuantity());
                bw.newLine(); // Xuống dòng cho mỗi chi tiết
            }
            System.out.println("save file InvoiceDentail.txt corret!");
        } catch (Exception e) {
            System.err.println("erorr when save file InvoiceDentail.txt: " + e.getMessage());
        }
    }


    
    
}
