package project.presentation;

import project.dao.CustomerDao;
import project.dao.IDao;
import project.entity.Customer;
import project.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class CustomerPresentation {
    public static IDao customerDao = new CustomerDao();

    public static void customerMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("-------------------Customer Management-------------------\n" +
                    "1. Danh sách người dùng\n" +
                    "2. Thêm mới người dùng\n" +
                    "3. Cập nhật thông tin người dùng\n" +
                    "4. Xóa người dùng\n" +
                    "5. Tìm kiếm nhân viên theo tên\n" +
                    "6. Thoát");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Customer> customerList = customerDao.getAll();
                        customerList.stream().forEach(customer -> customer.displayData());
                        break;
                    case 2:
                        Customer customer = new Customer();
                        customer.inputData(scanner);
                        boolean resultCreate = customerDao.create(customer);
                        if (resultCreate) {
                            System.out.println("Thêm mới thành công");
                        } else {
                            System.err.println("Thêm mới thất bại");
                        }
                        break;
                    case 3:
                        System.out.println("Nhập vào mã người dùng cần cập nhật:");
                        String customerId = scanner.nextLine();
                        Customer customerUpdate = new Customer();
                        customerUpdate.setCustomerId(customerId);
                        customerUpdate.inputData(scanner);
                        boolean resultUpdate = customerDao.update(customerUpdate);
                        if (resultUpdate) {
                            System.out.println("Cập nhật thành công");
                        } else {
                            System.err.println("Không tồn tại mã sách hoặc có lỗi trong quá trình thực hiện");
                        }
                        break;
                    case 4:
                        System.out.println("Nhập vào mã người dùng cần xóa:");
                        String customerIdDetele = scanner.nextLine();
                        boolean resultDelete = customerDao.delete(customerIdDetele);
                        if (resultDelete) {
                            System.out.println("Xóa thành công");
                        } else {
                            System.err.println("Mã người dùng không tồn tại hoặc có lỗi trong quá trình thực hiện");
                        }
                        break;
                    case 5:
                        System.out.println("Nhập keyword cần tìm kiếm");
                        String keyword = scanner.nextLine();
                        List<Customer> list = customerDao.searchName(keyword);
                        list.stream().forEach(customerSearch -> customerSearch.displayData());
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 6!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }
}
