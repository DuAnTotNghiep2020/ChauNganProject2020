/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.ThongBaoLoi;
import GUI.ThongBaoThongTin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import GUI.FullColor.FullColors;
/**
 *
 * @author Takemikazuchi
 */
public class DBConection {



    private static String DB_URL = "jdbc:mysql://103.138.88.11/sho71306_ChauNganProject";   
    private static String USER_NAME = "sho71306_Adminchaungan";
    private static String PASSWORD = "Duantotnghiep2020";
    public static Connection conn;
 public static Statement stm;

  public static ResultSet rs;
 
  
    public  DBConection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
           
           System.out.println( FullColors.CYAN_BACKGROUND + "Connect To Database Success!");

        } catch (ClassNotFoundException ex) {
            System.out.println("Kết nối thất bại, Vui lòng kiểm tra đường truyền mạng");
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối CSDL");
             ThongBaoLoi.ThongBao("Không tìm thấy mạng", "Thông báo");
        }

    }

    public static ResultSet GetData(String cauTruyVan) {
        try {
         
             stm = conn.createStatement();
             rs = stm.executeQuery(cauTruyVan);
          System.out.println(FullColors.GREEN_BACKGROUND + "GetData Complete!");
            return rs;
        } catch (SQLException ex) {
            System.out.println("lỗi lấy dữ liệu " + ex);
            ThongBaoThongTin.ThongBao(" Mất kết nối /n"+ " Vui lòng kiểm tra đường truyền mạng", "Thông báo");
            return null;
        }
    }
    public static int ExcuteTruyVan(String cauTruyVan) {
        try {
            
             stm = conn.createStatement();
            int kq = stm.executeUpdate(cauTruyVan);
           System.out.println(FullColors.WHITE + FullColors.BLUE_BACKGROUND + "ExecuteTruyVan Complete!");
            return kq;
        } catch (SQLException ex) {
            System.out.println("Lỗi Thực Thi lệnh SQL");
            return -1;
        }
    }
public static void Connect(){
     DBConection db = new DBConection();
     
}
    public static void Disconnect(){
        try {
             conn.close();
            System.out.println(FullColors.WHITE + FullColors.RED_BACKGROUND + "Disconnect To Database!");
        } catch (SQLException ex) {
            Logger.getLogger(DBConection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static int KhachHang(String cauTruyVan) {
        try {
             stm = conn.createStatement();
            int kq = stm.executeUpdate(cauTruyVan);
            System.out.println(cauTruyVan);
            return kq;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(),
                "Không xóa được Khách Hàng ngày vì có dữ liệu liên quan",
                "Thông báo lỗi", JOptionPane.OK_OPTION);
            return -1;
        }
    }
     
      public static ResultSet GetDataLogin(String cauTruyVan) {
        try {
             
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(cauTruyVan);
            System.out.println(FullColors.GREEN_BACKGROUND + "GetData Complete!");
            return rs;
        } catch (SQLException ex) {
            System.out.println("lỗi lấy dữ liệu " + ex);
            System.out.println(FullColors.WHITE + FullColors.PURPLE_BACKGROUND + "GetData Fail!");
            return null;
        }
    }
}
