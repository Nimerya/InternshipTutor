-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: May 10, 2018 at 02:10 PM
-- Server version: 5.6.38
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `internship_tutor`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `fiscal_code` varchar(255) DEFAULT NULL,
  `vat_number` varchar(255) DEFAULT NULL,
  `attorney` varchar(255) NOT NULL,
  `jurisdiction` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `degree`
--

CREATE TABLE `degree` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `department_id` int(10) UNSIGNED NOT NULL,
  `class` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `description_it-IT` varchar(255) DEFAULT NULL,
  `description_en-GB` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `internship`
--

CREATE TABLE `internship` (
  `id` int(10) UNSIGNED NOT NULL,
  `title` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `remote` tinyint(4) DEFAULT NULL,
  `schedule` text,
  `length` int(11) NOT NULL,
  `mode_it-IT` varchar(255) NOT NULL,
  `mode_en-GB` varchar(255) DEFAULT NULL,
  `goals_it-IT` text,
  `goals_en-GB` varchar(45) DEFAULT NULL,
  `refund` tinyint(4) DEFAULT NULL,
  `details_it-IT` text,
  `details_en-GB` text,
  `facilitations` text,
  `company_id` int(10) UNSIGNED NOT NULL,
  `active` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE `professor` (
  `id` int(10) UNSIGNED NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `department_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(10) UNSIGNED NOT NULL,
  `birthday` datetime NOT NULL,
  `matriculation_number` varchar(255) NOT NULL,
  `birthplace_city` varchar(255) NOT NULL,
  `birthplace_province` varchar(255) NOT NULL,
  `birthplace_state` varchar(255) NOT NULL,
  `residence_adress` varchar(255) NOT NULL,
  `residence_city` varchar(255) NOT NULL,
  `residence_province` varchar(255) NOT NULL,
  `residence_state` varchar(255) NOT NULL,
  `fiscal_code` varchar(255) NOT NULL,
  `handicap` tinyint(4) NOT NULL,
  `degree_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student_internship`
--

CREATE TABLE `student_internship` (
  `student_id` int(10) UNSIGNED NOT NULL,
  `intership_id` int(10) UNSIGNED NOT NULL,
  `cfu` int(11) DEFAULT NULL,
  `professor_id` int(10) UNSIGNED NOT NULL,
  `review` int(10) UNSIGNED DEFAULT NULL,
  `accepted` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `company_id` int(10) UNSIGNED DEFAULT NULL,
  `student_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name_UNIQUE` (`name`);

--
-- Indexes for table `degree`
--
ALTER TABLE `degree`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `class_UNIQUE` (`class`),
  ADD KEY `fk_degree_department1_idx` (`department_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `internship`
--
ALTER TABLE `internship`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_intership_company1_idx` (`company_id`);

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_professor_department1_idx` (`department_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_student_degree1_idx` (`degree_id`);

--
-- Indexes for table `student_internship`
--
ALTER TABLE `student_internship`
  ADD PRIMARY KEY (`intership_id`,`professor_id`,`student_id`),
  ADD KEY `fk_student_has_intership_intership1_idx` (`intership_id`),
  ADD KEY `fk_student_has_intership_student1_idx` (`student_id`),
  ADD KEY `fk__professor1_idx` (`professor_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `fk_user_company_idx` (`company_id`),
  ADD KEY `fk_user_student1_idx` (`student_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `degree`
--
ALTER TABLE `degree`
  ADD CONSTRAINT `fk_degree_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `internship`
--
ALTER TABLE `internship`
  ADD CONSTRAINT `fk_intership_company1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `professor`
--
ALTER TABLE `professor`
  ADD CONSTRAINT `fk_professor_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_student_degree1` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `student_internship`
--
ALTER TABLE `student_internship`
  ADD CONSTRAINT `fk__professor1` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_student_has_intership_intership1` FOREIGN KEY (`intership_id`) REFERENCES `internship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_student_has_intership_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
