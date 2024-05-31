package server;

import common.Permissinons;
import common.UserData;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class DataConnector {
    static Logger logger = Logger.getLogger("DataConn");
//    static String jdbcUrl = "jdbc:postgresql:localhost:5432:studs&user=s409091&password=NUTd/6706";
    static String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";

    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(jdbcUrl,"postgres","NUTd/6706");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public static void initialize_db(){
        try{
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS collection_info (id SERIAL PRIMARY KEY, name TEXT, preferred_tool TEXT, researcher_type TEXT, stats TEXT, isAlive TEXT, dug_counter TEXT, owner TEXT NOT NULL);");
            st.execute("CREATE TABLE IF NOT EXISTS user_info (id SERIAL PRIMARY KEY, name TEXT UNIQUE NOT NULL, salt TEXT, hash TEXT);");
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
    public static void addUserInfo(String name, String passw, Permissinons permissinons){
        try {
            String query = "INSERT INTO user_info(name, hash, permissions) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,passw);
            ps.setString(3, permissinons.name());
            ps.executeQuery();
            logger.info("added to table");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return;
        }
    };
    public static ArrayList<String> getCollectionInfo(){
        try {
            String query = "SELECT * FROM collection_info";
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(query);
            ArrayList<String> result = new ArrayList<>();
            while(res.next()){
                result.add(Integer.toString(res.getInt("id")));
                result.add(res.getString("name"));
                result.add(res.getString("preferred_tool"));
                result.add(res.getString("researcher_type"));
                result.add(res.getString("stats"));
                result.add(res.getString("isAlive"));
                result.add(res.getString("dug_counter"));
                result.add(res.getString("owner"));
//                result.add("");
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
//    public static void update(int id, UserData ud){
//        String query = "UPDATE ";
//        PreparedStatement st = conn.prepareStatement(query);
//        st.setInt(1,id);
//    }

}
