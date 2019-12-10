package com.dao;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
@Repository("baseDao")
public class BaseDao {
    //第20面的ppt可以改成静态方法
    private static String URL ;
    private static String USER;
    private static String PASSWORD ;
    private static String DRIVER;
    static{
        init();
    }
    public static void init() {
        Properties params = new Properties();
        String configFile = "spring/jdbc.properties";
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try {
            params.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER = params.getProperty("DRIVER");
        URL = params.getProperty("URL");
        USER = params.getProperty("USER");
        PASSWORD = params.getProperty("PASSWORD");

        }
        public static Connection getConnection(){
        try{
//            System.out.println("连接数据库...");
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("获取数据库失败，请重试!");
        }
        return null;
    }
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs, PreparedStatement pstmt){
        try{
            if(rs != null) rs.close();
            if(stmt != null)stmt.close();
            if(conn!=null) conn.close();
            if(pstmt!=null) pstmt.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("数据库各项连接关闭失败，请重试！");
        }


    }
}
