package server;

import common.Access;
import common.ResearcherType;
import common.ToolKinds;

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
            st.execute("CREATE TABLE IF NOT EXISTS collection_info (id SERIAL PRIMARY KEY, name TEXT, preferred_tool TEXT, researcher_type TEXT, stats TEXT, isAlive TEXT, dug_counter TEXT, owner TEXT NOT NULL);");
            st.execute("CREATE TABLE IF NOT EXISTS user_info (id SERIAL PRIMARY KEY, name TEXT UNIQUE NOT NULL, salt TEXT, hash TEXT, permissions TEXT);");
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
    public static void addHumanInfo(int id, String name, ToolKinds ptt, ResearcherType rt, String stats, boolean isAlive, int dug_counter, String owner){
        try {
            String query = "INSERT INTO collection_info(id, name, preferred_tool, researcher_type, stats, isAlive, dug_counter, owner) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,ptt.name());
            ps.setString(4, rt.name());
            ps.setString(5,stats);
            ps.setString(6, String.valueOf(isAlive));
            ps.setInt(7, dug_counter);
            ps.setString(8, owner);
            ps.executeQuery();
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
    public static ArrayList<String> getCollectionInfo(){
        try {
            String query = "SELECT * FROM collection_info";
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(query);
            ArrayList<String> result = new ArrayList<>();
            while(res.next()){
//                result.add(Integer.toString(res.getInt("id")));
//                result.add(res.getString("name"));
//                result.add(res.getString("preferred_tool"));
//                result.add(res.getString("researcher_type"));
//                result.add(res.getString("stats"));
//                result.add(res.getString("isAlive"));
//                result.add(res.getString("dug_counter"));
//                result.add(res.getString("owner"));
//                result.add("");
                result.add(res.getString("id")+","+res.getString("name")+","+res.getString("preferred_tool")+","+res.getString("researcher_type")+","+res.getString("isAlive")+","+res.getString("stats")+","+res.getString("dug_counter")+","+res.getString("owner"));

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
