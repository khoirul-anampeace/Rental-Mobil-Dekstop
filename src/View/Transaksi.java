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
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.collections.map.HashedMap;

/**
 *
 * @author Anam
 */
public class Transaksi extends javax.swing.JPanel {

    /**
     * Creates new form Mobilpane
     */
    public Transaksi() {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screen.width, screen.height);
        tampil_tb_transaksi();
        id_otomatis();
        in_idCustomer.requestFocus();
    }
    private void cetakStruk(String id_transaksi){
        JasperReport report;
        String path = ".\\src\\Report\\report3.jasper";
        try {
            HashMap ha = new HashMap();
            ha.put("paramid_transaksi", id_transaksi);
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            Connection con = new DatabaseConfig().getConnection();
            JasperPrint jprint = JasperFillManager.fillReport(path, ha, con);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void id_otomatis(){
        try {
            Connection con = new DatabaseConfig().getConnection();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM transaksi order by id_transaksi desc");
            if (rs.next()) {
                 String id = rs.getString("id_transaksi").substring(1);
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
                 in_idTransaksi.setText("T" + nol + AN);
            }else{
                in_idTransaksi.setText("T0001");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
//    private void data_combobox(){
//        cb_Status.removeAllItems();
//        
//        cb_Status.addItem("Sedang Dalam Peminjaman");
//        cb_Status.addItem("Dikembalikan");
//    }
    
    private DefaultTableModel tabmode;
    
    
    private void tampil_tb_transaksi(){
    Object []baris = {"Id_transaksi", "IdCust", "Customer", "IdMob", "Mobil", "Tarif Mobil", "IdSop", "Sopir", "Tarif Mobil", "Sewa", "Kembali", "Lama Sewa", "Total Harga", "Catatan", "Status Sewa"};
        tabmode = new DefaultTableModel (null, baris);
        tbl_transaksi.setModel(tabmode);
        Connection con = new DatabaseConfig().getConnection();
        try {
            String sql = "SELECT * from view_transaksi order by id_transaksi asc";
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

        in_idTransaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        in_idMobil = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        in_merk = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        in_idCustomer = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        RESET = new javax.swing.JButton();
        HAPUS = new javax.swing.JButton();
        EDIT = new javax.swing.JButton();
        SIMPAN = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        in_namaCustomer = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txt_titleTabel = new javax.swing.JLabel();
        in_tarifMobil = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        in_tarifSopir = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inCatatan = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        in_totalHarga1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        in_lamaSewa1 = new javax.swing.JTextField();
        in_tanggalSewa = new com.toedter.calendar.JDateChooser();
        in_tanggalPengembalian1 = new com.toedter.calendar.JDateChooser();
        HITUNG = new javax.swing.JButton();
        in_cari = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        in_sopir = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        in_wilayah = new javax.swing.JTextField();
        in_statusmobil = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        CETAK = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        in_idTransaksi.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_idTransaksi.setEnabled(false);
        add(in_idTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 72, 210, -1));

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel3.setText("Id Transaksi");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 49, 100, -1));

        in_idMobil.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_idMobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_idMobilActionPerformed(evt);
            }
        });
        add(in_idMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 135, 210, -1));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel4.setText("Id Mobil");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 112, 100, -1));

        in_merk.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        add(in_merk, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 135, 210, -1));

        jLabel8.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel8.setText("Merk");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 112, 100, -1));

        in_idCustomer.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_idCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_idCustomerActionPerformed(evt);
            }
        });
        add(in_idCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 72, 210, -1));

        jLabel7.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel7.setText("Id Customer");
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

        jLabel12.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel12.setText("Nama Customer");

        in_namaCustomer.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

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
        jLabel6.setText("TRANSAKSI");
        jLabel6.setToolTipText("");

        txt_titleTabel.setBackground(new java.awt.Color(0, 0, 0));
        txt_titleTabel.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        txt_titleTabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_titleTabel.setText("Data Transaksi");
        txt_titleTabel.setToolTipText("");

        in_tarifMobil.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel16.setText("Tarif Mobil");

        in_tarifSopir.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel17.setText("Tarif Sopir");

        jLabel10.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel10.setText("Catatan");

        inCatatan.setColumns(20);
        inCatatan.setRows(5);
        jScrollPane2.setViewportView(inCatatan);

        jLabel15.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel15.setText("Total Harga");

        in_totalHarga1.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel18.setText("Lama Sewa");

        in_lamaSewa1.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_lamaSewa1.setEnabled(false);

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

        HITUNG.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        HITUNG.setText("HITUNG");
        HITUNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HITUNGActionPerformed(evt);
            }
        });

        in_cari.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in_cariKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Cari");

        in_sopir.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_sopir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_sopirActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel5.setText("Id_sopir");

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel14.setText("Tgl Sewa");

        jLabel9.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel9.setText("Wilayah");

        jLabel13.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel13.setText("Tgl Pengembalian");

        in_wilayah.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N

        in_statusmobil.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        in_statusmobil.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel11.setText("Status Mobil");

        CETAK.setBackground(new java.awt.Color(51, 255, 51));
        CETAK.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        CETAK.setText("CETAK");
        CETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CETAKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(531, 531, 531)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_titleTabel)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 474, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(669, 669, 669)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(in_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_tanggalSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_sopir, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(69, 69, 69)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_tanggalPengembalian1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_wilayah, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(HITUNG)
                                            .addGap(37, 37, 37)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12)
                                                .addComponent(in_namaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(157, 157, 157)
                                            .addComponent(SIMPAN, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_tarifMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_tarifSopir, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_totalHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_lamaSewa1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(in_statusmobil, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(157, 157, 157)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(EDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(HAPUS, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(SIMPAN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(EDIT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(in_namaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel16)
                                .addGap(6, 6, 6)
                                .addComponent(in_tarifMobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel14))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(in_tanggalSewa, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                                    .addComponent(in_tanggalPengembalian1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(6, 6, 6)
                                                .addComponent(in_statusmobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel5))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(in_tarifSopir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(in_wilayah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(in_sopir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(HAPUS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(6, 6, 6)
                        .addComponent(in_lamaSewa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(in_totalHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HITUNG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(txt_titleTabel)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(in_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(CETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 860));
    }// </editor-fold>//GEN-END:initComponents

    private void reset(){
        in_idTransaksi.setText("");
        in_idMobil.setText("");
        in_sopir.setText("");
        in_namaCustomer.setText("");
        in_merk.setText("");
        in_idCustomer.setText("");
        in_lamaSewa1.setText("");
        in_wilayah.setText("");
        inCatatan.setText("");
        in_totalHarga1.setText("");
        in_tarifMobil.setText("");
        in_tarifSopir.setText("");
        in_tanggalPengembalian1.setDate(new Date());
        in_tanggalSewa.setDate(new Date());
        in_statusmobil.setText("");
        
        id_otomatis();
    }
    private void klicktable(){
        try {
            int baris = tbl_transaksi.getSelectedRow();
            in_idTransaksi.setText((String)tbl_transaksi.getValueAt(baris, 0));
            in_idCustomer.setText((String)tbl_transaksi.getValueAt(baris, 1));
            in_namaCustomer.setText((String)tbl_transaksi.getValueAt(baris, 2));
            in_idMobil.setText((String)tbl_transaksi.getValueAt(baris, 3));
            in_merk.setText((String)tbl_transaksi.getValueAt(baris, 4));
            in_tarifMobil.setText((String)tbl_transaksi.getValueAt(baris, 5));
            in_sopir.setText((String)tbl_transaksi.getValueAt(baris, 6));
            in_wilayah.setText((String)tbl_transaksi.getValueAt(baris, 7));
            in_tarifSopir.setText((String)tbl_transaksi.getValueAt(baris, 8));
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

            Date tgl_sewa = dateformat.parse((String)tbl_transaksi.getValueAt(baris, 9));
            Date tgl_kembali = dateformat.parse((String)tbl_transaksi.getValueAt(baris, 10));
            in_tanggalSewa.setDate(tgl_sewa);
            in_tanggalPengembalian1.setDate(tgl_kembali);
            in_lamaSewa1.setText((String)tbl_transaksi.getValueAt(baris, 11));
            in_totalHarga1.setText((String)tbl_transaksi.getValueAt(baris, 12));
            inCatatan.setText((String)tbl_transaksi.getValueAt(baris, 13));
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateMobil(String id_mobil){
        Connection con = new DatabaseConfig().getConnection();
        String status = "Beroperasi";
        
        if (id_mobil == "") {
            JOptionPane.showMessageDialog(null, "Id Mobil tidak ditemukan untuk update", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String sql = "UPDATE mobil SET status ='"+status+"' WHERE id_mobil ='"+id_mobil+"'";
            PreparedStatement stat = null;
            try {
                stat = con.prepareStatement(sql);
                stat.executeUpdate();
                System.out.println("Data mobil berhasil diubah " + id_mobil);
                tampil_tb_transaksi();
                reset();
                id_otomatis();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di ubah");
                System.out.println("Updatemobil " + e.getMessage());
            }
        }
    }
    
    public void cari_customer(){
        String id_customer = "";
        id_customer = in_idCustomer.getText();
        Connection con = new DatabaseConfig().getConnection();
        String sql = "SELECT * FROM customer WHERE id_customer='"+id_customer+"'";
        try {
            Statement stat = con.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {                
                in_namaCustomer.setText(set.getString("nama_customer"));
                
            }
            in_idMobil.requestFocus();
            set.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Pencarian Customer Gagal","Informasi",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void cari_mobil(){
        String id_mobil = "";
        id_mobil = in_idMobil.getText();
        Connection con = new DatabaseConfig().getConnection();
        String sql = "SELECT * FROM mobil WHERE id_mobil ='"+id_mobil+"'";
        try {
            Statement stat = con.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {                
                in_merk.setText(set.getString("merk"));
                in_tarifMobil.setText(set.getString("tarif"));
                in_statusmobil.setText(set.getString("status"));
//                System.out.println("cari sopir " + set.getString("wilayah_tujuan") );
            }
            in_sopir.requestFocus();
            set.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Pencarian mobil Gagal","Informasi",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void cari_sopir(){
        String id_sopir = "";
        id_sopir = in_sopir.getText();
        Connection con = new DatabaseConfig().getConnection();
        String sql = "SELECT * FROM sopir WHERE id_sopir ='"+id_sopir+"'";
        try {
            Statement stat = con.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {                
                in_wilayah.setText(set.getString("wilayah_tujuan"));
                in_tarifSopir.setText(set.getString("tarif_sopir"));
//                System.out.println("cari sopir " + set.getString("wilayah_tujuan") );
            }
            set.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Pencarian Sopir Gagal","Informasi",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    // Hitung lama dan total harga peminjaman
       private void hitungLama(){
           String Hasil= "";
           try {
               SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String strDate1 = df.format(in_tanggalSewa.getDate());
                String strDate2 = df.format(in_tanggalPengembalian1.getDate());
                Date Tanggal1 = df.parse(strDate1);
                Date Tanggal2 = df.parse(strDate2);
                long Hari1 = Tanggal1.getTime();
                long Hari2 = Tanggal2.getTime();
                long selisih = Hari2 - Hari1;
                long Lama = selisih / (24 * 60 * 60 * 1000);
                Hasil = (Long.toString(Lama));
                in_lamaSewa1.setText(Hasil);
                
                // Hitung tarif mobil
                long tarifMobil = Long.parseLong(in_tarifMobil.getText());
                long hasilTarifMobil = tarifMobil * Lama;

                // Hitung tarif Sopir
                long tarifSopir = Long.parseLong(in_tarifSopir.getText());
                long hasilTarifSopir = tarifSopir * Lama;

                long totalHargalong = hasilTarifMobil + hasilTarifSopir;
                String totalHarga = (Long.toString(totalHargalong));
                in_totalHarga1.setText(totalHarga);
           } catch (Exception e) {
               System.out.println(e);
               System.out.println("hasil " + Hasil);
           }
       }
    
    
    private void SIMPANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIMPANActionPerformed
        // TODO add your handling code here:
        int  lama_sewa = 0, total_harga = 0;
        String id_transaksi = "", id_customer="", id_sopir="", id_mobil="", tgl_sewa = "", tgl_kembali = "", catatan = "", status_sewa="";
        id_transaksi = in_idTransaksi.getText();
        id_customer = in_idCustomer.getText();
        id_mobil = in_idMobil.getText();
        id_sopir = in_sopir.getText();
        // ubah format tanggal
        SimpleDateFormat kal = new SimpleDateFormat("yyyy-MM-dd");
        tgl_sewa =  kal.format(in_tanggalSewa.getDate()).toString();
        tgl_kembali = kal.format(in_tanggalPengembalian1.getDate()).toString();
        lama_sewa = Integer.parseInt(in_lamaSewa1.getText());
        total_harga = Integer.parseInt(in_totalHarga1.getText());
        catatan = inCatatan.getText();
        status_sewa = "Sedang dalam peminjaman";
        
        if (id_transaksi == "" || lama_sewa == 0 || id_customer == "" || id_mobil == "" || id_sopir == "" || tgl_sewa == "" || total_harga == 0 || catatan == "" || tgl_kembali == "" || status_sewa == "") {
            JOptionPane.showMessageDialog(null, "Silahkan isi semua form", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else{ 
            try {
                Connection con = new DatabaseConfig().getConnection();
                String sql = "INSERT INTO transaksi VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stat = (PreparedStatement) con.prepareStatement(sql);
                stat = con.prepareStatement(sql);
                stat.setString(1, id_transaksi);
                stat.setString(2, id_customer);
                stat.setString(3, id_sopir);
                stat.setString(4, id_mobil);
                stat.setString(5, tgl_sewa);
                stat.setString(6, tgl_kembali);
                stat.setInt(7, lama_sewa);
                stat.setInt(8, total_harga);
                stat.setString(9, catatan);
                stat.setString(10, status_sewa);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan", "informasi", JOptionPane.INFORMATION_MESSAGE);
    //            JOptionPane.showMessageDialog(null, "Isi status " + status, "informasi", JOptionPane.INFORMATION_MESSAGE);
                tampil_tb_transaksi();
                reset();
                id_otomatis();
            } catch (Exception e) {
    //            System.out.println(no_bp + nama + tempat_lahir + jurusan + tanggal_lahir + tanggal_masuk);
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Menyimpan Data ga", "informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        updateMobil(id_mobil);
        cetakStruk(id_transaksi);
    }//GEN-LAST:event_SIMPANActionPerformed

    private void HAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HAPUSActionPerformed
        // TODO add your handling code here:
        int baris = tbl_transaksi.getSelectedRow();
        String id_transaksi = tabmode.getValueAt(baris, 0).toString();
        String nopol = tabmode.getValueAt(baris, 2).toString();
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin Menghapus data Transaksi dengan id_transaksi : " + id_transaksi + "?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                Connection con = new DatabaseConfig().getConnection();
                String sql = "DELETE FROM transaksi WHERE id_transaksi = '"+id_transaksi+"'";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data Berhasil dihapus");
//                tampil_tb_mahasiswa();
                reset();
                System.out.println(sql);
                tampil_tb_transaksi();
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
        int  lama_sewa = 0, total_harga = 0;
        String id_transaksi = "", id_customer="", id_sopir="", id_mobil="", tgl_sewa = "", tgl_kembali = "", catatan = "", status_sewa="";
        id_transaksi = in_idTransaksi.getText();
        id_customer = in_idCustomer.getText();
        id_mobil = in_idMobil.getText();
        id_sopir = in_sopir.getText();
        // ubah format tanggal
        SimpleDateFormat kal = new SimpleDateFormat("yyyy-MM-dd");
        tgl_sewa =  kal.format(in_tanggalSewa.getDate()).toString();
        tgl_kembali = kal.format(in_tanggalPengembalian1.getDate()).toString();
        lama_sewa = Integer.parseInt(in_lamaSewa1.getText());
        total_harga = Integer.parseInt(in_totalHarga1.getText());
        catatan = inCatatan.getText();
        status_sewa = "Sedang dalam peminjaman";
        
        if (id_transaksi == "" || lama_sewa == 0 || id_customer == "" || id_mobil == "" || id_sopir == "" || tgl_sewa == "" || total_harga == 0 || catatan == "" || tgl_kembali == "" || status_sewa == "") {
            JOptionPane.showMessageDialog(null, "Silahkan isi semua form", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String sql = "UPDATE transaksi SET id_customer=?, id_sopir=?,  id_mobil=?, tanggal_sewa=?, tgl_kembali=?, lama_sewa=?, total_harga =?, catatan=?, status_sewa =?  WHERE id_transaksi ='"+id_transaksi+"'";
            PreparedStatement stat = null;
            try {
                stat = con.prepareStatement(sql);
                stat.setString(1, id_customer);
                stat.setString(2, id_sopir);
                stat.setString(3, id_mobil);
                stat.setString(4, tgl_sewa);
                stat.setString(5, tgl_kembali);
                stat.setInt(6, lama_sewa);
                stat.setInt(7, total_harga);
                stat.setString(8, catatan);
                stat.setString(9, status_sewa);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
                tampil_tb_transaksi();
                reset();
                id_otomatis();
                System.out.println("Update nih " + stat);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di ubah");
                System.out.println(e.getMessage());
            }
        }
        updateMobil(id_mobil);
    }//GEN-LAST:event_EDITActionPerformed

    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        // TODO add your handling code here:
        klicktable();
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void in_idCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_idCustomerActionPerformed
        // TODO add your handling code here:
        cari_customer();
    }//GEN-LAST:event_in_idCustomerActionPerformed

    private void in_tanggalPengembalian1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_in_tanggalPengembalian1MousePressed
        // TODO add your handling code here:]
        
    }//GEN-LAST:event_in_tanggalPengembalian1MousePressed

    private void in_tanggalPengembalian1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_tanggalPengembalian1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_tanggalPengembalian1KeyPressed

    private void HITUNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HITUNGActionPerformed
        // TODO add your handling code here:
        hitungLama();
    }//GEN-LAST:event_HITUNGActionPerformed

    private void in_sopirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_sopirActionPerformed
        // TODO add your handling code here:
        cari_sopir();
    }//GEN-LAST:event_in_sopirActionPerformed

    private void in_idMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_idMobilActionPerformed
        // TODO add your handling code here:
        cari_mobil();
    }//GEN-LAST:event_in_idMobilActionPerformed

    private void in_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in_cariKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_in_cariKeyPressed

    private void CETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CETAKActionPerformed
        // TODO add your handling code here:
        cetakStruk(in_idTransaksi.getText());

    }//GEN-LAST:event_CETAKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CETAK;
    private javax.swing.JButton EDIT;
    private javax.swing.JButton HAPUS;
    private javax.swing.JButton HITUNG;
    private javax.swing.JButton RESET;
    private javax.swing.JButton SIMPAN;
    private javax.swing.JTextArea inCatatan;
    private javax.swing.JTextField in_cari;
    private javax.swing.JTextField in_idCustomer;
    private javax.swing.JTextField in_idMobil;
    private javax.swing.JTextField in_idTransaksi;
    private javax.swing.JTextField in_lamaSewa1;
    private javax.swing.JTextField in_merk;
    private javax.swing.JTextField in_namaCustomer;
    private javax.swing.JTextField in_sopir;
    private javax.swing.JTextField in_statusmobil;
    private com.toedter.calendar.JDateChooser in_tanggalPengembalian1;
    private com.toedter.calendar.JDateChooser in_tanggalSewa;
    private javax.swing.JTextField in_tarifMobil;
    private javax.swing.JTextField in_tarifSopir;
    private javax.swing.JTextField in_totalHarga1;
    private javax.swing.JTextField in_wilayah;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JLabel txt_titleTabel;
    // End of variables declaration//GEN-END:variables
}
