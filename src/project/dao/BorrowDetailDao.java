package project.dao;

import project.entity.BorrowDetail;
import project.entity.Employee;
import project.util.connectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowDetailDao {
    public List<BorrowDetail> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        conn = connectionDB.openConnection();
        List<BorrowDetail> listBorrowDetail = null;
        try {
            callSt = conn.prepareCall("{call get_all_borrow_detail}");
            ResultSet rs = callSt.executeQuery();
            listBorrowDetail = new ArrayList<>();
            while (rs.next()) {
                BorrowDetail borrowDetail = new BorrowDetail();
                borrowDetail.setCustomerId(rs.getString("customer_id"));
                borrowDetail.setBookId(rs.getInt("book_id"));
                borrowDetail.setQuantityBorrow(rs.getInt("borrow_quantity"));
                listBorrowDetail.add(borrowDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return listBorrowDetail;
    }

    public boolean create(BorrowDetail borrowDetail) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("call add_borrow_detail(?, ?, ?)");
            callSt.setString(1, borrowDetail.getCustomerId());
            callSt.setInt(2, borrowDetail.getBookId());
            callSt.setInt(3, borrowDetail.getQuantityBorrow());
            callSt.executeUpdate();
            conn.commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            connectionDB.closeConnection(conn);
        }
        return result;
    }

    public boolean update(BorrowDetail borrowDetail) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn.setAutoCommit((false));
            callSt = conn.prepareCall("update_borrow_detail(?, ?, ?)");
            callSt.setString(1, borrowDetail.getCustomerId());
            callSt.setInt(2, borrowDetail.getBookId());
            callSt.setInt(3, borrowDetail.getQuantityBorrow());
            callSt.executeUpdate();
            conn.commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            connectionDB.closeConnection(conn);
        }
        return result;
    }

    //check người dùng đã mượn sách
    public boolean countCustomer(String customerId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            callSt = conn.prepareCall("{call get_customer_borrow_by_id(?,?)}");
            callSt.setString(1, customerId);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int cnt_customer = callSt.getInt(2);
            if (cnt_customer > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }

        return result;
    }

    //check sách đã được người dùng mượn
    public boolean countBookByCustomerId(String customerId, int bookId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            callSt = conn.prepareCall("{call get_customer_borrow_by_id(?,?)}");
            callSt.setString(1, customerId);
            callSt.setInt(2, bookId);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int cnt_book = callSt.getInt(2);
            if (cnt_book > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }

        return result;
    }


    public void borrowBook(String customerId, int bookId, int numberBorrow) {
        BorrowDetail borrowDetail = new BorrowDetail(numberBorrow, bookId, customerId);
        BookDao bookDao = new BookDao();
        int numberBook = bookDao.countBook(bookId);
        if (numberBorrow > numberBook) {
            System.out.println("Sách này đã hết!");
        } else {
            //Nếu người dùng chưa từng mượn sách thêm mới vào bảng chi tiết
            if (!this.countCustomer(customerId) || !this.countBookByCustomerId(customerId, bookId)){
                this.create(borrowDetail);
                System.out.println("Thêm thành công");
            }else {
                this.update(borrowDetail);
                System.out.println("Cập nhật thành công");
            }
        }
    }


}
