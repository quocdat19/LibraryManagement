package project.dao;

import project.entity.Book;
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

public class BookDao implements IDao<Book, Integer, String> {
    @Override
    public List<Book> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        conn = connectionDB.openConnection();
        List<Book> listBook = null;
        try {
            callSt = conn.prepareCall("{call get_all_books()}");
            ResultSet rs = callSt.executeQuery();
            listBook = new ArrayList<>();
            while (rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setQuantity(rs.getInt("quantity"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setBookStatus(rs.getBoolean("book_status"));
                book.setCatalogId(rs.getInt("catalog_id"));
                listBook.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return listBook;
    }

    @Override
    public boolean create(Book book) {
        LocalDate today = LocalDate.now();
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("call add_book(?,?,?,?,?,?,?)");
            callSt.setInt(1, book.getBookId());
            callSt.setString(2, book.getBookName());
            callSt.setInt(3, book.getQuantity());
            callSt.setString(4, book.getTitle());
            callSt.setString(5, book.getAuthor());
            callSt.setBoolean(6, book.isBookStatus());
            callSt.setInt(7, book.getCatalogId());
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

    @Override
    public boolean update(Book book) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt1 = conn.prepareCall("{call count_book_by_id(?,?)}");
            callSt1.setInt(1, book.getBookId());
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            int cnt_book = callSt1.getInt(2);
            if (cnt_book>0) {
                callSt2 = conn.prepareCall("{call update_book(?,?,?,?,?,?,?)}");
                callSt2.setInt(1, book.getBookId());
                callSt2.setString(2, book.getBookName());
                callSt2.setInt(3, book.getQuantity());
                callSt2.setString(4, book.getTitle());
                callSt2.setString(5, book.getAuthor());
                callSt2.setBoolean(6, book.isBookStatus());
                callSt2.setInt(7, book.getCatalogId());
                callSt2.executeUpdate();
            }else{
                result = false;
            }
            conn.commit();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            connectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean delete(Integer bookId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt1 = conn.prepareCall("{call count_book_by_id(?,?)}");
            callSt1.setInt(1, bookId);
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            int cnt_book = callSt1.getInt(2);
            if (cnt_book>0) {
                callSt2 = conn.prepareCall("{call delete_book(?)}");
                callSt2.setInt(1, bookId);
                callSt2.executeUpdate();
            }else{
                result = false;
            }
            conn.commit();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            connectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public Book findById(Integer bookId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        Book book = null;
        try {
            callSt = conn.prepareCall("{call get_book_by_id(?)}");
            callSt.setInt(1, bookId);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setQuantity(rs.getInt("quantity"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setBookStatus(rs.getBoolean("book_status"));
                book.setCatalogId(rs.getInt("catalog_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return book;
    }

    @Override
    public Book findByName(String bookName) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        Book book = null;
        try {
            callSt = conn.prepareCall("{call get_book_by_name(?)}");
            callSt.setString(1, bookName);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                if(rs.next()){
                    book = new Book();
                    book.setBookId(rs.getInt("book_id"));
                    book.setBookName(rs.getString("book_name"));
                    book.setQuantity(rs.getInt("quantity"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setBookStatus(rs.getBoolean("book_status"));
                    book.setCatalogId(rs.getInt("catalog_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return book;
    }

    @Override
    public List<Book> searchName(String keyword) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        List<Book> listBook = null;
        try {
            callSt = conn.prepareCall("{call search_book(?)}");
            callSt.setString(1, keyword);
            ResultSet rs = callSt.executeQuery();
            listBook = new ArrayList<>();
            while (rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setQuantity(rs.getInt("quantity"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setBookStatus(rs.getBoolean("book_status"));
                book.setCatalogId(rs.getInt("catalog_id"));
                listBook.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return listBook;
    }

    public int countBook(int bookId){
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        int cnt_book = 0;
        try {
            callSt = conn.prepareCall("{call count_quantity_by_id(?,?)}");
            callSt.setInt(1, bookId);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            cnt_book = callSt.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }

        return cnt_book;
    }
}
