package com.Utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.NoMatchFoundException.NoMatchFoundException;
import com.classes.Resident;


public class VillageUtils {

    private static final String dbURL = "jdbc:mysql://127.0.0.1:3306/";
    private static final String dbName = "villageDB";
    private static final String username = "ebi";
    private static final String password = "Eb1l@n31124";

    private VillageUtils(){}

    private static final Logger logger = LoggerFactory.getLogger(VillageUtils.class);

    public static Connection getConnection(String schemaName) {

        MysqlDataSource mysqlDS = null;
        Connection conn = null;

        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setUrl(dbURL + schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();
        } catch (SQLException sqlEx) {
            logger.error("SQLException occurred while executing SQL statement", sqlEx);
        }

        return conn;
    }

    public static List<Resident> displayResidents() throws SQLException, NoMatchFoundException {

        Connection conn = VillageUtils.getConnection(dbName);
        List<Resident> list = new ArrayList<>();
        Statement s = conn.createStatement();
        ResultSet residents = s.executeQuery("SELECT * FROM residents");
        if (!residents.isBeforeFirst()) { // Check if the ResultSet is empty
            throw new NoMatchFoundException("No residents found in registry");
        }
        while (residents.next()) {
            int id = residents.getInt("id"); // Assuming "id" is an integer
            String name = residents.getString("name");
            String gender = residents.getString("gender");
            String residentAdd = residents.getString("residentAdd");
            boolean isHomeOwner = residents.getBoolean("isHomeOwner"); // Assuming "isHomeOwner" is a boolean
            boolean isTenant = residents.getBoolean("isTenant");        // Assuming "isTenant" is a boolean
            String contactNo = residents.getString("contactNo");

            System.out.printf("%d\t%-20s\t%-10s\t%-30s\t%-10s\t%-10s\t%-10s\n",
                    id, name, gender, residentAdd, isHomeOwner ? "Yes" : "No", isTenant ? "Yes" : "No", contactNo);
            list.add(new Resident(id, name, gender, residentAdd, isHomeOwner, isTenant, contactNo));
        }
        return list;
    }

    public static List<Resident> searchById(int _id) throws SQLException, NoMatchFoundException {
        Connection conn = VillageUtils.getConnection(dbName);
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM residents WHERE id=?");
        List<Resident> list = new ArrayList<>();
        ps.setInt(1,_id);


        ResultSet residents = ps.executeQuery();
        if (!residents.isBeforeFirst()) { // Check if the ResultSet is empty
            throw new NoMatchFoundException("No residents found with the id: " + _id);
        }
        while (residents.next()) {
            int id = residents.getInt("id"); // Assuming "id" is an integer
            String name = residents.getString("name");
            String gender = residents.getString("gender");
            String residentAdd = residents.getString("residentAdd");
            boolean isHomeOwner = residents.getBoolean("isHomeOwner"); // Assuming "isHomeOwner" is a boolean
            boolean isTenant = residents.getBoolean("isTenant");        // Assuming "isTenant" is a boolean
            String contactNo = residents.getString("contactNo");

            System.out.printf("%d\t%-20s\t%-10s\t%-30s\t%-10s\t%-10s\t%-10s\n",
                    id, name, gender, residentAdd, isHomeOwner ? "Yes" : "No", isTenant ? "Yes" : "No", contactNo);
            list.add(new Resident(id, name, gender, residentAdd, isHomeOwner, isTenant, contactNo));
        }
        return list;
    }

    public static List<Resident> searchByName(String _name) throws SQLException, NoMatchFoundException {
        Connection conn = VillageUtils.getConnection(dbName);
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM residents WHERE name=?");
        List<Resident> list = new ArrayList<>();
        ps.setString(1,_name);


        ResultSet residents = ps.executeQuery();
        if (!residents.isBeforeFirst()) { // Check if the ResultSet is empty
            throw new NoMatchFoundException("No residents found with the name: " + _name);
        }
        while (residents.next()) {
            int id = residents.getInt("id"); // Assuming "id" is an integer
            String name = residents.getString("name");
            String gender = residents.getString("gender");
            String residentAdd = residents.getString("residentAdd");
            boolean isHomeOwner = residents.getBoolean("isHomeOwner"); // Assuming "isHomeOwner" is a boolean
            boolean isTenant = residents.getBoolean("isTenant");        // Assuming "isTenant" is a boolean
            String contactNo = residents.getString("contactNo");

            System.out.printf("%d\t%-20s\t%-10s\t%-30s\t%-10s\t%-10s\t%-10s\n",
                    id, name, gender, residentAdd, isHomeOwner ? "Yes" : "No", isTenant ? "Yes" : "No", contactNo);
            list.add(new Resident(id, name, gender, residentAdd, isHomeOwner, isTenant, contactNo));
        }
        return list;
    }

