package project.entity;

import java.util.Scanner;

public class Book {
    private int bookId;
    private String bookName;
    private int quantity;
    private String title;
    private String author;
    private boolean bookStatus;
    private int catalogId;

    public Book() {
    }

    public Book(int bookId, String bookName, int quantity, String title, String author, boolean bookStatus, int catalogId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.title = title;
        this.author = author;
        this.bookStatus = bookStatus;
        this.catalogId = catalogId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
    public void inputData(Scanner scanner) {
        System.out.println("Nhập tên sách:");
        this.bookName = scanner.nextLine();
        System.out.println("Nhập số lượng sách:");
        this.quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập tiêu đề sách:");
        this.title = scanner.nextLine();
        System.out.println("Nhập tác giả:");
        this.author = scanner.nextLine();
        System.out.println("Nhập trạng thái sách:");
        this.bookStatus = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Nhập thư mục sách");
        this.catalogId = Integer.parseInt(scanner.nextLine());
    }

    public void displayData() {
        System.out.printf("Mã sách: %d - Tên sách: %s - Số lượng: %f\n", this.bookId, this.bookName, this.quantity);
        System.out.printf("Tiêu đề: %s - Tác giả: %s - Trạng thái: %s\n", this.title, this.author, this.bookStatus ? "Hoạt động" : "Không hoạt động");
    }
}
