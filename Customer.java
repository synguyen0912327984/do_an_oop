import java.io.BufferedReader;
import java.io.InputStreamReader;

class Customer extends Person implements ICustomerActions {
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
        super.displayinfo();
        System.out.println("LoyaltyPoints   : " + loyaltyPoints);
        System.out.println("=====================================");
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phoneNumber + "," + address + "," + loyaltyPoints;
    }

    // Interface

    @Override
    public void Buy(Book a) {
        System.out.print("Enter the amount you want to buy: ");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int temp1 = Integer.parseInt(br.readLine());
            if (a.getAmount() >= temp1) {
                System.out.print("Are you sure? y/n: ");
                String temp2 = br.readLine();
                if (temp2.equalsIgnoreCase("y")) {
                    System.out.println("Successfully purchased!");
                    a.setAmount(a.getAmount() - temp1);
                    addLoyaltyPoints((int) (a.getPrice() / 10000));
                } else
                    System.out.println("Cancelled.");
            } else
                System.out.println("Only " + a.getAmount() + " items are available. Please adjust your quantity.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
            System.out.println(points + " Added to account! Total: " + loyaltyPoints);
        } else
            System.out.println("Invalid points!");
    }

    @Override
    public void redeemPoints(int points) {
        if (points > 0 && points <= loyaltyPoints) {
            this.loyaltyPoints -= points;
            System.out.println(points + " points redeemed successfully! Remain: " + loyaltyPoints);
        } else
            System.out.println("Not enough points to redeem");
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
