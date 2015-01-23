SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `agriculture` ;
CREATE SCHEMA IF NOT EXISTS `agriculture` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `agriculture` ;

-- -----------------------------------------------------
-- Table `agriculture`.`provinces`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`provinces` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`provinces` (
  `province_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `province_name` VARCHAR(45) NOT NULL,
  `province_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`province_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`cities` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`cities` (
  `city_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `city_name` VARCHAR(45) NOT NULL,
  `city_code` VARCHAR(45) NOT NULL,
  `province_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`city_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 345
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`districts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`districts` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`districts` (
  `district_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `district_name` VARCHAR(45) NOT NULL,
  `district_code` VARCHAR(45) NOT NULL,
  `city_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`district_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3124
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`users` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `real_name` VARCHAR(45) NOT NULL,
  `mailbox` VARCHAR(45) NULL,
  `company_name` VARCHAR(45) NOT NULL,
  `company_province` BIGINT(20) NOT NULL,
  `company_city` BIGINT(20) NOT NULL,
  `company_district` BIGINT(20) NOT NULL,
  `company_address` VARCHAR(45) NOT NULL,
  `mobile_phone` VARCHAR(45) NOT NULL,
  `desk_phone` VARCHAR(45) NULL,
  `job_title` VARCHAR(45) NOT NULL,
  `enabled` CHAR(1) NOT NULL DEFAULT '1',
  `creation_time` TIMESTAMP NOT NULL,
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `cellphone_UNIQUE` (`mobile_phone` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_users_company_province_idx` (`company_province` ASC),
  INDEX `fk_users_company_district_idx` (`company_district` ASC),
  INDEX `fk_users_company_city_idx` (`company_city` ASC),
  CONSTRAINT `fk_users_company_province`
    FOREIGN KEY (`company_province`)
    REFERENCES `agriculture`.`provinces` (`province_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_company_city`
    FOREIGN KEY (`company_city`)
    REFERENCES `agriculture`.`cities` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_company_district`
    FOREIGN KEY (`company_district`)
    REFERENCES `agriculture`.`districts` (`district_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 209
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`applications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`applications` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`applications` (
  `application_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `comments` VARCHAR(225) NOT NULL,
  `creation_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`application_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_applications_username`
    FOREIGN KEY (`username`)
    REFERENCES `agriculture`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`authorities` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`authorities` (
  `authorities_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`authorities_id`),
  INDEX `fk_username_idx` (`username` ASC),
  CONSTRAINT `fk_username`
    FOREIGN KEY (`username`)
    REFERENCES `agriculture`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`persistent_logins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`persistent_logins` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`persistent_logins` (
  `username` VARCHAR(64) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`),
  INDEX `fk_persistent_logins_username_idx` (`username` ASC),
  CONSTRAINT `fk_persistent_logins_username`
    FOREIGN KEY (`username`)
    REFERENCES `agriculture`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;



-- -----------------------------------------------------
-- Table `agriculture`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`questions` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`questions` (
  `question_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `question_content` VARCHAR(500) NOT NULL,
  `option1` VARCHAR(45) NOT NULL,
  `option2` VARCHAR(45) NOT NULL,
  `option3` VARCHAR(45) NOT NULL,
  `option4` VARCHAR(45) NOT NULL,
  `option5` VARCHAR(45) NULL DEFAULT NULL,
  `single_selection_only` INT NOT NULL,
  `questionnaire_number` INT NOT NULL,
  PRIMARY KEY (`question_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`sms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`sms` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`sms` (
  `sms_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `sms_validation_code` VARCHAR(45) NOT NULL,
  `sent_time` TIMESTAMP NOT NULL,
  `mobile_phone` VARCHAR(45) NOT NULL,
  `sms_content` VARCHAR(160) NOT NULL,
  `sms_status` BIGINT(20) NOT NULL,
  PRIMARY KEY (`sms_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `agriculture`.`questionnaires`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriculture`.`questionnaires` ;

CREATE TABLE IF NOT EXISTS `agriculture`.`questionnaires` (
  `application_id` BIGINT NOT NULL,
  `questionnaire_number` INT NOT NULL,
  `answers` VARCHAR(450) NOT NULL,
  `questionnaire_committed` TINYINT(1) NOT NULL,
  `creation_time` TIMESTAMP NOT NULL,
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `fk_questionniares_application_id_idx` (`application_id` ASC),
  PRIMARY KEY (`application_id`, `questionnaire_number`),
  CONSTRAINT `fk_questionniares_application_id`
    FOREIGN KEY (`application_id`)
    REFERENCES `agriculture`.`applications` (`application_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
