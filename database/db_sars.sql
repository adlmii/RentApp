-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2024 at 05:06 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sars`
--

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id` int(11) NOT NULL,
  `katalog` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id`, `katalog`, `nama`, `harga`, `stok`) VALUES
(1, 'iPhone', 'iPhone 8 Plus', 90000, 2),
(2, 'iPhone', 'iPhone X', 110000, 5),
(3, 'iPhone', 'iPhone XR', 120000, 6),
(4, 'iPhone', 'iPhone XS', 130000, 4),
(5, 'iPhone', 'iPhone XS Max', 140000, 3),
(6, 'iPhone', 'iPhone 11', 150000, 5),
(7, 'iPhone', 'iPhone 12', 180000, 4),
(8, 'iPhone', 'iPhone 12 Pro', 210000, 2),
(9, 'iPhone', 'iPhone 13', 250000, 3),
(10, 'iPhone', 'iPhone SE (2020)', 100000, 7),
(11, 'Android', 'Samsung Galaxy S22U', 230000, 2),
(12, 'Android', 'Samsung Galaxy S23U', 250000, 3),
(13, 'Android', 'Samsung Galaxy S24U', 270000, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(64) NOT NULL,
  `email` varchar(30) NOT NULL,
  `telepon` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `nama`, `alamat`, `email`, `telepon`) VALUES
('admin', 'rahasia', 'sars', 'jl. sars no. 18', 'sars@gmail.com', '08123456781'),
('sars1', 'rahasia', 'sahrur ganteng', 'jln. in aja dulu', 'sahrur@gmail.com', '081234567812'),
('sars2', 'rahasia', 'aidil', 'jl. pesawat', 'aidil@gmail.com', '088888123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nama` (`nama`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `nama` (`nama`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `telepon` (`telepon`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
