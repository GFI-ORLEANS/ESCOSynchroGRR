delete from grr_utilisateurs;
delete from grr_etablissement;
delete from grr_log;
delete from grr_entry;
delete from grr_site;
delete from grr_area;
delete from grr_room;
delete from grr_j_etablissement_site;
delete from grr_j_user_etablissement;
delete from grr_j_user_area;
delete from grr_j_site_area;
delete from grr_j_user_room;
delete from grr_j_mailuser_room;
delete from grr_etablissement_regroupement;
delete from grr_j_useradmin_etablissement;

commit ;
INSERT INTO grr_utilisateurs (login, nom, prenom, password, email, statut, etat, default_site, default_area, default_etablissement, default_room, default_style, default_list_type, default_language, source) VALUES('F08001j5', 'Juliana', 'FLORENCE', '', 'FLORENCE.Juliana@domaine.fr', 'utilisateur', 'actif', 0, 0, 2, 0, '', '', '', 'ext');
INSERT INTO grr_utilisateurs (login, nom, prenom, password, email, statut, etat, default_site, default_area, default_etablissement, default_room, default_style, default_list_type, default_language, source) VALUES('F1000ugr', 'F1000ugr', 'F1000ugr', '', 'F1000ugr@domaine.fr', 'utilisateur', 'actif', 0, 0, 2, 0, '', '', '', 'ext');
INSERT INTO grr_etablissement (`id`,`code`, `shortname`, `name`, `codepostal`, `adresse`, `ville`) VALUES ('1','0450822X', '0450822X', 'LGT-JACQUES COEUR-ac-ORL._TOURS', '18026', 'Test adresse', 'BOURGES CEDEX');
INSERT INTO grr_etablissement (`id`,`code`, `shortname`, `name`, `codepostal`, `adresse`, `ville`) VALUES ('3','0290009C', '0290009C', 'LGT-0290009C 0290009C', '18026', '0290009C adresse', '0290009C CEDEX');
INSERT INTO grr_etablissement (`id`,`code`, `shortname`, `name`, `codepostal`, `adresse`, `ville`) VALUES ('2','0180007A', 'LGT-JACQUES COEUR-ac-ORL._TOUR', 'LGT-JACQUES COEUR-ac-ORL._TOURS', '18026', 'Test adresse', 'BOURGES CEDEX');
INSERT INTO grr_utilisateurs (`login`, `nom`, `prenom`, `password`, `email`, `statut`, `etat`, `default_site`, `default_area`, `default_etablissement`, `default_room`, `default_style`, `default_list_type`, `default_language`, `source`) VALUES ('F080d4pe', 'Administrateur4t', 'grrtest', '', 'non_rdenseigne@netocentre.fr', 'administrateur', 'actif', -1, -1, -1, -1, '', 'select', 'fr', 'local');
INSERT INTO grr_utilisateurs (`login`, `nom`, `prenom`, `password`, `email`, `statut`, `etat`, `default_site`, `default_area`, `default_etablissement`, `default_room`, `default_style`, `default_list_type`, `default_language`, `source`) VALUES ('F080D5PE', 'Administrateurtest', 'grrtest2', '', 'non_rdenseigne@netocentre.fr', 'utilisateur', 'actif', -1, -1, -1, -1, '', 'select', 'fr', 'ext');
INSERT INTO grr_log (`LOGIN`, `START`, `SESSION_ID`, `REMOTE_ADDR`, `USER_AGENT`, `REFERER`, `AUTOCLOSE`, `END`) VALUES ('F080D5PE',  '2010-11-09 16:54:31', '', '192.168.1.92', 'Windows 7 Firefox 63.0', 'https://lycees.netocentre.fr/', null, '2010-11-09 16:54:31');
INSERT INTO grr_entry (`id`,`start_time`, `end_time`, `entry_type`, `repeat_id`, `room_id`, `timestamp`, `create_by`, `beneficiaire_ext`, `beneficiaire`, `name`, `type`, `description`, `statut_entry`, `option_reservation`, `overload_desc`, `moderate`, `jours`, `clef`, `courrier`) VALUES ('1',1249897200, 1049899000, 0, 0, 1, '2010-02-11 15:45:21', 'TEST', '', 'TEST', 'test1', 'A', '', '-', -1, '', 0, 0, 0, 0);
INSERT INTO grr_entry (`id`,`start_time`, `end_time`, `entry_type`, `repeat_id`, `room_id`, `timestamp`, `create_by`, `beneficiaire_ext`, `beneficiaire`, `name`, `type`, `description`, `statut_entry`, `option_reservation`, `overload_desc`, `moderate`, `jours`, `clef`, `courrier`) VALUES ('2',1839824092, 1039824992, 0, 0, 1, '2010-02-11 15:45:21', 'TEST', '', 'TEST', 'test1', 'A', '', '-', -1, '', 0, 0, 0, 0);
INSERT INTO grr_site (`id`,`sitecode`, `sitename`, `adresse_ligne1`, `adresse_ligne2`, `adresse_ligne3`, `cp`, `ville`, `pays`, `tel`, `fax`) VALUES ('1','0370101A', 'Lycée Léonard de Vinci (Amboise) ', 'multi', '', '', '', '', '', '', '');
INSERT INTO grr_site (`id`,`sitecode`, `sitename`, `adresse_ligne1`, `adresse_ligne2`, `adresse_ligne3`, `cp`, `ville`, `pays`, `tel`, `fax`) VALUES ('2','0370102A', 'Lycée Léonard de Vinci (Amboise) ', 'multi', '', '', '', '', '', '', '');

