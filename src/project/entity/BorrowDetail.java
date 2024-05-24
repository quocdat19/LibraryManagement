package project.entity;

import java.util.Date;

public class BorrowDetail {
    private int borrowDetailId;
    private int quantityBorrow;
    private int bookId;
    private String customerId;


    public BorrowDetail() {
    }

    public BorrowDetail(int borrowDetailId, int quantityBorrow, int bookId, String customerId) {
        this.borrowDetailId = borrowDetailId;
        this.quantityBorrow = quantityBorrow;
        this.bookId = bookId;
        this.customerId = customerId;
    }

    public BorrowDetail(int quantityBorrow, int bookId, String customerId) {
        this.quantityBorrow = quantityBorrow;
        this.bookId = bookId;
        this.customerId = customerId;
    }

    public int getBorrowDetailId() {
        return borrowDetailId;
    }

    public void setBorrowDetailId(int borrowDetailId) {
        this.borrowDetailId = borrowDetailId;
    }

    public int getQuantityBorrow() {
        return quantityBorrow;
    }

    public void setQuantityBorrow(int quantityBorrow) {
        this.quantityBorrow = quantityBorrow;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String userId) {
        this.customerId = customerId;
    }
}
