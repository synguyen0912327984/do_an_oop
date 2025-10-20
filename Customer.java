package Java.project.team;

class Customer extends Person {
    private static int customerCount = 0;
    private int loyaltyPoints;

    public Customer(String name, String phoneNumber, String address, int loyaltyPoints) {
        super(null, name, phoneNumber, address);
        this.id = String.format("C%03d", customerCount++);
        /*
         * Định dạng chuỗi với chuỗi định dạng C + customerCount
         * %03d độ dài tối thiểu là 3 nếu không đủ thì chèn 0 vào đầu
         */
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public void displayinfo() {
        System.out.println(
                "Customer ID: " + id + ", Name: " + name + "Phone" + phoneNumber + "LoyaltyPoints" + loyaltyPoints);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phoneNumber + "," + address + "," + loyaltyPoints;
    }

    /*
     * Đọc file(.txt)
     * File có dạng : id,name,phonenumber,address,loyaltypoints
     */
    public static Customer fromString(String line) {
        String p[] = line.split(",");
        if (p.length == 5) {
            Customer c = new Customer(p[1], p[2], p[3], Integer.parseInt(p[4]));
            c.setId(p[0]);
            return c;
        }
        return null;
    }

    public static void setCustomerCounter(int value) {
        customerCount = value;
    }
}
