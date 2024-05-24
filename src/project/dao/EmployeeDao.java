package project.dao;

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

public class EmployeeDao implements IDao<Employee, String , String> {

    @Override
    public List<Employee> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        conn = connectionDB.openConnection();
        List<Employee> listEmp = null;
        try {
            callSt = conn.prepareCall("{call get_all_employee()}");
            ResultSet rs = callSt.executeQuery();
            listEmp = new ArrayList<>();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getString("emp_id"));
                emp.setEmployeeName(rs.getString("emp_name"));
                emp.setBirthOfDate(rs.getDate("birth_of_date"));
                emp.setCreatedDate(rs.getDate("created_date"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeStatus(rs.getBoolean("emp_status"));
                listEmp.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return listEmp;
    }

    @Override
    public boolean create(Employee employee) {
        LocalDate today = LocalDate.now();
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("call add_employee(?,?,?,?,?,?,?,?)");
            callSt.setString(1, employee.getEmployeeId());
            callSt.setString(2, employee.getEmployeeName());
            callSt.setDate(3, employee.getBirthOfDate());
            callSt.setDate(4, Date.valueOf(today));
            callSt.setString(5, employee.getEmail());
            callSt.setString(6, employee.getPhone());
            callSt.setString(7, employee.getAddress());
            callSt.setBoolean(8, employee.isEmployeeStatus());
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
    public boolean update(Employee employee) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt1 = conn.prepareCall("{call count_employee_by_id(?,?)}");
            callSt1.setString(1, employee.getEmployeeId());
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            int cnt_emp = callSt1.getInt(2);
            if (cnt_emp>0) {
                callSt2 = conn.prepareCall("{call update_employee(?,?,?,?,?,?,?)}");
                callSt2.setString(1, employee.getEmployeeId());
                callSt2.setString(2, employee.getEmployeeName());
                callSt2.setDate(3, employee.getBirthOfDate());
                callSt2.setString(4, employee.getEmail());
                callSt2.setString(5, employee.getPhone());
                callSt2.setString(6, employee.getAddress());
                callSt2.setBoolean(7, employee.isEmployeeStatus());
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
    public boolean  delete(String empId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt1 = null;
        CallableStatement callSt2 = null;
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            callSt1 = conn.prepareCall("{call count_employee_by_id(?,?)}");
            callSt1.setString(1, empId);
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.execute();
            int cnt_book = callSt1.getInt(2);
            if (cnt_book>0) {
                callSt2 = conn.prepareCall("{call delete_employee(?)}");
                callSt2.setString(1, empId);
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
    public Employee findById(String empId) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        Employee emp = null;
        try {
            callSt = conn.prepareCall("{call get_employee_by_id(?)}");
            callSt.setString(1, empId);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                emp = new Employee();
                emp.setEmployeeId(rs.getString("emp_id"));
                emp.setEmployeeName(rs.getString("emp_name"));
                emp.setBirthOfDate(rs.getDate("birth_of_date"));
                emp.setCreatedDate(rs.getDate("created_date"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeStatus(rs.getBoolean("emp_status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return emp;
    }

    @Override
    public Employee findByName(String empName) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        Employee emp = null;
        try {
            callSt = conn.prepareCall("{call get_employee_by_name(?)}");
            callSt.setString(1, empName);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                emp = new Employee();
                emp.setEmployeeId(rs.getString("emp_id"));
                emp.setEmployeeName(rs.getString("emp_name"));
                emp.setBirthOfDate(rs.getDate("birth_of_date"));
                emp.setCreatedDate(rs.getDate("created_date"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeStatus(rs.getBoolean("emp_status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return emp;
    }

    @Override
    public List<Employee> searchName(String keyword) {
        Connection conn = connectionDB.openConnection();
        CallableStatement callSt = null;
        List<Employee> listEmp = null;
        try {
            callSt = conn.prepareCall("{call search_employee(?)}");
            callSt.setString(1, keyword);
            ResultSet rs = callSt.executeQuery();
            listEmp = new ArrayList<>();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getString("emp_id"));
                emp.setEmployeeName(rs.getString("emp_name"));
                emp.setBirthOfDate(rs.getDate("birth_of_date"));
                emp.setCreatedDate(rs.getDate("created_date"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeStatus(rs.getBoolean("emp_status"));
                listEmp.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return listEmp;
    }

}
