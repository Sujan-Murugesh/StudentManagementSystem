-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 18, 2020 at 09:11 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `course_agri`
--

DROP TABLE IF EXISTS `course_agri`;
CREATE TABLE IF NOT EXISTS `course_agri` (
  `Session_id` int(11) NOT NULL,
  `Session_name` varchar(200) NOT NULL,
  `Session_theory` int(11) NOT NULL,
  `Session_practical` int(11) NOT NULL,
  `Session_resource` longblob NOT NULL,
  `remarks` varchar(200) NOT NULL,
  `res_title` varchar(200) NOT NULL,
  PRIMARY KEY (`Session_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course_b_tech`
--

DROP TABLE IF EXISTS `course_b_tech`;
CREATE TABLE IF NOT EXISTS `course_b_tech` (
  `Session_Id` int(11) NOT NULL,
  `Session_name` varchar(200) NOT NULL,
  `Session_theory` int(11) NOT NULL,
  `Session_practical` int(11) NOT NULL,
  `Session_resource` longblob NOT NULL,
  `remarks` varchar(200) NOT NULL,
  `res_title` varchar(200) NOT NULL,
  PRIMARY KEY (`Session_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course_s_tech`
--

DROP TABLE IF EXISTS `course_s_tech`;
CREATE TABLE IF NOT EXISTS `course_s_tech` (
  `Session_id` int(11) NOT NULL,
  `Session_name` varchar(200) NOT NULL,
  `Session_theory` int(11) NOT NULL,
  `Session_practical` int(11) NOT NULL,
  `Session_resource` longblob NOT NULL,
  `remarks` varchar(200) NOT NULL,
  `res_title` varchar(200) NOT NULL,
  PRIMARY KEY (`Session_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studentdetails`
--

DROP TABLE IF EXISTS `studentdetails`;
CREATE TABLE IF NOT EXISTS `studentdetails` (
  `St_Index` varchar(10) NOT NULL,
  `St_Name` varchar(100) NOT NULL,
  `St_Age` int(2) NOT NULL,
  `St_Address` varchar(100) NOT NULL,
  `St_Email` varchar(100) NOT NULL,
  `St_Tel` varchar(11) NOT NULL,
  `St_Batch` varchar(10) NOT NULL,
  `S_Tech` varchar(3) NOT NULL,
  `B_Tech` varchar(3) NOT NULL,
  `Agriculture` varchar(3) NOT NULL,
  `St_Emgy_tel` varchar(11) NOT NULL,
  `St_Parent` varchar(100) NOT NULL,
  `Addmission` varchar(3) NOT NULL,
  `St_image` longblob NOT NULL,
  PRIMARY KEY (`St_Index`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teachersinformation`
--

DROP TABLE IF EXISTS `teachersinformation`;
CREATE TABLE IF NOT EXISTS `teachersinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `DateOfBirth` varchar(10) NOT NULL,
  `Subject` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Telephone` varchar(12) NOT NULL,
  `Qualification` varchar(225) NOT NULL,
  `DateOfJoin` varchar(10) NOT NULL,
  `Address` varchar(225) NOT NULL,
  `Timage` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `utype` varchar(225) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `utype`) VALUES
(1, 'SUJAN', '202CB962AC59075B964B07152D234B70', 'Admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
