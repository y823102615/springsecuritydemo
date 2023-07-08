CREATE DATABASE  IF NOT EXISTS `securitydemo`;
USE `securitydemo`;


DROP TABLE IF EXISTS `authority_info`;

CREATE TABLE `authority_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '姓名',
  `authority` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


LOCK TABLES `authority_info` WRITE;

INSERT INTO `authority_info` VALUES (1,'user','hello'),(2,'user1','add');

UNLOCK TABLES;



DROP TABLE IF EXISTS `login_info`;

CREATE TABLE `login_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


LOCK TABLES `login_info` WRITE;

INSERT INTO `login_info` VALUES (1,'user','$2a$10$0LSbSGUqfLJCYBKSKYO6pum1IJ3jUxseYL09kavX2.6qmAwfpfZEO'),(2,'user1','$2a$10$1Lep4zWbRGCW8HSXmmEFTeFIK3B.9fRGONsbadiDFQK6lSAzwLAty');

UNLOCK TABLES;


DROP TABLE IF EXISTS `student_info`;

CREATE TABLE `student_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `age` int NOT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


LOCK TABLES `student_info` WRITE;

INSERT INTO `student_info` VALUES (1,'张三',18),(2,'李四',18);

UNLOCK TABLES;

