/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isp392.user;

import isp392.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ThinhHoang
 */
public class UserDAO {

    private final String INSERT_USER_GOOGLE = "INSERT INTO Users(Name, Email, Password, Phone, RoleID, Status) VALUES (?,?,?,?,?,1)";
    private final String INSERT_CUSTOMER_GOOGLE = "INSERT INTO Customer(CustomerID, Area, District, Ward, DetailAddress, DayOfBirth) VALUES(?,?,?,?,?,?)";
    private final String CHECK_USER_BY_EMAIL = "SELECT * FROM Users WHERE Email LIKE ?";
    private final String CHECK_USER_BY_EMAIL_UPDATE = "SELECT * FROM Users WHERE Email LIKE ? AND UserID !=?";
    private final String CHECK_USER_BY_PHONE_UPDATE = "SELECT * FROM Users WHERE Phone LIKE ? AND UserID !=?";
    private final String CHECK_USER_BY_PHONE = "SELECT * FROM Users WHERE Phone LIKE ?";
    private final String GET_CUSTOMER_BY_USERID = "SELECT * FROM Customer WHERE CustomerID = ?";
    private static final String LIST_USER = "SELECT UserID, Name, Email,Phone, Status, RoleID FROM Users";
    private static final String UPDATE = "UPDATE Users SET Name=?, Email=?, Phone=?, RoleID=? WHERE UserID=?";
    private static final String FILTER_EMPLOYEE = "SELECT UserID, Name, Email, Phone, Status, RoleID FROM Users where RoleID not like 1";
    private static final String FILTER_CUSTOMER = "SELECT UserID, Name, Email, Phone, Status, RoleID FROM Users where RoleID =1";
    private static final String UPDATE_USER_STATUS = "UPDATE Users SET Status=? WHERE UserID=?";
    private static final String LOGIN = "SELECT * FROM dbo.Users WHERE Email LIKE ? AND Password LIKE ?";
    private static final String GET_CUSTOMER_BY_USERID_01 = "SELECT    u.UserID, u.Name, u.Email, u.Password, u.Phone, u.Status, u.RoleID, c.CustomerID, c.Area, c.District, c.Ward, c.DetailAddress, c.DayOfBirth\n" +
"FROM      Users AS u INNER JOIN Customer AS c ON u.UserID = c.CustomerID\n" +
"WHERE     u.UserID = ?";
    private static final String UPDATE_PASSWORD = "UPDATE dbo.Users SET Password=? WHERE UserID=?";
    private static final String UPDATE_PROFILE_USER = "UPDATE dbo.Users SET Name=?, Email=?, Phone=? WHERE UserID=?";
    private static final String UPDATE_PROFILE_CUSTOMER = "UPDATE dbo.Customer SET DetailAddress=?, Area=?, District=?, Ward=?, DayOfBirth=? WHERE customerID=?";
    private static final String GET_PASSWORD_BY_ID = "SELECT Password FROM Users WHERE UserID = ?";
    private static final String GET_CUSTOMER_ID = "SELECT UserID FROM Users WHERE Phone LIKE ?";
    private static final String GET_STAFF_ID = "SELECT UserID FROM Users WHERE RoleID = 3";
    private static final String CREATE_EMPLOYEE_ACCOUNT = "INSERT INTO Users(Name, Email, Password, Phone, Status, RoleID) VALUES(?,?,?,?,?,?)";
    private static final String LIST_USER_BY_PHONE = "SELECT UserID, Name, Email,Phone, Status, RoleID FROM Users WHERE Phone like ?";

    public boolean checkEmailExisted(String email) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_USER_BY_EMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            DBUtils.closeConnection3(conn, ptm, rs);
        }
        return check;
    }

    public boolean addNewUser(UserDTO newUser, CustomerDTO newCust) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement userptm = null;
        PreparedStatement custptm = null;
        ResultSet rs = null;
