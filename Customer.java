class Customer extends Person {
    private static int customerCount = 0;
    private int loyaltyPoints;

    public Customer(String name, String phoneNumber, String address, int loyaltyPoints) {
        super(null, name, phoneNumber, address);
        this.id = String.format("C%03d", customerCount++);
        /*
         * Dinh dang chuoi voi chuoi dinh dang C + customnerCount
         * %03d do dai toi thieu la 3 neu khong du thi chen 0 vao dau
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
        System.out.println("=====================================");
        System.out.println("Customer ID     : " + id);
        System.out.println("Name            : " + name);
        System.out.println("Phone           : " + phoneNumber);
        System.out.println("Address         : " + address);
        System.out.println("LoyaltyPoints   : " + loyaltyPoints);
        System.out.println("=====================================");
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phoneNumber + "," + address + "," + loyaltyPoints;
    }

    /*
     * Doc file(.txt)
     * File co dang : id,name,phonenumber,address,loyaltypoints
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
