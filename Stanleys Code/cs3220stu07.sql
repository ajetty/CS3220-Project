-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Aug 09, 2018 at 09:23 PM
-- Server version: 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 7.0.30-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs3220stu07`
--

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id`, `fileName`, `filePath`, `parentId`, `date`) VALUES
(41, '2018-08-09 13:46:51.0_cat.jpg', '/var/lib/tomcat8/webapps/cs3220stu07/history', 176, '2018-08-09 13:46:51'),
(42, '2018-08-09 13:46:55.0_cat.jpg', '/var/lib/tomcat8/webapps/cs3220stu07/history', 176, '2018-08-09 13:46:55'),
(43, '2018-08-09 13:46:58.0_cat.jpg', '/var/lib/tomcat8/webapps/cs3220stu07/history', 176, '2018-08-09 13:46:58'),
(44, '2018-08-09 13:47:02.0_cat.jpg', '/var/lib/tomcat8/webapps/cs3220stu07/history', 176, '2018-08-09 13:47:02'),
(45, '2018-08-09 13:51:53.0_hello.html', '/var/lib/tomcat8/webapps/cs3220stu07/history', 183, '2018-08-09 13:51:53'),
(46, '2018-08-09 13:52:09.0__config.yml', '/var/lib/tomcat8/webapps/cs3220stu07/history', 184, '2018-08-09 13:52:09'),
(47, '2018-08-09 13:52:59.0_monkey.jpg', '/var/lib/tomcat8/webapps/cs3220stu07/history', 186, '2018-08-09 13:52:59'),
(48, '2018-08-09 13:53:07.0_monkey.jpg', '/var/lib/tomcat8/webapps/cs3220stu07/history', 186, '2018-08-09 13:53:07');

-- --------------------------------------------------------

--
-- Table structure for table `uploads`
--

CREATE TABLE `uploads` (
  `id` int(11) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uploads`
--

INSERT INTO `uploads` (`id`, `fileName`, `filePath`, `date`, `parentId`) VALUES
(186, 'monkey.jpg', '/var/lib/tomcat8/webapps/cs3220stu07/images', '2018-08-09 20:53:11', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uploads`
--
ALTER TABLE `uploads`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `uploads`
--
ALTER TABLE `uploads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=187;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
