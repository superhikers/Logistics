/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.15 : Database - logistics
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`logistics` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `logistics`;

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_username` varchar(50) DEFAULT NULL COMMENT '管理员用户名',
  `a_phone` varchar(20) DEFAULT '未填' COMMENT '管理员电话',
  `a_email` varchar(50) DEFAULT '未填' COMMENT '管理员邮箱',
  `a_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `a_state` varchar(50) DEFAULT '已启用' COMMENT '状态',
  `a_password` varchar(50) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`a_username`,`a_phone`,`a_email`,`a_date`,`a_state`,`a_password`) values (1,'admin','13232323232','a@qq.com',NULL,'已启用','admin'),(2,'admin2',NULL,NULL,NULL,'已启用','123'),(3,'jack','13222282938','a@qq.com',NULL,'已启用','aaa'),(4,'marry','13278432345','a@qq.com',NULL,'已删除','123'),(5,'aaa','13222222223','',NULL,'已删除','a'),(6,'abc','13224543234','a@qq.com',NULL,'已启用','aaaaaa'),(7,'黄鹤','13234567897','a@qq.com',NULL,'已启用','aaaaaa'),(9,'aaasss','13222222222','a@qq.com','2019-12-08 10:53:52','已删除','aaaaaa');

/*Table structure for table `order_` */

DROP TABLE IF EXISTS `order_`;

