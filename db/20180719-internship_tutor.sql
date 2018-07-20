-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jul 19, 2018 at 04:43 PM
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
  `fiscal_code` varchar(255) NOT NULL,
  `vat_number` varchar(255) NOT NULL,
  `attorney` varchar(255) NOT NULL,
  `jurisdiction` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `name`, `address`, `fiscal_code`, `vat_number`, `attorney`, `jurisdiction`, `active`) VALUES
(13, 'a', 'a', 'a', 'a', 'a', 'a', 0),
(14, 'b', 'b', 'b', 'b', 'b', 'b', 0),
(15, 'c', 'c', 'c', 'c', 'c', 'c', 0),
(16, 'd', 'd', 'd', 'd', 'd', 'd', 0),
(17, 'e', 'e', 'e', 'e', 'e', 'e', 0),
(18, 'f', 'f', 'f', 'f', 'f', 'f', 0),
(19, 'g', 'g', 'g', 'g', 'g', 'g', 0);

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
(22, 25, 'deg1', 'deg1'),
(23, 26, 'deg2', 'deg2'),
(24, 26, 'deg3', 'deg3'),
(25, 27, 'deg4', 'deg4'),
(26, 25, 'deg5', 'deg5');

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
(25, 'd1', 'd1', 'd1'),
(26, 'd2', 'd2', 'd2'),
(27, 'd3', 'd3', 'd3'),
(28, 'd4', 'd4', 'd4'),
(29, 'd5', 'd5', 'd5');

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

--
-- Dumping data for table `internship`
--

INSERT INTO `internship` (`id`, `title`, `address`, `city`, `province`, `state`, `remote`, `schedule`, `length`, `mode_it_it`, `mode_en_gb`, `goals_it_it`, `goals_en_gb`, `refund`, `details_it_it`, `details_en_gb`, `facilitations`, `company_id`, `active`) VALUES
(13, 'i1', 'i1', 'i1', 'i1', 'i1', 0, NULL, 3, NULL, 'i1', NULL, 'i1', 0, NULL, 'i1', NULL, 13, 0),
(14, 'i2', 'i2', 'i2', 'i2', 'i2', 0, NULL, 4, NULL, 'i2', NULL, 'i2', 0, NULL, 'i2', NULL, 14, 0),
(15, 'i3', 'i3', 'i3', 'i3', 'i3', 0, NULL, 5, NULL, 'i3', NULL, 'i3', 0, NULL, 'i3', NULL, 13, 0),
(16, 'i4', 'i4', 'i4', 'i4', 'i4', 0, NULL, 4, NULL, 'i4', NULL, 'i4', 0, NULL, 'i4', NULL, 16, 0),
(17, 'i5', 'i5', 'i5', 'i5', 'i5', 0, NULL, 2, NULL, 'i5', NULL, 'i5', 0, NULL, 'i5', NULL, 13, 0),
(18, 'qqq', 'q', 'q', 'q', 'q', 0, 'q', 7, 'q', 'q', 'q', 'q', 0, 'q', 'q', 'q', 17, 0),
(19, 'hhhh', 'h', 'h', 'h', 'h', 0, 'h', 8, 'j', 'j', 'j', 'j', 0, 'j', 'j', 'l', 17, 1);

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
(13, 25, 'p1', 'p1', 'p1@p1.it'),
(14, 26, 'p2', 'p2', 'p2@p2.it'),
(15, 27, 'p3', 'p3', 'p3@p3.it'),
(16, 25, 'p4', 'p4', 'p4@p4.it'),
(17, 26, 'p5', 'p5', 'p5@p5.it');

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
(13, 22, '2018-07-13 00:00:00', '12345', 's1', 's1', 's1', 's1', 's1', 's1', 's1', 's1', 0),
(14, 23, '2018-07-27 00:00:00', 's2', 's2', 's2', 's2\r\n', 's2', 's2', 's2', 's2', 's2', 0),
(15, 24, '2018-07-30 00:00:00', 's3', 's3', 's3', 's3', 's3', 's3', 's3', 's3', 's3', 0),
(16, 22, '2018-07-02 00:00:00', 's4', 's4', 's4', 's4', 's4', 's4', 's4', 's4', 's4', 0),
(17, 26, '2018-07-28 00:00:00', 's5', 's5', 's5', 's5', 's5', 's5', 's5', 's5', 's5', 0);

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
  `completed` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `student_internship`
--

INSERT INTO `student_internship` (`id`, `student_id`, `internship_id`, `professor_id`, `cfu`, `review`, `accepted`, `completed`) VALUES
(1, 13, 14, 13, 4, 3, 1, 0),
(2, 14, 13, 14, 5, 1, 1, 0),
(3, 15, 15, 15, 3, 0, 1, 0),
(4, 16, 16, 13, 6, 2, 1, 0),
(5, 17, 17, 13, 7, 5, 1, 0),
(6, 16, 16, 15, 1, 3, 1, 0),
(7, 17, 17, 14, 5, NULL, 0, 0),
(8, 17, 15, 16, 5, NULL, 1, 1);

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
(11, 2, NULL, 13, 's1@s1.it', 's1', 'default.jpg', 's1', 's1', 's1'),
(12, 2, NULL, 14, 's2@s2.it', 's2', 'default.jpg', 's2', 's2', 's2'),
(13, 2, NULL, 15, 's3@s3.it', 's3', 'default.jpg', 's3', 's3', 's3'),
(14, 2, NULL, 16, 's4', 's4', 'default.jpg', 's4', 's4', 's4'),
(15, 2, NULL, 17, 's5', 's5', 'default.jpg', 's5', 's5', 's5'),
(16, 3, 13, NULL, 'a@a.it', 'a', 'default.jpg', 'a', 'a', 'a'),
(17, 3, 14, NULL, 'b@b.it', 'b', 'default.jpg', 'b', 'b', 'b'),
(18, 3, 15, NULL, 'c@c.it', 'c', 'default.jpg', 'c', 'c', 'c'),
(19, 3, 16, NULL, 'd@d.it', 'd', 'default.jpg', 'd', 'd', 'd'),
(20, 3, 17, NULL, 'e@e.it', 'e', 'default.jpg', 'e', 'e', 'e'),
(21, 3, 18, NULL, 'f@f.it', 'f', 'default.jpg', 'f', 'f', 'f'),
(22, 3, 19, NULL, 'g@g.it', 'g', 'default.jpg', 'g', 'g', 'g');

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
  ADD UNIQUE KEY `password` (`password`),
  ADD UNIQUE KEY `phone_number` (`phone_number`),
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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `degree`
--
ALTER TABLE `degree`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `internship`
--
ALTER TABLE `internship`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `professor`
--
ALTER TABLE `professor`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `student_internship`
--
ALTER TABLE `student_internship`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

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
