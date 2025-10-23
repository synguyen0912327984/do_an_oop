abstract class Person {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String address;

    public Person(String id, String name, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void displayinfo() {
        System.out.println("=====================================");
        System.out.println("ID        : " + id);
        System.out.println("Name      : " + name);
        System.out.println("Phone     : " + phoneNumber);
        System.out.println("Address   : " + address);
        System.out.println("Role      : " + getRole());
    }

    public abstract String getRole();
}