//      INSERT INTO Users(Name, Email, Password, Phone, RoleID, Status) VALUES (?,?,?,?,?,1)
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                userptm = conn.prepareStatement(INSERT_USER_GOOGLE, PreparedStatement.RETURN_GENERATED_KEYS);
                userptm.setString(1, newUser.getName());
                userptm.setString(2, newUser.getEmail());
                userptm.setString(3, newUser.getPassword());
                userptm.setString(4, newUser.getPhone());
                userptm.setInt(5, newUser.getRoleID());
                int checkAddUser = userptm.executeUpdate();
                if (checkAddUser > 0) {
                    rs = userptm.getGeneratedKeys();
                    if (rs.next()) {
//                      INSERT INTO Customer(CustomerID, Area, District, Ward, DetailAddress, DayOfBirth) VALUES(?,?,?,?,?,null)
                        int custID = rs.getInt(1);
                        custptm = conn.prepareStatement(INSERT_CUSTOMER_GOOGLE);
                        custptm.setInt(1, custID);
                        custptm.setString(2, newCust.getArea());
                        custptm.setString(3, newCust.getDistrict());
                        custptm.setString(4, newCust.getWard());
                        custptm.setString(5, newCust.getDetailAddress());
                        custptm.setDate(6, newCust.getDayOfBirth());
                        check = custptm.executeUpdate() > 0;
                    }
                }
            }
        } finally {
            if (userptm != null) {
                userptm.close();
            }
            if (custptm != null) {
                custptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public UserDTO getUserByGoogleEmail(String email) throws ClassNotFoundException, SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_USER_BY_EMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("Name");
                    String userEmail = rs.getString("Email");
                    String password = rs.getString("Password");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    user = new UserDTO(userID, name, userEmail, password, phone, roleID, status);
                }
            }
        } finally {
            DBUtils.closeConnection3(conn, ptm, rs);
        }
        return user;
    }

    public CustomerDTO getCustInfoByID(int userID) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        CustomerDTO cust = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CUSTOMER_BY_USERID_01);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int custID = rs.getInt("CustomerID");
                    String area = rs.getString("Area");
                    String district = rs.getString("District");
                    String ward = rs.getString("Ward");
                    String detailAddress = rs.getString("DetailAddress");
                    Date dob = rs.getDate("DayOfBirth");
                    cust = new CustomerDTO(custID, area, district, ward, detailAddress, dob);
                }
            }
        } finally {
            DBUtils.closeConnection3(conn, ptm, rs);
        }
        return cust;
    }

    public boolean checkPhoneNumExisted(String phoneNum) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_USER_BY_PHONE);
                ptm.setString(1, phoneNum);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            DBUtils.closeConnection3(conn, ptm, rs);
        }
        return check;
    }

    public List<UserDTO> filterCustomer() throws SQLException, NamingException, ClassNotFoundException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(FILTER_CUSTOMER);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    listUser.add(new UserDTO(userID, name, email, "", phone, status, roleID));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public List<UserDTO> filterEmployee() throws SQLException, NamingException, ClassNotFoundException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(FILTER_EMPLOYEE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    listUser.add(new UserDTO(userID, name, email, "", phone, status, roleID));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public List<UserDTO> getListUser() throws ClassNotFoundException, SQLException, NamingException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_USER);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    listUser.add(new UserDTO(userID, name, email, phone, phone, status, roleID));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public boolean update(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getName());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getPhone());
                ptm.setInt(4, user.getRoleID());
                ptm.setInt(5, user.getUserID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateUserStatus(UserDTO user) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER_STATUS);
                ptm.setInt(1, user.getStatus());
                ptm.setInt(2, user.getUserID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public UserDTO checkLogin(String email, String password) throws SQLDataException, SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
//               SELECT * FROM dbo.Users WHERE Email LIKE ? AND Password LIKE ?
                ptm.setString(1, email);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("Name");
                    String emai11 = rs.getString("Email");
                    String password1 = rs.getString("Password");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    user = new UserDTO(userID, name, email, password, phone, status, roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public CustomerViewProfileDTO getCustInfoByUserID(int userID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        CustomerViewProfileDTO cust = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CUSTOMER_BY_USERID_01);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int custID = rs.getInt("CustomerID");
                    String area = rs.getString("Area");
                    String district = rs.getString("District");
                    String ward = rs.getString("Ward");
                    String detailAddress = rs.getString("DetailAddress");
                    Date dob = rs.getDate("DayOfBirth");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    cust = new CustomerViewProfileDTO(custID, area, district, ward, detailAddress, dob, name, email, password, phone, roleID, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return cust;
    }

    public String getPasswordByID(int userID) throws SQLException, ClassNotFoundException {
        String password = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_PASSWORD_BY_ID);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("Password");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return password;
    }

    public boolean updatePassword(int userID, String newPassword) throws SQLException, ClassNotFoundException {
        boolean isUpdated = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                //  "UPDATE dbo.Users SET Password=? WHERE UserID=?";
                ptm = conn.prepareStatement(UPDATE_PASSWORD);
                ptm.setString(1, newPassword);
                ptm.setInt(2, userID);
                isUpdated = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isUpdated;
    }

    public boolean updateCustomerProfile(CustomerViewProfileDTO customer) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        PreparedStatement userptm = null;
        PreparedStatement custptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Update Users
                userptm = conn.prepareStatement(UPDATE_PROFILE_USER);
                String cusName = customer.getName();
                String cusemail = customer.getEmail();
                String cusphone = customer.getPhone();
                int id = customer.getId();
                userptm.setString(1, customer.getName());
                userptm.setString(2, customer.getEmail());
                userptm.setString(3, customer.getPhone());
                userptm.setInt(4, customer.getId());
                boolean checkUserUpdate = userptm.executeUpdate() > 0;
                if (checkUserUpdate) {
//                    UPDATE dbo.Customer SET DetailAddress=?, Area=?, District=?, Ward=?, DayOfBirth=? WHERE customerID=?
                    custptm = conn.prepareStatement(UPDATE_PROFILE_CUSTOMER);
                    custptm.setString(1, customer.getDetailAddress());
                    custptm.setString(2, customer.getArea());
                    custptm.setString(3, customer.getDistrict());
                    custptm.setString(4, customer.getWard());
                    custptm.setDate(5, customer.getDayOfBirth());
                    custptm.setInt(6, customer.getId());
                    check = custptm.executeUpdate() > 0;
                }
            }
        } finally {
            if (userptm != null) {
                userptm.close();
            }
            if (custptm != null) {
                custptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public int getCusID(String phoneNum) throws ClassNotFoundException, SQLException {
        int userID = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_CUSTOMER_ID);
            ps.setString(1,"%" + phoneNum + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return userID;
    }

    public List<CustomerViewProfileDTO> getPersonalInfor(int customerID) throws ClassNotFoundException, SQLException {
        List<CustomerViewProfileDTO> profile = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        CustomerViewProfileDTO cust = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CUSTOMER_BY_USERID_01);
                ptm.setInt(1, customerID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int custID = rs.getInt("CustomerID");
                    String area = rs.getString("Area");
                    String district = rs.getString("District");
                    String ward = rs.getString("Ward");
                    String detailAddress = rs.getString("DetailAddress");
                    Date dob = rs.getDate("DayOfBirth");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    profile.add(new CustomerViewProfileDTO(custID, area, district, ward, detailAddress, dob, name, email, password, phone, roleID, status));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return profile;
    }

    public int getstaffID() throws ClassNotFoundException, SQLException {
        int staffID = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(GET_STAFF_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                staffID = rs.getInt("UserID");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return staffID;
    }
    
    public boolean createEmployeeAccount(String name, String email, String password,String phone, int status, int roleID ) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
//            INSERT INTO Promotion(PromotionName,ManagerID,Description,StartDate,EndDate,DiscountPer,Condition,Status) VALUES(?,?,?,?,?,?,?,?)
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_EMPLOYEE_ACCOUNT);
                ptm.setString(1, name);
                ptm.setString(2, email);
                ptm.setString(3, password);
                ptm.setString(4, phone);
                ptm.setInt(5, status);
                ptm.setInt(6, roleID);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<UserDTO> getListUserByPhone(String search) throws SQLException, NamingException, ClassNotFoundException {
         List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_USER_BY_PHONE);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    int status = rs.getInt("Status");
                    int roleID = rs.getInt("RoleID");
                    listUser.add(new UserDTO(userID, name, email, phone, phone, status, roleID));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public boolean checkEmailExistedForUp(String email, int userID) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_USER_BY_EMAIL_UPDATE);
                ptm.setString(1, email);
                ptm.setInt(2, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            DBUtils.closeConnection3(conn, ptm, rs);
        }
        return check;
    }

    public boolean checkPhoneNumExistedForUp(String phone, int userID) throws ClassNotFoundException, SQLException {
        
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_USER_BY_PHONE_UPDATE);
                ptm.setString(1, phone);
                ptm.setInt(2, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            DBUtils.closeConnection3(conn, ptm, rs);
        }
        return check;
    }
}
