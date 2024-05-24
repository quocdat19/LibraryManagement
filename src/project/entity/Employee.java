package project.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import static project.presentation.EmployeePresentation.empDao;

public class Employee {
    private String employeeId;
    private String employeeName;
    private Date birthOfDate;
    private Date createdDate;
    private String email;
    private String phone;
    private String address;
    private boolean employeeStatus;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Date birthOfDate,
                    Date createdDate, String email, String phone, String address, boolean employeeStatus) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.birthOfDate = birthOfDate;
        this.createdDate = createdDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public boolean isEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(boolean employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public void inputData(Scanner scanner) {
        this.employeeId = inputEmployeeId(scanner);
        this.employeeName = inputEmployeeName(scanner);
        this.birthOfDate = inputBirthDay(scanner);
        this.email = inputEmail(scanner);
        this.phone = inputPhone(scanner);
        this.address = inputAddress(scanner);
        this.employeeStatus = inputStatus(scanner);
    }

    public String inputEmployeeId(Scanner scanner) {
        do {
            System.out.println("Mã nhân viên(có 5 kí tự):");
            String employeeId = scanner.nextLine();

            if (employeeId.trim().length() == 5) {
                if (empDao.findById(employeeId) == null) {
                    return employeeId;
                } else {
                    System.err.println("mã nhân viên đã tồn tại! vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã nhân viên có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputEmployeeName(Scanner scanner) {
        do {
            System.out.println("Tên nhân viên:");
            String employeeName = scanner.nextLine();

            if (employeeName.trim().isEmpty()) {
                System.err.println("Tên nhân viên không được để trống!");
            } else {
                return employeeName;
            }
        } while (true);
    }

    public Date inputBirthDay(Scanner scanner) {
        System.out.println("Ngày sinh của nhân viên: ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        do {
            try {
                Date birthOfDate = (Date) sdf.parse(scanner.nextLine());
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
            System.out.println("địa chỉ nhân viên:");
            String phoneNumber = scanner.nextLine();

            if (phoneNumber.trim().isEmpty()) {
                System.err.println("địa chỉ nhân viên không được để trống!");
            } else {
                return phoneNumber;
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        do {
            System.out.println("trạng thái nhân viên:");
            String empStatus = scanner.nextLine();
            if (empStatus.trim().equalsIgnoreCase("true") || empStatus.trim().equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(empStatus);
            } else {
                System.err.println("Trạng thái của nhân viên chỉ nhận giá là true hoặc false!");
            }
        } while (true);
    }
    public void displayData() {
        System.out.printf("Mã nhân viên: %s - Tên nhân viên: %s - Ngày Sinh: %f\n", this.employeeId, this.employeeName, this.birthOfDate);
        System.out.printf("Ngày tạo: %s - Email: %s - Phone: %s\n", this.createdDate, this.email, this.phone);
        System.out.printf("Địa chỉ: %s - Trạng thái: %s\n", this.address, this.employeeStatus ? "Hoạt động" : "Không hoạt động");
    }


}
