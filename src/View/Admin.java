/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Config.DatabaseConfig;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anam
 */
public class Admin extends javax.swing.JPanel {

    /**
     * Creates new Admin
     */
    public Admin() {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screen.width, screen.height);
        tampil_tb_admin();
        tampil_cb();
        id_otomatis();
    }
    public void tampil_cb(){
        cb_level.removeAllItems();
        
        cb_level.addItem("Staff");
        cb_level.addItem("Owner");
    }
    
    private void id_otomatis(){
        try {
            Connection con = new DatabaseConfig().getConnection();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM admin order by id_admin desc");
            if (rs.next()) {
                 String id = rs.getString("id_admin").substring(1);
                 String AN = "" + (Integer.parseInt(id) + 1);
                 String nol = "";
                 if (AN.length() == 1) {
                    nol = "000";
                }else if(AN.length() == 2){
                     nol = "00";
                 }else if (AN.length() == 3) {
                     nol = "0";
                }else if (AN.length() == 4) {
                    nol = "";
                }
                 in_id.setText("A" + nol + AN);
            }else{
                in_id.setText("A0001");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//    
    private DefaultTableModel tabmode;
    
    private void tampil_tb_admin(){
    Object []baris = {"Id Admin","Nama Admin", "No Telp", "Username", "Password", "Level"};
        tabmode = new DefaultTableModel (null, baris);
        tbl_admin.setModel(tabmode);
        Connection con = new DatabaseConfig().getConnection();
        try {
            String sql = "SELECT * from admin order by id_admin asc";
            java.sql.Statement stat = con.createStatement();
            java.sql.ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id_admin = hasil.getString("id_admin");
                String nama_admin = hasil.getString("nama_admin");
                String no_telp = hasil.getString("no_telp");
                String username = hasil.getString("username");
                String password = hasil.getString("password");
                String level = hasil.getString("level");
                String[] data = {id_admin,nama_admin, no_telp, username, password, level};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            JOptionPane.showMessageDialog(null, "Menampilkan data gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        in_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        in_username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_admin = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        RESET = new javax.swing.JButton();
        HAPUS = new javax.swing.JButton();
        EDIT = new javax.swing.JButton();
        SIMPAN = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        in_password = new javax.swing.JTextField();
        in_nama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        in_telp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cb_level = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        in_id.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_id.setEnabled(false);
        add(in_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 72, 210, -1));

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel3.setText("Id Admin");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 49, 100, -1));

        in_username.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        add(in_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 135, 210, -1));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel4.setText("Username");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 112, 100, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tbl_admin.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        tbl_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_adminMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_admin);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 275, 1090, 340));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        RESET.setBackground(new java.awt.Color(51, 255, 51));
        RESET.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        HAPUS.setBackground(new java.awt.Color(255, 148, 148));
        HAPUS.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        HAPUS.setText("HAPUS");
        HAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HAPUSActionPerformed(evt);
            }
        });

        EDIT.setBackground(new java.awt.Color(255, 255, 102));
        EDIT.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        EDIT.setText("EDIT");
        EDIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDITActionPerformed(evt);
            }
        });

        SIMPAN.setBackground(new java.awt.Color(117, 177, 250));
        SIMPAN.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        SIMPAN.setText("SIMPAN");
        SIMPAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIMPANActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ADMIN");
        jLabel6.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel9.setText("Password");

        in_password.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        in_nama.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel5.setText("Nama");

        in_telp.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel7.setText("No Telphone");

        cb_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel12.setText("Level");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(32, 989, Short.MAX_VALUE)
                        .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(in_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(in_password, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb_level, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(HAPUS, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(in_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EDIT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SIMPAN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(497, 497, 497))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(in_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(in_telp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addComponent(in_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_level, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(SIMPAN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HAPUS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(394, 394, 394))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 640));
    }// </editor-fold>//GEN-END:initComponents

    private void reset(){
        in_nama.setText("");
        in_telp.setText("");
        in_username.setText("");
        in_password.setText("");
        cb_level.setSelectedIndex(0);
        id_otomatis();
    }
    private String klicktable(){
        int baris = tbl_admin.getSelectedRow();
        in_id.setText((String)tbl_admin.getValueAt(baris, 0));
        in_nama.setText((String)tbl_admin.getValueAt(baris, 1));
        in_telp.setText((String)tbl_admin.getValueAt(baris, 2));
        in_username.setText((String)tbl_admin.getValueAt(baris,3));
        in_password.setText((String)tbl_admin.getValueAt(baris,4));
        cb_level.setSelectedItem(tbl_admin.getValueAt(baris, 5));
        
        String username = tbl_admin.getValueAt(baris, 2).toString();
        return username;
    }
//    
    private void SIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIMPANActionPerformed
        // TODO add your handling code here:
        String id_admin, nama_admin ="",  no_telp ="", username = "", password = "", level = "";
        id_admin = in_id.getText();
        nama_admin = in_nama.getText();
        no_telp = in_telp.getText();
        username = in_username.getText();
        password = in_password.getText();
        level = cb_level.getSelectedItem().toString();
        
        
        if (id_admin == ""  || level == "") {
            JOptionPane.showMessageDialog(null, "Simpan tidak valid", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
            Connection con = new DatabaseConfig().getConnection();
            String sql = "INSERT INTO admin VALUES (?,?,?,?,?,?)";
            PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
            stat = con.prepareStatement(sql);
            stat.setString(1, id_admin);
            stat.setString(2, nama_admin);
            stat.setString(3, no_telp);
            stat.setString(4, username);
            stat.setString(5, password);
            stat.setString(6, level);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan", "informasi", JOptionPane.INFORMATION_MESSAGE);
            tampil_tb_admin();
            reset();
            id_otomatis();
        } catch (Exception e) {
//            System.out.println(no_bp + nama + tempat_lahir + jurusan + tanggal_lahir + tanggal_masuk);
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Menyimpan Data ga", "informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    }//GEN-LAST:event_SIMPANActionPerformed

    private void HAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HAPUSActionPerformed
        // TODO add your handling code here:
        int baris = tbl_admin.getSelectedRow();
        String id_admin = tabmode.getValueAt(baris, 0).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin Menghapus data dengan id : " + id_admin + "?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                Connection con = new DatabaseConfig().getConnection();
                String sql = "DELETE FROM admin WHERE id_admin = '"+id_admin+"'";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                reset();
                System.out.println(sql);
                tampil_tb_admin();
                id_otomatis();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_HAPUSActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_RESETActionPerformed

    private void EDITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDITActionPerformed
        // TODO add your handling code here:
        Connection con = new DatabaseConfig().getConnection();
        String id_admin ="", nama_admin ="",  no_telp ="", username = "", password = "", level = "";
        id_admin = in_id.getText();
        nama_admin = in_nama.getText();
        no_telp = in_telp.getText();
        username = in_username.getText();
        password = in_password.getText();
        level = cb_level.getSelectedItem().toString();
        
        String tangkapusername = klicktable();
        
        if (username == null) {
            JOptionPane.showMessageDialog(null, "Silahkan isi username", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(password == null){
            JOptionPane.showMessageDialog(null, "Silahkan isi password", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(id_admin == ""){
            JOptionPane.showMessageDialog(null, "Id_admin tidak terdeteksi", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String sql = "UPDATE admin SET nama_admin=?, no_telp=?, username=?, password =?, level=? WHERE id_admin ='"+id_admin+"'";
            PreparedStatement stat = null;
            try {
                stat = con.prepareStatement(sql);
                stat.setString(1, nama_admin);
                stat.setString(2, no_telp);
                stat.setString(3, username);
                stat.setString(4, password);
                stat.setString(5, level);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
                tampil_tb_admin();
                reset();
                id_otomatis();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di ubah");
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_EDITActionPerformed

    private void tbl_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_adminMouseClicked
        // TODO add your handling code here:
        klicktable();
    }//GEN-LAST:event_tbl_adminMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EDIT;
    private javax.swing.JButton HAPUS;
    private javax.swing.JButton RESET;
    private javax.swing.JButton SIMPAN;
    private javax.swing.JComboBox<String> cb_level;
    private javax.swing.JTextField in_id;
    private javax.swing.JTextField in_nama;
    private javax.swing.JTextField in_password;
    private javax.swing.JTextField in_telp;
    private javax.swing.JTextField in_username;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_admin;
    // End of variables declaration//GEN-END:variables
}
