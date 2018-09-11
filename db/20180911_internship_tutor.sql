-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Sep 11, 2018 at 04:07 PM
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
(42, 'Company11Name', 'Company11Address', 'Company11FiscalCode', 'Company11VatNumber', 'Company11Attorney', 'Company11Jurisdiction', 1, 'agreement_Company11Name_8256628958600250299.pdf'),
(44, 'company1Name', 'company1Address', 'company1FiscalCode', 'company1VatNumber', 'company1Attorney', 'company1Jurisdiction', 1, 'agreement_company1Name_14396199114859877153.pdf'),
(45, 'company2Name', 'company2Address', 'company2FiscalCode', 'company2VatNumber', 'company2Attorney', 'company2Jurisdiction', 0, NULL);

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
  `mode_it_it` text,
  `mode_en_gb` text NOT NULL,
  `goals_it_it` text,
  `goals_en_gb` text NOT NULL,
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
(4, 'Internship1ByCompany1', 'Internship1ByCompany1Address', 'Internship1ByCompany1City', 'Internship1ByCompany1Province', 'Internship1ByCompany1State', 0, 'Internship1ByCompany1Schedule', 150, '\r\nNonummy et potenti sed suspendisse imperdiet torquent hymenaeos neque. Lacus per sem placerat habitant vel amet.\r\n\r\nMaecenas mattis sodales ut et taciti massa sem. Suspendisse. Placerat volutpat enim lacus sapien imperdiet sagittis.\r\n\r\nMontes maecenas et tellus. Ultricies. Bibendum litora velit convallis. Vel scelerisque ultrices molestie dui enim habitant fermentum amet.', 'Habitant nonummy elementum quisque. Fusce fames semper aptent Urna eleifend quisque sed convallis quis sociis tempus tristique netus elit conubia pharetra vestibulum vel. Curae;.\r\n\r\nConsectetuer cras leo ut sodales hendrerit hymenaeos Rhoncus.\r\n\r\nAuctor. Per ultrices ad auctor quisque nibh netus. Mattis. Magnis placerat auctor dolor lobortis aliquam cras netus. Dictum.', 'Justo nullam class taciti. Natoque Sodales rhoncus aenean neque vitae ante tempus nisl. Duis vulputate, turpis platea accumsan.\r\n\r\nEget semper ac Proin enim facilisis quam ultrices facilisis lectus fusce, natoque. Netus.\r\n\r\nQuis eget class pretium dignissim aenean proin nonummy molestie litora sociosqu dui egestas integer dapibus lobortis quam sem urna.', 'Semper placerat, lectus lectus, lobortis vulputate tortor. Neque litora lacinia vitae malesuada nascetur.\r\n\r\nCommodo pulvinar. Mi sollicitudin id. Hendrerit mollis rhoncus parturient vestibulum quisque Natoque.\r\n\r\nCongue felis. Dignissim nisi vulputate condimentum varius vestibulum orci scelerisque habitasse semper interdum dictum etiam Pellentesque in curabitur vestibulum vitae mattis quam risus pharetra lorem.', 1, 'Dolor, justo porta porttitor primis eleifend euismod hac condimentum ultricies accumsan.\r\n\r\nOrnare. Netus amet cubilia tortor hymenaeos aliquam vivamus Sed imperdiet scelerisque neque, fringilla tristique est interdum donec netus aenean dictumst tincidunt.\r\n\r\nQuam, semper congue tempus consequat vivamus elit tempor arcu tellus facilisi imperdiet tellus consectetuer. Non non porttitor. Hendrerit.', 'Bibendum. Mus purus dictum. Odio mattis ut vestibulum rutrum eget. Diam hymenaeos fermentum urna dictum nulla.\r\n\r\nAccumsan vitae. Parturient purus faucibus enim molestie netus primis fames tristique lacus orci sem condimentum hymenaeos cubilia ornare lacinia.\r\n\r\nPharetra. Venenatis convallis fames consectetuer aenean. Accumsan lectus blandit. Magnis et pharetra egestas nascetur nec.', 'Tincidunt dolor a ultricies consequat, mi dolor elementum venenatis sollicitudin Fames luctus mattis massa. Cras. Magna sollicitudin.\r\n\r\nAuctor potenti libero curabitur, per tincidunt cras tortor tortor nostra dictum scelerisque.\r\n\r\nIpsum ultrices urna lobortis senectus pharetra venenatis placerat platea mus fusce tortor nascetur curae; eu primis iaculis quam leo ridiculus nam.', 44, 1),
(5, 'Internship2ByCompany1', 'Internship2ByCompany1Address', 'Internship2ByCompany1City', 'Internship2ByCompany1Province', 'Internship2ByCompany1State', 1, 'Internship2ByCompany1Schedule', 200, 'Nascetur aliquet cras mus vivamus curae;. Aliquam morbi viverra leo pede.\r\n\r\nDonec nibh, in at quis hendrerit nibh eu eros sem arcu praesent risus, cursus augue, integer tempor lacus nibh eget. Nascetur.\r\n\r\nSollicitudin tempus nostra accumsan. Aptent. Rhoncus. Eleifend hac torquent mus purus. Urna ipsum suspendisse Dictum Urna habitasse litora.', 'Non eu tellus auctor, sapien auctor suspendisse pulvinar blandit. Quisque rutrum potenti dictum praesent dui magna fames sociosqu turpis.\r\n\r\nLaoreet bibendum ornare dictum amet magnis dignissim rutrum Sollicitudin condimentum magna. Lorem. Tortor Tempor conubia.\r\n\r\nEget aliquet orci sociis in montes mattis fames nam magna porttitor habitasse sit taciti feugiat vitae.', 'Ornare risus gravida curae;. Amet pharetra. Lacus Class risus vehicula erat enim commodo morbi sollicitudin.\r\n\r\nViverra. Vivamus pellentesque quisque mi bibendum scelerisque convallis ullamcorper etiam eleifend ut adipiscing faucibus cursus.\r\n\r\nDis sodales auctor mollis litora ut magna parturient, duis mattis fringilla pellentesque nibh nascetur id. Consectetuer, quis. Tempus suscipit vestibulum.', 'Cum natoque dignissim fermentum imperdiet feugiat. Pharetra neque risus interdum fringilla ultricies adipiscing enim aenean suspendisse diam sit quis facilisis rhoncus sodales nibh.\r\n\r\nFacilisis sit. A diam, curabitur enim vitae litora.\r\n\r\nCongue per metus per proin fames Consequat et dictum integer luctus. Molestie sem mauris netus venenatis malesuada posuere torquent.', 0, 'Orci dapibus habitasse tempus vivamus molestie pellentesque molestie vehicula velit. Tempor fames inceptos etiam mattis nam, eleifend nulla.\r\n\r\nDapibus porttitor dolor tortor sollicitudin a erat curae; sociis in nascetur. Accumsan auctor, enim felis torquent non vehicula conubia.\r\n\r\nPhasellus mattis vulputate. Primis ullamcorper euismod in ad hymenaeos odio dui accumsan suspendisse.', 'Sociis aenean class orci. Eget fames hendrerit velit natoque. Magna, morbi montes vel, mollis quis gravida ornare ridiculus ut.\r\n\r\nEtiam primis. Vitae curabitur vehicula leo vulputate pede. Nostra.\r\n\r\nDictumst interdum facilisi ridiculus. Urna amet penatibus, mollis. Platea velit est iaculis iaculis netus natoque. Sociosqu ligula Vulputate proin ad in ligula.', 'Dictum Vitae bibendum pulvinar porttitor non. Hendrerit amet vestibulum. Hymenaeos.\r\n\r\nPosuere pharetra nisl nibh justo vestibulum dolor. Orci leo inceptos nullam semper, facilisi pede nulla dolor turpis lacinia odio.\r\n\r\nLorem turpis nullam arcu urna Sed odio, feugiat tortor commodo etiam egestas pretium scelerisque netus tempor. Tristique ante vehicula ipsum mi.', 44, 0),
(6, 'Internship3ByCompany1', 'Internship3ByCompany1Address', 'Internship3ByCompany1City', 'Internship3ByCompany1Province', 'Internship3ByCompany1State', 0, 'Internship3ByCompany1Schedule', 75, 'In suspendisse semper hac. Cubilia lectus pellentesque sit mattis scelerisque aliquet lacinia erat tempor convallis. Lorem purus vivamus.\r\n\r\nHendrerit. Commodo conubia ante dis venenatis, montes cursus sociis Facilisis. Aenean.\r\n\r\nEt blandit ad pulvinar quisque eros malesuada ultrices. Ultrices a eget nisl rhoncus porttitor bibendum dictumst fusce tempus platea libero. Praesent.', 'Accumsan congue, mus nonummy neque. Varius conubia. Habitant. Lacus ridiculus, interdum dui, amet habitant at ullamcorper. Volutpat eleifend. Eleifend Gravida.\r\n\r\nTaciti cursus. Fames nibh, pede interdum ornare Orci habitant gravida interdum nulla. Per quis. Varius proin magna.\r\n\r\nPosuere. Tempus eget leo ad sollicitudin justo vestibulum ligula dolor, nullam, scelerisque phasellus.', 'Pulvinar sociosqu tincidunt scelerisque leo vitae tellus nec natoque consequat tempor augue taciti felis egestas feugiat. Vel. Odio viverra malesuada viverra viverra ut Vestibulum egestas.\r\n\r\nLaoreet feugiat litora euismod lacus lacinia a sociosqu per.\r\n\r\nConsectetuer. Feugiat quis magna, luctus pulvinar interdum sollicitudin nunc neque erat magna natoque nisl conubia pede.', 'Habitant. Mauris lectus dictum felis vel scelerisque fames cubilia sem quis faucibus dis massa eros.\r\n\r\nAliquet fames Eu dictumst. Arcu eros semper parturient massa tempus primis nisi cursus auctor Ullamcorper ridiculus. Non posuere euismod iaculis. Diam taciti quisque.\r\n\r\nNibh integer justo ornare ornare. Dictum hac luctus porta ut quam rhoncus.', 0, 'Lobortis Nascetur litora magna auctor et tempor iaculis dictum hac. Nisl ultricies. Bibendum dictumst tellus elementum felis, massa.\r\n\r\nAliquet a curabitur. Aptent. Lorem ornare fames enim vivamus nunc tincidunt arcu montes velit.\r\n\r\nEu convallis. Lacus iaculis etiam, eu praesent. Porttitor. Et ridiculus. Ac imperdiet varius cursus mattis tincidunt. Cras dolor.', 'Elementum scelerisque consequat purus netus nullam rhoncus Ante orci donec. Duis. Magna habitant. Lectus nascetur.\r\n\r\nNonummy, vel. Enim enim mi hymenaeos natoque ullamcorper ut sodales.\r\n\r\nCongue aliquam quisque habitant sed. Donec phasellus donec, aptent massa orci venenatis dictumst placerat lectus, tristique turpis cubilia tortor. Tortor lorem. Penatibus imperdiet aliquam ridiculus.', 'Internship3ByCompany1Facilitations', 44, 1),
(7, 'Internship1ByCompany11', 'Internship1ByCompany11Address', 'Internship1ByCompany11City', 'Internship1ByCompany11Province', 'Internship1ByCompany11State', 1, 'Internship1ByCompany11Schedule', 250, 'Blandit pede. Cubilia luctus cubilia tellus venenatis scelerisque augue sociis vitae penatibus nibh sed. Lectus, molestie.\r\n\r\nSociosqu aliquet lacinia habitasse senectus nascetur dictumst orci, cras at mi sapien mollis vivamus Nulla. Facilisis molestie lacinia volutpat. Pharetra nunc.\r\n\r\nOrci, interdum habitant eleifend cum mi natoque. Lorem. Integer commodo nisl libero diam.', 'Convallis faucibus porttitor fringilla curae; praesent, justo in erat nostra fringilla velit id.\r\n\r\nFacilisis urna vivamus nunc nullam tincidunt mus. Ligula in habitant enim tristique, fames tempor. Per accumsan per.\r\n\r\nSollicitudin turpis curae; ridiculus cras dictum, ultrices Vestibulum vehicula ipsum vestibulum. Ad molestie lacinia. Egestas pellentesque nec. Dolor dui pellentesque.', 'Est. Quisque. Hendrerit curabitur rutrum montes risus ultricies dolor fringilla nulla faucibus phasellus ornare. Leo felis cras consectetuer condimentum augue eget senectus.\r\n\r\nHabitant sit, curabitur hac potenti class scelerisque vitae nascetur cursus elementum.\r\n\r\nNam lacus senectus Fermentum netus. Posuere in dictumst scelerisque elementum at eu adipiscing nonummy rutrum ligula orci.', 'Nibh rutrum commodo etiam, senectus nec habitasse penatibus proin quis iaculis mattis integer pharetra tincidunt natoque mi. Imperdiet libero ac.\r\n\r\nA aliquet vel dignissim dui turpis pellentesque. Amet nisl sem mattis vitae dictumst id ipsum.\r\n\r\nConvallis fames lacus malesuada tempor parturient placerat facilisi hac erat id suscipit semper nullam. Venenatis.', 1, 'Metus ut. Nunc netus phasellus integer convallis tempus nostra cubilia. Malesuada.\r\n\r\nDapibus condimentum Mauris pellentesque auctor. Nisl nulla adipiscing posuere sollicitudin Purus pretium nunc ridiculus torquent vulputate.\r\n\r\nEnim primis feugiat praesent viverra. Lorem habitant. Integer vestibulum fringilla elit velit. Augue litora purus eget lobortis amet quisque fames. Tellus arcu lobortis.', 'Nonummy inceptos ornare viverra, nonummy Mus iaculis platea euismod eget. Facilisis eget quisque per semper.\r\n\r\nMolestie ultrices. Odio orci magna consectetuer sem maecenas cras. Commodo fringilla curabitur. Sem.\r\n\r\nHabitasse lacus non malesuada. Congue. Felis imperdiet senectus egestas habitasse quam ac purus, adipiscing ipsum curabitur congue nibh ornare tempus pulvinar fermentum.', 'Internship1ByCompany11Facilitation', 42, 0),
(8, 'Internsip2ByCompany11', 'Internsip2ByCompany11Address', 'Internsip2ByCompany11City', 'Internsip2ByCompany11Province', 'Internsip2ByCompany11State', 0, 'Internsip2ByCompany11Schedule', 300, 'Auctor hac consequat lorem pretium mus consectetuer porttitor lorem velit ut eu fringilla fermentum. Augue blandit vehicula risus metus. Lobortis habitant.\r\n\r\nImperdiet duis fringilla facilisi enim turpis blandit.\r\n\r\nDictumst. Laoreet habitant pellentesque purus. A fusce quisque hac nostra ultrices senectus integer sem dui justo. Sociosqu lorem, lacinia pellentesque cras ligula.', 'Porta. Consectetuer nascetur laoreet rhoncus convallis. Massa tempor pretium maecenas posuere. Primis purus luctus augue. Lectus Tempus phasellus elit.\r\n\r\nElementum litora integer. Vulputate porttitor consectetuer tortor quisque orci sit inceptos facilisi venenatis nisi condimentum dolor praesent convallis mauris.\r\n\r\nLaoreet mauris ullamcorper turpis. Arcu mauris gravida imperdiet est. Praesent cum accumsan.', 'Bibendum leo mauris suscipit integer ligula nisi ornare porttitor inceptos aliquam. Parturient facilisis ullamcorper.\r\n\r\nPraesent. Metus convallis penatibus, habitant nec dapibus. In nisi mattis egestas torquent elit arcu nullam Nibh eu nostra nisl, ligula urna fermentum.\r\n\r\nNostra nibh. Iaculis duis praesent, egestas montes. Feugiat commodo ornare. Fringilla. Rhoncus. Luctus fames.', 'Nullam facilisi maecenas ad auctor nisl curae;. Ullamcorper fames cubilia aliquet aliquet torquent.\r\n\r\nConsequat. Diam malesuada lacinia sapien auctor morbi sociis consectetuer sodales ullamcorper sit magnis per rutrum netus at. Nullam.\r\n\r\nQuam primis adipiscing, varius leo porttitor Eu in hac amet placerat vivamus vestibulum dolor ac ante Hac eleifend. Nunc.', 0, 'Iaculis accumsan viverra magnis ligula. Morbi mattis. Ad mattis. Justo diam netus conubia in metus.\r\n\r\nUltricies mauris orci. Luctus conubia ut. Ac ullamcorper convallis montes fringilla cras lobortis condimentum. Potenti cras blandit platea aptent.\r\n\r\nMagna eleifend parturient per volutpat aenean. Lobortis proin vivamus leo, diam neque cum lacus montes gravida.', 'Habitasse penatibus. Fringilla aptent porta id ut sed lorem eleifend blandit aliquam nunc penatibus nostra eros nascetur eleifend congue molestie tellus malesuada magna nullam.\r\n\r\nTortor imperdiet proin. Sem commodo cubilia velit. Augue porttitor commodo cras tristique ipsum consequat.\r\n\r\nPorttitor ad Suscipit non cum accumsan, tincidunt mollis penatibus scelerisque pellentesque class.', 'Internsip2ByCompany11Facilitation', 42, 1);

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE `professor` (
  `id` int(10) UNSIGNED NOT NULL,
  `department_id` int(10) UNSIGNED NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `professor`
--

INSERT INTO `professor` (`id`, `department_id`, `first_name`, `last_name`, `email`, `phone_number`) VALUES
(18, 35, 'Professor1', 'Professor1', 'Professor1@Professor1.it', '1234567890'),
(19, 35, 'Professor2', 'Professor2', 'Professor2@Professor2.it', '1234567890'),
(20, 37, 'Professor3', 'Professor3', 'Professor3@Professor3.it', '1234567890'),
(21, 38, 'Professor4', 'Professor4', 'Professor4@Professor4.it', '1234567890'),
(22, 39, 'Professor5', 'Professor5', 'Professor5@Professor5.it', '1234567890'),
(23, 36, 'Professor6', 'Professor6', 'Professor6@Professor6.it', '1234567890'),
(24, 39, 'Professor7', 'Professor7', 'Professor7@Professor7.it', '1234567890'),
(25, 38, 'Professor8', 'Professor8', 'Professor8@Professor8.it', '1234567890'),
(26, 39, 'Professor9', 'Professor9', 'Professor9@Professor9.it', '1234567890'),
(27, 37, 'Professor10', 'Professor10', 'Professor10@Professor10.it', '1234567890');

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
(1, 'ROLE_ADMIN'),
(2, 'ROLE_STUDENT'),
(3, 'ROLE_COMPANY');

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
(33, 38, '1994-01-26 00:00:00', '463794', 'student3CityOfBirth', 'student3ProvinceOfBirth', 'student3StateOfBirth', 'student3CityOfResidence', 'student3ProvinceOfResidence', 'student3StateOfResidence', 'student3AddressOfResidence', 'student3FiscalCode', 0);

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

--
-- Dumping data for table `student_internship`
--

INSERT INTO `student_internship` (`id`, `student_id`, `internship_id`, `professor_id`, `cfu`, `review`, `accepted`, `rejected`, `completed`, `training_project`, `final_report`) VALUES
(6, 33, 5, 19, 4, NULL, 0, 0, 0, NULL, NULL),
(7, 33, 8, 23, 7, NULL, 0, 0, 0, NULL, NULL),
(8, 19, 4, 18, 5, NULL, 0, 0, 0, NULL, NULL);

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
(57, 2, NULL, 18, 'student1@student1.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student1', 'Student1', '1234567890'),
(58, 2, NULL, 19, 'student2@student2.it', '$2a$10$Htp8zQjrGkEDFGH8mGXkguuX1iwZhmXdIzf/aU4R2mplc7.tIj3Uu', 'default.jpg', 'Student2', 'Student2', '1234567890'),
(73, 1, NULL, NULL, 'admin@admin.it', '$2a$10$d/mJQ228Zu3cTc.xevQiR.MbqAxa0Vs1yCPiPvVDYhrh2k/ddKAeS', 'default.jpg', 'Admin', 'Admin', '1234567890'),
(74, 1, NULL, NULL, 'admin1@admin1.it', '$2a$10$5kH1HGl46zodissJPUpeD./thJDrZH1mrOyQkTkVHCbyVR8RE6TXe', 'default.jpg', 'Admin1', 'Admin1', '1234567890'),
(75, 3, 42, NULL, 'company11@company11.it', '$2a$10$/UE7vWlFcei25fCLicPHM.qysX52aRu090Ty0SHo5LQV34BevXDcu', 'default.jpg', 'Company11_Tutor', 'Company11_Tutor', '1234567890'),
(77, 3, 44, NULL, 'company1@company1.it', '$2a$10$f1egptIFGMQr.XyNTPqEyOyuKWWJaEfcX4Ub6dq9WSlF0YaIZEPk2', '', 'company1_FirstName', 'company1_LastName', '1234567890'),
(78, 3, 45, NULL, 'company2@company2.it', '$2a$10$K5UVoU6aP8ImjmDee3D7tucdXTOdNBds9VUGYJQQhA4NQs39ri43e', '', 'company2FirstName', 'company2LastName', '1234567890'),
(79, 2, NULL, 33, 'student3@student3.it', '$2a$10$0ifCAwOkrIp85spiicBTNuipVg9tXWQjit/3xORpbPhquHq06Am3u', '', 'student3FirstName', 'student3LastName', '1234567890');

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT for table `student_internship`
--
ALTER TABLE `student_internship`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;
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
