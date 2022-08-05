-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Agu 2022 pada 10.13
-- Versi server: 10.4.19-MariaDB
-- Versi PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_rentalmobil`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_transaksi` ()  BEGIN 
	SELECT id_transaksi, c.nama_customer, m.merk, s.wilayah_tujuan, tanggal_sewa, tgl_kembali, lama_sewa, m.tarif, s.tarif_sopir, total_harga , t.catatan , status_sewa FROM transaksi t JOIN customer c ON t.id_customer = c.id_customer JOIN mobil m ON t.id_mobil = m.id_mobil JOIN sopir s ON t.id_sopir = s.id_sopir;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_Transaksi_parameter` (`status_transaksi` VARCHAR(100))  BEGIN
	SELECT id_transaksi, c.nama_customer, m.merk, s.wilayah_tujuan, tanggal_sewa, tgl_kembali, lama_sewa, m.tarif, s.tarif_sopir, total_harga , t.catatan , status_sewa FROM transaksi t JOIN customer c ON t.id_customer = c.id_customer JOIN mobil m ON t.id_mobil = m.id_mobil JOIN sopir s ON t.id_sopir = s.id_sopir WHERE t.status_sewa = status_transaksi;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` varchar(6) NOT NULL,
  `nama_admin` varchar(255) NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `level` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `no_telp`, `username`, `password`, `level`) VALUES
('A0001', 'NamaPanjang Owner', '086454', 'owner', 'owner', 'Owner'),
('A0002', 'Nama Staff 123', '0865788', 'staff', '123', 'Staff'),
('A0003', 'Muh Ilham Awaludin', '98729', 'ilham', 'ilham', 'Staff'),
('A0004', 'Ary Pratama Putra', '008771', 'ary', 'ary', 'Owner'),
('A0005', 'Yuyun Ine Febriani', '085232234321', 'yuyun', 'yuyun', 'Staff'),
('A0006', 'Holila Ristafia', '081221342322', 'fia', 'fia ', 'Owner'),
('A0007', 'Arinda Agustin', '082334564789', 'rinda', 'rinda', 'Staff'),
('A0008', 'Lelita Amelia', '085678987666', 'leli', 'leli', 'Staff'),
('A0009', 'Jeffri Nichole', '081998876670', 'jeffri', 'nichole', 'Staff'),
('A0010', 'Manu Rios', '085342340987', 'rios', 'rios', 'Staff'),
('A0011', 'Lewis Capaldi', '085456732655', 'lewis', 'lewis', 'Staff'),
('A0012', 'Zara Larson', '085234432122', 'zara', 'zara', 'Staff'),
('A0013', 'Primily Audi', '085678876900', 'prim', 'prim', 'Staff'),
('A0014', 'Clarissa Disa', '081987980433', 'disa', 'disa', 'Staff');

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

