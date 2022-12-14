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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anam
 */
public class Customers extends javax.swing.JPanel {

    /**
     * Creates new form 
     */
    public Customers() {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screen.width, screen.height);
        tampil_tb_customer();
        data_combobox();
        id_otomatis();
    }
    
    private void id_otomatis(){
        try {
            Connection con = new DatabaseConfig().getConnection();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM customer order by id_customer desc");
            if (rs.next()) {
                 String id = rs.getString("id_customer").substring(1);
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
                 in_id.setText("C" + nol + AN);
            }else{
                in_id.setText("C0001");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void data_combobox(){
        cb_jeniskelamin.removeAllItems();
        
        cb_jeniskelamin.addItem("Laki - Laki");
        cb_jeniskelamin.addItem("Perempuan");
    }
    
    
    
    private DefaultTableModel tabmode;
    private void tampil_tb_customer(){
    Object []baris = {"Id Customer","Nama Customer", "NIK", "Jenis Kelamin", "No Telp", "Alamat", "Tgl Daftar"};
        tabmode = new DefaultTableModel (null, baris);
        tbl_customer.setModel(tabmode);
        Connection con = new DatabaseConfig().getConnection();
        try {
            String sql = "SELECT * from customer order by id_customer asc";
            java.sql.Statement stat = con.createStatement();
            java.sql.ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id_customer = hasil.getString("id_customer");
                String nama_customer = hasil.getString("nama_customer");
                String nik = hasil.getString("nik");
                String jenis_kelamin = hasil.getString("jenis_kelamin");
                String no_telp = hasil.getString("no_telp");
                String alamat = hasil.getString("alamat");
                String tgl_lahir = hasil.getString("tgl_lahir");
                String[] data = {id_customer, nama_customer, nik,jenis_kelamin, no_telp, alamat, tgl_lahir};
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
        in_nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_customer = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        in_nik = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        RESET = new javax.swing.JButton();
        HAPUS = new javax.swing.JButton();
        EDIT = new javax.swing.JButton();
        SIMPAN = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        in_alamat = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        in_tanggallahir = new com.toedter.calendar.JDateChooser();
        cb_jeniskelamin = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        in_notelp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        in_id.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_id.setEnabled(false);
        add(in_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 72, 210, -1));

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel3.setText("Id Customer");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 49, 100, -1));

        in_nama.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        add(in_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 135, 210, -1));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel4.setText("Nama");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 112, 100, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tbl_customer.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        tbl_customer.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_customerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_customer);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 275, 1090, 340));

        jLabel8.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel8.setText("Tanggal Lahir");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 112, 100, -1));

        in_nik.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        add(in_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 72, 210, -1));

        jLabel7.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel7.setText("NIK");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 49, 100, -1));

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

        jLabel10.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel10.setText("Alamat");

        in_alamat.setColumns(20);
        in_alamat.setRows(5);
        jScrollPane2.setViewportView(in_alamat);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel6.setText("CUSTOMER");
        jLabel6.setToolTipText("");

        cb_jeniskelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel12.setText("Jenis Kelamin");

        in_notelp.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel5.setText("No Telephone");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_jeniskelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(in_tanggallahir, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(in_notelp, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(197, 197, 197)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(HAPUS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SIMPAN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EDIT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(RESET, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(497, 497, 497))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(SIMPAN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HAPUS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_jeniskelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(in_tanggallahir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(in_notelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 640));
    }// </editor-fold>//GEN-END:initComponents

    private void reset(){
        in_nama.setText("");
        in_nik.setText("");
        in_tanggallahir.setDate(new Date());
        in_notelp.setText("");
        cb_jeniskelamin.setSelectedIndex(0);
        in_alamat.setText("");
        id_otomatis();
    }
    private void klicktable(){
        
        try {
            int baris = tbl_customer.getSelectedRow();
            in_id.setText((String)tbl_customer.getValueAt(baris, 0));
            in_nama.setText((String)tbl_customer.getValueAt(baris, 1));
            in_nik.setText((String)tbl_customer.getValueAt(baris, 2));
            cb_jeniskelamin.setSelectedItem(tbl_customer.getValueAt(baris, 3));
            in_notelp.setText((String)tbl_customer.getValueAt(baris,4));
            in_alamat.setText((String)tbl_customer.getValueAt(baris, 5));
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

            Date tanggal = dateformat.parse((String) tbl_customer.getValueAt(baris, 6));
            in_tanggallahir.setDate(tanggal);
            System.out.println(tanggal);
        } catch (Exception e) {
            System.out.println(e);
        }
//        System.out.println(dateformat.format(new Date()));
    }
//    
    private void SIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIMPANActionPerformed
        // TODO add your handling code here:
//        int  nik ;
        String nik = "", id_customer = "", nama_customer ="",  no_telp ="", alamat = "", jenis_kelamin = "", tgl_lahir;
        id_customer = in_id.getText();
        nama_customer = in_nama.getText();
        nik = in_nik.getText();
        no_telp = in_notelp.getText();
        alamat = in_alamat.getText();
        jenis_kelamin = cb_jeniskelamin.getSelectedItem().toString();
        // ubah format tanggal
        SimpleDateFormat kal = new SimpleDateFormat("yyyy-MM-dd");
        tgl_lahir =  kal.format(in_tanggallahir.getDate()).toString();
        
        // Mengambil tanggal Hari ini
        Date sekarang = new Date();
        String tgl_sekarang = kal.format(sekarang);
        
        if (tgl_lahir.equals(tgl_sekarang)) {
            JOptionPane.showMessageDialog(null, "Tanggal lahir tidak valid", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                Connection con = new DatabaseConfig().getConnection();
                String sql = "INSERT INTO customer VALUES (?,?,?,?,?,?,?)";
                PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
                stat = con.prepareStatement(sql);
                stat.setString(1, id_customer);
                stat.setString(2, nama_customer);
                stat.setString(3, nik);
                stat.setString(4, jenis_kelamin);
                stat.setString(5, no_telp);
                stat.setString(6, alamat);
                stat.setString(7, tgl_lahir);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan", "informasi", JOptionPane.INFORMATION_MESSAGE);
                tampil_tb_customer();
                reset();
                id_otomatis();
            } catch (Exception e) {
    //            System.out.println(no_bp + nama + tempat_lahir + jurusan + tanggal_lahir + tanggal_masuk);
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Menyimpan Data gagal", "informasi", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(nama_customer + nik + jenis_kelamin + no_telp + alamat + tgl_lahir);
            }
        }
    }//GEN-LAST:event_SIMPANActionPerformed

    private void HAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HAPUSActionPerformed
        // TODO add your handling code here:
        int baris = tbl_customer.getSelectedRow();
        String id_customer = tabmode.getValueAt(baris, 0).toString();
        String nama = tabmode.getValueAt(baris, 1).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin Menghapus data Mobil dengan id_customer : " + id_customer + "?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                Connection con = new DatabaseConfig().getConnection();
                String sql = "DELETE FROM customer WHERE id_customer = '"+id_customer+"'";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
//                tampil_tb_mahasiswa();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                reset();
                System.out.println(sql);
                tampil_tb_customer();
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
        String nik = "", id_customer = "", nama_customer ="",  no_telp ="", alamat = "", jenis_kelamin = "", tgl_lahir;
        id_customer = in_id.getText();
        nama_customer = in_nama.getText();
        nik = in_nik.getText();
        no_telp = in_notelp.getText();
        alamat = in_alamat.getText();
        jenis_kelamin = cb_jeniskelamin.getSelectedItem().toString();
        // ubah format tanggal
        SimpleDateFormat kal = new SimpleDateFormat("yyyy-MM-dd");
        tgl_lahir =  kal.format(in_tanggallahir.getDate()).toString();
        // Mengambil tanggal Hari ini
        Date sekarang = new Date();
        String tgl_sekarang = kal.format(sekarang);
                
        if (tgl_lahir == null || nama_customer == "" || nik == "" || no_telp == "" || alamat == "" || jenis_kelamin == null) {
            JOptionPane.showMessageDialog(null, "Silahkan isi semua form", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else if(id_customer == ""){
            JOptionPane.showMessageDialog(null, "Id Customer tidak ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(tgl_lahir.equals(tgl_sekarang) ){
            JOptionPane.showMessageDialog(null, "Tanggal lahir tidak valid", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String sql = "UPDATE customer SET nama_customer=?, nik=?, jenis_kelamin=?,  no_telp=?, alamat =?, tgl_lahir =? WHERE id_customer ='"+id_customer+"'";
            PreparedStatement stat = null;
            try {
                stat = con.prepareStatement(sql);
                stat.setString(1, nama_customer);
                stat.setString(2, nik);
                stat.setString(3, jenis_kelamin);
                stat.setString(4, no_telp);
                stat.setString(5, alamat);
                stat.setString(6, tgl_lahir);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
                System.out.println(tgl_sekarang + " | " + tgl_lahir);
                tampil_tb_customer();
                reset();
                id_otomatis();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di ubah");
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_EDITActionPerformed

    private void tbl_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_customerMouseClicked
        // TODO add your handling code here:
        klicktable();
    }//GEN-LAST:event_tbl_customerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EDIT;
    private javax.swing.JButton HAPUS;
    private javax.swing.JButton RESET;
    private javax.swing.JButton SIMPAN;
    private javax.swing.JComboBox<String> cb_jeniskelamin;
    private javax.swing.JTextArea in_alamat;
    private javax.swing.JTextField in_id;
    private javax.swing.JTextField in_nama;
    private javax.swing.JTextField in_nik;
    private javax.swing.JTextField in_notelp;
    private com.toedter.calendar.JDateChooser in_tanggallahir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_customer;
    // End of variables declaration//GEN-END:variables
}
