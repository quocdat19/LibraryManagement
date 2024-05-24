package project.presentation;

import project.dao.BookDao;
import project.dao.EmployeeDao;
import project.dao.IDao;
import project.entity.Book;
import project.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeePresentation {
    public static IDao empDao = new EmployeeDao();

    public static void empMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("-------------------Employee Management-------------------\n" +
                    "1. Danh sách nhân viên\n" +
                    "2. Thêm mới nhân viên\n" +
                    "3. Cập nhật thông tin nhân viên\n" +
                    "4. Xóa nhân viên\n" +
                    "5. Tìm kiếm nhân viên theo tên\n" +
                    "6. Thoát");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Employee> listEmp = empDao.getAll();
                        listEmp.stream().forEach(employee -> employee.displayData());
                        break;
                    case 2:
                        Employee employee = new Employee();
                        employee.inputData(scanner);
                        boolean resultCreate = empDao.create(employee);
                        if (resultCreate) {
                            System.out.println("Thêm mới thành công");
                        } else {
                            System.err.println("Thêm mới thất bại");
                        }
                        break;
                    case 3:
                        System.out.println("Nhập vào mã nhân viên cần cập nhật:");
                        String empId = scanner.nextLine();
                        Employee empUpdate = new Employee();
                        empUpdate.setEmployeeId(empId);
                        empUpdate.inputData(scanner);
                        boolean resultUpdate = empDao.update(empUpdate);
                        if (resultUpdate) {
                            System.out.println("Cập nhật thành công");
                        } else {
                            System.err.println("Không tồn tại mã sách hoặc có lỗi trong quá trình thực hiện");
                        }
                        break;
                    case 4:
                        System.out.println("Nhập vào mã nhân viên cần xóa:");
                        String empIdDetele = scanner.nextLine();
                        boolean resultDelete = empDao.delete(empIdDetele);
                        if (resultDelete) {
                            System.out.println("Xóa thành công");
                        } else {
                            System.err.println("Mã nhân viên không tồn tại hoặc có lỗi trong quá trình thực hiện");
                        }
                        break;
                    case 5:
                        System.out.println("Nhập keyword cần tìm kiếm");
                        String keyword = scanner.nextLine();
                        List<Employee> list = empDao.searchName(keyword);
                        list.stream().forEach(empSearch -> empSearch.displayData());
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
