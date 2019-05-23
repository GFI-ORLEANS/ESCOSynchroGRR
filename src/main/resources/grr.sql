-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: grr2_netocentre
-- ------------------------------------------------------
-- Server version	5.5.54-0+deb8u1-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `grr_area`
--

DROP TABLE IF EXISTS `grr_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaName` varchar(30) NOT NULL DEFAULT '',
  `access` char(1) NOT NULL DEFAULT '',
  `order_display` smallint(6) NOT NULL DEFAULT '0',
  `ip_adr` varchar(15) NOT NULL DEFAULT '',
  `morningstarts_area` smallint(6) NOT NULL DEFAULT '0',
  `eveningends_area` smallint(6) NOT NULL DEFAULT '0',
  `duree_max_resa_area` int(11) NOT NULL DEFAULT '-1',
  `resolution_area` int(11) NOT NULL DEFAULT '0',
  `eveningends_minutes_area` smallint(6) NOT NULL DEFAULT '0',
  `weekstarts_area` smallint(6) NOT NULL DEFAULT '0',
  `twentyfourhour_format_area` smallint(6) NOT NULL DEFAULT '0',
  `calendar_default_values` char(1) NOT NULL DEFAULT 'y',
  `enable_periods` char(1) NOT NULL DEFAULT 'n',
  `display_days` varchar(7) NOT NULL DEFAULT 'yyyyyyy',
  `id_type_par_defaut` int(11) NOT NULL DEFAULT '-1',
  `duree_par_defaut_reservation_area` int(11) NOT NULL DEFAULT '0',
  `max_booking` smallint(6) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1044 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_area_periodes`
--

DROP TABLE IF EXISTS `grr_area_periodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_area_periodes` (
  `id_area` int(11) NOT NULL DEFAULT '0',
  `num_periode` smallint(6) NOT NULL DEFAULT '0',
  `nom_periode` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_area`,`num_periode`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_calendar`
--

DROP TABLE IF EXISTS `grr_calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_calendar` (
  `DAY` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_calendrier_jours_cycle`
--

DROP TABLE IF EXISTS `grr_calendrier_jours_cycle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_calendrier_jours_cycle` (
  `DAY` int(11) NOT NULL DEFAULT '0',
  `Jours` varchar(20) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_correspondance_statut`
--

DROP TABLE IF EXISTS `grr_correspondance_statut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_correspondance_statut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_fonction` varchar(30) NOT NULL,
  `libelle_fonction` varchar(200) NOT NULL,
  `statut_grr` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GrrEntry`
--

DROP TABLE IF EXISTS `grr_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` int(11) NOT NULL DEFAULT '0',
  `end_time` int(11) NOT NULL DEFAULT '0',
  `entry_type` int(11) NOT NULL DEFAULT '0',
  `repeat_id` int(11) NOT NULL DEFAULT '0',
  `room_id` int(11) NOT NULL DEFAULT '1',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(100) NOT NULL DEFAULT '',
  `beneficiaire_ext` varchar(200) NOT NULL DEFAULT '',
  `beneficiaire` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(80) NOT NULL DEFAULT '',
  `type` char(5) NOT NULL DEFAULT '1',
  `description` text,
  `statut_entry` char(1) NOT NULL DEFAULT '-',
  `option_reservation` int(11) NOT NULL DEFAULT '0',
  `overload_desc` text,
  `moderate` tinyint(1) DEFAULT '0',
  `jours` tinyint(2) NOT NULL DEFAULT '0',
  `clef` int(2) NOT NULL DEFAULT '0',
  `courrier` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idxStartTime` (`start_time`),
  KEY `idxEndTime` (`end_time`),
  KEY `room_id` (`room_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1529272 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GrrEntryModerate`
--

DROP TABLE IF EXISTS `grr_entry_moderate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_entry_moderate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_moderateur` varchar(40) NOT NULL DEFAULT '',
  `motivation_moderation` text NOT NULL,
  `start_time` int(11) NOT NULL DEFAULT '0',
  `end_time` int(11) NOT NULL DEFAULT '0',
  `entry_type` int(11) NOT NULL DEFAULT '0',
  `repeat_id` int(11) NOT NULL DEFAULT '0',
  `room_id` int(11) NOT NULL DEFAULT '1',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(100) NOT NULL DEFAULT '',
  `beneficiaire_ext` varchar(200) NOT NULL DEFAULT '',
  `beneficiaire` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(80) NOT NULL DEFAULT '',
  `type` char(5) DEFAULT NULL,
  `description` text,
  `statut_entry` char(1) NOT NULL DEFAULT '-',
  `option_reservation` int(11) NOT NULL DEFAULT '0',
  `overload_desc` text,
  `moderate` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idxStartTime` (`start_time`),
  KEY `idxEndTime` (`end_time`)
) ENGINE=MyISAM AUTO_INCREMENT=1528897 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_etablissement`
--

DROP TABLE IF EXISTS `grr_etablissement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_etablissement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `shortname` varchar(30) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL DEFAULT '',
  `codepostal` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `index_grr_etablissement_multiple` (`id`,`code`,`shortname`)
) ENGINE=MyISAM AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_etablissement_regroupement`
--

DROP TABLE IF EXISTS `grr_etablissement_regroupement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_etablissement_regroupement` (
  `code_etablissement_secondaire` varchar(20) NOT NULL,
  `code_etablissement_principal` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_etablissement_calendar`
