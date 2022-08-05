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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Anam
 */
public class TransaksiOwner extends javax.swing.JPanel {

    /**
     * Creates new form Mobilpane
     */
    public TransaksiOwner() {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screen.width, screen.height);
        tampil_tb_transaksi("");
        data_combobox();
        pilih_cetak();
    }
    
    private void data_combobox(){
        cb_pilihdata_tabel.removeAllItems();
        
        cb_pilihdata_tabel.addItem("Tampilkan Semua");
        cb_pilihdata_tabel.addItem("Sedang Peminjaman");
        cb_pilihdata_tabel.addItem("Telat Mengembalikan");
        cb_pilihdata_tabel.addItem("Selesai Dikembalikan");
    }
    private void pilih_cetak(){
        cb_pilihcetak.removeAllItems();
        
        cb_pilihcetak.addItem("Laporan Keseluruhan");
        cb_pilihcetak.addItem("Laporan Perhari");
        cb_pilihcetak.addItem("Laporan Perbulan");
        cb_pilihcetak.addItem("Laporan Pertahun");
    }
    
//    private void data_combobox(){
//        cb_Status.removeAllItems();
//        
//        cb_Status.addItem("Sedang Dalam Peminjaman");
//        cb_Status.addItem("Dikembalikan");
//    }
    
    public void cetak_semua(){
        JasperReport report;
        String path = ".\\src\\Report\\laporanKeseluruhan.jasper";
        try {
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            Connection con = new DatabaseConfig().getConnection();
            JasperPrint jprint = JasperFillManager.fillReport(path, null, con);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
        } catch (Exception e) {
        }
    }
    public void cetak_perhari(){
        JasperReport report;
        String path = ".\\src\\Report\\laporanPerhari.jasper";
        try {
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            Connection con = new DatabaseConfig().getConnection();
            JasperPrint jprint = JasperFillManager.fillReport(path, null, con);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
        } catch (Exception e) {
        }
    }
    public void cetak_perbulan(){
        JasperReport report;
        String path = ".\\src\\Report\\laporanPerbulan.jasper";
        try {
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            Connection con = new DatabaseConfig().getConnection();
            JasperPrint jprint = JasperFillManager.fillReport(path, null, con);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
        } catch (Exception e) {
        }
    }
    public void cetak_pertahun(){
        JasperReport report;
        String path = ".\\src\\Report\\laporanPertahun.jasper";
        try {
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            Connection con = new DatabaseConfig().getConnection();
            JasperPrint jprint = JasperFillManager.fillReport(path, null, con);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            
        } catch (Exception e) {
        }
    }
    
        
    private DefaultTableModel tabmode;
    private void tampil_tb_transaksi(String cari){
    Object []baris = {"Id_transaksi", "IdCust", "Customer", "IdMob", "Mobil", "Tarif Mobil", "IdSop", "Sopir", "Tarif Mobil", "Sewa", "Kembali", "Lama Sewa", "Total Harga", "Catatan", "Status Sewa"};
        tabmode = new DefaultTableModel (null, baris);
        tbl_transaksi.setModel(tabmode);
        Connection con = new DatabaseConfig().getConnection();
        String sql;
        try {
            String filter = "";
            if(!cari.equals("")){
                sql = "SELECT * from view_transaksi WHERE id_transaksi "
                        + "LIKE '%"+cari+"%'"
                        + " OR id_customer LIKE '%"+cari+"%'"
                        + " OR nama_customer LIKE '%"+cari+"%'"
                        + "  OR id_mobil LIKE '%"+cari+"%'"
                        + "  OR merk LIKE '%"+cari+"%'"
                        + "  OR wilayah_tujuan LIKE '%"+cari+"%'"
                        + "  OR id_sopir LIKE '%"+cari+"%'"
                        + " order by id_transaksi asc";
            }else{
                sql = "SELECT * from view_transaksi order by id_transaksi asc";
            }
            
            java.sql.Statement stat = con.createStatement();
            java.sql.ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id_transaksi = hasil.getString("id_transaksi");
                String id_customer = hasil.getString("id_customer");
                String nama_customer = hasil.getString("nama_customer");
                String id_mobil = hasil.getString("id_mobil");
                String merk = hasil.getString("merk");
                String tarif = hasil.getString("tarif");
                String id_sopir = hasil.getString("id_sopir");
                String wilayah_tujuan = hasil.getString("wilayah_tujuan");
                String tarif_sopir = hasil.getString("tarif_sopir");
                String tanggal_sewa = hasil.getString("tanggal_sewa");
                String tgl_kembali = hasil.getString("tgl_kembali");
                String lama_sewa = hasil.getString("lama_sewa");
                String total_harga = hasil.getString("total_harga");
                String catatan = hasil.getString("catatan");
                String status_sewa = hasil.getString("status_sewa");
                String[] data = {id_transaksi, id_customer, nama_customer, id_mobil, merk, tarif, id_sopir, wilayah_tujuan, tarif_sopir, tanggal_sewa, tgl_kembali, lama_sewa, total_harga, catatan, status_sewa};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showConfirmDialog(null, e);
            JOptionPane.showMessageDialog(null, "Menampilkan data gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        txt_titleTabel.setText("Data Transaksi");
    }
    
    private void tampil_tb_transaksi_dikembalikan(String cari){
    Object []baris = {"Id_transaksi", "IdCust", "Customer", "IdMob", "Mobil", "Tarif Mobil", "IdSop", "Sopir", "Tarif Mobil", "Sewa", "Kembali", "Lama Sewa", "Total Harga", "Catatan", "Status Sewa"};
        tabmode = new DefaultTableModel (null, baris);
        tbl_transaksi.setModel(tabmode);
        String status = "Selesai dikembalikan";
        Connection con = new DatabaseConfig().getConnection();
        String sql;
        try {
            String filter = "";
            if(!cari.equals("")){
                sql = "SELECT * from view_transaksi_selesai WHERE id_transaksi "
                        + "LIKE '%"+cari+"%'"
                        + " OR id_customer LIKE '%"+cari+"%'"
                        + " OR nama_customer LIKE '%"+cari+"%'"
                        + "  OR id_mobil LIKE '%"+cari+"%'"
                        + "  OR merk LIKE '%"+cari+"%'"
                        + "  OR wilayah_tujuan LIKE '%"+cari+"%'"
                        + "  OR id_sopir LIKE '%"+cari+"%'"
                        + " order by id_transaksi asc";
            }else{
                sql = "SELECT * from view_transaksi_selesai order by id_transaksi asc";
            }java.sql.Statement stat = con.createStatement();
            java.sql.ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id_transaksi = hasil.getString("id_transaksi");
                String id_customer = hasil.getString("id_customer");
                String nama_customer = hasil.getString("nama_customer");
                String id_mobil = hasil.getString("id_mobil");
                String merk = hasil.getString("merk");
                String tarif = hasil.getString("tarif");
                String id_sopir = hasil.getString("id_sopir");
                String wilayah_tujuan = hasil.getString("wilayah_tujuan");
                String tarif_sopir = hasil.getString("tarif_sopir");
                String tanggal_sewa = hasil.getString("tanggal_sewa");
                String tgl_kembali = hasil.getString("tgl_kembali");
                String lama_sewa = hasil.getString("lama_sewa");
                String total_harga = hasil.getString("total_harga");
                String catatan = hasil.getString("catatan");
                String status_sewa = hasil.getString("status_sewa");
                String[] data = {id_transaksi, id_customer, nama_customer, id_mobil, merk, tarif, id_sopir, wilayah_tujuan, tarif_sopir, tanggal_sewa, tgl_kembali, lama_sewa, total_harga, catatan, status_sewa};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showConfirmDialog(null, e);
            JOptionPane.showMessageDialog(null, "Menampilkan data gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        txt_titleTabel.setText("Data Transaksi");
    }
    
    private void tampil_tb_transaksi_sedangpeminjaman(String cari){
    Object []baris = {"Id_transaksi", "IdCust", "Customer", "IdMob", "Mobil", "Tarif Mobil", "IdSop", "Sopir", "Tarif Mobil", "Sewa", "Kembali", "Lama Sewa", "Total Harga", "Catatan", "Status Sewa"};
        tabmode = new DefaultTableModel (null, baris);
        tbl_transaksi.setModel(tabmode);
        String status = "Sedang dalam peminjaman";
        Connection con = new DatabaseConfig().getConnection();
        String sql;
        try {
            String filter = "";
            if(!cari.equals("")){
                sql = "SELECT * from view_transaksi_belumdikembalikan WHERE id_transaksi "
                        + "LIKE '%"+cari+"%'"
                        + " OR id_customer LIKE '%"+cari+"%'"
                        + " OR nama_customer LIKE '%"+cari+"%'"
                        + "  OR id_mobil LIKE '%"+cari+"%'"
                        + "  OR merk LIKE '%"+cari+"%'"
                        + "  OR wilayah_tujuan LIKE '%"+cari+"%'"
                        + "  OR id_sopir LIKE '%"+cari+"%'"
                        + " order by id_transaksi asc";
            }else{
                sql = "SELECT * from view_transaksi_belumdikembalikan order by id_transaksi asc";
            }
            java.sql.Statement stat = con.createStatement();
            java.sql.ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id_transaksi = hasil.getString("id_transaksi");
                String id_customer = hasil.getString("id_customer");
                String nama_customer = hasil.getString("nama_customer");
                String id_mobil = hasil.getString("id_mobil");
                String merk = hasil.getString("merk");
                String tarif = hasil.getString("tarif");
                String id_sopir = hasil.getString("id_sopir");
                String wilayah_tujuan = hasil.getString("wilayah_tujuan");
                String tarif_sopir = hasil.getString("tarif_sopir");
                String tanggal_sewa = hasil.getString("tanggal_sewa");
                String tgl_kembali = hasil.getString("tgl_kembali");
                String lama_sewa = hasil.getString("lama_sewa");
                String total_harga = hasil.getString("total_harga");
                String catatan = hasil.getString("catatan");
                String status_sewa = hasil.getString("status_sewa");
                String[] data = {id_transaksi, id_customer, nama_customer, id_mobil, merk, tarif, id_sopir, wilayah_tujuan, tarif_sopir, tanggal_sewa, tgl_kembali, lama_sewa, total_harga, catatan, status_sewa};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            JOptionPane.showMessageDialog(null, "Menampilkan data gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        txt_titleTabel.setText("Data Transaksi");
    }
    
     private void tampil_tb_transaksi_terlambat(String cari){
        Object []baris = {"Id_transaksi", "IdCust", "Customer", "IdMob", "Mobil", "Tarif Mobil", "IdSop", "Sopir", "Tarif Mobil", "Sewa", "Kembali", "Lama Sewa", "Total Harga", "Catatan", "Status Sewa"};
            tabmode = new DefaultTableModel (null, baris);
            tbl_transaksi.setModel(tabmode);
            String status = "Selesai dikembalikan";
            Connection con = new DatabaseConfig().getConnection();
            String sql;
            try {
                if(!cari.equals("")){
                    sql = "SELECT * from view_transaksi_terlambat WHERE id_transaksi "
                            + "LIKE '%"+cari+"%'"
                            + " OR id_customer LIKE '%"+cari+"%'"
                            + " OR nama_customer LIKE '%"+cari+"%'"
                            + "  OR id_mobil LIKE '%"+cari+"%'"
                            + "  OR merk LIKE '%"+cari+"%'"
                            + "  OR wilayah_tujuan LIKE '%"+cari+"%'"
                            + "  OR id_sopir LIKE '%"+cari+"%'"
                            + " order by id_transaksi asc";
                }else{
                    sql = "SELECT * from view_transaksi_terlambat order by id_transaksi asc";
                }
                java.sql.Statement stat = con.createStatement();
                java.sql.ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    String id_transaksi = hasil.getString("id_transaksi");
                    String id_customer = hasil.getString("id_customer");
                    String nama_customer = hasil.getString("nama_customer");
                    String id_mobil = hasil.getString("id_mobil");
                    String merk = hasil.getString("merk");
                    String tarif = hasil.getString("tarif");
                    String id_sopir = hasil.getString("id_sopir");
                    String wilayah_tujuan = hasil.getString("wilayah_tujuan");
                    String tarif_sopir = hasil.getString("tarif_sopir");
                    String tanggal_sewa = hasil.getString("tanggal_sewa");
                    String tgl_kembali = hasil.getString("tgl_kembali");
                    String lama_sewa = hasil.getString("lama_sewa");
                    String total_harga = hasil.getString("total_harga");
                    String catatan = hasil.getString("catatan");
                    String status_sewa = hasil.getString("status_sewa");
                    String[] data = {id_transaksi, id_customer, nama_customer, id_mobil, merk, tarif, id_sopir, wilayah_tujuan, tarif_sopir, tanggal_sewa, tgl_kembali, lama_sewa, total_harga, catatan, status_sewa};
                    tabmode.addRow(data);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
                JOptionPane.showMessageDialog(null, "Menampilkan data gagal", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
            txt_titleTabel.setText("Data Transaksi");
        }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        txt_titleTabel = new javax.swing.JLabel();
        in_cari = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cb_pilihdata_tabel = new javax.swing.JComboBox<>();
        CETAK = new javax.swing.JButton();
        cb_pilihcetak = new javax.swing.JComboBox<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tbl_transaksi.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_transaksi);

        txt_titleTabel.setBackground(new java.awt.Color(0, 0, 0));
        txt_titleTabel.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        txt_titleTabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_titleTabel.setText("Data Transaksi");
        txt_titleTabel.setToolTipText("");

        in_cari.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in_cariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                in_cariKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/search.png"))); // NOI18N
        jLabel19.setAlignmentY(0.0F);

        cb_pilihdata_tabel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_pilihdata_tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_pilihdata_tabelMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cb_pilihdata_tabelMouseReleased(evt);
            }
        });
        cb_pilihdata_tabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihdata_tabelActionPerformed(evt);
            }
        });

        CETAK.setBackground(new java.awt.Color(51, 255, 51));
        CETAK.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        CETAK.setText("CETAK");
        CETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CETAKActionPerformed(evt);
            }
        });

        cb_pilihcetak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_pilihcetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_pilihcetakMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cb_pilihcetakMouseReleased(evt);
            }
        });
        cb_pilihcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihcetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_titleTabel)
                .addGap(547, 547, 547))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cb_pilihdata_tabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_pilihcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_titleTabel)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cb_pilihdata_tabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_pilihcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(in_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(400, 400, 400))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 860));
    }// </editor-fold>//GEN-END:initComponents

    
    public String tangkap_idmobil;
    
    
    
    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void in_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_cariKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_in_cariKeyPressed
    private String kirim = "";
    private void cb_pilihdata_tabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihdata_tabelActionPerformed
        // TODO add your handling code here:
        try {
            String cb_pilihan = cb_pilihdata_tabel.getSelectedItem().toString();
            if (cb_pilihan.equals("Selesai Dikembalikan")) {
//                kirim("Selesai Dikembalikan");
                tampil_tb_transaksi_dikembalikan("");
                txt_titleTabel.setText("Data Transaksi Selesai");
//                lbl_totaldenda.setVisible(false);
//                total_denda.setVisible(false);
            }else if (cb_pilihan.equals("Sedang Peminjaman")) {
//                kirim("Sedang Peminjaman");
                tampil_tb_transaksi_sedangpeminjaman("");
                txt_titleTabel.setText("Data Transaksi Dalam Peminjaman");
//                lbl_totaldenda.setVisible(false);
//                total_denda.setVisible(false);
            }else if (cb_pilihan.equals("Telat Mengembalikan")) {
//                kirim("Telat Mengembalikan");
                tampil_tb_transaksi_terlambat("");
                txt_titleTabel.setText("Data Transaksi Belum dikembalikan");
//                total_denda.setVisible(true);
//                lbl_totaldenda.setVisible(true);
            } else{
//                kirim("Data Semua Transaksi");
                tampil_tb_transaksi("");
                txt_titleTabel.setText("Data Semua Transaksi");
//                total_denda.setVisible(false);
//                lbl_totaldenda.setVisible(false);
            }
        } catch (Exception e) {
//            System.out.println("Pilih data eror" + e);
        }
    }//GEN-LAST:event_cb_pilihdata_tabelActionPerformed

    private void cb_pilihdata_tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_pilihdata_tabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihdata_tabelMouseClicked

    private void cb_pilihdata_tabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_pilihdata_tabelMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_pilihdata_tabelMouseReleased

    private void in_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_cariKeyReleased
        // TODO add your handling code here:
        String incari = in_cari.getText();
        tampil_tb_transaksi(incari);
    }//GEN-LAST:event_in_cariKeyReleased

    private void CETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CETAKActionPerformed
        // TODO add your handling code here:
        try {
            String pilihCetak = cb_pilihcetak.getSelectedItem().toString();
            if (pilihCetak.equals("Laporan Keseluruhan")) {
                cetak_semua();
            }else if (pilihCetak.equals("Laporan Perhari")) {
                cetak_perhari();
            }else if (pilihCetak.equals("Laporan Perbulan")) {
                cetak_perbulan();
            }else if (pilihCetak.equals("Laporan Pertahun")) {
                cetak_pertahun();
            }else{
                cetak_semua();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CETAKActionPerformed

    private void cb_pilihcetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_pilihcetakMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihcetakMouseClicked

    private void cb_pilihcetakMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_pilihcetakMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihcetakMouseReleased

    private void cb_pilihcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihcetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_pilihcetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CETAK;
    private javax.swing.JComboBox<String> cb_pilihcetak;
    private javax.swing.JComboBox<String> cb_pilihdata_tabel;
    private javax.swing.JTextField in_cari;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JLabel txt_titleTabel;
    // End of variables declaration//GEN-END:variables
}