CREATE TABLE `order_` (
  `o_id` int(20) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(50) DEFAULT NULL COMMENT '发货姓名',
  `f_phone` varchar(50) DEFAULT NULL COMMENT '发货电话',
  `f_province` varchar(50) DEFAULT NULL COMMENT '发货省',
  `f_address` varchar(50) DEFAULT NULL COMMENT '发货地址',
  `s_name` varchar(50) DEFAULT NULL COMMENT '收货姓名',
  `s_phone` varchar(50) DEFAULT NULL COMMENT '收货电话',
  `s_province` varchar(50) DEFAULT NULL COMMENT '收货省',
  `s_address` varchar(50) DEFAULT NULL COMMENT '收货地址',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `u_username` varchar(50) DEFAULT NULL COMMENT '订单用户',
  `o_number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `o_startDate` varchar(50) DEFAULT NULL COMMENT '订单开始时间',
  `o_endDate` varchar(50) DEFAULT NULL COMMENT '订单结束时间',
  `o_state` varchar(50) DEFAULT '未接单' COMMENT '订单状态',
  `o_location` varchar(100) DEFAULT '无' COMMENT '订单位置',
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `order_` */

insert  into `order_`(`o_id`,`f_name`,`f_phone`,`f_province`,`f_address`,`s_name`,`s_phone`,`s_province`,`s_address`,`remark`,`u_username`,`o_number`,`o_startDate`,`o_endDate`,`o_state`,`o_location`) values (17,'lin','13425463728','广东省','东莞市东城区','jerry','13427685748','广东省','佛山市三水区','哈哈哈','lin','20191126103515723','2019-11-26 10:35:15',NULL,'派件中','佛山派件中心已揽件'),(18,'lin','13425463728','广东省','东莞市东城区','jerry','13427685748','广东省','佛山市三水区','哈哈哈','lin','20191126104152509','2019-11-26 10:41:52',NULL,'已接单','无'),(22,'lin','13425463728','广东省','东莞市东城区','jerry','13427685748','广东省','佛山市三水区','哈哈哈','lin','20191126103515722','2019-11-26 10:35:15',NULL,'派件中','东莞市派件中心已揽件'),(23,'lin','13425463728','广东省','东莞市东城区','jerry','13427685748','广东省','佛山市三水区','哈哈哈','lin','20191126104152508','2019-11-26 10:41:52',NULL,'已接单','无'),(24,'lin','13425463728','广东省','佛山市三水区','abc','13243546372','湖南省','长沙市湘桥区','啊啊啊啊啊','lin','20191126105325525','2019-11-26 10:53:25',NULL,'已接单','无'),(26,'abc','13243546372','广东省','东莞市东莞区','jerry','13427685748','广东省','佛山市三水区','wu','abc','20191210140735189','2019-12-10 14:07:35',NULL,'已发货','无'),(27,'abc','13243546372','广东省','东莞市东莞区','lin','13425463728','广东省','佛山市三水区','','abc','20191210140927143','2019-12-10 14:09:27','2019-12-12 17:48:00','确认收货','广东分公司已收件啊'),(28,'abc','13243546372','广东省','东莞市东莞区','lin','13425463728','广东省','佛山市三水区','','abc','20191210140946878','2019-12-10 14:09:46','2019-12-12 11:41:36','未接单','无'),(30,'lin','13425463728','广东省','东莞市东城区','锡林郭勒','13223455621','新疆省','伊犁','666','lin','20191212104629572','2019-12-12 10:46:29',NULL,'已接单','无'),(31,'lin','13425463728','广东','深圳','哇哈哈嘻嘻嘻哈哈','13223455621','广东','佛山','','lin','20191212104921176','2019-12-12 10:49:21',NULL,'未接单','无');

/*Table structure for table `order_staff` */

DROP TABLE IF EXISTS `order_staff`;

CREATE TABLE `order_staff` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `o_number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `s_userid` varchar(50) DEFAULT NULL COMMENT '接单员id',
  `s_name` varchar(50) DEFAULT NULL COMMENT '接单员姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `order_staff` */

insert  into `order_staff`(`id`,`o_number`,`s_userid`,`s_name`) values (4,'20191126103515723','1','王五'),(5,'20191126104152509','1','王五'),(6,'20191126103515722','1','王五'),(7,'20191126104152508','1','王五'),(8,'20191126105325525','1','王五'),(9,'20191210140735189','1','王五'),(12,'20191210140946878','1','王五'),(13,'20191212104629572','1','王五'),(14,'20191210140927143','201912112154243','李四');

/*Table structure for table `positions` */

DROP TABLE IF EXISTS `positions`;

CREATE TABLE `positions` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `o_number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `p_time` varchar(50) DEFAULT NULL COMMENT '时间',
  `p_position` varchar(50) DEFAULT NULL COMMENT '位置信息',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `positions` */

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `s_name` varchar(50) DEFAULT '未填写' COMMENT '员工姓名',
  `s_age` varchar(10) DEFAULT '未填写' COMMENT '员工年龄',
  `s_sex` varchar(20) DEFAULT '未填写' COMMENT '员工性别',
  `s_phone` varchar(20) DEFAULT '未填写' COMMENT '员工电话',
  `s_address` varchar(100) DEFAULT '未填写' COMMENT '员工地址',
  `s_state` varchar(20) DEFAULT '已启用' COMMENT '员工状态',
  `s_userid` varchar(50) NOT NULL COMMENT '登录用id',
  `s_password` varchar(50) NOT NULL DEFAULT '123456' COMMENT '密码',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `staff` */

insert  into `staff`(`s_id`,`s_name`,`s_age`,`s_sex`,`s_phone`,`s_address`,`s_state`,`s_userid`,`s_password`) values (10,'张三','30','男','13778374859','广东省佛山市三水区','已启用','3','123456'),(11,'李四','38','男','13882947985','广东省广州市','已启用','2','123456'),(12,'王五','40','男','13859383958','湖南省长沙市湘桥区','已启用','1','123456'),(13,'李四','22','男','13222222222','广东','已启用','201912112154','123456'),(14,'黄鹤','35','男','13222323223','佛山市三水区','已启用','201912121270','123456');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `u_username` varchar(50) NOT NULL COMMENT '账号',
  `u_password` varchar(50) DEFAULT NULL COMMENT '密码',
  `u_phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `u_address` varchar(100) DEFAULT '未填写',
  `u_sex` varchar(10) DEFAULT '未填写',
  `u_state` varchar(30) DEFAULT '已启用' COMMENT '用户状态',
  `u_email` varchar(50) DEFAULT '未填写' COMMENT '邮箱',
  PRIMARY KEY (`u_id`,`u_username`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`u_id`,`u_username`,`u_password`,`u_phone`,`u_address`,`u_sex`,`u_state`,`u_email`) values (37,'abc','123','13243546372','广东','未填写','已启用','未填写'),(38,'lin','123','13425463728','广东省东莞市','男','已启用','a@qq.cmm'),(39,'jerry','123','13427685748','未填写','女','已启用','未填写'),(42,'呀哈哈','123','13222244356','未填写','女','已删除','未填写'),(43,'呀哈哈哈','123456','13223455321','未填写','女','已启用','未填写'),(44,'呀嘻嘻嘻哈哈','123456','13223455321','未填写','女','已删除','未填写'),(45,'哇哈哈嘻嘻嘻哈哈','123456','13223455621','未填写','女','已删除','未填写'),(46,'哟呵呵嘻哈哈','123456','13223455621','未填写','男','已启用','未填写'),(47,'劈里啪啦','123456','13223455621','未填写','女','已删除','未填写'),(48,'锡林郭勒','123456','13223455621','未填写','男','已启用','未填写'),(49,'admin','admin','13245467789','未填写','男','已启用','未填写'),(50,'ccc','ccc','13939393933','未填写','未填写','已启用','未填写');

/*Table structure for table `user_del` */

DROP TABLE IF EXISTS `user_del`;

CREATE TABLE `user_del` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `u_username` varchar(50) DEFAULT NULL COMMENT '账号',
  `u_password` varchar(50) DEFAULT NULL COMMENT '密码',
  `u_phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `u_address` varchar(50) DEFAULT '未填写' COMMENT '地址',
  `u_sex` varchar(10) DEFAULT '未填写' COMMENT '性别',
  `d_time` varchar(50) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_del` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
