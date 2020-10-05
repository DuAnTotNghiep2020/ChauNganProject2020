/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ThongBaoThanhCong {
    public static void ThongBao(String NoiDung,String TieuDe){
        ImageIcon icon = new ImageIcon("src/IMAGE/checked_checkbox_35px.png");
                JOptionPane.showMessageDialog(
                        null,
                        NoiDung,
                        TieuDe, JOptionPane.INFORMATION_MESSAGE,
                        icon);
    }//đóng của thông báo 
}//đóng của thông báo 
