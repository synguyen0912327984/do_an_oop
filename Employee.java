class Employee extends Person {
    private static int employeeCount = 0;
    private String position;
    private double salary;
    private boolean available = false;

    public String getPosition() {
        return position;
    }

    public void setAvailable(boolean value) {
        available = value;
    }

    public Employee(String name, String phoneNumber, String address, String position, double salary,
            boolean available) {
        super(null, name, phoneNumber, address);
        employeeCount++;
        this.id = String.format("E%03", employeeCount);
        this.position = position;
        this.salary = salary;
        this.available = available;
    }

    @Override
    public void displayinfo() {
        super.displayinfo();
        System.out.println("Position        : " + position);
        System.out.println("Salary          : " + salary);
        System.out.println("=====================================");
    }

    @Override
    public String getRole() {
        return "Employee";
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phoneNumber + "," + address + "," + position + "," + salary + "," + available;
    }

    /*
     * Doc file(.txt)
     * File co dang : id,name,phonenumber,address,position,salary
     */
    public static Employee fromString(String line) {
        String p[] = line.split(",");
        if (p.length == 7) {
            Employee e = new Employee(p[1], p[2], p[3], p[4], Double.parseDouble(p[5]), Boolean.parseBoolean(p[6]));
            e.setId(p[0]);
            return e;
        }
        return null;
    }

    public static void setEmloyeeCount(int value) {
        employeeCount = value;
    }
}
