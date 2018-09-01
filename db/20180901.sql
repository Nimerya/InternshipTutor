-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Sep 01, 2018 at 04:10 PM
-- Server version: 5.6.35
-- PHP Version: 7.1.8

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
  `fiscal_code` varchar(255) NOT NULL,
  `vat_number` varchar(255) NOT NULL,
  `attorney` varchar(255) NOT NULL,
  `jurisdiction` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `agreement` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `name`, `address`, `fiscal_code`, `vat_number`, `attorney`, `jurisdiction`, `active`, `agreement`) VALUES
(25, 'Company1', 'Company1_Address', 'Company1_FiscalCode', 'Company1_VatNumber', 'Company1_Attorney', 'Company1_Jurisdiction', 0, NULL),
(26, 'Company2', 'Company2_Address', 'Company2_FiscalCode', 'Company2_VatNumber', 'Company2_Attorney\r\n', 'Company2_Jurisdiction', 0, NULL),
(27, 'Company3', 'Company3_Address', 'Company3_FiscalCode', 'Company3_VatNumber', 'Company3_Attorney\r\n', 'Company3_Jurisdiction', 0, NULL),
(28, 'Company4', 'Company4_Address', 'Company4_FiscalCode', 'Company4_VatNumber', 'Company4_Attorney\r\n', 'Company4_Jurisdiction', 0, NULL),
(29, 'Company5', 'Company5_Address', 'Company5_FiscalCode', 'Company5_VatNumber', 'Company5_Attorney\r\n', 'Company5_Jurisdiction', 0, NULL),
(30, 'Company6', 'Company6_Address', 'Company6_FiscalCode', 'Company6_VatNumber', 'Company6_Attorney\r\n', 'Company6_Jurisdiction', 0, NULL),
(31, 'Company7', 'Company7_Address', 'Company7_FiscalCode', 'Company7_VatNumber', 'Company7_Attorney\r\n', 'Company7_Jurisdiction', 0, NULL),
(32, 'Company8', 'Company8_Address', 'Company8_FiscalCode', 'Company8_VatNumber', 'Company8_Attorney\r\n', 'Company8_Jurisdiction', 0, NULL),
(33, 'Company9', 'Company9_Address', 'Company9_FiscalCode', 'Company9_VatNumber', 'Company9_Attorney\r\n', 'Company9_Jurisdiction', 0, NULL),
(34, 'Company10', 'Company10_Address', 'Company10_FiscalCode', 'Company10_VatNumber', 'Company10_Attorney\r\n', 'Company10_Jurisdiction', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `degree`
--

CREATE TABLE `degree` (
  `id` int(10) UNSIGNED NOT NULL,
  `department_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `class` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `degree`
--

INSERT INTO `degree` (`id`, `department_id`, `name`, `class`) VALUES
(32, 35, 'Degree1', 'Degree1_class'),
(33, 35, 'Degree2', 'Degree2_class'),
(34, 35, 'Degree3', 'Degree3_class'),
(35, 36, 'Degree4', 'Degree4_class'),
(36, 36, 'Degree5', 'Degree5_class'),
(37, 37, 'Degree6', 'Degree6_class'),
(38, 38, 'Degree7', 'Degree7_class'),
(39, 38, 'Degree8', 'Degree8_class'),
(40, 39, 'Degree9', 'Degree9_class'),
(41, 39, 'Degree10', 'Degree10_class');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `description_it_it` varchar(255) DEFAULT NULL,
  `description_en_gb` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `name`, `description_it_it`, `description_en_gb`) VALUES
(35, 'Department1\r\n', 'Department1_DescriptionItIt', 'Department1_DescriptionEnGb'),
(36, 'Department2\r\n', 'Department2_DescriptionItIt', 'Department2_DescriptionEnGb'),
(37, 'Department3\r\n', 'Department3_DescriptionItIt', 'Department3_DescriptionEnGb'),
(38, 'Department4', 'Department4_DescriptionItIt', 'Department4_DescriptionEnGb'),
(39, 'Department5', 'Department5_DescriptionItIt', 'Department5_DescriptionEnGb');

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
  `remote` tinyint(1) NOT NULL DEFAULT '0',
  `schedule` text,
  `length` int(11) NOT NULL,
  `mode_it_it` varchar(255) DEFAULT NULL,
  `mode_en_gb` varchar(255) NOT NULL,
  `goals_it_it` text,
  `goals_en_gb` varchar(45) NOT NULL,
  `refund` tinyint(1) NOT NULL DEFAULT '0',
  `details_it_it` text,
  `details_en_gb` text NOT NULL,
  `facilitations` text,
  `company_id` int(10) UNSIGNED NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE `professor` (
  `id` int(10) UNSIGNED NOT NULL,
  `department_id` int(10) UNSIGNED NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `professor`
--

INSERT INTO `professor` (`id`, `department_id`, `first_name`, `last_name`, `email`) VALUES
(18, 35, 'Professor1', 'Professor1', 'Professor1@Professor1.it'),
(19, 35, 'Professor2', 'Professor2', 'Professor2@Professor2.it'),
(20, 37, 'Professor3', 'Professor3', 'Professor3@Professor3.it'),
(21, 38, 'Professor4', 'Professor4', 'Professor4@Professor4.it'),
(22, 39, 'Professor5', 'Professor5', 'Professor5@Professor5.it'),
(23, 36, 'Professor6', 'Professor6', 'Professor6@Professor6.it'),
(24, 39, 'Professor7', 'Professor7', 'Professor7@Professor7.it'),
(25, 38, 'Professor8', 'Professor8', 'Professor8@Professor8.it'),
(26, 39, 'Professor9', 'Professor9', 'Professor9@Professor9.it'),
(27, 37, 'Professor10', 'Professor10', 'Professor10@Professor10.it');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'STUDENT'),
(3, 'COMPANY');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(10) UNSIGNED NOT NULL,
  `degree_id` int(10) UNSIGNED NOT NULL,
  `birthday` datetime NOT NULL,
  `matriculation_number` varchar(255) NOT NULL,
  `birthplace_city` varchar(255) NOT NULL,
  `birthplace_province` varchar(255) NOT NULL,
  `birthplace_state` varchar(255) NOT NULL,
  `residence_city` varchar(255) NOT NULL,
  `residence_province` varchar(255) NOT NULL,
  `residence_state` varchar(255) NOT NULL,
  `residence_address` varchar(255) NOT NULL,
  `fiscal_code` varchar(255) NOT NULL,
  `handicap` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `degree_id`, `birthday`, `matriculation_number`, `birthplace_city`, `birthplace_province`, `birthplace_state`, `residence_city`, `residence_province`, `residence_state`, `residence_address`, `fiscal_code`, `handicap`) VALUES
(18, 32, '1993-04-07 00:00:00', 'Student1_MatriculationNumber', 'Student1_BirthplaceCity', 'Student1_BirthplaceProvince', 'Student1_BirthplaceState', 'Student1_ResidenceCity', 'Student1_ResidentProvince', 'Student1_ResidenceState', 'Student1_ResidentAddress', 'Student1_FiscalCode', 1),
(19, 33, '1994-06-07 00:00:00', 'Student2_MatriculationNumber', 'Student2_BirthplaceCity', 'Student2_BirthplaceProvince', 'Student2_BirthplaceState', 'Student2_ResidenceCity', 'Student2_ResidentProvince', 'Student2_ResidenceState', 'Student2_ResidentAddress', 'Student2_FiscalCode', 0),
(20, 34, '1993-07-07 00:00:00', 'Student3_MatriculationNumber', 'Student3_BirthplaceCity', 'Student3_BirthplaceProvince', 'Student3_BirthplaceState', 'Student3_ResidenceCity', 'Student3_ResidentProvince', 'Student3_ResidenceState', 'Student3_ResidentAddress', 'Student3_FiscalCode', 0),
(21, 35, '1994-01-27 00:00:00', 'Student4_MatriculationNumber', 'Student4_BirthplaceCity', 'Student4_BirthplaceProvince', 'Student4_BirthplaceState', 'Student4_ResidenceCity', 'Student4_ResidentProvince', 'Student4_ResidenceState', 'Student4_ResidentAddress', 'Student4_FiscalCode', 0),
(22, 36, '1994-12-06 00:00:00', 'Student5_MatriculationNumber', 'Student5_BirthplaceCity', 'Student5_BirthplaceProvince', 'Student5_BirthplaceState', 'Student5_ResidenceCity', 'Student5_ResidentProvince', 'Student5_ResidenceState', 'Student5_ResidentAddress', 'Student5_FiscalCode', 0),
(23, 37, '1993-02-11 00:00:00', 'Student6_MatriculationNumber', 'Student6_BirthplaceCity', 'Student6_BirthplaceProvince', 'Student6_BirthplaceState', 'Student6_ResidenceCity', 'Student6_ResidentProvince', 'Student6_ResidenceState', 'Student6_ResidentAddress', 'Student6_FiscalCode', 0),
(24, 38, '1992-04-07 00:00:00', 'Student7_MatriculationNumber', 'Student7_BirthplaceCity', 'Student7_BirthplaceProvince', 'Student7_BirthplaceState', 'Student7_ResidenceCity', 'Student7_ResidentProvince', 'Student7_ResidenceState', 'Student7_ResidentAddress', 'Student7_FiscalCode', 0),
(25, 39, '1997-04-07 00:00:00', 'Student8_MatriculationNumber', 'Student8_BirthplaceCity', 'Student8_BirthplaceProvince', 'Student8_BirthplaceState', 'Student8_ResidenceCity', 'Student8_ResidentProvince', 'Student8_ResidenceState', 'Student8_ResidentAddress', 'Student8_FiscalCode', 0),
(26, 40, '1993-08-17 00:00:00', 'Student9_MatriculationNumber', 'Student9_BirthplaceCity', 'Student9_BirthplaceProvince', 'Student9_BirthplaceState', 'Student9_ResidenceCity', 'Student9_ResidentProvince', 'Student9_ResidenceState', 'Student9_ResidentAddress', 'Student9_FiscalCode', 0),
(27, 41, '1994-05-21 00:00:00', 'Student10_MatriculationNumber', 'Student10_BirthplaceCity', 'Student10_BirthplaceProvince', 'Student10_BirthplaceState', 'Student10_ResidenceCity', 'Student10_ResidentProvince', 'Student10_ResidenceState', 'Student10_ResidentAddress', 'Student10_FiscalCode', 1),
(28, 32, '1992-04-25 00:00:00', 'Student11_MatriculationNumber', 'Student11_BirthplaceCity', 'Student11_BirthplaceProvince', 'Student11_BirthplaceState', 'Student11_ResidenceCity', 'Student11_ResidentProvince', 'Student11_ResidenceState', 'Student11_ResidentAddress', 'Student11_FiscalCode', 0),
(29, 32, '1995-04-29 00:00:00', 'Student12_MatriculationNumber', 'Student12_BirthplaceCity', 'Student12_BirthplaceProvince', 'Student12_BirthplaceState', 'Student12_ResidenceCity', 'Student12_ResidentProvince', 'Student12_ResidenceState', 'Student12_ResidentAddress', 'Student12_FiscalCode', 0),
(30, 37, '1995-03-02 00:00:00', 'Student13_MatriculationNumber', 'Student13_BirthplaceCity', 'Student13_BirthplaceProvince', 'Student13_BirthplaceState', 'Student13_ResidenceCity', 'Student13_ResidentProvince', 'Student13_ResidenceState', 'Student13_ResidentAddress', 'Student13_FiscalCode', 0),
(31, 38, '1995-11-11 00:00:00', 'Student14_MatriculationNumber', 'Student14_BirthplaceCity', 'Student14_BirthplaceProvince', 'Student14_BirthplaceState', 'Student14_ResidenceCity', 'Student14_ResidentProvince', 'Student14_ResidenceState', 'Student14_ResidentAddress', 'Student14_FiscalCode', 0),
(32, 34, '1994-09-09 00:00:00', 'Student15_MatriculationNumber', 'Student15_BirthplaceCity', 'Student15_BirthplaceProvince', 'Student15_BirthplaceState', 'Student15_ResidenceCity', 'Student15_ResidentProvince', 'Student15_ResidenceState', 'Student15_ResidentAddress', 'Student15_FiscalCode', 0);

-- --------------------------------------------------------

--
-- Table structure for table `student_internship`
--

CREATE TABLE `student_internship` (
  `id` int(10) UNSIGNED NOT NULL,
  `student_id` int(10) UNSIGNED NOT NULL,
  `internship_id` int(10) UNSIGNED NOT NULL,
  `professor_id` int(10) UNSIGNED NOT NULL,
  `cfu` int(11) NOT NULL,
  `review` int(10) UNSIGNED DEFAULT NULL,
  `accepted` tinyint(1) NOT NULL DEFAULT '0',
  `rejected` tinyint(1) NOT NULL DEFAULT '0',
  `completed` tinyint(1) NOT NULL DEFAULT '0',
  `training_project` varchar(255) DEFAULT NULL,
  `final_report` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `role_id` int(10) UNSIGNED NOT NULL,
  `company_id` int(10) UNSIGNED DEFAULT NULL,
  `student_id` int(10) UNSIGNED DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL DEFAULT 'default.jpg',
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `role_id`, `company_id`, `student_id`, `email`, `password`, `image`, `first_name`, `last_name`, `phone_number`) VALUES
(46, 1, NULL, NULL, 'admin@admin.it\r\n', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Administrator', 'Administrator', '1234567890'),
(47, 3, 25, NULL, 'company1@company1.it\r\n', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company1_Tutor', 'Company1_Tutor', '1234567890'),
(48, 3, 26, NULL, 'company2@company2.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company2_Tutor', 'Company2_Tutor', '1234567890'),
(49, 3, 27, NULL, 'company3@company3.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company3_Tutor', 'Company3_Tutor', '1234567890'),
(50, 3, 28, NULL, 'company4@company4.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company4_Tutor', 'Company4_Tutor', '1234567890'),
(51, 3, 29, NULL, 'company5@company5.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company5_Tutor', 'Company5_Tutor', '1234567890'),
(52, 3, 30, NULL, 'company6@company6.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company6_Tutor', 'Company6_Tutor', '1234567890'),
(53, 3, 31, NULL, 'company7@company7.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company7_Tutor', 'Company7_Tutor', '1234567890'),
(54, 3, 32, NULL, 'company8@company8.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company8_Tutor', 'Company8_Tutor', '1234567890'),
(55, 3, 33, NULL, 'company9@company9.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company9_Tutor', 'Company9_Tutor', '1234567890'),
(56, 3, 34, NULL, 'company10@company10.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Company10_Tutor', 'Company10_Tutor', '1234567890'),
(57, 2, NULL, 18, 'student1@student1.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student1', 'Student1', '1234567890'),
(58, 2, NULL, 19, 'student2@student2.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student2', 'Student2', '1234567890'),
(59, 2, NULL, 20, 'student3@student3.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student3', 'Student3', '1234567890'),
(60, 2, NULL, 21, 'student4@student4.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student4', 'Student4', '1234567890'),
(61, 2, NULL, 22, 'student5@student5.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student5', 'Student5', '1234567890'),
(62, 2, NULL, 23, 'student6@student6.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student6', 'Student6', '1234567890'),
(63, 2, NULL, 24, 'student7@student7.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student7', 'Student7', '1234567890'),
(64, 2, NULL, 25, 'student8@student8.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student8', 'Student8', '1234567890'),
(65, 2, NULL, 26, 'student9@student9.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student9', 'Student9', '1234567890'),
(66, 2, NULL, 27, 'student10@student10.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student10', 'Student10', '1234567890'),
(67, 2, NULL, 28, 'student11@student11.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student11', 'Student11', '1234567890'),
(68, 2, NULL, 29, 'student12@student12.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student12', 'Student12', '1234567890'),
(69, 2, NULL, 30, 'student13@student13.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student13', 'Student13', '1234567890'),
(70, 2, NULL, 31, 'student14@student14.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student14', 'Student14', '1234567890'),
(71, 2, NULL, 32, 'student15@student15.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student15', 'Student15', '1234567890');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name_UNIQUE` (`name`),
  ADD UNIQUE KEY `fiscal_code` (`fiscal_code`);

--
-- Indexes for table `degree`
--
ALTER TABLE `degree`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `class_UNIQUE` (`class`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_degree_department1_idx` (`department_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

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
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_professor_department1_idx` (`department_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `fiscal_code` (`fiscal_code`),
  ADD UNIQUE KEY `matriculation_number` (`matriculation_number`),
  ADD KEY `fk_student_degree1_idx` (`degree_id`);

--
-- Indexes for table `student_internship`
--
ALTER TABLE `student_internship`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_student_has_intership_intership1_idx` (`internship_id`),
  ADD KEY `fk_student_has_intership_student1_idx` (`student_id`),
  ADD KEY `fk__professor1_idx` (`professor_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `fk_user_company_idx` (`company_id`),
  ADD KEY `fk_user_student1_idx` (`student_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `degree`
--
ALTER TABLE `degree`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `internship`
--
ALTER TABLE `internship`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `professor`
--
ALTER TABLE `professor`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `student_internship`
--
ALTER TABLE `student_internship`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `degree`
--
ALTER TABLE `degree`
  ADD CONSTRAINT `FKoykfh783nk2191uhuqswnaq6v` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `internship`
--
ALTER TABLE `internship`
  ADD CONSTRAINT `FK5m7aghwpiy1nsiwudjowy0lhb` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `professor`
--
ALTER TABLE `professor`
  ADD CONSTRAINT `FKbxh9gr7acx9qalq9jjcj4j9tr` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKl942dniuelpv8jljxgsw4r43b` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `student_internship`
--
ALTER TABLE `student_internship`
  ADD CONSTRAINT `FK31jd0d2govrgoth0e2sw14v0q` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `FKctfglfj8aergqfbbm4g4j4aqk` FOREIGN KEY (`internship_id`) REFERENCES `internship` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `FKool3erlg1kwib96wo8bhb23pl` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK4dcom0y59k6tvg3yrguh8wjla` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKd806v2m4dlsi4k8dh35xo03i6` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
