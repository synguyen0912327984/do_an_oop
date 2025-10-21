class Employee extends Person {
    private static int employeeCount = 0;
    private String position;
    private double salary;

    public String getPosition() {
        return position;
    }

    public Employee(String name, String phoneNumber, String address, int position, double salary) {
        super(null, name, phoneNumber, address);
        employeeCount++;
        this.id = String.format("E%d03", employeeCount);
    }

    @Override
    public void displayinfo() {
        System.out.println("=====================================");
        System.out.println("Employee ID     : " + id);
        System.out.println("Name            : " + name);
        System.out.println("Phone           : " + phoneNumber);
        System.out.println("Address         : " + address);
        System.out.println("Position        : " + position);
        System.out.println("Salary          : " + salary);
        System.out.println("=====================================");
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phoneNumber + "," + address + "," + position + "," + salary;
    }

    /*
     * Doc file(.txt)
     * File co dang : id,name,phonenumber,address,position,salary
     */
    public static Employee fromString(String line) {
        String p[] = line.split(",");
        if (p.length == 6) {
            Employee e = new Employee(p[1], p[2], p[3], Integer.parseInt(p[4]), Double.parseDouble(p[5]));
            e.setId(p[0]);
            return e;
        }
        return null;
    }

    public static void setEmloyeeCount(int value) {
        employeeCount = value;
    }
}
