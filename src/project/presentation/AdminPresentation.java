package project.presentation;

import java.util.Scanner;

public class AdminPresentation {
    public static void adminMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("******************ADMIN****************\n" +
                    "1. Quản lý sách\n" +
                    "2. Quản lý nhân viên\n" +
                    "3. Quản lý người dùng\n" +
                    "4. Quản lý tài khoản\n" +
                    "5. đăng xuất");
            System.out.println("6. thoát");
            System.out.println("lựa chọn của bạn:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        BookPresentation.bookMenu(scanner);
                        break;
                    case 2:
                        EmployeePresentation.empMenu(scanner);
                        break;
                    case 3:
                        CustomerPresentation.customerMenu(scanner);
                        break;
                    case 4:
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("vui lòng chọn từ 1 - 8!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        } while (isExit);
    }
}
