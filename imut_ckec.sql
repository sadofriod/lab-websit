/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.39 : Database - imut_ckec
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`imut_ckec` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `imut_ckec`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(32) DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '管理员密码',
  `head_sculpture` varchar(200) DEFAULT NULL COMMENT '管理员头像路径',
  `permission_type1` tinyint(1) DEFAULT NULL COMMENT '管理员权限1',
  `permission_type2` tinyint(1) DEFAULT NULL COMMENT '管理员权限2',
  `permission_type3` tinyint(1) DEFAULT NULL COMMENT '管理员权限3',
  `permission_type4` tinyint(1) DEFAULT NULL COMMENT '管理员权限4',
  `permission_type5` tinyint(1) DEFAULT NULL COMMENT '管理员权限5',
  `time_created` datetime DEFAULT NULL COMMENT '创建时间',
  `sex` tinyint(1) DEFAULT NULL COMMENT '1为男，0为女',
  `major` varchar(64) DEFAULT NULL COMMENT '专业',
  `description` varchar(250) DEFAULT NULL COMMENT '介绍信息',
  `cellphone` varchar(32) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_name` (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

/*Table structure for table `administrator_register` */

DROP TABLE IF EXISTS `administrator_register`;

CREATE TABLE `administrator_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `admin_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `head_sculpture` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `status` tinyint(1) DEFAULT NULL COMMENT '申请状态 0为申请中，1为申请成功',
  `time_created` datetime DEFAULT NULL COMMENT '创建时间',
  `power_id` int(11) DEFAULT NULL COMMENT '审批者id',
  `major` varchar(32) DEFAULT NULL COMMENT '专业信息',
  `description` varchar(250) DEFAULT NULL COMMENT '介绍信息',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `cellphone` varchar(32) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_name` (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator_register` */

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成员id',
  `member_name` varchar(32) DEFAULT NULL COMMENT '成员姓名',
  `major` varchar(32) DEFAULT NULL COMMENT '成员专业',
  `description` varchar(300) DEFAULT NULL COMMENT '成员介绍',
  `password` varchar(32) DEFAULT NULL COMMENT '成员密码',
  `head_sculpture` varchar(200) DEFAULT NULL COMMENT '成员头像路径',
  `sex` tinyint(1) DEFAULT NULL COMMENT '成员性别',
  `time_created` datetime DEFAULT NULL COMMENT '成员创建时间',
  `cellphone` varchar(40) DEFAULT NULL COMMENT '成员手机号',
  `permission_type1` tinyint(1) DEFAULT NULL COMMENT '成员权限1，值为0表示不拥有',
  `permission_type2` tinyint(1) DEFAULT NULL COMMENT '成员权限2',
  `permission_type3` tinyint(1) DEFAULT NULL COMMENT '成员权限3',
  `permission_type4` tinyint(1) DEFAULT NULL COMMENT '成员权限4',
  `permission_type5` tinyint(1) DEFAULT NULL COMMENT '成员权限5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_name` (`member_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member` */

/*Table structure for table `member_register` */

DROP TABLE IF EXISTS `member_register`;

CREATE TABLE `member_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '注册id',
  `member_name` varchar(64) DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `major` varchar(64) DEFAULT NULL COMMENT '专业',
  `description` varchar(250) DEFAULT NULL COMMENT '介绍信息',
  `head_sculpture` varchar(200) DEFAULT NULL COMMENT '头像存储地址',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `time_created` datetime DEFAULT NULL COMMENT '创建时间',
  `cellphone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) DEFAULT NULL COMMENT '申请状态 0为申请中，1为申请成功',
  `power_id` int(11) DEFAULT NULL COMMENT '审批此条者的id',
  `type_power` tinyint(1) DEFAULT NULL COMMENT '1为管理员，2为超级管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_name` (`member_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_register` */

/*Table structure for table `normal` */

DROP TABLE IF EXISTS `normal`;

CREATE TABLE `normal` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '普通用户id',
  `username` varchar(16) DEFAULT NULL COMMENT '用户名，唯一索引',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(32) DEFAULT NULL COMMENT '用户密码',
  `time_created` datetime DEFAULT NULL COMMENT '用户创建时间',
  `cellphone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) DEFAULT NULL COMMENT '1为男，0位女',
  `head_sculpture` varchar(200) DEFAULT NULL COMMENT '用户头像路径',
  `permission_type1` tinyint(1) DEFAULT NULL COMMENT '用户权限1  值默认为0,1为拥有',
  `permission_type2` tinyint(1) DEFAULT NULL COMMENT '用户权限2',
  `permission_type3` tinyint(1) DEFAULT NULL COMMENT '用户权限3',
  `permission_type4` tinyint(1) DEFAULT NULL COMMENT '用户权限4',
  `permission_type5` tinyint(1) DEFAULT NULL COMMENT '用户权限5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `normal` */

/*Table structure for table `super_administrator` */

DROP TABLE IF EXISTS `super_administrator`;

CREATE TABLE `super_administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '超级管理员id',
  `admin_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `head_sculpture` varchar(200) DEFAULT NULL COMMENT '头像路径',
  `permission_type1` tinyint(1) DEFAULT NULL COMMENT '用户权限1',
  `permission_type2` tinyint(1) DEFAULT NULL COMMENT '用户权限2',
  `permission_type3` tinyint(1) DEFAULT NULL COMMENT '用户权限3',
  `permission_type4` tinyint(1) DEFAULT NULL COMMENT '用户权限4',
  `permission_type5` tinyint(1) DEFAULT NULL COMMENT '用户权限5',
  `permission_type6` tinyint(1) DEFAULT NULL COMMENT '用户权限6',
  `permission_type7` tinyint(1) DEFAULT NULL COMMENT '用户权限7',
  `permission_type8` tinyint(1) DEFAULT NULL COMMENT '用户权限8',
  `permission_type9` tinyint(1) DEFAULT NULL COMMENT '用户权限9',
  `permission_type10` tinyint(1) DEFAULT NULL COMMENT '用户权限10',
  `time_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `super_administrator` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
