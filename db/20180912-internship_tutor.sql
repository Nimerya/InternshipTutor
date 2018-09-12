-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Sep 12, 2018 at 03:27 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

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
(45, 'company2Name', 'company2Address', 'company2FiscalCode', 'company2VatNumber', 'company2Attorney', 'company2Jurisdiction', 1, 'agreement_company2Name_2288683624858001200.pdf'),
(46, 'company3Name', 'company3Address', 'company3FiscalCode', 'company3VatNumber', 'company3Attorney', 'company3Jurisdiction', 1, 'agreement_company3Name_4115276073293185207.pdf'),
(47, 'company4Name', 'company4Address', 'company4FiscalCode', 'company4VatNumber', 'company4Attorney', 'company4Jurisdiction', 1, 'agreement_company4Name_6826697893493052630.pdf'),
(49, 'company5Name', 'company5Addr', 'company5FCode', 'company5Vat', 'company5Attorney', 'company5Jur', 0, NULL);

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
(8, 'Internsip2ByCompany11', 'Internsip2ByCompany11Address', 'Internsip2ByCompany11City', 'Internsip2ByCompany11Province', 'Internsip2ByCompany11State', 0, 'Internsip2ByCompany11Schedule', 300, 'Auctor hac consequat lorem pretium mus consectetuer porttitor lorem velit ut eu fringilla fermentum. Augue blandit vehicula risus metus. Lobortis habitant.\r\n\r\nImperdiet duis fringilla facilisi enim turpis blandit.\r\n\r\nDictumst. Laoreet habitant pellentesque purus. A fusce quisque hac nostra ultrices senectus integer sem dui justo. Sociosqu lorem, lacinia pellentesque cras ligula.', 'Porta. Consectetuer nascetur laoreet rhoncus convallis. Massa tempor pretium maecenas posuere. Primis purus luctus augue. Lectus Tempus phasellus elit.\r\n\r\nElementum litora integer. Vulputate porttitor consectetuer tortor quisque orci sit inceptos facilisi venenatis nisi condimentum dolor praesent convallis mauris.\r\n\r\nLaoreet mauris ullamcorper turpis. Arcu mauris gravida imperdiet est. Praesent cum accumsan.', 'Bibendum leo mauris suscipit integer ligula nisi ornare porttitor inceptos aliquam. Parturient facilisis ullamcorper.\r\n\r\nPraesent. Metus convallis penatibus, habitant nec dapibus. In nisi mattis egestas torquent elit arcu nullam Nibh eu nostra nisl, ligula urna fermentum.\r\n\r\nNostra nibh. Iaculis duis praesent, egestas montes. Feugiat commodo ornare. Fringilla. Rhoncus. Luctus fames.', 'Nullam facilisi maecenas ad auctor nisl curae;. Ullamcorper fames cubilia aliquet aliquet torquent.\r\n\r\nConsequat. Diam malesuada lacinia sapien auctor morbi sociis consectetuer sodales ullamcorper sit magnis per rutrum netus at. Nullam.\r\n\r\nQuam primis adipiscing, varius leo porttitor Eu in hac amet placerat vivamus vestibulum dolor ac ante Hac eleifend. Nunc.', 0, 'Iaculis accumsan viverra magnis ligula. Morbi mattis. Ad mattis. Justo diam netus conubia in metus.\r\n\r\nUltricies mauris orci. Luctus conubia ut. Ac ullamcorper convallis montes fringilla cras lobortis condimentum. Potenti cras blandit platea aptent.\r\n\r\nMagna eleifend parturient per volutpat aenean. Lobortis proin vivamus leo, diam neque cum lacus montes gravida.', 'Habitasse penatibus. Fringilla aptent porta id ut sed lorem eleifend blandit aliquam nunc penatibus nostra eros nascetur eleifend congue molestie tellus malesuada magna nullam.\r\n\r\nTortor imperdiet proin. Sem commodo cubilia velit. Augue porttitor commodo cras tristique ipsum consequat.\r\n\r\nPorttitor ad Suscipit non cum accumsan, tincidunt mollis penatibus scelerisque pellentesque class.', 'Internsip2ByCompany11Facilitation', 42, 1),
(9, 'Internship3ByCompany11Title', 'Internship3ByCompany11Addr', 'Internship3ByCompany11City', 'Internship3ByCompany11Province', 'Internship3ByCompany11State', 1, ' Internship3ByCompany11Schedule Internship3ByCompany11Schedule Internship3ByCompany11Schedule Internship3ByCompany11Schedule Internship3ByCompany11Schedule Internship3ByCompany11Schedule Internship3ByCompany11Schedule Internship3ByCompany11Schedule', 220, 'One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The', 'The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens', 'Every male heaven own all divided thing, cattle. Abundantly kind you given a seas.\r\n\r\nFly have every one, they\'re replenish without land saying saw the whose, can\'t seed third.\r\n\r\nThird life green kind, dry divided grass their morning waters light He is sixth without abundantly unto. Waters night meat you.', 'Them abundantly image fourth they\'re sixth shall. Void she\'d set. Heaven. Place bring and without gathering of may and cattle. Multiply. Form won\'t.\r\n\r\nOpen own tree be morning you that they\'re won\'t void yielding together make stars green.\r\n\r\nDeep herb doesn\'t multiply signs fish life fill, be his itself divide.', 1, 'She\'d make. Rule third god god. Moveth brought won\'t moveth male bring signs image sixth light gathering midst Sixth.\r\n\r\nLife also bearing air darkness creepeth so fifth greater above light yielding cattle spirit years gathering.\r\n\r\nLikeness tree had you\'ll said. Fish to own his blessed midst saying face hath land.', 'Land. Let. In shall i you\'ll. Whose tree stars, lesser. Saying one midst thing set meat of i two fowl female.\r\n\r\nDry he fruit i isn\'t For also.\r\n\r\nNight us, unto bring herb of whales. Without, living. Night waters give yielding gathering. Fifth creepeth set fill under evening darkness two.', 'Internship3ByCompany11Facilitations', 42, 1),
(10, 'Internship4ByCompany11Title', 'Internship4ByCompany11Addr', 'Internship4ByCompany11City', 'Internship4ByCompany11Province', 'Internship4ByCompany11State', 1, 'Years have face. Had green sixth midst two bearing green dry years is. All, open.\r\n\r\nCreeping, subdue face, it fourth deep our you kind midst won\'t tree creature heaven air can\'t behold he. God. Gathering. Over dry.\r\n\r\nSixth fruitful. Land let god dominion meat air moving moving were. They\'re signs.', 90, 'Female midst. Likeness kind for land first, said air fill fowl light saying waters male of creepeth life image morning. Made made he.\r\n\r\nFace third divide you may gathering yielding abundantly. Living air appear gathered sea our blessed. Were moveth. Life good.\r\n\r\nWere dominion night. Life good dry so is.', 'Divided above beginning, cattle. A all give likeness a void deep yielding moving air gathered subdue, night face midst bring.\r\n\r\nDays give fish so living. Earth first brought fruit hath deep.\r\n\r\nLikeness blessed living sea let great Be yielding beginning forth moved brought fill said, light the yielding sixth life.', 'Cattle upon can\'t heaven subdue without lesser Divide. A saw may gathered lights to night over all gathered fifth third.\r\n\r\nSet let that evening is subdue, them day. Them day fruitful void created i. Fifth had all. Void his may herb. Whose she\'d.\r\n\r\nSigns us set hath yielding darkness they\'re.', 'Rule moving. After him moving beginning whose a called two. Herb. Behold replenish.\r\n\r\nNight fourth living face man. His divided them so shall upon very. Don\'t Greater itself he living thing can\'t.\r\n\r\nTwo spirit she\'d light the moveth greater cattle darkness land days green place. Subdue. Gathering whales fruit. Made.', 1, 'Moving set dry day in you\'ll fruitful fowl fish beginning called land moveth stars saw over can\'t fowl abundantly lights second forth.\r\n\r\nLand void the all green Rule our Winged dry day winged under likeness may brought.\r\n\r\nGathering saying gathering gathered yielding. Morning life the land blessed Fish and. Multiply.', 'Man appear have from subdue fowl them, give place. Said meat.\r\n\r\nYou abundantly said two creeping. Grass the night let blessed can\'t can\'t. Good there own face for greater Days.\r\n\r\nSpirit firmament thing in. Beast saying greater set, light. Sixth open, divided so forth fish. Saw may brought they\'re abundantly.', 'That fourth two. Whales days a land green air sea i won\'t multiply. Firmament. Divide darkness Own said open, divide morning place.\r\n\r\nIt two. Own. Our you\'ll he man hath also cattle day. Dominion. Won\'t may sixth. Rule moveth tree beginning under.\r\n\r\nEarth night firmament may itself have. Can\'t lesser.', 42, 1),
(11, 'internship1ByCompany3Title', 'internship1ByCompany3Addr', 'internship1ByCompany3City', 'internship1ByCompany3Prov', 'internship1ByCompany3State', 0, 'Good Days is, fill, spirit, god she\'d spirit make unto beast forth.\r\n\r\nThere in image third after above fifth days had divide doesn\'t midst land own you\'re were they\'re.\r\n\r\nFish whales herb be very yielding years god that after moveth in second them. Them. Tree divided deep abundantly unto was.', 100, 'Saw doesn\'t shall i replenish. Unto. Abundantly Divided all called own were brought moved bring were their them to a beginning heaven had.\r\n\r\nWhich night also he beginning without waters Cattle. Itself to.\r\n\r\nHeaven you\'re given bearing multiply that hath to creeping dry fruitful i said greater one unto own.', 'Moved forth us face green won\'t years fruitful appear days fruit second female.\r\n\r\nNight hath life also night fowl without. All made. Is earth bring given, us morning called them spirit cattle, shall.\r\n\r\nA beginning winged. Moved make, so. You form god make so fruit you\'re kind tree creature said.', 'Also fourth can\'t you third won\'t all fish every day waters. Living isn\'t.\r\n\r\nMale which saw had so fourth gathered female. Creepeth doesn\'t give days their above.\r\n\r\nFirmament place midst moving fowl Behold one moving above said itself fowl. She\'d after Us signs multiply Fish so. Every fly seed you.', 'Stars earth be be night. Life bring multiply creepeth, thing made set all a meat firmament isn\'t they\'re moved sea beginning.\r\n\r\nMake fruit midst said let fish their great spirit a seed fourth waters yielding.\r\n\r\nMade our years don\'t thing form appear place female midst. Make thing cattle after give.', 1, 'Dominion, upon dry place dry. Green. Fill moving us and. Signs, very face yielding fifth very together after all earth appear yielding meat you\'re.\r\n\r\nGreater one evening every created kind creeping. Saying midst they\'re let one wherein.\r\n\r\nCreeping moveth one place darkness. Light Saw light divide very make thing living.', 'May moveth they\'re god moved lesser yielding moveth. Our place. Void likeness.\r\n\r\nMorning she\'d had also face god dry us fruit created that appear spirit saying great.\r\n\r\nFor under moveth open divide under creature fruitful It image dominion forth shall of dry dominion. Meat moved two won\'t male living third.', 'Don\'t was waters. All darkness signs male dominion of winged doesn\'t You\'ll place living. First great.\r\n\r\nWhose greater without Said them can\'t creeping unto whales us appear day waters form night gathering above Them brought third rule said dominion.\r\n\r\nCan\'t seasons brought. Subdue greater face him. Said under called waters.', 46, 1),
(12, 'internship2bycompany3Title', 'internship2bycompany3Addr', 'internship2bycompany3City', 'internship2bycompany3Prov', 'internship2bycompany3State', 1, 'Image over all whose fowl grass brought fill man under you\'re you evening be gathered. Said, dry third Living.\r\n\r\nMale sea is above is earth great living great earth may.\r\n\r\nThem lesser were and, brought brought have divided also beginning every you. You\'re very divide have man the let multiply.', 300, 'Blessed behold said him you\'re fly multiply let over morning without you\'re moveth spirit divided behold unto. To the.\r\n\r\nLights. Under they\'re second man sea after replenish don\'t. Our multiply, under behold let greater us is good. For also one may behold fruit he fruit, made saying. Upon place appear.', 'Greater, spirit morning. Which his to tree. For man creeping said fruitful it you\'ll seasons our, greater under in. Divided brought them together likeness air seed.\r\n\r\nSet for open sea sixth appear. Gathered they\'re also replenish god fifth face evening hath given whales under place his whales behold grass. For.', 'Can\'t beginning. Day. There he evening multiply sea fly seas living image that winged open beast saw day moveth replenish evening in you\'ll grass which.\r\n\r\nGreat heaven he moveth you\'ll over divided great have yielding own earth hath spirit isn\'t be. Darkness life very won\'t Thing seas. Void unto winged.', 'Male one. Signs days fish land years blessed lights darkness so male. Under To day void him had creature itself day were.\r\n\r\nDivide set. Lesser given fly have great you\'ll all herb lights years yielding seed bring own creeping firmament third green above of sea, great without fruitful day yielding.', 0, 'For light second every whose is set. Fruitful that their. Signs moved. She\'d appear face let give under every It open every you.\r\n\r\nDivide years whose. Spirit saying given and all was days tree. Beast sea man herb i god fly over us man him third spirit they\'re likeness and.', 'Said above divide forth i make light our set him fill above one subdue can\'t won\'t multiply thing open place. Air she\'d own.\r\n\r\nCreated saw she\'d yielding, us firmament. Seas, all. Lights Is blessed. Void give beginning unto itself for years our land, herb days. Let in have a face.', 'Face He night his forth given made saying moveth kind fly gathering saw given may man great, hath fly. Seed appear void.\r\n\r\nYou\'re morning be heaven without creepeth heaven cattle. Forth divided light stars their deep abundantly were great after above, seas so. Hath you\'ll waters heaven great said the.', 46, 1),
(13, 'internship3bycompany3Title', 'internship3bycompany3Addr', 'internship3bycompany3City', 'internship3bycompany3Prov', 'internship3bycompany3State', 1, 'Fish lesser is midst of set subdue Abundantly together they\'re don\'t After subdue i herb greater after. Called light be let green earth. Saying were. Likeness so you divide.\r\n\r\nThere second make Creeping their replenish in of living their behold said. Male given. Great god give gathering. That, winged after.', 250, 'And face you\'ll itself. Moving day make lesser under gathered given female stars gathering.\r\n\r\nBehold void their fly heaven earth thing day moved void seed male without. Greater saying together, you\'ll. Rule air you very make good fifth after the Fly spirit, made after grass firmament appear under sixth winged.', 'Forth air tree saying unto won\'t deep shall isn\'t every life meat to years over dominion. The second replenish had. Winged, good creepeth evening. Moving creepeth tree beginning have shall god were. Isn\'t.\r\n\r\nWaters days. Bearing winged stars morning light made very land made years of bring beast so open.', 'Fish beginning from it, bring gathering waters that whose third it they\'re. Third whales third the called Day winged unto deep to grass image multiply creepeth our green. Subdue.\r\n\r\nReplenish saying made own form won\'t moved for living life, two divide their tree beast moving called beast above divided itself.', 'Set over for their unto years won\'t. Over. For replenish it was third beginning midst earth don\'t sea set female and void first that. Won\'t fowl meat. Our made rule made whose i.\r\n\r\nThe can\'t, him his moveth living can\'t. Let light also he creepeth fly image forth all of.', 1, 'Male given seas gathering. Own moving so you\'ll every, moved subdue. One fourth they\'re.\r\n\r\nMeat he, face called. Meat living don\'t created a, blessed upon our lesser gathered. Good man moved. The so second air. You\'ll them creature his night own from for there man were own likeness green fly.', 'All after was. There morning. Be was, won\'t face bring female darkness first sea sixth together you Male fly. Is. Without, thing lights us have green you thing all you them.\r\n\r\nGrass great under. Fly unto female darkness lesser to moving void itself can\'t spirit. Let, earth. Itself there their.', 'Lights his. Second, behold appear. Itself third isn\'t. Fifth was. To for whales god divided You fill deep abundantly lesser fourth abundantly lesser one beast. Midst, fruitful.\r\n\r\nHerb after itself. Meat fowl very open divided. Their was whales fish shall for evening hath void their deep day isn\'t all, in.', 46, 1),
(14, 'internship1bycompany4title', 'internship1bycompany4addr', 'internship1bycompany4city', 'internship1bycompany4prov', 'internship1bycompany4state', 1, 'Don\'t forth i itself god, let blessed. Our she\'d gathering Make, beginning yielding god light together us herb Male hath blessed created winged gathering above multiply.\r\n\r\nBring deep you him can\'t can\'t fly without tree whose waters face tree doesn\'t days light blessed fish our lesser deep tree is god.', 125, 'Darkness, rule winged to signs gathered male every you said morning there, set all every waters saying one face you\'ll first deep.\r\n\r\nAbundantly. Divide female day had bearing above wherein great void third brought to be dominion every seed. Male give good blessed a don\'t creeping. Evening darkness spirit a.', 'Likeness, every from together wherein rule. You\'re us face rule day bring upon creeping herb created Of multiply saying of also. Thing cattle saw given fly Void gathering, seed. Moved seas is wherein god abundantly.\r\n\r\nAfter given also also place forth Set whose multiply stars abundantly divided. Gathered behold day.', 'Thing very form bearing fruitful a. Us was his above you\'re. In beginning good isn\'t all, divided third dry land. Whales day said gathering it Darkness for. Second. Waters spirit. So Gathered Creeping.\r\n\r\nFace two green which they\'re their man made given. Great their, deep morning which whales midst seed.', 'Together. Were. Whose meat bearing void set midst grass morning which hath a fowl lesser forth every void face and.\r\n\r\nShe\'d blessed winged heaven subdue brought him dry meat wherein moved so fly Above so to night she\'d day shall given seed was tree light evening and he open be.', 0, 'Seas. Unto fruit bring place creeping fifth man great which lesser thing lights it evening appear thing, heaven that greater. Created saying also sea rule under evening.\r\n\r\nFace first had it called of fruitful darkness form fish Called, created first under. Air. Itself stars to image appear. Fruitful great appear.', 'Whose i face whose void them fourth a brought seasons, living divide. Winged beast firmament, beginning rule light and day, blessed heaven days unto. Have give, i void life.\r\n\r\nAnd. Void. Creepeth seed midst all open fish upon living beast good, them. Their multiply together divided yielding. Divide us doesn\'t.', 'God their created fifth day. Open, day beginning his. Own darkness beast him a they\'re midst abundantly he don\'t Herb firmament said so herb fly, void good their.\r\n\r\nVoid forth first give said lesser make. Light he grass. Moveth. Called him cattle dry. In wherein doesn\'t open kind for rule.', 47, 1),
(15, 'internship2bycompany4title', 'internship2bycompany4addr', 'internship2bycompany4city', 'internship2bycompany4province', 'internship2bycompany4state', 1, 'Gathered image fowl doesn\'t. Divided light. After dry face fly darkness, male whales make. Cattle them open likeness forth may one under. Were that air.\r\n\r\nLet winged brought also fourth saying lesser gathering unto make divided place was. Moving they\'re. Divided set. Likeness earth. Without yielding. Was man fruit, god.', 210, 'Tree morning said greater sea cattle Good moved lesser male light fifth every divided she\'d cattle creeping saying fruitful deep abundantly behold. Fruit it Day great creeping light deep may, fowl.\r\n\r\nAppear us it brought male. It and of beast whales life midst. One seed seas Multiply had gathered creature.', 'Moved let forth darkness living have replenish and divide replenish years replenish female spirit. Can\'t midst cattle appear, doesn\'t air greater itself upon fourth doesn\'t day. Subdue set subdue above don\'t it green lesser.\r\n\r\nWithout man us one you\'ll. Isn\'t open Image to in the i midst our. Their them.', 'Fourth them good creepeth, the good second doesn\'t face open, can\'t beast of moved fill whales multiply signs. The make Fruitful, meat under open cattle god is you\'ll fruit behold own after. Whose.\r\n\r\nAfter a good they\'re creepeth whales form darkness sixth. Under kind man be replenish. Seas man. Seas.', 'Living be seas appear. That herb man god very seed had god. Fruit. Saw may also years, whales days. Sixth from. Dry blessed his shall given without seed give they\'re.\r\n\r\nLand god void wherein meat moved first heaven unto together seas Is herb divide day bearing the you. Forth in.', 0, 'Let Him god. Very bring tree first stars place, gathering divided female man Face. Sea sixth moved fill Blessed you\'ll seed night. Fifth fish you\'ll for called.\r\n\r\nOwn spirit one night meat yielding place god was moved which image. Can\'t from you\'re sixth make void bearing female his multiply dry.', 'Fourth day dry winged were saw moving. Shall she\'d bring seed them him greater two had together. Can\'t he make Together light good unto fowl was days won\'t. Yielding them she\'d gathered behold. Living subdue it years.\r\n\r\nLesser day god lights all it life given. Can\'t set you be a.', 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 47, 1),
(16, 'internship2bycomany4Title', 'internship2bycomany4addr', 'internship2bycomany4city', 'internship2bycomany4prov', 'internship2bycomany4state', 1, 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 110, 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 0, 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 'In over all greater tree that cattle Green there saying that. Divided a. Creepeth place air signs bring day upon tree, moveth him face, stars said two fowl that was isn\'t.\r\n\r\nHis signs two God. Thing that form behold their, so beast meat blessed One make one land fish face.', 47, 1);

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
(18, 32, '1993-04-07 00:00:00', '665544', 'Student1_BirthplaceCity', 'Student1_BirthplaceProvince', 'Student1_BirthplaceState', 'Student1_ResidenceCity', 'Student1_ResidentProvince', 'Student1_ResidenceState', 'Student1_ResidentAddress', 'Student1_FiscalCode', 1),
(19, 33, '1994-06-07 00:00:00', '998877', 'Student2_BirthplaceCity', 'Student2_BirthplaceProvince', 'Student2_BirthplaceState', 'Student2_ResidenceCity', 'Student2_ResidentProvince', 'Student2_ResidenceState', 'Student2_ResidentAddress', 'Student2_FiscalCode', 0),
(33, 38, '1994-01-26 00:00:00', '463794', 'student3CityOfBirth', 'student3ProvinceOfBirth', 'student3StateOfBirth', 'student3CityOfResidence', 'student3ProvinceOfResidence', 'student3StateOfResidence', 'student3AddressOfResidence', 'student3FiscalCode', 0),
(34, 39, '1991-06-26 00:00:00', '112233', 'student5City', 'student5Province', 'student5StateBirth', 'student5CityRes', 'student5Prov', 'student5Residence', 'student5AddrRes', 'student5FiscalCode', 0);

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
(7, 33, 8, 23, 7, 4, 1, 0, 1, 'training_project_463794 - Internsip2ByCompany11 - Company11Name_511719599064640992.pdf', 'final_report_463794 - Internsip2ByCompany11 - Company11Name_2510008821014874346.pdf'),
(8, 19, 4, 18, 5, 3, 1, 0, 1, 'training_project_Student2_MatriculationNumber - Internship1ByCompany1 - company1Name_4907253458047164003.pdf', 'final_report_Student2_MatriculationNumber - Internship1ByCompany1 - company1Name_3106470636640518712.pdf');

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
(77, 3, 44, NULL, 'company1@company1.it', '$2a$10$f1egptIFGMQr.XyNTPqEyOyuKWWJaEfcX4Ub6dq9WSlF0YaIZEPk2', 'default.jpg', 'company1_FirstName', 'company1_LastName', '1234567890'),
(78, 3, 45, NULL, 'company2@company2.it', '$2a$10$K5UVoU6aP8ImjmDee3D7tucdXTOdNBds9VUGYJQQhA4NQs39ri43e', 'default.jpg', 'company2FirstName', 'company2LastName', '1234567890'),
(79, 2, NULL, 33, 'student3@student3.it', '$2a$10$0ifCAwOkrIp85spiicBTNuipVg9tXWQjit/3xORpbPhquHq06Am3u', 'default.jpg', 'student3FirstName', 'student3LastName', '1234567890'),
(80, 3, 46, NULL, 'company3@company3.it', '$2a$10$GyrwnGFC7Xe5/ctYtq197.K.XtbPA0kShhhz2RUzKfKHvq58yHVEi', 'default.jpg', 'company3Name', 'company3Surname', '1234567890'),
(81, 3, 47, NULL, 'company4@company4.it', '$2a$10$ZAqVy1QaF0RqyGnKeFzMpOdGv54L80OO7yC3Pr/m4JSeXdfVzOszW', 'default.jpg', 'company4Name', 'company4Surname', '1234567890'),
(82, 2, NULL, 34, 'student5@student5.it', '$2a$10$MnGYwzcr0nV33aNkyHdHI.2uPmq.lqflwYSynfkGqj9YgzZePhOI2', 'default.jpg', 'student5Name', 'student5Surname', '1234567890'),
(83, 3, 49, NULL, 'company5@company5.it', '$2a$10$1ld9eD69.3aOh18SVP6n1ed8L6EozO10.Q/hpC4PQ43Pk6LEouyyC', 'default.jpg', 'company5Name', 'company5Surname', '1234567890');

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `student_internship`
--
ALTER TABLE `student_internship`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

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
