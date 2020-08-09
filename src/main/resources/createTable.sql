DROP DATABASE IF EXISTS  yunxi;
CREATE DATABASE yunxi;
USE yunxi;
CREATE TABLE `student` (
	id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) NOT NULL,
	age INT(4) NOT NULL,
	gender VARCHAR(8) NOT NULL,
	createTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updateTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `teacher` (
	id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(64) NOT NULL,
	nickName VARCHAR(64) NOT NULL,
	age INT(4) NOT NULL,
	gender VARCHAR(8) NOT NULL,
	createTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updateTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;