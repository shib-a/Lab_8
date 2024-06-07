package server.managers;

import common.*;
import javafx.scene.paint.Color;
import server.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class DataConnector {
    static Logger logger = Logger.getLogger("DataConn");
//    static String jdbcUrl = "jdbc:postgresql:localhost:5432:studs&user=s409091&password=NUTd/6706";
    static String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
//    static String jdbcUrl = "jdbc:postgresql://pg:5432/studs";
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(jdbcUrl,"postgres","NUTd/6706");
//            conn = DriverManager.getConnection(jdbcUrl,"s409091","0QdjvOPZ6gsasXOL");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public static void initialize_db(){
        try{
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS collection_info (id SERIAL PRIMARY KEY, name TEXT, status TEXT, color TEXT, stats TEXT, isAlive TEXT ,rarity TEXT, owner TEXT NOT NULL, coordinates TEXT);");
            st.execute("CREATE TABLE IF NOT EXISTS user_info (id SERIAL PRIMARY KEY, name TEXT UNIQUE NOT NULL, salt TEXT, hash TEXT, permissions TEXT);");
            st.execute("CREATE TABLE IF NOT EXISTS banner_info (id SERIAL PRIMARY KEY, name TEXT not null , loot TEXT NOT NULL);");
        }catch (SQLException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.err.println(e);
        }
    }
    public static ArrayList<String> getUserInfo(String name) throws CustomException {
        ArrayList<String> res = new ArrayList<>();
        try {
            String query = "SELECT name, hash, permissions FROM user_info WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                res.add(rs.getString("name"));
                res.add(rs.getString("hash"));
            }
            return res;
        }catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new CustomException();
        }
    }

    public static void addHumanInfo(int id, String name, Status ptt, Color rt, String stats, boolean isAlive, Rarity rarity, String owner, Coordinates cords){
        try {
            String query = "INSERT INTO collection_info(id, name, status, color, stats, isAlive, rarity, owner, coordinates) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,ptt.name());
            ps.setString(4, rt.toString());
            ps.setString(5,stats);
            ps.setString(6, String.valueOf(isAlive));
            ps.setString(7,rarity.name());
            ps.setString(8, owner);
            if(cords==null){ps.setString(9, "null");}else{
                ps.setString(9, cords.toString());}
            ps.execute();
            logger.info("Added to sql table " + stats);
        }catch (SQLException e){e.printStackTrace();}
    }
    public static void addUserInfo(String name, String passw, Access access){
        try {
            String query = "INSERT INTO user_info(name, hash, permissions) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,passw);
            ps.setString(3, access.name());
            ps.executeQuery();
            logger.info("added to table");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return;
        }
    };
    public static void removeCollectionInfo(int id){
        String query = "DELETE FROM collection_info WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<String> getCollectionInfo(){
        try {
            String query = "SELECT * FROM collection_info";
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(query);
            ArrayList<String> result = new ArrayList<>();
            while(res.next()){
                result.add(res.getString("id")+","+res.getString("name")+","+res.getString("status")+","+res.getString("color")+","+res.getString("isAlive")+","+res.getString("stats")+","+res.getString("rarity")+","+res.getString("owner")+","+res.getString("coordinates"));
            }
            return result;
        } catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public static Integer getLatestId(){
        try{
            String query = "SELECT nextval('collection_info_id_seq');";
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(query);
            res.next();
            return res.getInt("nextval");
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
//    public static void addToLootPool(Human human){
//        try{
//            String query = "INSERT INTO banner_info(name, loot) VALUES ()";
//            Statement st = conn.prepareStatement(query);
//
//        }catch (SQLException e){e.printStackTrace();}
//    }
}
