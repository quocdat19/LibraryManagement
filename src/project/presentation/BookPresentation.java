package project.presentation;

import project.dao.BookDao;
import project.dao.IDao;
import project.entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookPresentation {
    private static IDao bookDao = new BookDao();

    public static void bookMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("-------------------Book Management-------------------\n" +
                    "1. Danh sách sách\n" +
                    "2. Thêm mới sách\n" +
                    "3. Cập nhật thông tin sách\n" +
                    "4. Xóa sách\n" +
                    "5. Tìm kiếm sách\n" +
                    "6. Thoát");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<Book> listBook = bookDao.getAll();
                        listBook.stream().forEach(book -> book.displayData());
                        break;
                    case 2:
                        Book book = new Book();
                        book.inputData(scanner);
                        boolean resultCreate = bookDao.create(book);
                        if (resultCreate) {
                            System.out.println("Thêm mới thành công");
                        } else {
                            System.err.println("Thêm mới thất bại");
                        }
                        break;
                    case 3:
                        System.out.println("Nhập vào mã sách cần cập nhật:");
                        int bookId = Integer.parseInt(scanner.nextLine());
                        Book bookUpdate = new Book();
                        bookUpdate.setBookId(bookId);
                        bookUpdate.inputData(scanner);
                        boolean resultUpdate = bookDao.update(bookUpdate);
                        if (resultUpdate) {
                            System.out.println("Cập nhật thành công");
                        } else {
                            System.err.println("Không tồn tại mã sách hoặc có lỗi trong quá trình thực hiện");
                        }
                        break;
                    case 4:
                        System.out.println("Nhập vào mã sách cần xóa:");
                        int bookIdDelete = Integer.parseInt(scanner.nextLine());
                        boolean resultDelete = bookDao.delete(bookIdDelete);
                        if (resultDelete) {
                            System.out.println("Xóa thành công");
                        } else {
                            System.err.println("Mã sách không tồn tại hoặc có lỗi trong quá trình thực hiện");
                        }
                        break;
                    case 5:
                        System.out.println("Nhập keyword cần tìm kiếm");
                        String keyword = scanner.nextLine();
                        List<Book> list = bookDao.searchName(keyword);
                        list.stream().forEach(bookSearch -> bookSearch.displayData());
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
    }while(isExit);

}
}
