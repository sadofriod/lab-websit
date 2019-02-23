/*
 Navicat Premium Data Transfer

 Source Server         : CNCAB
 Source Server Type    : MySQL
 Source Server Version : 50632
 Source Host           : 60.205.208.104:3306
 Source Schema         : CNCAB

 Target Server Type    : MySQL
 Target Server Version : 50632
 File Encoding         : 65001

 Date: 23/02/2019 19:05:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for CNCAB_TITLE_DATA
-- ----------------------------
DROP TABLE IF EXISTS `CNCAB_TITLE_DATA`;
CREATE TABLE `CNCAB_TITLE_DATA` (
  `title_id` int(6) NOT NULL,
  `title_name` varchar(32) DEFAULT NULL,
  `title_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for CNCAB_USER_MANAGER
-- ----------------------------
DROP TABLE IF EXISTS `CNCAB_USER_MANAGER`;
CREATE TABLE `CNCAB_USER_MANAGER` (
  `user_id` int(32) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `user_detail` longtext COMMENT 'other json 5.7',
  `cellphone` varchar(16) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `headSculpyure` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
