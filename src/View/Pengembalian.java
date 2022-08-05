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
public class Pengembalian extends javax.swing.JPanel {

    /**
     * Creates new form Mobilpane
     */
    public Pengembalian() {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screen.width, screen.height);
        tampil_tb_transaksi("");
        data_combobox();
        total_denda.setVisible(false);
        lbl_totaldenda.setVisible(false);
        hidden();
    }
    private String lempar;
    public String kirim(String kirim){
        String inkirim = kirim;                                                                                                                                                                                                                                                                                                                                                                                                                                    
        // System.out.println("kirim = " + kirim );
        lempar = kirim;
        lemparan(lempar);
        return inkirim;
    }
    public void lemparan(String lempar){
        System.out.println(lempar);
    }
    public void hidden(){
        lbl_tglpengembalian.setVisible(false);
        lbl_tglsewa.setVisible(false);
        in_tanggalPengembalian1.setVisible(false);
        in_tanggalSewa.setVisible(false);
    }
    
    private void reset(){
        in_idTransaksi.setText("");
        inCatatanTransaksi.setText("");
        inCatatanMobil.setText("");
        total_denda.setText("");
    }
    public String tangkap_idmobil;
    Date tanggal_hariini;
    Date tanggal_pengembalian;
    private void klicktable(){
        try {
            int baris = tbl_transaksi.getSelectedRow();
            in_idTransaksi.setText((String)tbl_transaksi.getValueAt(baris, 0));
            inCatatanTransaksi.setText((String)tbl_transaksi.getValueAt(baris, 13));
            tangkap_idmobil = tbl_transaksi.getValueAt(baris, 3).toString();
            
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

//            Date tgl_sewa = dateformat.parse((String)tbl_transaksi.getValueAt(baris, 9));
            Date tgl_kembali = dateformat.parse((String)tbl_transaksi.getValueAt(baris, 10));
            in_tanggalSewa.setDate(new Date());
            in_tanggalPengembalian1.setDate(tgl_kembali);
            HitungDenda();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void data_combobox(){
        cb_pilihdata_tabel.removeAllItems();
        
        cb_pilihdata_tabel.addItem("Tampilkan Semua");
        cb_pilihdata_tabel.addItem("Sedang Peminjaman");
        cb_pilihdata_tabel.addItem("Telat Mengembalikan");
        cb_pilihdata_tabel.addItem("Selesai Dikembalikan");
    }
    
//    private void data_combobox(){
//        cb_Status.removeAllItems();
//        
//        cb_Status.addItem("Sedang Dalam Peminjaman");
//        cb_Status.addItem("Dikembalikan");
//    }
    
    
        public void updateMobil(String id_mobil){
        Connection con = new DatabaseConfig().getConnection();
        String status = "Tersedia";
        String catatan = inCatatanMobil.getText();
        
        
        if (id_mobil == "") {
            JOptionPane.showMessageDialog(null, "Id Mobil tidak ditemukan untuk update", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String sql = "UPDATE mobil SET catatan ='"+catatan+"', status ='"+status+"' WHERE id_mobil ='"+id_mobil+"'";
            PreparedStatement stat = null;
            try {
                stat = con.prepareStatement(sql);
                stat.executeUpdate();
                System.out.println("Data mobil berhasil diubah " + id_mobil);
                tampil_tb_transaksi("");
                reset();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di ubah");
                System.out.println("Updatemobil " + e.getMessage());
            }
        }
    }
    
    private void HitungDenda(){
           String Hasil= "";
           try {
               SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String strDate2 = df.format(in_tanggalSewa.getDate());
                String strDate1 = df.format(in_tanggalPengembalian1.getDate());
                Date Tanggal1 = df.parse(strDate1);
                Date Tanggal2 = df.parse(strDate2);
                long Hari1 = Tanggal1.getTime();
                long Hari2 = Tanggal2.getTime();
                long selisih = Hari2 - Hari1;
                long Lama = selisih / (24 * 60 * 60 * 1000);
                long denda = 50000;
                denda *= Lama;
                Hasil = (Long.toString(denda));
                
                total_denda.setText(Hasil);
               
           } catch (Exception e) {
               System.out.println(e);
               System.out.println("hasil " + Hasil);
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
            String filter = "";
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
        RESET = new javax.swing.JButton();
        KEMBALIKANMOBIL = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txt_titleTabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inCatatanTransaksi = new javax.swing.JTextArea();
        in_cari = new javax.swing.JTextField();
        lbl_cari = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        inCatatanMobil = new javax.swing.JTextArea();
        in_idTransaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cb_pilihdata_tabel = new javax.swing.JComboBox<>();
        in_tanggalSewa = new com.toedter.calendar.JDateChooser();
        lbl_tglsewa = new javax.swing.JLabel();
        lbl_tglpengembalian = new javax.swing.JLabel();
        in_tanggalPengembalian1 = new com.toedter.calendar.JDateChooser();
        lbl_totaldenda = new javax.swing.JLabel();
        total_denda = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        RESET.setBackground(new java.awt.Color(51, 255, 51));
        RESET.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        KEMBALIKANMOBIL.setBackground(new java.awt.Color(117, 177, 250));
        KEMBALIKANMOBIL.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        KEMBALIKANMOBIL.setText("KEMBALIKAN MOBIL");
        KEMBALIKANMOBIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KEMBALIKANMOBILActionPerformed(evt);
            }
        });

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

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PENGEMBALIAN");
        jLabel6.setToolTipText("");

        txt_titleTabel.setBackground(new java.awt.Color(0, 0, 0));
        txt_titleTabel.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        txt_titleTabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_titleTabel.setText("Data Transaksi");
        txt_titleTabel.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel10.setText("Catatan Transaksi");

        inCatatanTransaksi.setColumns(20);
        inCatatanTransaksi.setRows(5);
        jScrollPane2.setViewportView(inCatatanTransaksi);

        in_cari.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in_cariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                in_cariKeyReleased(evt);
            }
        });

        lbl_cari.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lbl_cari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_cari.setText("Cari");

        jLabel11.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel11.setText("Catatan Mobil");

        inCatatanMobil.setColumns(20);
        inCatatanMobil.setRows(5);
        jScrollPane3.setViewportView(inCatatanMobil);

        in_idTransaksi.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_idTransaksi.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel3.setText("Id Transaksi");

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

        lbl_tglsewa.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lbl_tglsewa.setText("Tgl Sewa");

        lbl_tglpengembalian.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lbl_tglpengembalian.setText("Tgl Pengembalian");

        in_tanggalPengembalian1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                in_tanggalPengembalian1MousePressed(evt);
            }
        });
        in_tanggalPengembalian1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in_tanggalPengembalian1KeyPressed(evt);
            }
        });

        lbl_totaldenda.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lbl_totaldenda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_totaldenda.setText("Denda");

        total_denda.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        total_denda.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(in_idTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel10)))
                .addGap(138, 138, 138)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_tglpengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(in_tanggalPengembalian1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cb_pilihdata_tabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(in_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(in_tanggalSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_tglsewa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(KEMBALIKANMOBIL)
                                .addGap(76, 76, 76)
                                .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_totaldenda, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(total_denda, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))
                        .addGap(388, 388, 388)))
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(487, 487, 487)
                .addComponent(txt_titleTabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(in_idTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KEMBALIKANMOBIL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total_denda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_totaldenda))
                .addGap(10, 10, 10)
                .addComponent(lbl_tglsewa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(in_tanggalSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_titleTabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_tglpengembalian)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_tanggalPengembalian1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_pilihdata_tabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(in_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_cari)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 860));
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void KEMBALIKANMOBILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KEMBALIKANMOBILActionPerformed
        // TODO add your handling code here:
        Connection con = new DatabaseConfig().getConnection();
        String id_transaksi = "", catatan = "", status_sewa="";
        id_transaksi = in_idTransaksi.getText();
        catatan = inCatatanTransaksi.getText();
        status_sewa = "Selesai dikembalikan";
        
        
        if (id_transaksi == "") {
            JOptionPane.showMessageDialog(null, "Silahkan isi semua form", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String sql = "UPDATE transaksi SET catatan ='"+catatan+"', status_sewa ='"+status_sewa+"' WHERE id_transaksi ='"+id_transaksi+"'";
            PreparedStatement stat = null;
            try {
                stat = con.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
                tampil_tb_transaksi("");
                reset();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di ubah");
                System.out.println(e.getMessage());
            }
        }
        updateMobil(tangkap_idmobil);
        System.out.println("id mobil " + tangkap_idmobil);
    }//GEN-LAST:event_KEMBALIKANMOBILActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_RESETActionPerformed

    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        // TODO add your handling code here:
        klicktable();
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void in_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_cariKeyPressed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_in_cariKeyPressed
    
    private void cb_pilihdata_tabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihdata_tabelActionPerformed
        // TODO add your handling code here:
        try {
            String cb_pilihan = cb_pilihdata_tabel.getSelectedItem().toString();
            if (cb_pilihan.equals("Selesai Dikembalikan")) {
                kirim("Selesai Dikembalikan");
                tampil_tb_transaksi_dikembalikan("");
                txt_titleTabel.setText("Data Transaksi Selesai");
                lbl_totaldenda.setVisible(false);
                total_denda.setVisible(false);
            }else if (cb_pilihan.equals("Sedang Peminjaman")) {
                kirim("Sedang Peminjaman");
                tampil_tb_transaksi_sedangpeminjaman("");
                txt_titleTabel.setText("Data Transaksi Dalam Peminjaman");
                lbl_totaldenda.setVisible(false);
                total_denda.setVisible(false);
            }else if (cb_pilihan.equals("Telat Mengembalikan")) {
                kirim("Telat Mengembalikan");
                tampil_tb_transaksi_terlambat("");
                txt_titleTabel.setText("Data Transaksi Belum dikembalikan");
                total_denda.setVisible(true);
                lbl_totaldenda.setVisible(true);
            } else{
                kirim("Data Semua Transaksi");
                tampil_tb_transaksi("");
                txt_titleTabel.setText("Data Semua Transaksi");
                total_denda.setVisible(false);
                lbl_totaldenda.setVisible(false);
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
        String cekkirim = kirim("");
        if (lempar.equals("Selesai Dikembalikan")) {
                tampil_tb_transaksi_dikembalikan(incari);
//                System.out.println(kirim(""));
            }else if (lempar.equals("Sedang Peminjaman")) {
//                System.out.println(kirim(""));
                tampil_tb_transaksi_sedangpeminjaman(incari);
            }else if (lempar.equals("Telat Mengembalikan")) {
                tampil_tb_transaksi_terlambat(incari);
//                System.out.println(kirim(""));
            } else{
                tampil_tb_transaksi(incari);
//                System.out.println(kirim(""));
            }
        System.out.println(lempar);
    }//GEN-LAST:event_in_cariKeyReleased

    private void in_tanggalPengembalian1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_tanggalPengembalian1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_tanggalPengembalian1KeyPressed

    private void in_tanggalPengembalian1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_in_tanggalPengembalian1MousePressed
        // TODO add your handling code here:]
    }//GEN-LAST:event_in_tanggalPengembalian1MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KEMBALIKANMOBIL;
    private javax.swing.JButton RESET;
    private javax.swing.JComboBox<String> cb_pilihdata_tabel;
    private javax.swing.JTextArea inCatatanMobil;
    private javax.swing.JTextArea inCatatanTransaksi;
    private javax.swing.JTextField in_cari;
    private javax.swing.JTextField in_idTransaksi;
    private com.toedter.calendar.JDateChooser in_tanggalPengembalian1;
    private com.toedter.calendar.JDateChooser in_tanggalSewa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_cari;
    private javax.swing.JLabel lbl_tglpengembalian;
    private javax.swing.JLabel lbl_tglsewa;
    private javax.swing.JLabel lbl_totaldenda;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField total_denda;
    private javax.swing.JLabel txt_titleTabel;
    // End of variables declaration//GEN-END:variables
}
