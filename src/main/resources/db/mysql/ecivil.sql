-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2014 at 09:00 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ecivil`
--

CREATE DATABASE IF NOT EXISTS ecivil;
GRANT ALL PRIVILEGES ON ecivil.* TO pc@localhost IDENTIFIED BY 'pc';

USE ecivil;

-- --------------------------------------------------------

--
-- Table structure for table `accidents`
--

CREATE TABLE IF NOT EXISTS `accidents` (
  `EVENT_ID` int(11) NOT NULL,
  `ACCIDENT_TYPE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accidents`
--

INSERT INTO `accidents` (`EVENT_ID`, `ACCIDENT_TYPE`) VALUES
(1, 'Πυρκαγιά'),
(2, 'Other'),
(3, 'BadMedications'),
(4, 'Διάρρηξη στο σπίτι'),
(11, 'Άτύχημα'),
(13, 'Πυρκαγιά');

-- --------------------------------------------------------

--
-- Table structure for table `actions`
--

CREATE TABLE IF NOT EXISTS `actions` (
  `EVENT_ID` int(11) NOT NULL,
  `EMERGENCY_ID` int(11) DEFAULT NULL,
  `TOOL` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`),
  KEY `FK_at2ea4ec43epfkjqlo339gn8b` (`EMERGENCY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `actions`
--

INSERT INTO `actions` (`EVENT_ID`, `EMERGENCY_ID`, `TOOL`) VALUES
(7, 4, 'medical set'),
(8, 3, ''),
(9, 4, ''),
(10, 5, 'dsada');

-- --------------------------------------------------------

--
-- Table structure for table `dangers`
--

CREATE TABLE IF NOT EXISTS `dangers` (
  `EVENT_ID` int(11) NOT NULL,
  `DANGER_TYPE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dangers`
--

INSERT INTO `dangers` (`EVENT_ID`, `DANGER_TYPE`) VALUES
(5, 'Αποκλεισμένος δρόμος'),
(6, 'Κρημνός'),
(12, 'Κίνδυνος');

-- --------------------------------------------------------

--
-- Table structure for table `emergencies`
--

CREATE TABLE IF NOT EXISTS `emergencies` (
  `EVENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `emergencies`
--

INSERT INTO `emergencies` (`EVENT_ID`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(11),
(12),
(13);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `EVENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE_TIME` datetime NOT NULL,
  `PLACE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEXT_DESCRIPTION` longtext COLLATE utf8_unicode_ci,
  `OWNER_ID` int(11) DEFAULT NULL,
  `CERTIFICATION` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `FRESHNESS` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `LOCATION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`),
  KEY `FK_934urau4inqfe6cns7tohtbrg` (`OWNER_ID`),
  KEY `FK_h9n9u5ghk265ngokat1vboxyi` (`LOCATION_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`EVENT_ID`, `CREATED_DATE_TIME`, `PLACE`, `TEXT_DESCRIPTION`, `OWNER_ID`, `CERTIFICATION`, `FRESHNESS`, `LOCATION_ID`) VALUES
(1, '2014-05-17 17:01:31', 'testopl', 'test', 1, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(2, '2014-05-17 18:03:23', 'moscow', 'New test acc', 5, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(3, '2014-05-17 18:21:45', 'mosdsf', 'hfasdfasdfuuuu', 5, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(4, '2014-05-17 19:56:27', 'σδσαδσαδ', 'Νεο ατυχημα', 9, 'Ανεπιβεβαιωμένο', 'Eνεργό', 6),
(5, '2014-05-17 20:18:50', 'iraklio', 'Test daangeeeerghjgj', 9, 'Eπιβεβαιωμένο', 'Eνεργό', 1),
(6, '2014-05-17 22:19:45', 'here', 'new test danger', 17, 'Ανεπιβεβαιωμένο', 'Kλειστό', 1),
(7, '2014-05-19 11:55:11', 'near you', 'I want to help', 17, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(8, '2014-05-19 12:07:03', 'moscow', 'can help', 17, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(9, '2014-05-19 17:32:36', 'moscow', 'admin is helping too', 1, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(10, '2014-05-19 22:27:19', 'Ηράκλειο', 'djsnsfg dasjldjaslj fsddfjskl', 1, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(11, '2014-05-21 11:08:40', '', 'new test accident', 9, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(12, '2014-05-21 11:09:28', '', 'Test danger', 9, 'Ανεπιβεβαιωμένο', 'Eνεργό', 1),
(13, '2014-05-23 18:26:58', '', 'accident with location', 24, 'Ανεπιβεβαιωμένο', 'Eνεργό', 5);

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE IF NOT EXISTS `locations` (
  `LOCATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LATITUDE` double DEFAULT NULL,
  `LONGITUDE` double DEFAULT NULL,
  PRIMARY KEY (`LOCATION_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`LOCATION_ID`, `LATITUDE`, `LONGITUDE`) VALUES
(1, 0, 0),
(5, 55.8611413, 37.4176317),
(6, 56, 37.833332999999996),
(7, 55.861288699999996, 37.4172462);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'INSTITUTIONS_ADMIN'),
(3, 'VOLUNTEERS_ADMIN'),
(4, 'INSTITUTION'),
(5, 'VOLUNTEER'),
(6, 'MEMBER');

