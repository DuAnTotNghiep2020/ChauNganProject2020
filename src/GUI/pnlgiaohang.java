/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Takemikazuchi
 */
public class pnlgiaohang extends javax.swing.JPanel {

    /**
     * Creates new form pnldonhang
     */
    public pnlgiaohang() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        srcdanhsach3 = new javax.swing.JScrollPane();
        tblkhachhang3 = new javax.swing.JTable();
        txttimkiem3 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(980, 620));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblkhachhang3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblkhachhang3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        tblkhachhang3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", null, "", "", "", null, null, null},
                {"", "", null, "", "", "", null, null, null},
                {"", "", null, "", "", "", null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã đơn", "Tên nhà cung cấp", "Chi nhánh", "Trạng thái", "Thanh toán", "Nhập kho", "Tổng tiền", "Nhân viên", "Ngày nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkhachhang3.setDragEnabled(true);
        tblkhachhang3.setFocusable(false);
        tblkhachhang3.setName(""); // NOI18N
        tblkhachhang3.setRowHeight(25);
        tblkhachhang3.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblkhachhang3.setShowVerticalLines(false);
        tblkhachhang3.getTableHeader().setReorderingAllowed(false);
        srcdanhsach3.setViewportView(tblkhachhang3);

        add(srcdanhsach3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 980, 550));

        txttimkiem3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(txttimkiem3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 800, 30));

        jButton11.setText("Thêm phiếu nhập hàng");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(802, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 980, 40));

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/filter_edit_20px.png"))); // NOI18N
        jButton12.setText("Lọc phiếu nhập");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
      
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JScrollPane srcdanhsach3;
    public static javax.swing.JTable tblkhachhang3;
    private javax.swing.JTextField txttimkiem3;
    // End of variables declaration//GEN-END:variables
}