INSERT INTO grr_area (`id`,`area_name`, `access`, `order_display`, `ip_adr`, `morningstarts_area`, `eveningends_area`, `duree_max_resa_area`, `resolution_area`, `eveningends_minutes_area`, `weekstarts_area`, `twentyfourhour_format_area`, `calendar_default_values`, `enable_periods`, `display_days`, `id_type_par_defaut`, `duree_par_defaut_reservation_area`, `max_booking`) VALUES ('1','Salles', 'a', 0, '', 8, 19, -1, 1800, 0, 1, 1, 'n', 'n', 'nyyyyyn', -1, 3600, -1);
INSERT INTO grr_room (`id`,`area_id`, `room_name`, `description`, `capacity`, `max_booking`, `statut_room`, `show_fic_room`, `picture_room`, `comment_room`, `show_comment`, `delais_max_resa_room`, `delais_min_resa_room`, `allow_action_in_past`, `dont_allow_modify`, `order_display`, `delais_option_reservation`, `type_affichage_reser`, `moderate`, `qui_peut_reserver_pour`, `active_ressource_empruntee`, `who_can_see`, `room_warning`) VALUES ('1',1, 'Salle du conseil', 'Salle du conseil du lycée Fictif', 0, -1, '1', 'n', '', '', 'n', -1, 0, 'n', 'n', 0, 0, 0, 0, '5', 'n', 0, '');
INSERT INTO grr_j_etablissement_site (`id_site`,`id_etablissement`) VALUES ('1','1');
INSERT INTO grr_j_user_etablissement (`id_etablissement`,`login`) VALUES ('1','F08001j5');
INSERT INTO grr_j_user_etablissement (`id_etablissement`,`login`) VALUES ('1','F1000ugr');
INSERT INTO grr_j_useradmin_etablissement (`id_etablissement`,`login`) VALUES ('3','F1000ugr');
INSERT INTO grr_j_user_area (`id_area`,`login`) VALUES ('1','F08001j5');
INSERT INTO grr_j_site_area (`id_area`,`id_site`) VALUES ('1','1');
INSERT INTO grr_j_user_room (`id_room`,`login`) VALUES ('1','F08001j5');
INSERT INTO grr_j_mailuser_room (`id_room`,`login`) VALUES ('1','F08001j5');


INSERT INTO grr_etablissement_regroupement (`code_etablissement_secondaire`,`code_etablissement_principal`) VALUES ('0290009C','0450822X');

commit ;