CREATE TABLE `customer` (
  `id_customer` varchar(6) NOT NULL,
  `nama_customer` varchar(255) NOT NULL,
  `nik` varchar(18) NOT NULL,
  `jenis_kelamin` varchar(30) NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `alamat` text NOT NULL,
  `tgl_lahir` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `customer`
--

INSERT INTO `customer` (`id_customer`, `nama_customer`, `nik`, `jenis_kelamin`, `no_telp`, `alamat`, `tgl_lahir`) VALUES
('C0001', 'Muhammad Al Kwarizmi', '351313012210005', 'Laki - Laki', '081882001882', 'Kaliurang No. 05 - Jember', '2000-07-10'),
('C0003', 'Lydora Firdausiah', '351313031500001', 'Perempuan', '081441001555', 'Sumbersari No. 15 - Jember', '2000-05-10'),
('C0004', 'Ferdi Abdillah', '351313033050007', 'Laki - Laki', '081441001440', 'Sumbersari No. 20 - Jember', '1999-03-11'),
('C0005', 'Rebecca Khairunnisa', '351313033020026', 'Perempuan', '081441001001', 'Patrang No. 54 - Jember', '2000-12-10'),
('C0006', 'Vivi', '35156768', 'perempuan', '081234233112', 'Jl.Semangka No.5', '2000-06-01'),
('C0007', 'Vira', '3510987', 'perempuan', '085678987677', 'Jl.Kalimantan', '1999-12-12'),
('C0008', 'Rahwana', '351340', 'laki-laki', '085678887900', 'Jl. Panglima Sudirman', '2001-02-26'),
('C0009', 'Arinda', '351455', 'perempuan', '085888976098', 'Jl.Diponegoro No.9', '1998-11-12'),
('C0010', 'Nauval', '351223', 'laki-laki', '081234564433', 'Jl.Bondowoso 5', '2002-06-01'),
('C0011', 'Canva narendra', '351887', 'laki-laki', '081998789880', 'Jl.Kenanga 3', '1999-02-26'),
('C0012', 'Arifin', '351776', 'laki-laki', '085678765345', 'Jl.Brantas', '2003-07-07'),
('C0013', 'Maudi', '351909', 'perempuan', '085234432121', 'Jl.Anggrek No.10', '1998-04-05'),
('C0014', 'Primily', '351789', 'perempuan', '085678876907', 'Jl.Pondok Indah', '2001-09-02'),
('C0015', 'Refal', '351432', 'laki-laki', '081987980434', 'Jl.Kalitidu No.60', '1999-05-12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `deletetransaksi`
--

CREATE TABLE `deletetransaksi` (
  `id_transaksi` varchar(11) NOT NULL,
  `id_customer` varchar(11) NOT NULL,
  `id_sopir` varchar(11) NOT NULL,
  `id_mobil` varchar(6) NOT NULL,
  `tanggal_sewa` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `lama_sewa` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `catatan` text NOT NULL,
  `status_sewa` varchar(255) NOT NULL,
  `update_delete` datetime DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `mobil`
--

CREATE TABLE `mobil` (
  `id_mobil` varchar(6) NOT NULL,
  `merk` varchar(255) NOT NULL,
  `tahun` int(4) NOT NULL,
  `no_polisi` varchar(255) NOT NULL,
  `no_rangka` varchar(255) NOT NULL,
  `no_mesin` varchar(255) NOT NULL,
  `warna` varchar(255) NOT NULL,
  `jumlah_kursi` int(11) NOT NULL,
  `catatan` text NOT NULL,
  `tarif` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mobil`
--

INSERT INTO `mobil` (`id_mobil`, `merk`, `tahun`, `no_polisi`, `no_rangka`, `no_mesin`, `warna`, `jumlah_kursi`, `catatan`, `tarif`, `status`) VALUES
('M0001', 'Avanza', 2022, 'p 1234 kuw', '1234', '123ert4', 'Merah', 7, '-', 500000, 'Beroperasi'),
('M0002', 'Veloz', 2021, 'L 4321 uj', '3456', '23gfd71', 'hitam', 7, '-', 500000, 'Tersedia'),
('M0003', 'Alphard', 2019, 'p 6543 hn', '567ghm', '567gbj', 'hitam', 7, '', 1500000, 'Tersedia'),
('M0004', 'Ayla', 2020, 'B 5674 fj', '9876', '898ygh', 'putih', 5, '-', 600000, 'Tersedia'),
('M0005', 'Honda Jazz', 2021, 'S 8763 su', '654987', '567wes8', 'hitam', 5, '-', 1000000, 'Tersedia'),
('M0006', 'Xenia', 2019, 'L 5976 KU', '9845', '34hg7h', 'merah', 7, '-', 500000, 'Tersedia'),
('M0007', 'Sigra', 2021, 'P 6523 LU', '98712h', '56dft23', 'biru', 7, '-', 600000, 'Tersedia'),
('M0008', 'Calya', 2020, 'S 5698 DL', '56789', '987yhu6', 'putih', 7, '-', 550000, 'Tersedia'),
('M0009', 'Honda Brio', 2021, 'L 9811 HR', '45467tgh', '876ygh7', 'merah', 5, '-', 800000, 'Tersedia'),
('M0010', 'Pajero', 2021, 'S 4563 SE', '9876ty', '569uin', 'hitam', 7, '-', 850000, 'Tersedia');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sopir`
--

CREATE TABLE `sopir` (
  `id_sopir` varchar(6) NOT NULL,
  `wilayah_tujuan` varchar(255) NOT NULL,
  `tarif_sopir` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `sopir`
--

INSERT INTO `sopir` (`id_sopir`, `wilayah_tujuan`, `tarif_sopir`) VALUES
('S0001', 'Tanpa Sopir', 0),
('S0002', 'Lumajang', 50000),
('S0003', 'Malang', 100000),
('S0004', 'Surabaya', 150000),
('S0005', 'New York', 200000),
('S0006', 'Solo', 250000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(11) NOT NULL,
  `id_customer` varchar(11) NOT NULL,
  `id_sopir` varchar(11) NOT NULL,
  `id_mobil` varchar(6) NOT NULL,
  `tanggal_sewa` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `lama_sewa` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `catatan` text NOT NULL,
  `status_sewa` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_customer`, `id_sopir`, `id_mobil`, `tanggal_sewa`, `tgl_kembali`, `lama_sewa`, `total_harga`, `catatan`, `status_sewa`) VALUES
('T0001', 'C0003', 'S0004', 'M0003', '2022-06-25', '2022-06-26', 1, 1650000, 'Dipakai untuk pernikahan', 'Selesai dikembalikan'),
('T0002', 'C0009', 'S0001', 'M0001', '2022-06-23', '2022-06-24', 1, 500000, 'Untuk Jalan jalan', 'Sedang dalam peminjaman');

--
-- Trigger `transaksi`
--
DELIMITER $$
CREATE TRIGGER `triggerdeletetransaksi` AFTER DELETE ON `transaksi` FOR EACH ROW BEGIN
	INSERT INTO deletetransaksi(
    	id_transaksi,
    	id_customer,
    	id_sopir,
        id_mobil,
        tanggal_sewa,
        tgl_kembali,
        lama_sewa,
        total_harga,
        catatan,
        status_sewa,
        update_delete,
        username
    )VALUES (
    	OLD.id_transaksi,
    	OLD.id_customer,
    	OLD.id_sopir,
        OLD.id_mobil,
        OLD.tanggal_sewa,
        OLD.tgl_kembali,
        OLD.lama_sewa,
        OLD.total_harga,
        OLD.catatan,
        OLD.status_sewa,
        SYSDATE(),
        CURRENT_USER
    );
 END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_transaksi`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_transaksi` (
`id_transaksi` varchar(11)
,`id_customer` varchar(11)
,`nama_customer` varchar(255)
,`id_sopir` varchar(11)
,`wilayah_tujuan` varchar(255)
,`tarif_sopir` int(11)
,`id_mobil` varchar(6)
,`merk` varchar(255)
,`tarif` int(11)
,`tanggal_sewa` date
,`tgl_kembali` date
,`lama_sewa` int(11)
,`total_harga` int(11)
,`catatan` text
,`status_sewa` varchar(255)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_transaksi_belumdikembalikan`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_transaksi_belumdikembalikan` (
`id_transaksi` varchar(11)
,`id_customer` varchar(11)
,`nama_customer` varchar(255)
,`id_sopir` varchar(11)
,`wilayah_tujuan` varchar(255)
,`tarif_sopir` int(11)
,`id_mobil` varchar(6)
,`merk` varchar(255)
,`tarif` int(11)
,`tanggal_sewa` date
,`tgl_kembali` date
,`lama_sewa` int(11)
,`total_harga` int(11)
,`catatan` text
,`status_sewa` varchar(255)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_transaksi_selesai`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_transaksi_selesai` (
`id_transaksi` varchar(11)
,`id_customer` varchar(11)
,`nama_customer` varchar(255)
,`id_sopir` varchar(11)
,`wilayah_tujuan` varchar(255)
,`tarif_sopir` int(11)
,`id_mobil` varchar(6)
,`merk` varchar(255)
,`tarif` int(11)
,`tanggal_sewa` date
,`tgl_kembali` date
,`lama_sewa` int(11)
,`total_harga` int(11)
,`catatan` text
,`status_sewa` varchar(255)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_transaksi_struk`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_transaksi_struk` (
`id_transaksi` varchar(11)
,`id_customer` varchar(11)
,`nama_customer` varchar(255)
,`id_sopir` varchar(11)
,`wilayah_tujuan` varchar(255)
,`tarif_sopir` int(11)
,`id_mobil` varchar(6)
,`merk` varchar(255)
,`tarif` int(11)
,`tanggal_sewa` date
,`tgl_kembali` date
,`lama_sewa` int(11)
,`total_harga` int(11)
,`catatan` text
,`status_sewa` varchar(255)
,`no_polisi` varchar(255)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_transaksi_terlambat`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_transaksi_terlambat` (
`id_transaksi` varchar(11)
,`id_customer` varchar(11)
,`nama_customer` varchar(255)
,`id_sopir` varchar(11)
,`wilayah_tujuan` varchar(255)
,`tarif_sopir` int(11)
,`id_mobil` varchar(6)
,`merk` varchar(255)
,`tarif` int(11)
,`tanggal_sewa` date
,`tgl_kembali` date
,`lama_sewa` int(11)
,`total_harga` int(11)
,`catatan` text
,`status_sewa` varchar(255)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `view_transaksi`
--
DROP TABLE IF EXISTS `view_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi`  AS SELECT `t`.`id_transaksi` AS `id_transaksi`, `t`.`id_customer` AS `id_customer`, `c`.`nama_customer` AS `nama_customer`, `t`.`id_sopir` AS `id_sopir`, `s`.`wilayah_tujuan` AS `wilayah_tujuan`, `s`.`tarif_sopir` AS `tarif_sopir`, `t`.`id_mobil` AS `id_mobil`, `m`.`merk` AS `merk`, `m`.`tarif` AS `tarif`, `t`.`tanggal_sewa` AS `tanggal_sewa`, `t`.`tgl_kembali` AS `tgl_kembali`, `t`.`lama_sewa` AS `lama_sewa`, `t`.`total_harga` AS `total_harga`, `t`.`catatan` AS `catatan`, `t`.`status_sewa` AS `status_sewa` FROM (((`transaksi` `t` join `mobil` `m` on(`t`.`id_mobil` = `m`.`id_mobil`)) join `customer` `c` on(`t`.`id_customer` = `c`.`id_customer`)) join `sopir` `s` on(`t`.`id_sopir` = `s`.`id_sopir`)) ORDER BY `t`.`id_transaksi` ASC ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_transaksi_belumdikembalikan`
--
DROP TABLE IF EXISTS `view_transaksi_belumdikembalikan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi_belumdikembalikan`  AS SELECT `view_transaksi`.`id_transaksi` AS `id_transaksi`, `view_transaksi`.`id_customer` AS `id_customer`, `view_transaksi`.`nama_customer` AS `nama_customer`, `view_transaksi`.`id_sopir` AS `id_sopir`, `view_transaksi`.`wilayah_tujuan` AS `wilayah_tujuan`, `view_transaksi`.`tarif_sopir` AS `tarif_sopir`, `view_transaksi`.`id_mobil` AS `id_mobil`, `view_transaksi`.`merk` AS `merk`, `view_transaksi`.`tarif` AS `tarif`, `view_transaksi`.`tanggal_sewa` AS `tanggal_sewa`, `view_transaksi`.`tgl_kembali` AS `tgl_kembali`, `view_transaksi`.`lama_sewa` AS `lama_sewa`, `view_transaksi`.`total_harga` AS `total_harga`, `view_transaksi`.`catatan` AS `catatan`, `view_transaksi`.`status_sewa` AS `status_sewa` FROM `view_transaksi` WHERE `view_transaksi`.`status_sewa` = 'Sedang dalam peminjaman' ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_transaksi_selesai`
--
DROP TABLE IF EXISTS `view_transaksi_selesai`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi_selesai`  AS SELECT `view_transaksi`.`id_transaksi` AS `id_transaksi`, `view_transaksi`.`id_customer` AS `id_customer`, `view_transaksi`.`nama_customer` AS `nama_customer`, `view_transaksi`.`id_sopir` AS `id_sopir`, `view_transaksi`.`wilayah_tujuan` AS `wilayah_tujuan`, `view_transaksi`.`tarif_sopir` AS `tarif_sopir`, `view_transaksi`.`id_mobil` AS `id_mobil`, `view_transaksi`.`merk` AS `merk`, `view_transaksi`.`tarif` AS `tarif`, `view_transaksi`.`tanggal_sewa` AS `tanggal_sewa`, `view_transaksi`.`tgl_kembali` AS `tgl_kembali`, `view_transaksi`.`lama_sewa` AS `lama_sewa`, `view_transaksi`.`total_harga` AS `total_harga`, `view_transaksi`.`catatan` AS `catatan`, `view_transaksi`.`status_sewa` AS `status_sewa` FROM `view_transaksi` WHERE `view_transaksi`.`status_sewa` = 'Selesai dikembalikan' ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_transaksi_struk`
--
DROP TABLE IF EXISTS `view_transaksi_struk`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi_struk`  AS SELECT `t`.`id_transaksi` AS `id_transaksi`, `t`.`id_customer` AS `id_customer`, `t`.`nama_customer` AS `nama_customer`, `t`.`id_sopir` AS `id_sopir`, `t`.`wilayah_tujuan` AS `wilayah_tujuan`, `t`.`tarif_sopir` AS `tarif_sopir`, `t`.`id_mobil` AS `id_mobil`, `t`.`merk` AS `merk`, `t`.`tarif` AS `tarif`, `t`.`tanggal_sewa` AS `tanggal_sewa`, `t`.`tgl_kembali` AS `tgl_kembali`, `t`.`lama_sewa` AS `lama_sewa`, `t`.`total_harga` AS `total_harga`, `t`.`catatan` AS `catatan`, `t`.`status_sewa` AS `status_sewa`, `m`.`no_polisi` AS `no_polisi` FROM (`view_transaksi` `t` join `mobil` `m` on(`t`.`id_mobil` = `m`.`id_mobil`)) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_transaksi_terlambat`
--
DROP TABLE IF EXISTS `view_transaksi_terlambat`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi_terlambat`  AS SELECT `view_transaksi`.`id_transaksi` AS `id_transaksi`, `view_transaksi`.`id_customer` AS `id_customer`, `view_transaksi`.`nama_customer` AS `nama_customer`, `view_transaksi`.`id_sopir` AS `id_sopir`, `view_transaksi`.`wilayah_tujuan` AS `wilayah_tujuan`, `view_transaksi`.`tarif_sopir` AS `tarif_sopir`, `view_transaksi`.`id_mobil` AS `id_mobil`, `view_transaksi`.`merk` AS `merk`, `view_transaksi`.`tarif` AS `tarif`, `view_transaksi`.`tanggal_sewa` AS `tanggal_sewa`, `view_transaksi`.`tgl_kembali` AS `tgl_kembali`, `view_transaksi`.`lama_sewa` AS `lama_sewa`, `view_transaksi`.`total_harga` AS `total_harga`, `view_transaksi`.`catatan` AS `catatan`, `view_transaksi`.`status_sewa` AS `status_sewa` FROM `view_transaksi` WHERE `view_transaksi`.`status_sewa` = 'Sedang dalam peminjaman' AND `view_transaksi`.`tgl_kembali` < curdate() ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_customer`);

--
-- Indeks untuk tabel `deletetransaksi`
--
ALTER TABLE `deletetransaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_customer` (`id_customer`),
  ADD KEY `id_mobil` (`id_mobil`),
  ADD KEY `id_sopir` (`id_sopir`);

--
-- Indeks untuk tabel `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`id_mobil`);

--
-- Indeks untuk tabel `sopir`
--
ALTER TABLE `sopir`
  ADD PRIMARY KEY (`id_sopir`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_customer` (`id_customer`),
  ADD KEY `id_mobil` (`id_mobil`),
  ADD KEY `id_sopir` (`id_sopir`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_mobil`) REFERENCES `mobil` (`id_mobil`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_sopir`) REFERENCES `sopir` (`id_sopir`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
