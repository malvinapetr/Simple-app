DROP SCHEMA IF EXISTS simple_app;
CREATE SCHEMA simple_app;
USE simple_app;


CREATE TABLE user_info (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(45) NOT NULL,
    gender ENUM('M','F') NOT NULL,
    birthdate DATE NOT NULL,
    work_address SMALLINT UNSIGNED,
    home_address SMALLINT UNSIGNED,
    PRIMARY KEY  (id),
    CONSTRAINT `fk_work_address` FOREIGN KEY (work_address) REFERENCES work_address (id) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT `fk_home_address` FOREIGN KEY (home_address) REFERENCES home_address (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE work_address (
  id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  address VARCHAR(60),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE home_address (
  id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  address VARCHAR(60),
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

