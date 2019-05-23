INSERT INTO grr_utilisateurs (login, nom, prenom, password, email, statut, etat, default_site, default_area, default_room, default_style, default_list_type, default_language, source) VALUES('TEST', 'test', 'test', '5f4dcc3b5aa765d61d8327deb882cf99', '', 'utilisateur', 'actif', -1, -1, -1, '', 'item', 'fr', 'local');
INSERT INTO grr_site (`sitecode`, `sitename`, `adresse_ligne1`, `adresse_ligne2`, `adresse_ligne3`, `cp`, `ville`, `pays`, `tel`, `fax`) VALUES ('0370101A', 'Lycée Léonard de Vinci (Amboise) ', 'migration', '', '', '', '', '', '', '');
INSERT INTO grr_site (`sitecode`, `sitename`, `adresse_ligne1`, `adresse_ligne2`, `adresse_ligne3`, `cp`, `ville`, `pays`, `tel`, `fax`) VALUES ('0370102A', 'Lycée Léonard de Vinci (Amboise) ', 'migration', '', '', '', '', '', '', '');


INSERT INTO grr_area (`id`,`area_name`, `access`, `order_display`, `ip_adr`, `morningstarts_area`, `eveningends_area`, `duree_max_resa_area`, `resolution_area`, `eveningends_minutes_area`, `weekstarts_area`, `twentyfourhour_format_area`, `calendar_default_values`, `enable_periods`, `display_days`, `id_type_par_defaut`, `duree_par_defaut_reservation_area`, `max_booking`) VALUES ('1','Salles', 'a', 0, '', 8, 19, -1, 1800, 0, 1, 1, 'n', 'n', 'nyyyyyn', -1, 3600, -1);
INSERT INTO grr_room (`id`,`area_id`, `room_name`, `description`, `capacity`, `max_booking`, `statut_room`, `show_fic_room`, `picture_room`, `comment_room`, `show_comment`, `delais_max_resa_room`, `delais_min_resa_room`, `allow_action_in_past`, `dont_allow_modify`, `order_display`, `delais_option_reservation`, `type_affichage_reser`, `moderate`, `qui_peut_reserver_pour`, `active_ressource_empruntee`, `who_can_see`, `room_warning`) VALUES ('1',1, 'Salle du conseil', 'Salle du conseil du lycée Fictif', 0, -1, '1', 'n', '', '', 'n', -1, 0, 'n', 'n', 0, 0, 0, 0, '5', 'n', 0, '');
INSERT INTO grr_overload (`id_area`, `fieldname`, `fieldtype`, `fieldlist`, `obligatoire`, `affichage`, `confidentiel`, `overload_mail`) VALUES (1, '3. nom de l''élève :', 'text', '', 'y', 'y', 'n', 'y');
INSERT INTO grr_j_user_area (`id_area`,`login`) VALUES ('1','TEST');
INSERT INTO grr_j_site_area (`id_area`,`id_site`) VALUES ('1','1');
INSERT INTO grr_j_user_room (`id_room`,`login`) VALUES ('1','TEST');
INSERT INTO grr_j_mailuser_room (`id_room`,`login`) VALUES ('1','TEST');
