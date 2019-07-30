/*
SQLyog v10.2 
MySQL - 5.0.96-community-nt : Database - bysj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bysj` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bysj`;

/*Table structure for table `classes` */

DROP TABLE IF EXISTS `classes`;

CREATE TABLE `classes` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `classesId` varchar(10) NOT NULL COMMENT '班级编号',
  `majorId` varchar(10) NOT NULL COMMENT '所属专业',
  `cName` varchar(16) NOT NULL COMMENT '班级名称',
  PRIMARY KEY  (`classesId`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `classes` */

insert  into `classes`(`id`,`classesId`,`majorId`,`cName`) values (1,'1001','1','软件1501'),(2,'1002','1','软件1502'),(3,'1003','1','软件1521'),(5,'1004','2','物联1501'),(6,'1005','2','物联1502'),(7,'1006','4','学前1501');

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `deptId` varchar(10) NOT NULL COMMENT '院系ID',
  `dName` varchar(16) NOT NULL COMMENT '院系名',
  PRIMARY KEY  (`deptId`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`id`,`deptId`,`dName`) values (1,'10','计算机学院'),(2,'11','教育与科学学院'),(3,'12','体育学院'),(4,'13','美术学院'),(5,'14','物电院'),(6,'15','数信院'),(7,'16','设计学院');

/*Table structure for table `docstate` */

DROP TABLE IF EXISTS `docstate`;

CREATE TABLE `docstate` (
  `id` int(11) NOT NULL auto_increment,
  `stateId` varchar(2) character set gbk NOT NULL,
  `stateName` varchar(10) character set gbk NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `Id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `docstate` */

insert  into `docstate`(`id`,`stateId`,`stateName`) values (1,'0','未审核'),(2,'1','一级审核未通过'),(3,'2','一级审核通过'),(4,'3','二级审核未通过'),(5,'4','二级审核通过'),(6,'5','已上传修改意见'),(8,'6','已申请'),(9,'7','退回修改'),(10,'11','一辩'),(11,'12','二辩');

/*Table structure for table `doctype` */

DROP TABLE IF EXISTS `doctype`;

CREATE TABLE `doctype` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `typeId` varchar(2) NOT NULL COMMENT '文档类型编号',
  `typeName` varchar(16) NOT NULL COMMENT '类型名',
  PRIMARY KEY  (`typeId`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `doctype` */

insert  into `doctype`(`id`,`typeId`,`typeName`) values (1,'01','申报表'),(2,'02','选题表'),(3,'03','任务书'),(4,'04','开题报告'),(5,'05','指导记录表'),(6,'06','指导教师评阅表'),(7,'07','评阅教师评审表'),(8,'08','答辩申请表'),(9,'09','答辩专家组评审表'),(10,'10','成绩表'),(11,'12','选题表修改意见'),(12,'13','任务书修改意见'),(14,'14','开题修改意见报告'),(13,'15','指导记录表修改意见'),(15,'18','答辩申请表修改意见'),(17,'19','毕业论文修改意见'),(18,'21','学生自拟'),(19,'22','教师命题'),(16,'99','毕业论文');

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `docId` varchar(12) NOT NULL COMMENT '唯一标识文档，学号+文档类型',
  `stu_id` varchar(10) NOT NULL COMMENT '学生学号',
  `tea_id` varchar(10) NOT NULL COMMENT '教师工号',
  `deptId` varchar(10) NOT NULL COMMENT '学院代码',
  `majorId` varchar(10) NOT NULL COMMENT '专业编号',
  `classesId` varchar(10) NOT NULL COMMENT '班级编号',
  `typeId` varchar(2) NOT NULL COMMENT '文档类型编号',
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '文档上传时间',
  `location` varchar(100) character set gbk NOT NULL COMMENT '文档保存位置',
  `stateId` varchar(1) NOT NULL default '0' COMMENT '文档状态0未审核,1一级审核未通过,2一级审核通过，3二级审核未通过，4二级审核通过',
  PRIMARY KEY  (`docId`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='文档记录表';

/*Data for the table `document` */

insert  into `document`(`id`,`docId`,`stu_id`,`tea_id`,`deptId`,`majorId`,`classesId`,`typeId`,`time`,`location`,`stateId`) values (62,'151006410609','1510064106','2','10','1','1001','09','2019-05-20 12:44:11','/doc/10/2015/1001/1510064106/151006410609.docx','0'),(60,'151006410707','1510064107','2','10','1','1001','07','2019-05-13 20:22:35','/doc/10/2015/1001/1510064107/151006410707.docx','0'),(58,'151006410801','1510064108','2','10','1','1001','01','2019-05-20 12:44:11','/doc/10/2015/1001/1510064108/151006410801.docx','0'),(67,'151006410802','1510064108','2','10','1','1001','02','2019-05-20 12:47:03','/doc/10/2015/1001/1510064108/151006410802.doc','0'),(69,'151006410804','1510064108','2','10','1','1001','04','2019-05-20 12:49:04','/doc/10/2015/1001/1510064108/151006410804.doc','0'),(70,'151006410805','1510064108','2','10','1','1001','05','2019-05-20 12:50:15','/doc/10/2015/1001/1510064108/151006410805.doc','0'),(73,'151006410806','1510064108','2','10','1','1001','06','2019-05-20 12:54:30','/doc/10/2015/1001/1510064108/151006410806.doc','0'),(71,'151006410808','1510064108','2','10','1','1001','08','2019-05-20 12:51:26','/doc/10/2015/1001/1510064108/151006410808.doc','0'),(74,'151006410810','1510064108','2','10','1','1001','10','2019-05-20 12:55:43','/doc/10/2015/1001/1510064108/151006410810.doc','0'),(72,'151006410899','1510064108','2','10','1','1001','99','2019-05-20 12:51:50','/doc/10/2015/1001/1510064108/151006410899.doc','0'),(61,'151006411206','1510064112','2','10','1','1001','06','2019-05-13 20:23:06','/doc/10/2015/1001/1510064112/151006411206.doc','0');

/*Table structure for table `major` */

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `id` int(11) NOT NULL auto_increment,
  `majorId` varchar(10) NOT NULL,
  `majorName` varchar(16) NOT NULL,
  `deptId` varchar(10) NOT NULL,
  KEY `Id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `major` */

insert  into `major`(`id`,`majorId`,`majorName`,`deptId`) values (1,'1','软件工程','10'),(2,'2','物联网工程','10'),(3,'3','计算机科学与技术','10'),(4,'4','学前教育','11');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `noticeId` int(11) NOT NULL auto_increment,
  `authorName` varchar(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `abs` varchar(100) default NULL,
  `content` text NOT NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `isDelete` int(1) default '0',
  `type` varchar(10) default NULL,
  PRIMARY KEY  (`noticeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL,
  `permission_name` varchar(50) default NULL,
  `permission_url` varchar(100) default NULL,
  `parentId` int(2) default NULL,
  `state` varchar(10) default NULL,
  `ionCls` varchar(50) default NULL,
  PRIMARY KEY  (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`permission_id`,`permission_name`,`permission_url`,`parentId`,`state`,`ionCls`) values (0,NULL,NULL,0,NULL,NULL),(1,'查看个人信息','userInfo',45,NULL,''),(2,'编辑个人信息','userInfoEditPage',45,NULL,''),(3,'修改密码','userPasswordEditPage',45,NULL,''),(4,'查看小组信息','toGroupInfo',45,'',''),(6,'进度信息','toProcess',45,'',''),(8,'院系信息','toDeptInfo',45,'',''),(10,'专业信息','toMajorInfo',45,'',''),(12,'班级信息','toClassesInfo',45,'',''),(13,'编辑公告','toNews',45,'',''),(16,'课题查询','toTopic',46,'',''),(17,'课题申请结果','toTopicResult',46,'',''),(18,'课题审批','toTopicChk',46,'',''),(19,'文档类型管理','toDocType',47,'',''),(21,'查询个人文档','toSRead',47,'',''),(22,'查询学生文档','toSDoc',47,'',''),(24,'文档上传','toUpload',47,'',''),(28,'文档审批','toDocChk',47,'',''),(32,'答辩申请','toSG',48,'',''),(33,'答辩专家组分组','toTG',48,'',''),(35,'成绩查询','toScore',48,'',''),(36,'院系用户管理','toUserList',49,'',''),(38,'系统角色管理','toRole',49,'',''),(40,'菜单树管理','toPermission',49,'',''),(45,'基本信息管理','',0,'closed',NULL),(46,'选题管理','',0,'closed',NULL),(47,'文档管理','',0,'closed',NULL),(48,'答辩管理','',0,'closed',NULL),(49,'权限管理','',0,'closed',NULL),(50,'答辩审批','toSSpeechApply',48,'',''),(51,'查看学生答辩申请','toSSpeechA',48,'',''),(52,'查看学生答辩分组','toSpeechGroup',48,'',''),(53,'重置密码','toResetPassword',49,'','');

/*Table structure for table `process` */

DROP TABLE IF EXISTS `process`;

CREATE TABLE `process` (
  `id` int(11) NOT NULL auto_increment,
  `process_id` varchar(10) NOT NULL,
  `user_name` varchar(10) NOT NULL,
  `operate` varchar(50) NOT NULL,
  `state` varchar(10) NOT NULL,
  `remark` text,
  PRIMARY KEY  (`process_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `process` */

insert  into `process`(`id`,`process_id`,`user_name`,`operate`,`state`,`remark`) values (1,'1','教学秘书','新建并启动1个毕业设计论文管理任务','已完成','教学秘书可以设置和修改毕业设计的各个环节的时间段'),(10,'10','教学院长','复核选题','已完成','复核整改选题，通过或再退回整改'),(11,'11','教学秘书','选题终审，启动开题','已完成','所有选题终审完毕后教师和学生均无权修改选题，弱有特殊情况应提交纸质申请有教学副院长审批后教学秘书进行修改。'),(12,'12','学生','毕设开题','已完成','选题终审后，由教学秘书启动开题环节。'),(13,'13','教师','开题申请审核','未完成','提交开题申请'),(14,'14','学生','开题答辩','未完成','可以同意开题，或退回修改，指导满足要求后同意'),(15,'15','教师','论文指导','未完成','学生将自己开题答辩的记录上传至系统'),(16,'16','学生','论文电子稿上传','未完成','教师将每次指导学生的记录上传至系统'),(17,'17','教师','评审','未完成','学生将自己和老师确定的论文稿件上传至系统'),(18,'18','教学秘书','交叉评审安排','未完成','指导教师评审'),(19,'19','教师','交叉评审','未完成','分配交叉评审教师'),(2,'2','教学秘书','为每个指导教师分配指导学生的人数','已完成','在这里，教学秘书可以单独添加新指导教师'),(20,'20','学生','答辩申请','未完成','教师进行交叉评审（盲审，教师看不到学生和指导教师的信息）'),(21,'21','教师','答辩申请审核','未完成','学生提交答辩申请'),(22,'22','学生','毕业答辩','未完成','指导教师审核自己指导学生的答辩申请，同意或退修'),(23,'23','教师','成绩评定','未完成','学生答辩'),(24,'24','教学秘书','成绩上传','未完成','答辩小组教师进行综合评价'),(25,'25','教学秘书','毕业设计总结','未完成','上传答辩成绩，评议优秀论文。'),(3,'3','指导教师','指导教师添加选题名称和要求 ','已完成','指导教师必须添加多于自己指导人数个数的选题，原则上没有上限。对于学生自选题目，提交时可以选择（学生自选类型），对于教师自命题目，进行填写题目名称和要求'),(4,'4','学生','学生选题','已完成','学生进入系统后，能够看到所有还有剩余指导名额的教师和题目要求，这里要求能看到教师的电话等联系方式。然后学生选择一名知道教师的一个题目，并可以填写选题理由。如果是学生自拟题目，要求学生填写自拟题目的名称、具体工作内容和选题理由。\r\n'),(5,'5','指导教师','确认、退回修改、和拒绝选题','已完成','教师可以被超过其指导人数的学生选题，但只能按照指导人数进行选题确认。如果同意选题，直接点同意选题；如果感觉学生选题理由不充分或学生自选题目不完全符合要求；可以退回让学生修改，修改通过后还可再次进行确认、退回修改、和拒绝操作，但教师要明确说明修改意见；否则，如果教师认为学生不能胜任题目，可直接拒绝学生的选题申请。被拒绝的学生可以选该导师的其他题目或其他导师均可。\r\n教师的确认名额满额后，其他学生将无法再选择该老师的题目\r\n'),(6,'6','学生','退回修改','已完成','被退回修改的学生可以按照老师的要求修改，也可以不做修改，另选其他导师。'),(7,'7','教学秘书','选题审核申请','已完成','所有教师和学生的选题结束之后，教学秘书做初步审核后，提交教学副院长审核'),(8,'8','教学院长','审核选题','已完成','审核所有教师的选题，对有问题的选题提出整改意见'),(9,'9','教师和学生','选题整改','已完成','选题整改可以由教师进行，也可以由教师指派学生在系统中进行。');

/*Table structure for table `rank` */

DROP TABLE IF EXISTS `rank`;

CREATE TABLE `rank` (
  `rankId` int(11) NOT NULL,
  `rankName` varchar(10) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY  (`rankId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rank` */

insert  into `rank`(`rankId`,`rankName`,`num`) values (1,'讲师',0),(2,'副教授',0),(3,'教授',0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` varchar(10) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `role_remark` varchar(200) default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_name`,`role_remark`) values ('1','学生','普通角色权限'),('2','教师','普通角色权限'),('3','教学秘书','普通角色权限'),('4','教务处','普通角色权限'),('5','系统管理员','系统管理权限'),('6','教学院长','自定义扩充角色；新增角色；自定义角色');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_permission_id` int(11) NOT NULL auto_increment,
  `role_id` varchar(10) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY  (`role_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1438 DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_permission_id`,`role_id`,`permission_id`) values (808,'5',1),(809,'5',2),(810,'5',3),(811,'5',38),(812,'5',40),(813,'5',45),(814,'5',49),(1293,'2',1),(1294,'2',2),(1295,'2',3),(1296,'2',4),(1297,'2',6),(1298,'2',16),(1299,'2',18),(1300,'2',22),(1301,'2',24),(1302,'2',28),(1303,'2',33),(1304,'2',35),(1305,'2',50),(1306,'2',52),(1307,'2',45),(1308,'2',46),(1309,'2',47),(1310,'2',48),(1334,'4',1),(1335,'4',2),(1336,'4',3),(1337,'4',4),(1338,'4',6),(1339,'4',8),(1340,'4',16),(1341,'4',22),(1342,'4',33),(1343,'4',35),(1344,'4',52),(1345,'4',45),(1346,'4',46),(1347,'4',47),(1348,'4',48),(1362,'3',1),(1363,'3',2),(1364,'3',3),(1365,'3',4),(1366,'3',6),(1367,'3',8),(1368,'3',10),(1369,'3',12),(1370,'3',16),(1371,'3',18),(1372,'3',19),(1373,'3',22),(1374,'3',28),(1375,'3',33),(1376,'3',35),(1377,'3',51),(1378,'3',52),(1379,'3',36),(1380,'3',53),(1381,'3',45),(1382,'3',46),(1383,'3',47),(1384,'3',48),(1385,'3',49),(1404,'6',1),(1405,'6',2),(1406,'6',3),(1407,'6',16),(1408,'6',22),(1409,'6',33),(1410,'6',35),(1411,'6',51),(1412,'6',52),(1413,'6',45),(1414,'6',46),(1415,'6',47),(1416,'6',48),(1417,'1',1),(1418,'1',2),(1419,'1',3),(1420,'1',4),(1421,'1',6),(1422,'1',16),(1423,'1',17),(1424,'1',21),(1425,'1',24),(1426,'1',32),(1427,'1',35),(1428,'1',52),(1429,'1',49),(1430,'1',36),(1431,'1',38),(1432,'1',40),(1433,'1',53),(1434,'1',45),(1435,'1',46),(1436,'1',47),(1437,'1',48);

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `stu_id` varchar(10) NOT NULL,
  `score1` varchar(10) NOT NULL COMMENT '指导教师评阅',
  `score2` varchar(10) default NULL COMMENT '评阅教师评审',
  `score3` varchar(10) default NULL COMMENT '答辩',
  `score4` varchar(10) default NULL COMMENT '总成绩',
  `remark` varchar(200) default NULL,
  `scoreType` varchar(10) NOT NULL default '11',
  PRIMARY KEY  (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`stu_id`,`score1`,`score2`,`score3`,`score4`,`remark`,`scoreType`) values ('13','46',NULL,NULL,NULL,'暂无','11'),('1510064107','','','',NULL,'暂无','11'),('1510064108','80','80','70',NULL,'暂无','11'),('1510064112','86','50','46',NULL,'暂无','12'),('6','70',NULL,NULL,NULL,'暂无','11'),('7','90',NULL,NULL,NULL,'暂无','12');

/*Table structure for table `speech_group` */

DROP TABLE IF EXISTS `speech_group`;

CREATE TABLE `speech_group` (
  `stu_id` varchar(10) NOT NULL COMMENT '学号',
  `groupId` varchar(10) default NULL COMMENT '所属答辩组',
  `time` datetime default NULL COMMENT '答辩时间',
  `location` varchar(100) default NULL COMMENT '答辩地点',
  PRIMARY KEY  (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `speech_group` */

insert  into `speech_group`(`stu_id`,`groupId`,`time`,`location`) values ('1510064108','1','2019-05-08 14:30:00','3#121'),('1510064111','1','2019-05-08 14:30:00','3#121'),('1510064126','1','2019-05-08 14:30:00','3#121'),('1510064135','1','2019-05-08 14:30:00','3#121'),('1510064136','1','2019-05-08 14:30:00','3#121'),('1510064144','1','2019-05-08 14:30:00','3#121');

/*Table structure for table `stu_group` */

DROP TABLE IF EXISTS `stu_group`;

CREATE TABLE `stu_group` (
  `stu_tea_id` int(11) NOT NULL auto_increment,
  `stu_id` varchar(10) NOT NULL,
  `stu_name` varchar(50) NOT NULL,
  `tea_id` varchar(10) NOT NULL,
  `tea_name` varchar(50) NOT NULL,
  `stateId` varchar(1) default '0' COMMENT '答辩审批标记',
  PRIMARY KEY  (`stu_tea_id`),
  KEY `stu_name` (`stu_name`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `stu_group` */

insert  into `stu_group`(`stu_tea_id`,`stu_id`,`stu_name`,`tea_id`,`tea_name`,`stateId`) values (4,'13','邓某','9','赵老师','0'),(9,'1510064114','王五','8','李老师','0'),(36,'1510064108','周佳理','2','某老师','0'),(39,'1510064135','韩壮壮','2','某老师','0'),(40,'1510064136','杜浪浪','2','某老师','0'),(41,'1510064111','李景龙','2','某老师','0'),(42,'1510064144','陈义安','2','某老师','0'),(43,'1510064126','朱哓轩','2','某老师','0'),(44,'2','某老师','2','某老师','0');

/*Table structure for table `tea_group` */

DROP TABLE IF EXISTS `tea_group`;

CREATE TABLE `tea_group` (
  `tea_group_id` int(11) NOT NULL auto_increment,
  `groupId` varchar(10) NOT NULL,
  `groupName` varchar(20) NOT NULL,
  `tea_id` varchar(10) NOT NULL,
  `deptId` varchar(10) default NULL,
  `isHeadMan` varchar(1) default '0' COMMENT '是否答辩组组长',
  PRIMARY KEY  (`tea_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tea_group` */

insert  into `tea_group`(`tea_group_id`,`groupId`,`groupName`,`tea_id`,`deptId`,`isHeadMan`) values (1,'1','第一答辩小组','18','10','1'),(3,'2','某某院系某某组','9','11','0'),(7,'1','第一答辩小组','19','10','0'),(8,'1','第一答辩小组','20','10','0'),(9,'2','某某院系某某组','7','11','1'),(10,'1','第一答辩小组','21',NULL,'0'),(11,'1','第一答辩小组','22',NULL,'0');

/*Table structure for table `topic` */

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `topicId` int(11) NOT NULL,
  `topicName` varchar(50) NOT NULL,
  `abs` varchar(50) default NULL,
  `typeId` varchar(2) default NULL COMMENT '命题类型',
  `user_name` varchar(50) default NULL COMMENT '命题者',
  `user_id` varchar(10) default NULL COMMENT '命题者编号',
  `stu_id` varchar(10) default NULL,
  `tea_id` varchar(10) default NULL,
  `deptId` varchar(10) default NULL,
  `majorId` varchar(10) default NULL,
  `stateId` varchar(1) default NULL COMMENT '课题状态',
  `chk` varchar(1) default NULL COMMENT '师生关系批复',
  `apply` varchar(1) default '0' COMMENT '答辩审批标识',
  PRIMARY KEY  (`topicId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `topic` */

insert  into `topic`(`topicId`,`topicName`,`abs`,`typeId`,`user_name`,`user_id`,`stu_id`,`tea_id`,`deptId`,`majorId`,`stateId`,`chk`,`apply`) values (1,'基于SSM框架的高校毕业设计管理系统的设计与实现','实现教务工作信息化，提高工作效率。为教务工作提供便利。','22','某老师','2','1510064108','2','10','1','2','2','2'),(2,'计算机科学与技术专业一流专业建设网站','学科内容建设。','22','某老师','2','1510064126','2','10','1','2','2','6'),(3,'智能电影推荐','娱乐影音信息推荐。','22','某老师','2','1510064136','2','10','1','2','2','0'),(4,'教育研究','暂无','22','赵老师','9','13','9','11','4','2','2','0'),(5,'自动化排课系统','暂无','22','李老师','8','1510064114','8','10','2','2','0','0'),(6,'自拟课题测试','暂无','21','李四','7','1510064107','2','10','1','4','2','0'),(7,'旅游博客网站','旅游信息分享。','22','某老师','2','1510064144','2','10','1','2','6','0'),(8,'在线评测系统','自动组卷，自动评阅。','22','某老师','2','1510064111','2','10','1','2','2','0'),(9,'计算机科学与技术专业一流专业在线学习系统的设计与实现','暂无','22','某老师','2','1510064135','2','10','1','2','2','0'),(10,'课题测试','课题测试','22','段老师','2','','2','10','1','2','0','0'),(11,'这是一个课题','暂无','22','段老师','2','','2','10','1','0','0','0'),(12,'网上花店系统','网上花店系统','22','段老师','2','','2','10','1','0','0','0'),(13,'新增课题','暂无','22','段老师','2','','2','10','1','2','0','0');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(50) default NULL,
  `role_id` varchar(10) NOT NULL,
  `password` varchar(32) NOT NULL,
  `question` varchar(50) default NULL,
  `answer` varchar(50) default NULL,
  `isOk` varchar(5) NOT NULL default '可用' COMMENT '可用|锁定',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`role_id`,`password`,`question`,`answer`,`isOk`) values ('10','测试用户','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'锁定'),('11','赵秘书','6','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('15','田老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064101','李华基','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064106','王荣慧','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064107','李攀','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064108','周佳理','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064111','李景龙','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064112','王毅琳','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'锁定'),('1510064113','强虎','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064114','杨凡','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064126','朱哓轩','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064135','韩壮壮','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064136','杜浪浪','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('1510064144','陈义安','1','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('18','解老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('19','陈老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('2','某老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('20','李老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('21','魏老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('22','邹老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('3','教学院长','3','96e79218965eb72c92a549dd5a330112',NULL,NULL,'锁定'),('4','张老师','4','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('5','ADMIN','5','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('8','李老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用'),('9','张老师','2','96e79218965eb72c92a549dd5a330112',NULL,NULL,'可用');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(50) default NULL,
  `user_sex` varchar(2) default NULL,
  `deptId` varchar(10) default NULL,
  `majorId` varchar(10) default NULL COMMENT '教师为教研室',
  `classesId` varchar(10) default NULL COMMENT '教师无具体班级外键，有则为班主任',
  `sfzh` varchar(18) default NULL,
  `address` varchar(50) default NULL,
  `tel` varchar(11) default NULL,
  `rankId` varchar(10) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`user_name`,`user_sex`,`deptId`,`majorId`,`classesId`,`sfzh`,`address`,`tel`,`rankId`) values ('10','测试用户','男','10',NULL,NULL,NULL,NULL,NULL,NULL),('11','赵秘书','男','10',NULL,NULL,NULL,NULL,NULL,NULL),('15','田老师','男','10','1',NULL,NULL,NULL,NULL,NULL),('1510064101','李华基','男','10','1','1001',NULL,NULL,NULL,NULL),('1510064106','王荣慧','男','10','1','1001','610','咸阳师范学院','',NULL),('1510064107','李攀','男','10','1','1001',NULL,NULL,NULL,NULL),('1510064108','周佳理','男','10','1','1001','610124199710244212','咸阳师范学院','13109630506',NULL),('1510064111','李景龙','男','10','1','1001','610122199708184318','蓝田','18710563990',NULL),('1510064112','王毅琳','男','10','1','1001',NULL,NULL,NULL,NULL),('1510064113','强虎','男','11','4','1006',NULL,NULL,NULL,NULL),('1510064114','杨凡','男','10','2','1004',NULL,NULL,NULL,NULL),('1510064126','朱哓轩','男','10','1','1001','610523199708211679','渭南大荔','15029505536',NULL),('1510064135','韩壮壮','男','10','1','1001','612725199610185017','榆林','15929627600',NULL),('1510064136','杜浪浪','男','10','1','1001','612726199601053318','定边','15771806065',NULL),('1510064144','陈义安','男','10','1','1001','612401199512105098','安康','15709282600',NULL),('18','解老师','男','10','1','1001',NULL,'咸阳师范学院',NULL,NULL),('19','陈老师','男','10','1','1001',NULL,'咸阳师范学院',NULL,NULL),('2','某老师','女','10','1','1001','610000111122224444','咸阳师范学院','13200001111',NULL),('20','李老师','女','10','1','1001',NULL,'咸阳师范学院',NULL,NULL),('21','魏老师','男','10','1','1001',NULL,'咸阳师范学院',NULL,NULL),('22','邹老师','女','10','1','1001',NULL,'咸阳师范学院',NULL,NULL),('3','教学院长','男','10','','','暂无','暂无','暂无',NULL),('4','张老师','男','10','','','暂无','暂无','暂无',NULL),('5','ADMIN','女','','','','暂无','暂无','暂无',NULL),('7','张老师','女','11','4','1006',NULL,NULL,NULL,NULL),('8','李老师','女','10','2',NULL,NULL,NULL,'13100000',NULL),('9','赵老师','男','11','4',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user_permission` */

DROP TABLE IF EXISTS `user_permission`;

CREATE TABLE `user_permission` (
  `user_permission_id` int(11) NOT NULL auto_increment,
  `user_id` varchar(10) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

/*Data for the table `user_permission` */

insert  into `user_permission`(`user_permission_id`,`user_id`,`permission_id`) values (85,'11',1),(86,'11',2),(87,'11',3),(88,'11',4),(89,'11',6),(90,'11',16),(91,'11',35),(92,'11',45),(93,'11',46),(94,'11',48),(95,'12',1),(96,'12',2),(97,'12',3),(98,'12',4),(99,'12',6),(100,'12',16),(101,'12',17),(102,'12',21),(103,'12',23),(104,'12',24),(105,'12',25),(106,'12',19),(107,'12',32),(108,'12',35),(109,'12',45),(110,'12',46),(111,'12',47),(112,'12',48);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL auto_increment,
  `user_id` varchar(10) NOT NULL,
  `role_id` varchar(10) NOT NULL,
  PRIMARY KEY  (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_role_id`,`user_id`,`role_id`) values (1,'1510064108','1'),(2,'2','2'),(3,'3','3'),(7,'4','4'),(8,'5','5'),(9,'1510064106','1'),(10,'1510064107','1'),(11,'11','6'),(12,'1510064112','1'),(13,'13','1'),(14,'8','2'),(15,'1510064114','1'),(16,'1510064101','1'),(17,'1510064126','1'),(18,'1510064135','1'),(19,'1510064136','1'),(20,'1510064144','1'),(21,'1510064111','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
