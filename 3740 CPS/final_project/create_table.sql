use final_project;

CREATE TABLE `final_project`.`country` (
  `country_id` INT NOT NULL,
  `country_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE INDEX `country_name_UNIQUE` (`country_name` ASC) VISIBLE,
  UNIQUE INDEX `country_id_UNIQUE` (`country_id` ASC) VISIBLE);

INSERT INTO `country` (`country_id`, `country_name`) VALUES (1, 'Mondstadt');
INSERT INTO `country` (`country_id`, `country_name`) VALUES (2, 'Liyue');
INSERT INTO `country` (`country_id`, `country_name`) VALUES (3, 'Inadzuma');

CREATE TABLE `final_project`.`element` (
  `element_id` INT NOT NULL,
  `element_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`element_id`),
  UNIQUE INDEX `element_name_UNIQUE` (`element_name` ASC) VISIBLE);

INSERT INTO `element` (`element_id`, `element_name`) VALUES (1, 'Pyro');
INSERT INTO `element` (`element_id`, `element_name`) VALUES (2, 'Geo');
INSERT INTO `element` (`element_id`, `element_name`) VALUES (3, 'Dendro');
INSERT INTO `element` (`element_id`, `element_name`) VALUES (4, 'Cryo');
INSERT INTO `element` (`element_id`, `element_name`) VALUES (5, 'Electro');
INSERT INTO `element` (`element_id`, `element_name`) VALUES (6, 'Anemo');
INSERT INTO `element` (`element_id`, `element_name`) VALUES (7, 'Hydro');

CREATE TABLE `final_project`.`archon` (
  `archon_id` INT NOT NULL,
  `archon_name` VARCHAR(45) NOT NULL,
  `archon_country` VARCHAR(45) NOT NULL,
  `archon_element` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`archon_id`),
  FOREIGN KEY (`archon_country`) REFERENCES `country`(`country_name`),
  FOREIGN KEY (`archon_element`) REFERENCES `element`(`element_name`),
  UNIQUE INDEX `archon_id` (`archon_id` ASC) VISIBLE);

INSERT INTO `archon` (`archon_id`, `archon_name`, `archon_country`, `archon_element`) VALUES (1, 'Venti', 'Mondstadt', 'Anemo');
INSERT INTO `archon` (`archon_id`, `archon_name`, `archon_country`, `archon_element`) VALUES (2, 'Zhongli', 'Liyue', 'Geo');
INSERT INTO `archon` (`archon_id`, `archon_name`, `archon_country`, `archon_element`) VALUES (3, 'Shougun', 'Inadzuma', 'Electro');

CREATE TABLE `final_project`.`character` (
  `character_id` INT NOT NULL,
  `character_name` VARCHAR(45) NOT NULL,
  `character_age` INT NOT NULL CHECK(`character_age` >= 5),
  `character_country` VARCHAR(45) NOT NULL,
  `character_element` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`character_id`),
  FOREIGN KEY (`character_country`) REFERENCES `country`(`country_name`),
  FOREIGN KEY (`character_element`) REFERENCES `element`(`element_name`),
  UNIQUE INDEX `character_id` (`character_id` ASC) VISIBLE);

INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (1, 'Kazuha', 'Inadzuma', 18, 'Anemo');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (2, 'Bennett', 'Mondstadt', 18, 'Geo');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (3, 'Xingqiu', 'Liyue', 18, 'Electro');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (4, 'Ayaka', 'Inadzuma', 18, 'Cryo');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (5, 'Ganyu', 'Liyue', 18, 'Cryo');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (6, 'Albedo', 'Mondstadt', 18, 'Geo');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (7, 'Hutao', 'Liyue', 18, 'Pyro');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (8, 'Xiangling', 'Liyue', 18, 'Pyro');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (9, 'Kokomi', 'Inadzuma', 18, 'Hydro');
INSERT INTO `character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (10, 'Diona', 'Mondstadt', 7, 'Cryo');

CREATE TABLE `final_project`.`unpublished_character` (
  `character_id` INT NOT NULL,
  `character_name` VARCHAR(45) NOT NULL,
  `character_age` INT NOT NULL CHECK(`character_age` >= 5),
  `character_country` VARCHAR(45) NOT NULL,
  `character_element` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`character_id`),
  FOREIGN KEY (`character_country`) REFERENCES `country`(`country_name`),
  FOREIGN KEY (`character_element`) REFERENCES `element`(`element_name`),
  UNIQUE INDEX `character_id` (`character_id` ASC) VISIBLE);

INSERT INTO `unpublished_character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (99, 'Gorou', 'Inadzuma', 18, 'Geo');
INSERT INTO `unpublished_character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (98, 'Itto', 'Inadzuma', 18, 'Geo');
INSERT INTO `unpublished_character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (97, 'ShenHe', 'Liyue', 18, 'Cryo');
INSERT INTO `unpublished_character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (96, 'YunJin', 'Liyue', 18, 'Geo');

--- suppose this character Diona is unpublished
INSERT INTO `unpublished_character` (`character_id`, `character_name`, `character_country`, `character_age`, `character_element`) VALUES (95, 'Diona', 'Mondstadt', 7, 'Cryo');