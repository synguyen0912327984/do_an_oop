public interface ICustomerActions {
    void Buy(Book a);                    // Mua sach
    void addLoyaltyPoints(int points);   // Them diem tich luy
    void redeemPoints(int points);       // Doi diem
}
