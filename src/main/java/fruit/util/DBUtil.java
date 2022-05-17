package fruit.util;

import java.sql.*;

public class DBUtil {
    private static final String URl = "jdbc:mysql://localhost:3306/fruitdb?characterEncoding=utf-8&useSSL=false";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    //获取Connection
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URl,USERNAME,PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public static void close(ResultSet rs,PreparedStatement pst,Connection con){
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            rs = null;
        }
        if(pst!=null){
            try{
                pst.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            pst = null;
        }
        if(con!=null){
            try{
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            con = null;
        }
    }
}