-- --------------------------------------------------------

--
-- Table structure for table `specialties`
--

CREATE TABLE IF NOT EXISTS `specialties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE IF NOT EXISTS `teams` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `team_email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `team_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `team_telephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `t_type` int(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`team_id`),
  UNIQUE KEY `UK_dsqu2wx93en6lbl2bnrjy7kol` (`team_name`),
  KEY `FK_t7w6gxta3w8qm5ywhblq8npii` (`t_type`),
  KEY `FK_nrreidr57j527lat5n0pbdy` (`admin_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`team_id`, `team_address`, `team_email`, `team_name`, `team_telephone`, `t_type`, `admin_id`) VALUES
(3, 'dasdsa', 'sdasda', 'ΠΑΓΝΗ ΝΟΣΟΚΟΜΕΙΟ', '34241234', 1, 2),
(6, 'dsd', 'dsda', 'hjdkashkdfas', '3123123', 2, 9),
(8, 'dsdsadbhbv', 'sdasd', 'dasdasdasdmmm', '12312312', 1, 1),
(9, 'dasdasd', 'sdasdsd', 'new test team institutional', '341241234', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `team_type`
--

CREATE TABLE IF NOT EXISTS `team_type` (
  `tt_id` int(11) NOT NULL AUTO_INCREMENT,
  `tt_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tt_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `team_type`
--

INSERT INTO `team_type` (`tt_id`, `tt_name`) VALUES
(1, 'INSTITUTIONAL'),
(2, 'VOLUNTEERS');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `blood_group` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `google_account` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `skill` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `verified` bit(1) DEFAULT NULL,
  `CURRENT_LOCATION` int(11) DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`),
  KEY `FK_g13o1c7a6pb5lmtv75cg8e4aw` (`CURRENT_LOCATION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=27 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `address`, `blood_group`, `city`, `enabled`, `first_name`, `google_account`, `last_name`, `login`, `password`, `skill`, `telephone`, `verified`, `CURRENT_LOCATION`, `uuid`) VALUES
