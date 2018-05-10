-- MySQL Workbench Synchronization
-- Generated: 2018-05-10 15:46
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Federico Battista

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT(10) UNSIGNED NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `company_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `student_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_company_idx` (`company_id` ASC),
  INDEX `fk_user_student1_idx` (`student_id` ASC),
  CONSTRAINT `fk_user_company`
    FOREIGN KEY (`company_id`)
    REFERENCES `mydb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `mydb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`company` (
  `id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `fiscal_code` VARCHAR(255) NULL DEFAULT NULL,
  `vat_number` VARCHAR(255) NULL DEFAULT NULL,
  `attorney` VARCHAR(255) NOT NULL,
  `jurisdiction` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`student` (
  `id` INT(10) UNSIGNED NOT NULL,
  `birthday` DATETIME NOT NULL,
  `matriculation_number` VARCHAR(255) NOT NULL,
  `birthplace_city` VARCHAR(255) NOT NULL,
  `birthplace_province` VARCHAR(255) NOT NULL,
  `birthplace_state` VARCHAR(255) NOT NULL,
  `residence_adress` VARCHAR(255) NOT NULL,
  `residence_city` VARCHAR(255) NOT NULL,
  `residence_province` VARCHAR(255) NOT NULL,
  `residence_state` VARCHAR(255) NOT NULL,
  `fiscal_code` VARCHAR(255) NOT NULL,
  `handicap` TINYINT(4) NOT NULL,
  `degree_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_student_degree1_idx` (`degree_id` ASC),
  CONSTRAINT `fk_student_degree1`
    FOREIGN KEY (`degree_id`)
    REFERENCES `mydb`.`degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`department` (
  `id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description_it-IT` VARCHAR(255) NULL DEFAULT NULL,
  `description_en-GB` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`degree` (
  `id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `department_id` INT(10) UNSIGNED NOT NULL,
  `class` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_degree_department1_idx` (`department_id` ASC),
  UNIQUE INDEX `class_UNIQUE` (`class` ASC),
  CONSTRAINT `fk_degree_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `mydb`.`department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`intership` (
  `id` INT(10) UNSIGNED NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `province` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `remote` TINYINT(4) NULL DEFAULT NULL,
  `schedule` TEXT NULL DEFAULT NULL,
  `length` INT(11) NOT NULL,
  `mode_it-IT` VARCHAR(255) NOT NULL,
  `mode_en-GB` VARCHAR(255) NULL DEFAULT NULL,
  `goals_it-IT` TEXT NULL DEFAULT NULL,
  `goals_en-GB` VARCHAR(45) NULL DEFAULT NULL,
  `refund` TINYINT(4) NULL DEFAULT NULL,
  `details_it-IT` TEXT NULL DEFAULT NULL,
  `details_en-GB` TEXT NULL DEFAULT NULL,
  `facilitations` TEXT NULL DEFAULT NULL,
  `company_id` INT(10) UNSIGNED NOT NULL,
  `active` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_intership_company1_idx` (`company_id` ASC),
  CONSTRAINT `fk_intership_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `mydb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`student_intership` (
  `student_id` INT(10) UNSIGNED NOT NULL,
  `intership_id` INT(10) UNSIGNED NOT NULL,
  `cfu` INT(11) NULL DEFAULT NULL,
  `professor_id` INT(10) UNSIGNED NOT NULL,
  `review` INT(10) UNSIGNED NULL DEFAULT NULL,
  `accepted` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`intership_id`, `professor_id`, `student_id`),
  INDEX `fk_student_has_intership_intership1_idx` (`intership_id` ASC),
  INDEX `fk_student_has_intership_student1_idx` (`student_id` ASC),
  INDEX `fk__professor1_idx` (`professor_id` ASC),
  CONSTRAINT `fk_student_has_intership_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `mydb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_has_intership_intership1`
    FOREIGN KEY (`intership_id`)
    REFERENCES `mydb`.`intership` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk__professor1`
    FOREIGN KEY (`professor_id`)
    REFERENCES `mydb`.`professor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mydb`.`professor` (
  `id` INT(10) UNSIGNED NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `department_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_professor_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_professor_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `mydb`.`department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
