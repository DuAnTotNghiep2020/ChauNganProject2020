/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.ChuyenDoi;
import DAO.DBConection;
import static DAO.DBConection.conn;
import DTO.DTOKhachHang;
import DTO.DTOLoaiKhachHang;
import DTO.MyCombobox;
import static GUI.ThongBaoCanhBao.ThongBao;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import static java.nio.file.Files.list;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Takemikazuchi
 */
public final class pnlkhachhang extends javax.swing.JPanel {

    public ImageIcon ResizeImage(String imagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lblAnhDaiDien.getWidth(), lblAnhDaiDien.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    private void FillKhachHang() {

        pnlloaikhachhang.removeAll();

        DefaultTableModel dtm = (DefaultTableModel) tblkhachhang.getModel();
        dtm.setRowCount(0);

        ArrayList<DTOLoaiKhachHang> loaikhachhang = DAO.DAOLoaiKhachHang.GetLoaiKhachHang();
        ArrayList<Boolean> checkclick = new ArrayList<>();
        JPanel[] pnlBan = new JPanel[loaikhachhang.size()];
        JLabel[] lblImgBan = new JLabel[loaikhachhang.size()];
        JLabel[] lblTenBan = new JLabel[loaikhachhang.size()];
        int i = 0;
        final int fu = i;
        for (i = 0; i < loaikhachhang.size(); i++) {
            checkclick.add(i, false);
            pnlBan[i] = new javax.swing.JPanel();
            lblImgBan[i] = new javax.swing.JLabel();
            lblTenBan[i] = new javax.swing.JLabel();
            lblTenBan[i].setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            lblTenBan[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblTenBan[i].setText(loaikhachhang.get(i).getTenLoaiKhachHang());

            javax.swing.GroupLayout pnlBanLayout = new javax.swing.GroupLayout(pnlBan[i]);
            pnlBan[i].setLayout(pnlBanLayout);
            pnlBanLayout.setHorizontalGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBanLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblImgBan[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTenBan[i], javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(21, Short.MAX_VALUE))
            );
            pnlBanLayout.setVerticalGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBanLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblImgBan[i])
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTenBan[i])
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            int j = i;
            pnlBan[j].addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if (checkclick.get(j)) {
                        checkclick.set(j, false);

                        e.getComponent().setBackground(new Color(33,36,51));
                    } else {
                        checkclick.set(j, true);
                        BLL.BLLKhachHang.HienThiKhachHangTheoMa(tblkhachhang, loaikhachhang.get(j).getIdLoaiKhachHang());
                        for (int k = 0; k < loaikhachhang.size(); k++) {
                            if (k != j) {
                                checkclick.set(k, false);
                                pnlBan[k].setBackground(new java.awt.Color(240, 240, 240));
                            }
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    e.getComponent().setBackground(new Color(33,36,51));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (checkclick.get(j)) {
                        e.getComponent().setBackground(new Color(33,36,51));

                    } else {

                        e.getComponent().setBackground(new java.awt.Color(240, 240, 240));
                    }
                }
            });
            pnlloaikhachhang.add(pnlBan[i]);
        }
        pnlloaikhachhang.updateUI();
    }

    public pnlkhachhang() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {

        }
        initComponents();
        cbbNhanVien.setSelectedItem(BLL.BLLlogin.nguoidung.getTenNguoiDung());
        BLL.BLLKhachHang.DoDuLieuVaoCBBLoaiKhachHang(cbbLoaiKhachHang);
        FillKhachHang();
        BLL.BLLKhachHang.HienThiKhachHang(tblkhachhang);
        cbbloaikhachhang.setBackground(Color.WHITE);
        tblkhachhang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblkhachhang.getTableHeader().setOpaque(false);
        tblkhachhang.getTableHeader().setBackground(new Color(33, 36, 51));
        tblkhachhang.getTableHeader().setForeground(Color.WHITE);
        tblkhachhang.setRowHeight(25);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpchuyentab = new javax.swing.JTabbedPane();
        pnldanhsach = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        cbbloaikhachhang = new javax.swing.JComboBox<>();
        srcdanhsach = new javax.swing.JScrollPane();
        tblkhachhang = new javax.swing.JTable();
        txttimkiem = new javax.swing.JTextField();
        pnlloaikhachhang = new javax.swing.JPanel();
        lblloaikhachhang = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnlthemsuakhachhang = new javax.swing.JPanel();
        pnlnenthemsuakhachhang1 = new javax.swing.JPanel();
        pnlnenngoaianhdaidienvathongtinkhac1 = new javax.swing.JPanel();
        pnlnenngoaianhdaidienvathongtinkhac = new javax.swing.JPanel();
        pnlnenngoaianhdaidien1 = new javax.swing.JPanel();
        pnlnenngoaianhdaidien = new javax.swing.JPanel();
        pnlnenanhdaidien = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlanhdaidien = new javax.swing.JPanel();
        lblAnhDaiDien = new javax.swing.JLabel();
        lblanhdaidien = new javax.swing.JLabel();
        pnlnenngoaithongtinkhac2 = new javax.swing.JPanel();
        pnlnenngoaithongtinkhac1 = new javax.swing.JPanel();
        pnlnenngoaithongtinkhac = new javax.swing.JPanel();
        pnlnenthongtinkhac = new javax.swing.JPanel();
        lblthongtinkhac = new javax.swing.JLabel();
        pnlnennhomkhachhang = new javax.swing.JPanel();
        lblnhomkhachhang = new javax.swing.JLabel();
        cbbLoaiKhachHang = new javax.swing.JComboBox<>();
        pnlnennhanvienphutrach = new javax.swing.JPanel();
        lblnhanvienphutrach = new javax.swing.JLabel();
        cbbNhanVien = new javax.swing.JComboBox<>();
        pnlnenmota = new javax.swing.JPanel();
        lblmota = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextPane();
        pnlnenlonthongtincoban = new javax.swing.JPanel();
        pnlnenlonngoaithongtin = new javax.swing.JPanel();
        pnlnenngoaithongtin = new javax.swing.JPanel();
        pnlnenthongtin = new javax.swing.JPanel();
        lblthongtincoban = new javax.swing.JLabel();
        pnlnenmatkhau = new javax.swing.JPanel();
        lblmatkhau = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        pnlemail = new javax.swing.JPanel();
        lblemail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlnensodienthoai = new javax.swing.JPanel();
        lblsodienthoai = new javax.swing.JLabel();
        txtsodienthoai = new javax.swing.JTextField();
        pnlnenhotenkhachhang = new javax.swing.JPanel();
        lblhotenkhachhang = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblthongtinbosung = new javax.swing.JLabel();
        pnlnenid = new javax.swing.JPanel();
        lblid = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        pnlnenmangxahoi = new javax.swing.JPanel();
        lblmangxahoi = new javax.swing.JLabel();
        txtMangXaHoi = new javax.swing.JTextField();
        pnlnengioitinh = new javax.swing.JPanel();
        lblgioitinh = new javax.swing.JLabel();
        cbbGioiTinh = new javax.swing.JComboBox<>();
        pnlnenngaysinh = new javax.swing.JPanel();
        lblngaysinh = new javax.swing.JLabel();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        lblthongtindiachi = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jPanel40 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTag = new javax.swing.JTextField();
        pnlnentinh = new javax.swing.JPanel();
        lbltinh = new javax.swing.JLabel();
        cbbtinh = new javax.swing.JComboBox<>();
        pnlnendiachi = new javax.swing.JPanel();
        lbldiachihientai = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        pnlnenngoaibtn = new javax.swing.JPanel();
        pnlnenbtn = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblghichu = new javax.swing.JLabel();
        FileChooser = new javax.swing.JFileChooser();

        setBackground(new java.awt.Color(33, 36, 51));
        setPreferredSize(new java.awt.Dimension(980, 620));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        tbpchuyentab.setBackground(new java.awt.Color(255, 255, 255));
        tbpchuyentab.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        pnldanhsach.setBackground(new java.awt.Color(33, 36, 51));
        pnldanhsach.setPreferredSize(new java.awt.Dimension(980, 618));

        jCheckBox1.setBackground(new java.awt.Color(33, 36, 51));
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Tìm bằng mã QR-Barcode");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        cbbloaikhachhang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cbbloaikhachhang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lọc khách hàng" }));
        cbbloaikhachhang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cbbloaikhachhang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbloaikhachhang.setFocusCycleRoot(true);
        cbbloaikhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbloaikhachhangActionPerformed(evt);
            }
        });

        tblkhachhang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblkhachhang.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        tblkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", "", "", "", null, null},
                {"", "", "", "", "", "", null, null},
                {"", "", "", "", "", null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số Điện Thoại", "Email", "Ngày Sinh", "Địa Chỉ", "Giới Tính", "Hình Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkhachhang.setDragEnabled(true);
        tblkhachhang.setFocusable(false);
        tblkhachhang.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblkhachhang.setName(""); // NOI18N
        tblkhachhang.setRowHeight(25);
        tblkhachhang.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblkhachhang.setShowVerticalLines(false);
        tblkhachhang.getTableHeader().setReorderingAllowed(false);
        tblkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhachhangMouseClicked(evt);
            }
        });
        srcdanhsach.setViewportView(tblkhachhang);

        txttimkiem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttimkiemKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttimkiemKeyTyped(evt);
            }
        });

        pnlloaikhachhang.setBackground(new java.awt.Color(255, 255, 255));
        pnlloaikhachhang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblloaikhachhang.setBackground(new java.awt.Color(33, 36, 51));
        lblloaikhachhang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblloaikhachhang.setForeground(new java.awt.Color(255, 255, 255));
        lblloaikhachhang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblloaikhachhang.setText("Loại khách hàng");
        lblloaikhachhang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/delete_20px_1.png"))); // NOI18N
        jButton1.setText("Xóa K.Hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/maintenance_20px.png"))); // NOI18N
        jButton2.setText("Sửa K.Hàng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Thêm loại KH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/microsoft_excel_18px.png"))); // NOI18N
        jButton4.setText("Xuất Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnldanhsachLayout = new javax.swing.GroupLayout(pnldanhsach);
        pnldanhsach.setLayout(pnldanhsachLayout);
        pnldanhsachLayout.setHorizontalGroup(
            pnldanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldanhsachLayout.createSequentialGroup()
                .addComponent(lblloaikhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cbbloaikhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txttimkiem)
                .addGap(5, 5, 5)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnldanhsachLayout.createSequentialGroup()
                .addGroup(pnldanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlloaikhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnldanhsachLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnldanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jSeparator4)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addComponent(srcdanhsach, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE))
        );
        pnldanhsachLayout.setVerticalGroup(
            pnldanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldanhsachLayout.createSequentialGroup()
                .addGroup(pnldanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblloaikhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbloaikhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnldanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldanhsachLayout.createSequentialGroup()
                        .addComponent(pnlloaikhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(srcdanhsach, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)))
        );

        cbbloaikhachhang.getAccessibleContext().setAccessibleParent(cbbloaikhachhang);

        tbpchuyentab.addTab("Danh sách khách hàng", new javax.swing.ImageIcon(getClass().getResource("/IMAGE/list_20px.png")), pnldanhsach); // NOI18N

        pnlnenthemsuakhachhang1.setLayout(new java.awt.BorderLayout());

        pnlnenngoaianhdaidienvathongtinkhac1.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenngoaianhdaidienvathongtinkhac1.setPreferredSize(new java.awt.Dimension(275, 550));
        pnlnenngoaianhdaidienvathongtinkhac1.setLayout(new java.awt.BorderLayout());

        pnlnenngoaianhdaidienvathongtinkhac.setPreferredSize(new java.awt.Dimension(275, 300));
        pnlnenngoaianhdaidienvathongtinkhac.setLayout(new java.awt.BorderLayout());

        pnlnenngoaianhdaidien1.setBackground(new java.awt.Color(0, 51, 204));
        pnlnenngoaianhdaidien1.setPreferredSize(new java.awt.Dimension(200, 260));
        pnlnenngoaianhdaidien1.setLayout(new java.awt.BorderLayout());

        pnlnenngoaianhdaidien.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenngoaianhdaidien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 246, 248), 5));
        pnlnenngoaianhdaidien.setPreferredSize(new java.awt.Dimension(275, 260));

        pnlnenanhdaidien.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenanhdaidien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("                                                                ");
        jLabel1.setPreferredSize(new java.awt.Dimension(192, 1));
        pnlnenanhdaidien.add(jLabel1);

        pnlanhdaidien.setBackground(new java.awt.Color(255, 255, 255));
        pnlanhdaidien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAnhDaiDien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhDaiDienMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlanhdaidienLayout = new javax.swing.GroupLayout(pnlanhdaidien);
        pnlanhdaidien.setLayout(pnlanhdaidienLayout);
        pnlanhdaidienLayout.setHorizontalGroup(
            pnlanhdaidienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        pnlanhdaidienLayout.setVerticalGroup(
            pnlanhdaidienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );

        pnlnenanhdaidien.add(pnlanhdaidien);

        lblanhdaidien.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblanhdaidien.setText("Ảnh đại diện");
        pnlnenanhdaidien.add(lblanhdaidien);

        javax.swing.GroupLayout pnlnenngoaianhdaidienLayout = new javax.swing.GroupLayout(pnlnenngoaianhdaidien);
        pnlnenngoaianhdaidien.setLayout(pnlnenngoaianhdaidienLayout);
        pnlnenngoaianhdaidienLayout.setHorizontalGroup(
            pnlnenngoaianhdaidienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnenngoaianhdaidienLayout.createSequentialGroup()
                .addComponent(pnlnenanhdaidien, javax.swing.GroupLayout.PREFERRED_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlnenngoaianhdaidienLayout.setVerticalGroup(
            pnlnenngoaianhdaidienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenanhdaidien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlnenngoaianhdaidien1.add(pnlnenngoaianhdaidien, java.awt.BorderLayout.PAGE_START);

        pnlnenngoaianhdaidienvathongtinkhac.add(pnlnenngoaianhdaidien1, java.awt.BorderLayout.PAGE_START);

        pnlnenngoaithongtinkhac2.setBackground(new java.awt.Color(255, 255, 0));
        pnlnenngoaithongtinkhac2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(244, 246, 248), 5, true));
        pnlnenngoaithongtinkhac2.setPreferredSize(new java.awt.Dimension(275, 310));

        pnlnenngoaithongtinkhac1.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenngoaithongtinkhac1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlnenngoaithongtinkhac1.setLayout(new java.awt.BorderLayout());

        pnlnenngoaithongtinkhac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        pnlnenthongtinkhac.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenthongtinkhac.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblthongtinkhac.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblthongtinkhac.setText("Thông tin khác");
        lblthongtinkhac.setPreferredSize(new java.awt.Dimension(34, 20));

        pnlnennhomkhachhang.setBackground(new java.awt.Color(255, 255, 255));
        pnlnennhomkhachhang.setLayout(new java.awt.BorderLayout());

        lblnhomkhachhang.setBackground(new java.awt.Color(255, 255, 255));
        lblnhomkhachhang.setText("Nhóm khách hàng");
        lblnhomkhachhang.setPreferredSize(new java.awt.Dimension(75, 20));
        pnlnennhomkhachhang.add(lblnhomkhachhang, java.awt.BorderLayout.PAGE_START);

        cbbLoaiKhachHang.setToolTipText("");
        pnlnennhomkhachhang.add(cbbLoaiKhachHang, java.awt.BorderLayout.CENTER);

        pnlnennhanvienphutrach.setBackground(new java.awt.Color(255, 255, 255));
        pnlnennhanvienphutrach.setLayout(new java.awt.BorderLayout());

        lblnhanvienphutrach.setBackground(new java.awt.Color(255, 255, 255));
        lblnhanvienphutrach.setText("Nhân viên phụ trách");
        lblnhanvienphutrach.setPreferredSize(new java.awt.Dimension(75, 20));
        pnlnennhanvienphutrach.add(lblnhanvienphutrach, java.awt.BorderLayout.PAGE_START);

        pnlnennhanvienphutrach.add(cbbNhanVien, java.awt.BorderLayout.CENTER);

        pnlnenmota.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenmota.setLayout(new java.awt.BorderLayout());

        lblmota.setBackground(new java.awt.Color(255, 255, 255));
        lblmota.setText("Mô tả");
        lblmota.setPreferredSize(new java.awt.Dimension(75, 20));
        pnlnenmota.add(lblmota, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setViewportView(txtMoTa);

        pnlnenmota.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlnenthongtinkhacLayout = new javax.swing.GroupLayout(pnlnenthongtinkhac);
        pnlnenthongtinkhac.setLayout(pnlnenthongtinkhacLayout);
        pnlnenthongtinkhacLayout.setHorizontalGroup(
            pnlnenthongtinkhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnenthongtinkhacLayout.createSequentialGroup()
                .addGroup(pnlnenthongtinkhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblthongtinkhac, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlnenthongtinkhacLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlnenthongtinkhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlnennhomkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlnennhanvienphutrach, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlnenmota, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlnenthongtinkhacLayout.setVerticalGroup(
            pnlnenthongtinkhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnenthongtinkhacLayout.createSequentialGroup()
                .addComponent(lblthongtinkhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlnennhomkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlnennhanvienphutrach, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlnenmota, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlnenngoaithongtinkhacLayout = new javax.swing.GroupLayout(pnlnenngoaithongtinkhac);
        pnlnenngoaithongtinkhac.setLayout(pnlnenngoaithongtinkhacLayout);
        pnlnenngoaithongtinkhacLayout.setHorizontalGroup(
            pnlnenngoaithongtinkhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenthongtinkhac, javax.swing.GroupLayout.PREFERRED_SIZE, 251, Short.MAX_VALUE)
        );
        pnlnenngoaithongtinkhacLayout.setVerticalGroup(
            pnlnenngoaithongtinkhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenthongtinkhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlnenngoaithongtinkhac1.add(pnlnenngoaithongtinkhac, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlnenngoaithongtinkhac2Layout = new javax.swing.GroupLayout(pnlnenngoaithongtinkhac2);
        pnlnenngoaithongtinkhac2.setLayout(pnlnenngoaithongtinkhac2Layout);
        pnlnenngoaithongtinkhac2Layout.setHorizontalGroup(
            pnlnenngoaithongtinkhac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenngoaithongtinkhac1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlnenngoaithongtinkhac2Layout.setVerticalGroup(
            pnlnenngoaithongtinkhac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenngoaithongtinkhac1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlnenngoaianhdaidienvathongtinkhac.add(pnlnenngoaithongtinkhac2, java.awt.BorderLayout.CENTER);

        pnlnenngoaianhdaidienvathongtinkhac1.add(pnlnenngoaianhdaidienvathongtinkhac, java.awt.BorderLayout.CENTER);

        pnlnenthemsuakhachhang1.add(pnlnenngoaianhdaidienvathongtinkhac1, java.awt.BorderLayout.EAST);

        pnlnenlonthongtincoban.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenlonthongtincoban.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 246, 248), 5));
        pnlnenlonthongtincoban.setPreferredSize(new java.awt.Dimension(700, 550));
        pnlnenlonthongtincoban.setLayout(new java.awt.BorderLayout());

        pnlnenlonngoaithongtin.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenlonngoaithongtin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlnenlonngoaithongtin.setLayout(new java.awt.BorderLayout());

        pnlnenngoaithongtin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        pnlnenngoaithongtin.setLayout(new java.awt.BorderLayout());

        pnlnenthongtin.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenthongtin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblthongtincoban.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblthongtincoban.setText("Thông tin cơ bản");

        pnlnenmatkhau.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenmatkhau.setLayout(new java.awt.BorderLayout());

        lblmatkhau.setBackground(new java.awt.Color(255, 255, 255));
        lblmatkhau.setText("Mật khẩu");
        lblmatkhau.setPreferredSize(new java.awt.Dimension(52, 20));
        pnlnenmatkhau.add(lblmatkhau, java.awt.BorderLayout.PAGE_START);
        pnlnenmatkhau.add(txtMatKhau, java.awt.BorderLayout.CENTER);

        pnlemail.setBackground(new java.awt.Color(255, 255, 255));
        pnlemail.setLayout(new java.awt.BorderLayout());

        lblemail.setBackground(new java.awt.Color(255, 255, 255));
        lblemail.setText("Email");
        lblemail.setPreferredSize(new java.awt.Dimension(31, 20));
        pnlemail.add(lblemail, java.awt.BorderLayout.PAGE_START);
        pnlemail.add(txtEmail, java.awt.BorderLayout.CENTER);

        pnlnensodienthoai.setBackground(new java.awt.Color(255, 255, 255));
        pnlnensodienthoai.setLayout(new java.awt.BorderLayout());

        lblsodienthoai.setBackground(new java.awt.Color(255, 255, 255));
        lblsodienthoai.setText("Số điện thoại");
        lblsodienthoai.setPreferredSize(new java.awt.Dimension(75, 20));
        pnlnensodienthoai.add(lblsodienthoai, java.awt.BorderLayout.PAGE_START);
        pnlnensodienthoai.add(txtsodienthoai, java.awt.BorderLayout.CENTER);

        pnlnenhotenkhachhang.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenhotenkhachhang.setLayout(new java.awt.BorderLayout());

        lblhotenkhachhang.setBackground(new java.awt.Color(255, 255, 255));
        lblhotenkhachhang.setText("Họ tên khách hàng");
        lblhotenkhachhang.setPreferredSize(new java.awt.Dimension(106, 20));
        pnlnenhotenkhachhang.add(lblhotenkhachhang, java.awt.BorderLayout.PAGE_START);

        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });
        pnlnenhotenkhachhang.add(txtHoTen, java.awt.BorderLayout.CENTER);

        lblthongtinbosung.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblthongtinbosung.setText("Thông tin bổ sung");

        pnlnenid.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenid.setLayout(new java.awt.BorderLayout());

        lblid.setBackground(new java.awt.Color(255, 255, 255));
        lblid.setText("ID");
        lblid.setPreferredSize(new java.awt.Dimension(52, 20));
        pnlnenid.add(lblid, java.awt.BorderLayout.PAGE_START);

        txtId.setEditable(false);
        pnlnenid.add(txtId, java.awt.BorderLayout.CENTER);

        pnlnenmangxahoi.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenmangxahoi.setLayout(new java.awt.BorderLayout());

        lblmangxahoi.setBackground(new java.awt.Color(255, 255, 255));
        lblmangxahoi.setText("Mạng xã hội");
        lblmangxahoi.setPreferredSize(new java.awt.Dimension(31, 20));
        pnlnenmangxahoi.add(lblmangxahoi, java.awt.BorderLayout.PAGE_START);
        pnlnenmangxahoi.add(txtMangXaHoi, java.awt.BorderLayout.CENTER);

        pnlnengioitinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlnengioitinh.setLayout(new java.awt.BorderLayout());

        lblgioitinh.setBackground(new java.awt.Color(255, 255, 255));
        lblgioitinh.setText("Giới tính");
        lblgioitinh.setPreferredSize(new java.awt.Dimension(75, 20));
        pnlnengioitinh.add(lblgioitinh, java.awt.BorderLayout.PAGE_START);

        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        pnlnengioitinh.add(cbbGioiTinh, java.awt.BorderLayout.CENTER);

        pnlnenngaysinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlnenngaysinh.setLayout(new java.awt.BorderLayout());

        lblngaysinh.setBackground(new java.awt.Color(255, 255, 255));
        lblngaysinh.setText("Ngày sinh");
        lblngaysinh.setPreferredSize(new java.awt.Dimension(106, 20));
        pnlnenngaysinh.add(lblngaysinh, java.awt.BorderLayout.PAGE_START);
        pnlnenngaysinh.add(jdcNgaySinh, java.awt.BorderLayout.CENTER);

        lblthongtindiachi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblthongtindiachi.setText("Thông tin địa chỉ");

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setLayout(new java.awt.BorderLayout());

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Text");
        jLabel17.setPreferredSize(new java.awt.Dimension(52, 20));
        jPanel39.add(jLabel17, java.awt.BorderLayout.PAGE_START);

        jTextField15.setEditable(false);
        jPanel39.add(jTextField15, java.awt.BorderLayout.CENTER);

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setLayout(new java.awt.BorderLayout());

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tag");
        jLabel18.setPreferredSize(new java.awt.Dimension(31, 20));
        jPanel40.add(jLabel18, java.awt.BorderLayout.PAGE_START);

        txtTag.setEditable(false);
        jPanel40.add(txtTag, java.awt.BorderLayout.CENTER);

        pnlnentinh.setBackground(new java.awt.Color(255, 255, 255));
        pnlnentinh.setLayout(new java.awt.BorderLayout());

        lbltinh.setBackground(new java.awt.Color(255, 255, 255));
        lbltinh.setText("Tỉnh");
        lbltinh.setPreferredSize(new java.awt.Dimension(75, 20));
        pnlnentinh.add(lbltinh, java.awt.BorderLayout.PAGE_START);

        cbbtinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtinhActionPerformed(evt);
            }
        });
        pnlnentinh.add(cbbtinh, java.awt.BorderLayout.CENTER);

        pnlnendiachi.setBackground(new java.awt.Color(255, 255, 255));
        pnlnendiachi.setLayout(new java.awt.BorderLayout());

        lbldiachihientai.setBackground(new java.awt.Color(255, 255, 255));
        lbldiachihientai.setText("Địa chỉ hiện tại (Xã, Phường - Quận, Huyện) *");
        lbldiachihientai.setPreferredSize(new java.awt.Dimension(106, 20));
        pnlnendiachi.add(lbldiachihientai, java.awt.BorderLayout.PAGE_START);
        pnlnendiachi.add(txtDiaChi, java.awt.BorderLayout.CENTER);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout pnlnenthongtinLayout = new javax.swing.GroupLayout(pnlnenthongtin);
        pnlnenthongtin.setLayout(pnlnenthongtinLayout);
        pnlnenthongtinLayout.setHorizontalGroup(
            pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlnenthongtinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlnenthongtinLayout.createSequentialGroup()
                        .addComponent(pnlemail, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(pnlnenmatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addGroup(pnlnenthongtinLayout.createSequentialGroup()
                        .addComponent(pnlnenhotenkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(pnlnensodienthoai, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addComponent(lblthongtincoban, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlnenthongtinLayout.createSequentialGroup()
                        .addComponent(pnlnenmangxahoi, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(pnlnenid, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addGroup(pnlnenthongtinLayout.createSequentialGroup()
                        .addComponent(pnlnenngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(pnlnengioitinh, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addComponent(lblthongtinbosung, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlnenthongtinLayout.createSequentialGroup()
                        .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addGroup(pnlnenthongtinLayout.createSequentialGroup()
                        .addComponent(pnlnendiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(pnlnentinh, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addComponent(lblthongtindiachi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlnenthongtinLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlnenthongtinLayout.setVerticalGroup(
            pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnenthongtinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblthongtincoban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlnensodienthoai, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(pnlnenhotenkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlnenmatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(pnlemail, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblthongtinbosung, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlnengioitinh, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(pnlnenngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlnenid, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(pnlnenmangxahoi, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblthongtindiachi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlnentinh, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(pnlnendiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlnenthongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlnenngoaithongtin.add(pnlnenthongtin, java.awt.BorderLayout.CENTER);

        pnlnenlonngoaithongtin.add(pnlnenngoaithongtin, java.awt.BorderLayout.CENTER);

        pnlnenlonthongtincoban.add(pnlnenlonngoaithongtin, java.awt.BorderLayout.CENTER);

        pnlnenthemsuakhachhang1.add(pnlnenlonthongtincoban, java.awt.BorderLayout.CENTER);

        pnlnenngoaibtn.setPreferredSize(new java.awt.Dimension(975, 40));

        pnlnenbtn.setBackground(new java.awt.Color(244, 246, 248));

        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/checkmark_20px.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/maintenance_20px.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnlammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/available_updates_20px.png"))); // NOI18N
        btnlammoi.setText("Làm mới");
        btnlammoi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblghichu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblghichu.setForeground(new java.awt.Color(255, 0, 0));
        lblghichu.setText("* Vui lòng nhập đúng địa chỉ để được hỗ trợ giao hàng nhanh nhất");

        javax.swing.GroupLayout pnlnenbtnLayout = new javax.swing.GroupLayout(pnlnenbtn);
        pnlnenbtn.setLayout(pnlnenbtnLayout);
        pnlnenbtnLayout.setHorizontalGroup(
            pnlnenbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnenbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        pnlnenbtnLayout.setVerticalGroup(
            pnlnenbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnenbtnLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(pnlnenbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblghichu))
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout pnlnenngoaibtnLayout = new javax.swing.GroupLayout(pnlnenngoaibtn);
        pnlnenngoaibtn.setLayout(pnlnenngoaibtnLayout);
        pnlnenngoaibtnLayout.setHorizontalGroup(
            pnlnenngoaibtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlnenngoaibtnLayout.setVerticalGroup(
            pnlnenngoaibtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlnenthemsuakhachhang1.add(pnlnenngoaibtn, java.awt.BorderLayout.PAGE_END);

        FileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlthemsuakhachhangLayout = new javax.swing.GroupLayout(pnlthemsuakhachhang);
        pnlthemsuakhachhang.setLayout(pnlthemsuakhachhangLayout);
        pnlthemsuakhachhangLayout.setHorizontalGroup(
            pnlthemsuakhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenthemsuakhachhang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlthemsuakhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlthemsuakhachhangLayout.createSequentialGroup()
                    .addGap(487, 487, 487)
                    .addComponent(FileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(488, Short.MAX_VALUE)))
        );
        pnlthemsuakhachhangLayout.setVerticalGroup(
            pnlthemsuakhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlnenthemsuakhachhang1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
            .addGroup(pnlthemsuakhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlthemsuakhachhangLayout.createSequentialGroup()
                    .addGap(293, 293, 293)
                    .addComponent(FileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(293, Short.MAX_VALUE)))
        );

        tbpchuyentab.addTab("Thêm - Sửa khách hàng", pnlthemsuakhachhang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpchuyentab, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpchuyentab, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbloaikhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbloaikhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbloaikhachhangActionPerformed

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        String HoTen = txtHoTen.getText();
        int SoDienThoai = Integer.parseInt(txtsodienthoai.getText());
        String Email = txtEmail.getText();
        String MatKhau = txtMatKhau.getText();
        String DiaChi = txtDiaChi.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String NgaySinh = sdf.format(jdcNgaySinh.getDate());
        String gioiTinh;
        if (cbbGioiTinh.getSelectedItem().toString().equals("Nam")) {
            gioiTinh = "1";
        } else {
            gioiTinh = "0";
        }
        MyCombobox mb = (MyCombobox) cbbLoaiKhachHang.getSelectedItem();
        int MaLoaiKhachHang = Integer.parseInt(mb.Value.toString());
        int MaNguoiDung = Integer.parseInt(cbbNhanVien.getSelectedItem().toString());
        String MangXaHoi = txtMangXaHoi.getText();
        String Tag = txtTag.getText();
        String MoTa = txtMoTa.getText();
        if (BLL.BLLKhachHang.KiemTraThemKhachHang(HoTen)) {

            DTOKhachHang kh = new DTOKhachHang(MaLoaiKhachHang, MaNguoiDung, Tag, SoDienThoai, Email, MatKhau, NgaySinh, DiaChi, gioiTinh, MangXaHoi, MangXaHoi, MoTa, Tag);
            BLL.BLLKhachHang.ThemKhachHang(kh);
            ThongBaoCanhBao.ThongBao("Thêm khách hàng thành công", "Thông báo");

            BLL.BLLKhachHang.HienThiKhachHang(tblkhachhang);
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void tblkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhachhangMouseClicked
        int Dongduocchon = tblkhachhang.getSelectedRow();
        TableModel model = tblkhachhang.getModel();
        if (evt.getClickCount() == 2) {
            if (Dongduocchon >= 0) {
                int MaKH = Integer.parseInt(tblkhachhang.getValueAt(Dongduocchon, 0).toString());
                DTOKhachHang kh = BLL.BLLKhachHang.GetMaKH(MaKH);
                txtHoTen.setText(kh.getTenKhachHang());
                txtsodienthoai.setText(kh.getSoDienThoai() + "");
                txtEmail.setText(kh.getEmail());
                txtMatKhau.setText(kh.getMatKhau());
                txtDiaChi.setText(kh.getDiaChi());
                 System.out.println((tblkhachhang.getValueAt(Dongduocchon, 4).toString()));
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tblkhachhang.getValueAt(Dongduocchon, 4));
                    jdcNgaySinh.setDate(date);
                } catch (ParseException ex) {
                    Logger.getLogger(pnlkhachhang.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (kh.getGioiTinh().equals("1")) {
                    cbbGioiTinh.setSelectedItem("Nam");
                } else {
                    cbbGioiTinh.setSelectedItem("Nữ");
                }
                txtMangXaHoi.setText(kh.getMangXaHoi());
                txtId.setText(kh.getIdKhachHang() + "");
                BLL.BLLKhachHang.SetCBBLoaiKhachHang(cbbLoaiKhachHang, kh.getIdLoaiKhachHang());
                lblAnhDaiDien.setIcon(readLogo(model.getValueAt(Dongduocchon, 7).toString()));
                lblAnhDaiDien.setToolTipText(model.getValueAt(Dongduocchon, 7).toString());
                txtTag.setText(kh.getTag());
                txtMoTa.setText(kh.getMoTa());

                tbpchuyentab.setSelectedIndex(1);

            }
        }
    }//GEN-LAST:event_tblkhachhangMouseClicked
    public static File FILE;
    private void lblAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDaiDienMouseClicked
        if (FileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            FILE = FileChooser.getSelectedFile();
            if (!FILE.getName().matches(".+(\\.png|\\.jpg|\\.gif)$")) {
                ThongBaoCanhBao.ThongBao("Không phải hình ảnh", "Thông Báo");
                return;
            }
            if (saveLogo(FILE)) {
                lblAnhDaiDien.setIcon(readLogo(FILE.getName()));
                lblAnhDaiDien.setToolTipText(FILE.getName());
            }
        }
    }//GEN-LAST:event_lblAnhDaiDienMouseClicked

    private void FileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FileChooserActionPerformed

    private void cbbtinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtinhActionPerformed

    private void txttimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyPressed
     
   
                  
                    String cauTruyVan = "DELETE FROM `khachhang` WHERE idkhachhang = "+ txttimkiem.getText() +"";
                    System.out.println(cauTruyVan);
                    System.out.println("xóa thành công");
        DBConection.KhachHang(cauTruyVan);
                
            BLL.BLLKhachHang.HienThiKhachHang(tblkhachhang);
        
    }//GEN-LAST:event_txttimkiemKeyPressed
 public void listed()
    {
         
        try
        {
           
        DefaultTableModel tbModel = (DefaultTableModel) tblkhachhang.getModel();
            String sql = "SELECT * FROM khachhang WHERE idkhachhang='"+GUI.pnlkhachhang.txttimkiem.getText()+"'";
            Statement S = conn.createStatement();
            ResultSet R = S.executeQuery(sql);
             Object obj[] = new Object[8];
            while(R.next())
            {
                 obj[0] = R.getInt("idkhachhang");
                obj[1] = R.getString("tenkhachhang");
                obj[2] = R.getInt("sodienthoai");
                obj[3] = R.getString("email");
                 obj[4] = ChuyenDoi.GetNgay(R.getDate("ngaysinh"));
                obj[5] = R.getString("diachi");
                if (R.getString("gioitinh").equals("1")) {
                    obj[6] = "Nam";
                } else {
                    obj[6] = "Nữ";
                }
                obj[7] = R.getString("anhdaidien");

                tbModel.addRow(obj);
            }
          
          
        }
        catch(Exception e){
    
} 
      
    }
    private void txttimkiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyTyped
     
        
        
        
    }//GEN-LAST:event_txttimkiemKeyTyped

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
   
        if (jCheckBox1.isSelected()) {
            listed();
        }else{
            System.out.println("null");
        }
        
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         int cacDong[] = tblkhachhang.getSelectedRows();
                for (int i = 0; i < cacDong.length; i++) {
                    String makh = tblkhachhang.getValueAt(cacDong[i], 0).toString();
                    String cauTruyVan = "DELETE FROM `khachhang` WHERE idkhachhang = "+makh+"";
                    System.out.println(makh);
                    System.out.println("Xóa thành công");
        DBConection.KhachHang(cauTruyVan);
                }
            BLL.BLLKhachHang.HienThiKhachHang(tblkhachhang);
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       BLL.BLLKhachHang.HienThiKhachHang(tblkhachhang);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Excel excel = new Excel();
        if (excel.ExportExcel(tblkhachhang)) {
             ThongBao("Xuất file Excel thành công", "Thông báo");
              BLL.BLLKhachHang.HienThiKhachHang(tblkhachhang);
        } else {
            ThongBao("Lỗi", "Thông báo");
        }
      
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
   
  
    
    public static boolean saveLogo(File file) {
        File dir = new File("logos");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static ImageIcon readLogo(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JComboBox<String> cbbLoaiKhachHang;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JComboBox<String> cbbloaikhachhang;
    private javax.swing.JComboBox<String> cbbtinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextField15;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private javax.swing.JLabel lblAnhDaiDien;
    private javax.swing.JLabel lblanhdaidien;
    private javax.swing.JLabel lbldiachihientai;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblghichu;
    private javax.swing.JLabel lblgioitinh;
    private javax.swing.JLabel lblhotenkhachhang;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lblloaikhachhang;
    private javax.swing.JLabel lblmangxahoi;
    private javax.swing.JLabel lblmatkhau;
    private javax.swing.JLabel lblmota;
    private javax.swing.JLabel lblngaysinh;
    private javax.swing.JLabel lblnhanvienphutrach;
    private javax.swing.JLabel lblnhomkhachhang;
    private javax.swing.JLabel lblsodienthoai;
    private javax.swing.JLabel lblthongtinbosung;
    private javax.swing.JLabel lblthongtincoban;
    private javax.swing.JLabel lblthongtindiachi;
    private javax.swing.JLabel lblthongtinkhac;
    private javax.swing.JLabel lbltinh;
    private javax.swing.JPanel pnlanhdaidien;
    private javax.swing.JPanel pnldanhsach;
    private javax.swing.JPanel pnlemail;
    private javax.swing.JPanel pnlloaikhachhang;
    private javax.swing.JPanel pnlnenanhdaidien;
    private javax.swing.JPanel pnlnenbtn;
    private javax.swing.JPanel pnlnendiachi;
    private javax.swing.JPanel pnlnengioitinh;
    private javax.swing.JPanel pnlnenhotenkhachhang;
    private javax.swing.JPanel pnlnenid;
    private javax.swing.JPanel pnlnenlonngoaithongtin;
    private javax.swing.JPanel pnlnenlonthongtincoban;
    private javax.swing.JPanel pnlnenmangxahoi;
    private javax.swing.JPanel pnlnenmatkhau;
    private javax.swing.JPanel pnlnenmota;
    private javax.swing.JPanel pnlnenngaysinh;
    private javax.swing.JPanel pnlnenngoaianhdaidien;
    private javax.swing.JPanel pnlnenngoaianhdaidien1;
    private javax.swing.JPanel pnlnenngoaianhdaidienvathongtinkhac;
    private javax.swing.JPanel pnlnenngoaianhdaidienvathongtinkhac1;
    private javax.swing.JPanel pnlnenngoaibtn;
    private javax.swing.JPanel pnlnenngoaithongtin;
    private javax.swing.JPanel pnlnenngoaithongtinkhac;
    private javax.swing.JPanel pnlnenngoaithongtinkhac1;
    private javax.swing.JPanel pnlnenngoaithongtinkhac2;
    private javax.swing.JPanel pnlnennhanvienphutrach;
    private javax.swing.JPanel pnlnennhomkhachhang;
    private javax.swing.JPanel pnlnensodienthoai;
    private javax.swing.JPanel pnlnenthemsuakhachhang1;
    private javax.swing.JPanel pnlnenthongtin;
    private javax.swing.JPanel pnlnenthongtinkhac;
    private javax.swing.JPanel pnlnentinh;
    private javax.swing.JPanel pnlthemsuakhachhang;
    private javax.swing.JScrollPane srcdanhsach;
    public static javax.swing.JTable tblkhachhang;
    private javax.swing.JTabbedPane tbpchuyentab;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMangXaHoi;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextPane txtMoTa;
    private javax.swing.JTextField txtTag;
    private javax.swing.JTextField txtsodienthoai;
    public static javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