(1, '', NULL, 'fasdfcas', 1, 'Admin', 'fasfsd', 'dasda', 'admin', 'pass', NULL, '342523523', b'1', 5, NULL),
(2, NULL, NULL, 'dasdas', 1, 'Admininst', 'dsda', 'sdasd', 'admininst', 'pass', NULL, '3425235435', b'1', NULL, NULL),
(3, NULL, NULL, 'dsadasd', 1, 'Adminvol', NULL, 'sdsd', 'adminvol', 'pass', NULL, '3423423', b'1', NULL, NULL),
(4, NULL, NULL, 'sdasf', 1, 'Institution', NULL, 'sdsad', 'institution', 'pass', NULL, '3452365436', b'1', NULL, NULL),
(5, NULL, NULL, 'fsadf', 1, 'Member', NULL, 'dasd', 'member', 'pass', NULL, '352523', b'1', NULL, NULL),
(6, NULL, NULL, 'gasdfdf', 1, 'Volunteer', NULL, 'sdasd', 'volunteer', 'pass', NULL, '34235235', b'1', NULL, NULL),
(9, 'dasdsd', NULL, 'dsdsd', 1, 'Milan', 'google', 'Jevtic', 'mimi', 'pass', NULL, '35252366', b'0', 6, NULL),
(10, 'fdasdas', NULL, 'dasda', 1, 'sdasd', 'gasfas', 'dsadas', 'mimitest', 'pass', NULL, '3411', b'0', NULL, NULL),
(11, 'addressssss', NULL, 'dssadas', 1, 'fdassdsa', 'dasdas', 'sads', 'mimitt', 'pass', NULL, '8798797', b'0', NULL, NULL),
(12, 'dsadsad', NULL, 'dsadasdf', 1, 'dsdasd', 'lkdhjkashkfaf', 'dasdsad', 'mimimimi', 'pass', NULL, '34242342', b'0', NULL, NULL),
(16, 'hkhkh', NULL, 'hkhkh', 1, 'ikhkhk', 'bjj', 'hkhbkhb', 'newuserr', 'pass', NULL, '76896', b'0', NULL, NULL),
(17, 'dasdas', NULL, 'dasdas', 1, 'fasdas', 'dasdasfd', 'sdad', 'micko', 'pass', NULL, '786969', b'0', 7, NULL),
(18, 'dasdas', NULL, 'dasdas', 1, 'dasdasd', 'dasdasd', 'dasdasd', 'miki', 'pass', NULL, '868686', b'0', NULL, NULL),
(19, 'dsdasd', NULL, 'dasdasd', 1, 'dasdasd', 'dasdas', 'dsdasdas', 'user9', 'pass', NULL, '34234', b'0', NULL, NULL),
(22, 'dasda', NULL, 'dsada', 1, 'sada', 'dasdas', 'dasda', 'user11', 'pass', NULL, '34124314', b'0', NULL, NULL),
(23, 'khghkhk', NULL, 'dasda', 1, 'gjghkjg', 'dasdas', 'kdasdas', 'user13', 'pass', NULL, '3412331245', b'0', NULL, NULL),
(24, 'Vilisa Lacisa 27', NULL, 'Moscow', 1, 'Milan', 'jmmimmi@yahoo.com', 'Jevtic', 'milan', 'pass', NULL, '432423565443', b'1', 5, 'e63892b1-45db-4f70-a0fd-d5ea1a6a6208'),
(25, 'dasdas', NULL, 'dasda', 1, 'dasdas', 'jmmimmi@yahoo.com', 'dasda', 'user1', 'pass', NULL, '3424523535', b'1', NULL, '9dc9f5d2-3bcb-4d1f-bf47-00d69c39eec5'),
(26, 'hkhkh', NULL, 'hkhk', 1, 'dasd', 'jmmimmi@yahoo.com', 'hkjhkjh', 'user2', 'pass', NULL, '7987797899', b'1', NULL, 'b76f0270-3d92-42eb-aedd-8cb4ae3ccdcb');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE IF NOT EXISTS `users_roles` (
  `roleid` int(11) NOT NULL DEFAULT '0',
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`roleid`,`userid`),
  UNIQUE KEY `UK_mu5w27i1qeissk8udkctnsxpu` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`roleid`, `userid`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(6, 5),
