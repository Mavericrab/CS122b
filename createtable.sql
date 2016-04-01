CREATE SCHEMA `moviedb` ;

CREATE  TABLE `moviedb`.`movies` (
  `id` INT NOT NULL  AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `year` INT NOT NULL ,
  `director` VARCHAR(100) NOT NULL ,
  `banner_url` VARCHAR(200)  ,
  `trailer_url` VARCHAR(200)  ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `moviedb`.`stars` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(50) NOT NULL ,
  `last_name` VARCHAR(50) NOT NULL ,
  `dob` DATETIME ,
  `photo_url` VARCHAR(200) ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `moviedb`.`stars_in_movies` (
  `star_id` INT NOT NULL ,
  `movie_id` INT NOT NULL ,

  CONSTRAINT `star_id`
    FOREIGN KEY (`star_id` )
    REFERENCES `moviedb`.`stars` (`id` )
   ,
  CONSTRAINT `movie_id`
    FOREIGN KEY (`movie_id` )
    REFERENCES `moviedb`.`movies` (`id` )
   );


CREATE  TABLE `moviedb`.`genres` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(32) NOT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `moviedb`.`genres_in_movies` (
  `genre_id` INT NOT NULL ,
  `movie_id` INT NOT NULL ,
  
    FOREIGN KEY (`genre_id` )
    REFERENCES `moviedb`.`genres` (`id` )
   ,
 
    FOREIGN KEY (`movie_id` )
    REFERENCES `moviedb`.`movies` (`id` )
   );

CREATE  TABLE `moviedb`.`creditcards` (
  `id` VARCHAR(20) NOT NULL ,
  `first_name` VARCHAR(50) NOT NULL ,
  `last_name` VARCHAR(50) NOT NULL ,
  `expiration` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `moviedb`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(50) NOT NULL ,
  `last_name` VARCHAR(50) NOT NULL ,
  `cc_id` VARCHAR(20) NOT NULL ,
  `address` VARCHAR(200) NOT NULL ,
  `email` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  
    FOREIGN KEY (`cc_id` )
    REFERENCES `moviedb`.`creditcards` (`id` )
    );

CREATE  TABLE `moviedb`.`sales` (
  `id` INT NOT NULL ,
  `customer_id` INT NOT NULL ,
  `movie_id` INT NOT NULL ,
  `sale_date` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,

  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id` )
    REFERENCES `moviedb`.`customers` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
 
    FOREIGN KEY (`movie_id` )
    REFERENCES `moviedb`.`movies` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);