--

DROP TABLE IF EXISTS `grr_j_etablissement_calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_etablissement_calendar` (
  `DAY` int(11) NOT NULL DEFAULT '0',
  `id_etablissement` int(11) NOT NULL DEFAULT '0',
  KEY `index_grr_j_etablissement_calendar_multiple` (`id_etablissement`,`DAY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_etablissement_site`
--

DROP TABLE IF EXISTS `grr_j_etablissement_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_etablissement_site` (
  `id_etablissement` int(11) NOT NULL DEFAULT '0',
  `id_site` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_etablissement`,`id_site`),
  KEY `index_grr_j_etablissement_site_multiple` (`id_etablissement`,`id_site`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_etablissement_type_area`
--

DROP TABLE IF EXISTS `grr_j_etablissement_type_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_etablissement_type_area` (
  `id_type_area` int(11) NOT NULL DEFAULT '0',
  `id_etablissement` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_type_area`,`id_etablissement`),
  KEY `index_grr_j_etablissement_type_area_multiple` (`id_etablissement`,`id_type_area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_mailuser_room`
--

DROP TABLE IF EXISTS `grr_j_mailuser_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_mailuser_room` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `id_room` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`,`id_room`),
  KEY `index_grr_j_mailuser_room_multiple` (`id_room`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_site_area`
--

DROP TABLE IF EXISTS `grr_j_site_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_site_area` (
  `id_site` int(11) NOT NULL DEFAULT '0',
  `id_area` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_site`,`id_area`),
  KEY `index_grr_j_site_area_multiple` (`id_site`,`id_area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_type_area`
--

DROP TABLE IF EXISTS `grr_j_type_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_type_area` (
  `id_type` int(11) NOT NULL DEFAULT '0',
  `id_area` int(11) NOT NULL DEFAULT '0',
  KEY `index_grr_j_type_area_multiple` (`id_type`,`id_area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_user_area`
--

DROP TABLE IF EXISTS `grr_j_user_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_user_area` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `id_area` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`,`id_area`),
  KEY `index_grr_j_user_area_multiple` (`id_area`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_user_etablissement`
--

DROP TABLE IF EXISTS `grr_j_user_etablissement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_user_etablissement` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `id_etablissement` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`,`id_etablissement`),
  KEY `index_grr_j_user_etablissement_multiple` (`id_etablissement`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_user_room`
--

DROP TABLE IF EXISTS `grr_j_user_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_user_room` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `id_room` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`,`id_room`),
  KEY `index_grr_j_user_room_multiple` (`id_room`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_useradmin_area`
--

DROP TABLE IF EXISTS `grr_j_useradmin_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_useradmin_area` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `id_area` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`,`id_area`),
  KEY `index_grr_j_useradmin_area_multiple` (`id_area`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_useradmin_etablissement`
--

DROP TABLE IF EXISTS `grr_j_useradmin_etablissement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_useradmin_etablissement` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `id_etablissement` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`,`id_etablissement`),
  KEY `index_grr_j_useradmin_etablissement_multiple` (`id_etablissement`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_j_useradmin_site`
--

DROP TABLE IF EXISTS `grr_j_useradmin_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_j_useradmin_site` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `id_site` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user`,`id_site`),
  KEY `index_grr_j_useradmin_site_multiple` (`id_site`,`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GrrLog`
--

DROP TABLE IF EXISTS `grr_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_log` (
  `LOGIN` varchar(40) NOT NULL DEFAULT '',
  `START` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `SESSION_ID` varchar(64) NOT NULL DEFAULT '',
  `REMOTE_ADDR` varchar(16) NOT NULL DEFAULT '',
  `USER_AGENT` varchar(255) NOT NULL DEFAULT '',
  `REFERER` varchar(255) NOT NULL DEFAULT '',
  `AUTOCLOSE` enum('0','1') NOT NULL DEFAULT '0',
  `END` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`SESSION_ID`,`START`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_overload`
--

DROP TABLE IF EXISTS `grr_overload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_overload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_area` int(11) NOT NULL,
  `fieldname` varchar(25) NOT NULL DEFAULT '',
  `fieldtype` varchar(25) NOT NULL DEFAULT '',
  `fieldlist` text NOT NULL,
  `obligatoire` char(1) NOT NULL DEFAULT 'n',
  `affichage` char(1) NOT NULL DEFAULT 'n',
  `confidentiel` char(1) NOT NULL DEFAULT 'n',
  `overload_mail` char(1) NOT NULL DEFAULT 'n',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_repeat`
--

DROP TABLE IF EXISTS `grr_repeat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_repeat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` int(11) NOT NULL DEFAULT '0',
  `end_time` int(11) NOT NULL DEFAULT '0',
  `rep_type` int(11) NOT NULL DEFAULT '0',
  `end_date` int(11) NOT NULL DEFAULT '0',
  `rep_opt` varchar(32) NOT NULL DEFAULT '',
  `room_id` int(11) NOT NULL DEFAULT '1',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(100) NOT NULL DEFAULT '',
  `beneficiaire_ext` varchar(200) NOT NULL DEFAULT '',
  `beneficiaire` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(80) NOT NULL DEFAULT '',
  `type` char(5) NOT NULL DEFAULT 'A',
  `description` text,
  `rep_num_weeks` tinyint(4) DEFAULT '0',
  `overload_desc` text,
  `jours` tinyint(2) NOT NULL DEFAULT '0',
  `courrier` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33900 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_room`
--

DROP TABLE IF EXISTS `grr_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL DEFAULT '0',
  `room_name` varchar(60) NOT NULL DEFAULT '',
  `description` varchar(60) NOT NULL DEFAULT '',
  `capacity` int(11) NOT NULL DEFAULT '0',
  `max_booking` smallint(6) NOT NULL DEFAULT '-1',
  `statut_room` char(1) NOT NULL DEFAULT '1',
  `show_fic_room` char(1) NOT NULL DEFAULT 'n',
  `picture_room` varchar(50) NOT NULL DEFAULT '',
  `comment_room` text NOT NULL,
  `show_comment` char(1) NOT NULL DEFAULT 'n',
  `delais_max_resa_room` smallint(6) NOT NULL DEFAULT '-1',
  `delais_min_resa_room` smallint(6) NOT NULL DEFAULT '0',
  `allow_action_in_past` char(1) NOT NULL DEFAULT 'n',
  `dont_allow_modify` char(1) NOT NULL DEFAULT 'n',
  `order_display` smallint(6) NOT NULL DEFAULT '0',
  `delais_option_reservation` smallint(6) NOT NULL DEFAULT '0',
  `type_affichage_reser` smallint(6) NOT NULL DEFAULT '0',
  `moderate` tinyint(1) DEFAULT '0',
  `qui_peut_reserver_pour` char(1) NOT NULL DEFAULT '5',
  `active_ressource_empruntee` char(1) NOT NULL DEFAULT 'y',
  `who_can_see` smallint(6) NOT NULL DEFAULT '0',
  `room_warning` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3621 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_session`
--

DROP TABLE IF EXISTS `grr_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_session` (
  `sess_id` char(60) DEFAULT NULL,
  `sess_datas` text,
  `sess_expire` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_setting`
--

DROP TABLE IF EXISTS `grr_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_setting` (
  `NAME` varchar(32) NOT NULL DEFAULT '',
  `VALUE` text NOT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_setting_etablissement`
--

DROP TABLE IF EXISTS `grr_setting_etablissement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_setting_etablissement` (
  `NAME` varchar(32) NOT NULL DEFAULT '',
  `VALUE` text NOT NULL,
  `code_etab` varchar(20) NOT NULL,
  PRIMARY KEY (`NAME`,`code_etab`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GrrSite`
--

DROP TABLE IF EXISTS `grr_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sitecode` varchar(50) DEFAULT NULL,
  `sitename` varchar(50) NOT NULL DEFAULT '',
  `adresse_ligne1` varchar(38) DEFAULT NULL,
  `adresse_ligne2` varchar(38) DEFAULT NULL,
  `adresse_ligne3` varchar(38) DEFAULT NULL,
  `cp` varchar(5) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `pays` varchar(50) DEFAULT NULL,
  `tel` varchar(25) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=360 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grr_type_area`
--

DROP TABLE IF EXISTS `grr_type_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_type_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(30) NOT NULL DEFAULT '',
  `order_display` smallint(6) NOT NULL DEFAULT '0',
  `couleur` smallint(6) NOT NULL DEFAULT '0',
  `type_letter` char(5) NOT NULL DEFAULT '',
  `disponible` varchar(1) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`),
  KEY `index_grr_type_area_type_letter` (`type_letter`)
) ENGINE=MyISAM AUTO_INCREMENT=2607 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GrrUtilisateurs`
--

DROP TABLE IF EXISTS `grr_utilisateurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grr_utilisateurs` (
  `user` varchar(40) NOT NULL DEFAULT '',
  `nom` varchar(30) NOT NULL DEFAULT '',
  `prenom` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL DEFAULT '',
  `email` varchar(100) NOT NULL DEFAULT '',
  `statut` varchar(30) NOT NULL DEFAULT '',
  `etat` varchar(20) NOT NULL DEFAULT '',
  `default_site` smallint(6) NOT NULL DEFAULT '0',
  `default_area` smallint(6) NOT NULL DEFAULT '0',
  `defaultEtablissement` smallint(11) NOT NULL DEFAULT '0',
  `default_room` smallint(6) NOT NULL DEFAULT '0',
  `default_style` varchar(50) NOT NULL DEFAULT '',
  `default_list_type` varchar(50) NOT NULL DEFAULT '',
  `default_language` char(3) NOT NULL DEFAULT '',
  `source` varchar(10) NOT NULL DEFAULT 'local',
  PRIMARY KEY (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-22 16:05:25