(5, 6),
(3, 9),
(6, 12),
(6, 16),
(6, 17),
(4, 18),
(4, 19),
(6, 22),
(6, 23),
(6, 24),
(6, 25),
(6, 26);

-- --------------------------------------------------------

--
-- Table structure for table `user_team`
--

CREATE TABLE IF NOT EXISTS `user_team` (
  `CREATED_DATE` datetime NOT NULL,
  `RESPONSIBILITY` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `USERID` int(11) NOT NULL DEFAULT '0',
  `TEAMID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TEAMID`,`USERID`),
  KEY `FK_81e22hv8mih08qh4cu0auodsi` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_team`
--

INSERT INTO `user_team` (`CREATED_DATE`, `RESPONSIBILITY`, `STATUS`, `USERID`, `TEAMID`) VALUES
('2014-05-21 14:07:53', NULL, 'Eπιβεβαιωμένο', 19, 3),
('2014-05-21 14:39:38', NULL, 'Eπιβεβαιωμένο', 22, 3),
('2014-05-21 19:38:54', NULL, 'Ανεπιβεβαιωμένο', 25, 6),
('2014-05-16 15:05:57', NULL, 'Ανεπιβεβαιωμένο', 18, 8);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accidents`
--
ALTER TABLE `accidents`
  ADD CONSTRAINT `FK_38gqy6c232i6olrf82v5ag6uq` FOREIGN KEY (`EVENT_ID`) REFERENCES `emergencies` (`EVENT_ID`);

--
-- Constraints for table `actions`
--
ALTER TABLE `actions`
  ADD CONSTRAINT `FK_a49e2i2nonu1t077rfn3go26w` FOREIGN KEY (`EVENT_ID`) REFERENCES `events` (`EVENT_ID`),
  ADD CONSTRAINT `FK_at2ea4ec43epfkjqlo339gn8b` FOREIGN KEY (`EMERGENCY_ID`) REFERENCES `emergencies` (`EVENT_ID`);

--
-- Constraints for table `dangers`
--
ALTER TABLE `dangers`
  ADD CONSTRAINT `FK_iq3i9qb07uma16myn5j9tocw9` FOREIGN KEY (`EVENT_ID`) REFERENCES `emergencies` (`EVENT_ID`);

--
-- Constraints for table `emergencies`
--
ALTER TABLE `emergencies`
  ADD CONSTRAINT `FK_ajyr4qi2qo40omdlxuwjrlrk0` FOREIGN KEY (`EVENT_ID`) REFERENCES `events` (`EVENT_ID`);

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FK_934urau4inqfe6cns7tohtbrg` FOREIGN KEY (`OWNER_ID`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FK_h9n9u5ghk265ngokat1vboxyi` FOREIGN KEY (`LOCATION_ID`) REFERENCES `locations` (`LOCATION_ID`);

--
-- Constraints for table `teams`
--
ALTER TABLE `teams`
  ADD CONSTRAINT `FK_nrreidr57j527lat5n0pbdy` FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FK_t7w6gxta3w8qm5ywhblq8npii` FOREIGN KEY (`t_type`) REFERENCES `team_type` (`tt_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_g13o1c7a6pb5lmtv75cg8e4aw` FOREIGN KEY (`CURRENT_LOCATION`) REFERENCES `locations` (`LOCATION_ID`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK_1ix84l6h7xjfvg6negrt85tm0` FOREIGN KEY (`roleid`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `FK_mu5w27i1qeissk8udkctnsxpu` FOREIGN KEY (`userid`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `user_team`
--
ALTER TABLE `user_team`
  ADD CONSTRAINT `FK_26tx2irt2khtoca6obnb0oe7k` FOREIGN KEY (`TEAMID`) REFERENCES `teams` (`team_id`),
  ADD CONSTRAINT `FK_81e22hv8mih08qh4cu0auodsi` FOREIGN KEY (`USERID`) REFERENCES `users` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
