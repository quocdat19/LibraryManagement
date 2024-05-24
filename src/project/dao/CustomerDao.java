package project.dao;

import project.entity.Customer;
import project.util.connectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements IDao<Customer, String, String> {
    @Override
    public List<Customer> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        conn = connectionDB.openConnection();
        List<Customer> listCustomer = null;
        try {
            callSt = conn.prepareCall("{call get_all_customer()}");
            ResultSet rs = callSt.executeQuery();
            listCustomer = new ArrayList<>();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCustomerStatus(rs.getBoolean("customer_status"));
                listCustomer.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return listCustomer;
    }

    @Override
    public boolean create(Customer customer) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("call add_customer(?,?,?,?,?,?,?)");
            callSt.setString(1, customer.getCustomerId());
            callSt.setString(2, customer.getCustomerName());
            callSt.setDate(3, (Date) customer.getBirthOfDate());
            callSt.setString(5, customer.getEmail());
            callSt.setString(6, customer.getPhone());
            callSt.setString(7, customer.getAddress());
            callSt.setBoolean(8, customer.isCustomerStatus());
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
    public boolean update(Customer customer) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt1 = conn.prepareCall("{call count_customer_by_id(?,?)}");
            callSt1.setString(1, customer.getCustomerId());
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            int cnt_customer = callSt1.getInt(2);
            if (cnt_customer>0) {
                callSt2 = conn.prepareCall("{call update_customer(?,?,?,?,?,?,?)}");
                callSt2.setString(1, customer.getCustomerId());
                callSt2.setString(2, customer.getCustomerName());
                callSt2.setDate(3, (Date) customer.getBirthOfDate());
                callSt2.setString(4, customer.getEmail());
                callSt2.setString(5, customer.getPhone());
                callSt2.setString(6, customer.getAddress());
                callSt2.setBoolean(7, customer.isCustomerStatus());
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
    public boolean delete(String customerId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt1 = conn.prepareCall("{call count_customer_by_id(?,?)}");
            callSt1.setString(1, customerId);
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            int cnt_customer = callSt1.getInt(2);
            if (cnt_customer>0) {
                callSt2 = conn.prepareCall("{call delete_customer(?)}");
                callSt2.setString(1, customerId);
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
    public Customer findById(String customerId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        Customer customer = null;
        try {
            callSt = conn.prepareCall("{call get_customer_by_id(?)}");
            callSt.setString(1, customerId);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCustomerStatus(rs.getBoolean("customer_status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return customer;
    }

    @Override
    public Customer findByName(String customerName) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        Customer customer = null;
        try {
            callSt = conn.prepareCall("{call get_customer_by_name(?)}");
            callSt.setString(1, customerName);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCustomerStatus(rs.getBoolean("customer_status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return customer;
    }

    @Override
    public List<Customer> searchName(String keyword) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        List<Customer> customerList = null;
        try {
            callSt = conn.prepareCall("{call search_customer(?)}");
            callSt.setString(1, keyword);
            ResultSet rs = callSt.executeQuery();
            customerList = new ArrayList<>();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCustomerStatus(rs.getBoolean("customer_status"));
                customerList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return customerList;
    }
}
