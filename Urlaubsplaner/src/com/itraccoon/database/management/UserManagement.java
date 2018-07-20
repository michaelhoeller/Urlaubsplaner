package com.itraccoon.database.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itraccoon.database.ConnectionManager;
import com.itraccoon.database.device.UserDevice;
import com.itraccoon.exceptions.DefaultUserRoleException;
import com.itraccoon.object.User;
import com.itraccoon.util.Check;

public class UserManagement {
    
    private final static Logger logger = Logger.getLogger(UserManagement.class);
    
    public static User getUserById(Integer id) {
        User user = new User();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement(UserDevice.getSelectUserById());
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            conn.commit();
            while (rs.next()) {
                user.setId(rs.getInt("USER_ID"));
                user.setName(rs.getString("USER_NAME"));
                user.setDaysSpent(rs.getInt("USER_DAYS_SPENT"));
                user.setDaysRemaining(rs.getInt("USER_DAYS_REMAINING"));
                user.setDaysPerYear(rs.getInt("USER_DAYS_PER_YEAR"));
                user.setUserRole(rs.getInt("USER_USRO_ID"));
            }
        }
        catch (SQLException e) {
            logger.error("Could not get user with id " + id, e);
        }
        finally {
            ConnectionManager.close(rs, stmt, conn);
        }
        
        return user;
    }
    
    /**
     * @return A list of all users
     */
    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement(UserDevice.getSelectAllUsers());
            rs = stmt.executeQuery();
            
            conn.commit();
            while (rs.next()) {
                User userToAdd = new User();
                userToAdd.setId(rs.getInt("USER_ID"));
                userToAdd.setName(rs.getString("USER_NAME"));
                userToAdd.setDaysSpent(rs.getInt("USER_DAYS_SPENT"));
                userToAdd.setDaysRemaining(rs.getInt("USER_DAYS_REMAINING"));
                userToAdd.setDaysPerYear(rs.getInt("USER_DAYS_PER_YEAR"));
                userToAdd.setUserRole(rs.getInt("USER_USRO_ID"));
                
                userList.add(userToAdd);
            }
        }
        catch (SQLException e) {
            logger.error("Could not get userlist", e);
        }
        finally {
            ConnectionManager.close(rs, stmt, conn);
        }
        return userList;
    }
    
    public static void updateUser(User user) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UserDevice.getUpdateUserById());
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getDaysSpent());
            stmt.setInt(3, user.getDaysRemaining());
            stmt.setInt(4, user.getDaysPerYear());
            stmt.setInt(5, user.getUserRole());
            stmt.setInt(6, user.getId());
            
            stmt.execute();
            conn.commit();
        }
        catch (Exception e) {
            logger.error("Could not update user " + user.getName() + " with id " + user.getId(), e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
        logger.info("User " + user.getName() + " successfully updated");
    }
    
    /**
     * @param user
     *            Make sure user is propperly initialized
     */
    public static void insertNewUser(User user) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(UserDevice.getInsertNewUser());
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getDaysSpent());
            stmt.setInt(3, user.getDaysRemaining());
            stmt.setInt(4, user.getDaysPerYear());
            stmt.setInt(5, user.getUserRole());
            
            stmt.execute();
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Could not insert user", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
        logger.info("User " + user.getName() + " successfully inserted");
    }
    
    public static void insertNewUser(String name, Integer daysSpent, Integer daysRemaining, Integer daysPerYear, Integer userRole) {
        if (Check.isNullOrEmpty(name)) {
            name = "No name specified";
        }
        
        if (userRole == null) {
            try {
                userRole = UserRoleManagement.getDefaultUserRole();
            }
            catch (DefaultUserRoleException e) {
                logger.error("Could not get the default USRO_ID", e);
            }
        }
        
        User user = new User(name, daysSpent, daysRemaining, daysPerYear, userRole);
        insertNewUser(user);
    }
    
    /**
     * Deletes a user and all respective holidays and absences
     * 
     * @param id
     *            userId
     */
    public static void deleteUserById(Integer id) {
        
        HolidayManagement.deleteHolidaysByUserId(id);
        AbsenceManagement.deleteAbsencessByUserId(id);
        
        Connection conn = ConnectionManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(UserDevice.getDeleteUserById());
            stmt.setInt(1, id);
            
            stmt.execute();
            
            conn.commit();
        }
        catch (SQLException e) {
            logger.error("Could not delete user", e);
        }
        finally {
            ConnectionManager.close(null, stmt, conn);
        }
        logger.info("User successfully deleted");
    }
    
}