    public static List<Resident> searchByPartialName (String _name) throws SQLException, NoMatchFoundException {
        Connection conn = VillageUtils.getConnection(dbName);
        List<Resident> list = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM residents WHERE name LIKE ?");
        String __name = "%"+_name+"%";
        ps.setString(1,__name);


        ResultSet residents = ps.executeQuery();
        if (!residents.isBeforeFirst()) { // Check if the ResultSet is empty
            throw new NoMatchFoundException("No residents found with the name: " + _name);
        }
        while (residents.next()) {
            int id = residents.getInt("id"); // Assuming "id" is an integer
            String name = residents.getString("name");
            String gender = residents.getString("gender");
            String residentAdd = residents.getString("residentAdd");
            boolean isHomeOwner = residents.getBoolean("isHomeOwner"); // Assuming "isHomeOwner" is a boolean
            boolean isTenant = residents.getBoolean("isTenant");        // Assuming "isTenant" is a boolean
            String contactNo = residents.getString("contactNo");

            System.out.printf("%d\t%-20s\t%-10s\t%-30s\t%-10s\t%-10s\t%-10s\n",
                    id, name, gender, residentAdd, isHomeOwner ? "Yes" : "No", isTenant ? "Yes" : "No", contactNo);
            list.add(new Resident(id, name, gender, residentAdd, isHomeOwner, isTenant, contactNo));
        }
        return list;
    }

    public static List<Resident> searchByPartialAddress (String _add) throws SQLException, NoMatchFoundException {
        Connection conn = VillageUtils.getConnection(dbName);
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM residents WHERE residentAdd LIKE ?");
        String __add = "%"+_add+"%";
        ps.setString(1,__add);
        List<Resident> list = new ArrayList<>();


        ResultSet residents = ps.executeQuery();
        if (!residents.isBeforeFirst()) { // Check if the ResultSet is empty
            throw new NoMatchFoundException("No residents found with the address: " + _add);
        }
        while (residents.next()) {
            int id = residents.getInt("id"); // Assuming "id" is an integer
            String name = residents.getString("name");
            String gender = residents.getString("gender");
            String residentAdd = residents.getString("residentAdd");
            boolean isHomeOwner = residents.getBoolean("isHomeOwner"); // Assuming "isHomeOwner" is a boolean
            boolean isTenant = residents.getBoolean("isTenant");        // Assuming "isTenant" is a boolean
            String contactNo = residents.getString("contactNo");

            System.out.printf("%d\t%-20s\t%-10s\t%-30s\t%-10s\t%-10s\t%-10s\n",
                    id, name, gender, residentAdd, isHomeOwner ? "Yes" : "No", isTenant ? "Yes" : "No", contactNo);
            list.add(new Resident(id, name, gender, residentAdd, isHomeOwner, isTenant, contactNo));
        }
        return list;
    }

    public static void registerResident(
            String name, String gender, String residentAdd, Boolean isHO, Boolean isTen, String contactNo){
        Connection conn = null;
        try{
            conn = VillageUtils.getConnection(dbName);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO residents " +
                    "(name, gender, residentAdd, isHomeOwner, isTenant, contactNo) VALUES (?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, residentAdd);
            ps.setBoolean(4,isHO);
            ps.setBoolean(5,isTen);
            ps.setString(6, contactNo);
            ps.executeUpdate();

            System.out.println("Successfully registered resident");
        }
        catch(SQLException sqlEx){
            logger.error("SQLException occurred while executing SQL statement", sqlEx);
        }
    }

    public static void updateResident(String field, Object value, int id){
        Connection conn = null;
        try{
            conn = VillageUtils.getConnection(dbName);
            switch (field) {
                case "name" -> {PreparedStatement ps = conn.prepareStatement("UPDATE residents SET name=? WHERE id=?");
                    ps.setString(1,(String) value); ps.setInt(2, id); ps.executeUpdate();}
                case "gender" -> {PreparedStatement ps = conn.prepareStatement("UPDATE residents SET gender=? WHERE id=?");
                    ps.setString(1,(String) value); ps.setInt(2, id); ps.executeUpdate();}
                case "residentAdd" -> {PreparedStatement ps = conn.prepareStatement("UPDATE residents SET residentAdd=? WHERE id=?");
                    ps.setString(1,(String) value); ps.setInt(2, id); ps.executeUpdate();}
                case "isHomeOwner" -> {PreparedStatement ps = conn.prepareStatement("UPDATE residents SET isHomeOwner=? WHERE id=?");
                    ps.setBoolean(1,(Boolean) value); ps.setInt(2, id); ps.executeUpdate();}
                case "isTenant" -> {PreparedStatement ps = conn.prepareStatement("UPDATE residents SET isTenant=? WHERE id=?");
                    ps.setBoolean(1,(Boolean) value); ps.setInt(2, id); ps.executeUpdate();}
                case "isActive" -> {PreparedStatement ps = conn.prepareStatement("UPDATE residents SET isActive=? WHERE id=?");
                    ps.setBoolean(1,(Boolean) value); ps.setInt(2, id); ps.executeUpdate();}
                case "contactNo" -> {PreparedStatement ps = conn.prepareStatement("UPDATE residents SET contactNo=? WHERE id=?");
                    ps.setString(1,(String) value); ps.setInt(2, id); ps.executeUpdate();}
                case null, default -> throw new IllegalArgumentException("Field entry invalid");
            }


            System.out.println("Successfully updated Resident Information");
            }
        catch(SQLException sqlEx){
            logger.error("SQLException occurred while executing SQL statement", sqlEx);
        }
    }

    public static void deleteResident(int id){
        Connection conn = null;
        try{
            conn = VillageUtils.getConnection("villageDB");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM residents WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Resident registry deleted successfully");
        }
        catch(SQLException sqlEx){
            logger.error("SQLException occurred while executing SQL statement", sqlEx);
        }
    }
}
