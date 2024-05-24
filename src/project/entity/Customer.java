package project.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static project.presentation.CustomerPresentation.customerDao;
import static project.presentation.EmployeePresentation.empDao;

public class Customer {
    private String customerId;
    private String customerName;
    private Date birthOfDate;
    private String email;
    private String phone;
    private String address;
    private boolean customerStatus;

    public Customer() {
    }

    public Customer(String customerId, String customerName,
                    Date birthOfDate, String email, String phone, String address, boolean customerStatus) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.birthOfDate = birthOfDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.customerStatus = customerStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(boolean customerStatus) {
        this.customerStatus = customerStatus;
    }
    public void inputData(Scanner scanner) {
        this.customerId = inputCustomerId(scanner);
        this.customerName = inputCustomerName(scanner);
        this.birthOfDate = inputBirthDay(scanner);
        this.email = inputEmail(scanner);
        this.phone = inputPhone(scanner);
        this.address = inputAddress(scanner);
        this.customerStatus = inputStatus(scanner);
    }

    public String inputCustomerId(Scanner scanner) {
        do {
            System.out.println("Mã người dùng(có 5 kí tự):");
            String employeeId = scanner.nextLine();

            if (employeeId.trim().length() == 5) {
                if (customerDao.findById(employeeId) == null) {
                    return customerId;
                } else {
                    System.err.println("mã người dùng đã tồn tại! vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã người dùng có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputCustomerName(Scanner scanner) {
        do {
            System.out.println("Tên người dùng:");
            String customerName = scanner.nextLine();

            if (customerName.trim().isEmpty()) {
                System.err.println("Tên người dùng không được để trống!");
            } else {
                return customerName;
            }
        } while (true);
    }

    public java.sql.Date inputBirthDay(Scanner scanner) {
        System.out.println("Ngày sinh của người dùng: ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        do {
            try {
                java.sql.Date birthOfDate = (java.sql.Date) sdf.parse(scanner.nextLine());
                return birthOfDate;
            } catch (Exception e) {
                System.err.println("Ngày tháng nhập vào không đúng định dạng, vui lòng nhập lại!");
            }
        } while (true);
    }

    public String inputEmail(Scanner scanner) {
        do {
            System.out.println("email nhân viên:");
            String email = scanner.nextLine();

            if (email.trim().isEmpty()) {
                System.err.println("email không được để trống!");
            } else {
                return email;
            }
        } while (true);
    }

    public String inputPhone(Scanner scanner) {
        do {
            System.out.println("số điện thoại nhân viên:");
            String phoneNumber = scanner.nextLine();

            if (phoneNumber.trim().isEmpty()) {
                System.err.println("số điên thoại không được để trống!");
            } else {
                return phoneNumber;
            }
        } while (true);
    }

    public String inputAddress(Scanner scanner) {
        do {
            System.out.println("địa chỉ người dùng:");
            String phoneNumber = scanner.nextLine();

            if (phoneNumber.trim().isEmpty()) {
                System.err.println("địa chỉ người dùng không được để trống!");
            } else {
                return phoneNumber;
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        do {
            System.out.println("trạng thái người dùng:");
            String customerStatus = scanner.nextLine();
            if (customerStatus.trim().equalsIgnoreCase("true") || customerStatus.trim().equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(customerStatus);
            } else {
                System.err.println("Trạng thái của người dùng chỉ nhận giá là true hoặc false!");
            }
        } while (true);
    }
    public void displayData() {
        System.out.printf("Mã người dùng: %s - Tên người dùng: %s - Ngày Sinh: %f\n", this.customerId, this.customerName, this.birthOfDate);
        System.out.printf("Email: %s - Phone: %s\n", this.email, this.phone);
        System.out.printf("Địa chỉ: %s - Trạng thái: %s\n", this.address, this.customerStatus ? "Hoạt động" : "Không hoạt động");
    }
}
