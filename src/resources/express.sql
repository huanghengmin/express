-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.6.13-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 express 的数据库结构
DROP DATABASE IF EXISTS `express`;
CREATE DATABASE IF NOT EXISTS `express` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `express`;


-- 导出  表 express.account 结构
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `depart` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `start_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `end_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `start_hour` int(11) DEFAULT NULL,
  `end_hour` int(11) DEFAULT NULL,
  `description` text COLLATE utf8_bin,
  `remote_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mac` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ip_type` int(1) NOT NULL DEFAULT '1',
  `user_type` int(1) NOT NULL DEFAULT '1' COMMENT '1代表管理员用户,2代表网点用户',
  `company_point_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB9D38A2DA6C405B` (`company_point_id`),
  CONSTRAINT `FKB9D38A2DA6C405B` FOREIGN KEY (`company_point_id`) REFERENCES `company_point` (`id`),
  CONSTRAINT `FK_account_company_point` FOREIGN KEY (`company_point_id`) REFERENCES `company_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账户表';

-- 正在导出表  express.account 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
REPLACE INTO `account` (`id`, `user_name`, `password`, `sex`, `phone`, `created_time`, `modified_time`, `status`, `depart`, `title`, `name`, `email`, `start_ip`, `end_ip`, `start_hour`, `end_hour`, `description`, `remote_ip`, `mac`, `ip_type`, `user_type`, `company_point_id`) VALUES
	(1, 'admin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2010-07-04 13:52:36', '2014-05-22 17:16:38', '有效', '信息中心', '主任', '初始化管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.254.254', 9, 18, '这是一个默认的超级用户信息', '192.168.2.176', '5C-63-BF-1D-72-07', 1, 1, NULL),
	(2, 'authadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-04-12 14:22:35', '2013-05-07 18:27:30', '有效', '信息中心', '主任', '授权管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 1, 22, '这是一个默认的授权用户信息', '', NULL, 1, 1, NULL),
	(3, 'configadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-06-12 18:04:01', '2013-05-07 18:27:45', '有效', '信息中心', '主任', '配置管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 9, 21, '这是一个默认的配置用户信息', '', NULL, 1, 1, NULL),
	(4, 'auditadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-07-03 10:19:57', '2014-08-26 13:01:36', '有效', '信息中心', '主任', '审计管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 7, 22, '这是一个默认的审计用户信息', NULL, NULL, 1, 1, NULL),
	(5, 'test', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88880571', '2016-02-24 17:40:36', NULL, '有效', '信息部', '主任', '测试', '**@hzih.net', '0.0.0.0', '192.255.255.255', 9, 18, '这是一个用户信息', NULL, '', 1, 1, NULL),
	(6, 'lisi', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88880571', '2016-02-24 18:18:21', NULL, '有效', '信息部', '主任', 'lisi', '**@hzih.net', '0.0.0.0', '192.255.255.255', 9, 18, '这是一个用户信息', NULL, '', 1, 2, 1),
	(7, 'wangwu', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88880571', '2016-02-25 15:41:39', NULL, '有效', '信息部', '主任', 'wangwu', '**@hzih.net', '0.0.0.0', '192.255.255.255', 9, 18, '这是一个用户信息', NULL, '', 1, 2, 2),
	(8, 'ocradmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88880571', '2016-03-15 19:54:32', NULL, '有效', '信息部', '主任', 'ocradmin', '**@hzih.net', '0.0.0.0', '255.255.255.255', 9, 18, '这是一个用户信息', NULL, '', 1, 1, NULL),
	(9, 'ga', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88880571', '2016-03-16 11:17:32', NULL, '有效', '信息部', '主任', 'ga', '**@hzih.net', '0.0.0.0', '255.255.255.255', 9, 18, '这是一个公安用户信息', NULL, '', 1, 1, NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


-- 导出  表 express.account_oper_log 结构
DROP TABLE IF EXISTS `account_oper_log`;
CREATE TABLE IF NOT EXISTS `account_oper_log` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '审计时间',
  `level` varchar(10) DEFAULT NULL COMMENT '日志级别',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `audit_module` varchar(255) DEFAULT NULL COMMENT '审计模块',
  `audit_info` varchar(255) DEFAULT NULL COMMENT '审计内容',
  PRIMARY KEY (`Id`),
  KEY `log_time` (`log_time`,`level`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='用户操作审计表';

-- 正在导出表  express.account_oper_log 的数据：~37 rows (大约)
/*!40000 ALTER TABLE `account_oper_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_oper_log` ENABLE KEYS */;


-- 导出  表 express.account_role 结构
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE IF NOT EXISTS `account_role` (
  `account_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FK410D034851BABF58` (`role_id`),
  KEY `FK410D0348BE9C187C` (`account_id`),
  KEY `FK410D034880878851` (`role_id`),
  KEY `FK410D0348E7AB80E3` (`account_id`),
  KEY `FK410D03481FCE46BD` (`role_id`),
  KEY `FK410D034811351AF7` (`account_id`),
  KEY `FK410D03488A556D64` (`role_id`),
  KEY `FK410D0348D6E01EF0` (`account_id`),
  KEY `FK410D0348D063FAAC` (`role_id`),
  KEY `FK410D03486E4B2CA8` (`account_id`),
  KEY `FK410D0348F7EB1F96` (`role_id`),
  KEY `FK410D0348562BE77E` (`account_id`),
  CONSTRAINT `FK410D034811351AF7` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D03481FCE46BD` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D034851BABF58` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D0348562BE77E` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D03486E4B2CA8` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D034880878851` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D03488A556D64` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D0348BE9C187C` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D0348D063FAAC` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D0348D6E01EF0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D0348E7AB80E3` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D0348F7EB1F96` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  express.account_role 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
REPLACE INTO `account_role` (`account_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(4, 3),
	(3, 4),
	(6, 5),
	(7, 5),
	(5, 6),
	(8, 7),
	(9, 8);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;


-- 导出  表 express.company 结构
DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;

-- 正在导出表  express.company 的数据：~201 rows (大约)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
REPLACE INTO `company` (`id`, `code`, `name`) VALUES
	(1, 'ems', 'EMS快递'),
	(2, 'shentong', '申通快递'),
	(3, 'shunfeng', '顺丰快递'),
	(4, 'yuantong', '圆通快递'),
	(5, 'yunda', '韵达快递'),
	(6, 'huitong', '汇通快递'),
	(7, 'tiantian', '天天快递'),
	(8, 'zhongtong', '中通快递'),
	(9, 'zhaijisong', '宅急送快递'),
	(10, 'pingyou', '中国邮政'),
	(11, 'quanfeng', '全峰快递'),
	(12, 'guotong', '国通快递'),
	(13, 'jingdong', '京东快递'),
	(14, 'sure', '速尔快递'),
	(15, 'kuaijie', '快捷快递'),
	(16, 'ririshun', '日日顺物流'),
	(17, 'zhongtie', '中铁快运'),
	(18, 'yousu', '优速快递'),
	(19, 'longbang', '龙邦快递'),
	(20, 'debang', '德邦物流'),
	(21, 'rufeng', '如风达快递'),
	(22, 'lianhaotong', '联昊通快递'),
	(23, 'fedex', '国际Fedex'),
	(24, 'fedexcn', 'Fedex国内'),
	(25, 'dhl', 'DHL快递'),
	(26, 'xinfeng', '信丰快递'),
	(27, 'eyoubao', 'E邮宝'),
	(28, 'zhongxinda', '忠信达快递'),
	(29, 'changtong', '长通物流'),
	(30, 'usps', 'USPS快递'),
	(31, 'huaqi', '华企快递'),
	(32, 'zhima', '芝麻开门快递'),
	(33, 'gnxb', '邮政小包'),
	(34, 'nell', '尼尔快递'),
	(35, 'zengyi', '增益快递'),
	(36, 'yuxin', '宇鑫物流'),
	(37, 'xingchengzhaipei', '星程宅配快递'),
	(38, 'anneng', '安能物流'),
	(39, 'dada', '大达物流'),
	(40, 'tongzhishu', '高考录取通知书'),
	(41, 'aol', 'AOL快递'),
	(42, 'dongjun', '成都东骏快递'),
	(43, 'quanyi', '全一快递'),
	(44, 'huayu', '华宇物流'),
	(45, 'quanritong', '全日通快递'),
	(46, 'fengcheng', '丰程物流'),
	(47, 'minhang', '民航快递'),
	(48, 'zhongyou', '中邮物流'),
	(49, 'wanjia', '万家物流'),
	(50, 'jiaji', '佳吉快运'),
	(51, 'wanxiang', '万象物流'),
	(52, 'beihai', '贝海国际快递'),
	(53, 'junfeng', '墨尔本骏丰快递'),
	(54, 'junda', '骏达快递'),
	(55, 'quanxintong', '全信通快递'),
	(56, 'ups', 'UPS快递'),
	(57, 'tnt', 'TNT快递'),
	(58, 'yibang', '一邦快递'),
	(59, 'shenghui', '盛辉物流'),
	(60, 'yafeng', '亚风快递'),
	(61, 'dsu', 'D速快递'),
	(62, 'datian', '大田物流'),
	(63, 'jiayi', '佳怡物流'),
	(64, 'jiayunmei', '加运美快递'),
	(65, 'quanchen', '全晨快递'),
	(66, 'ocs', 'OCS快递'),
	(67, 'shengfeng', '盛丰物流'),
	(68, 'xinbang', '新邦物流'),
	(69, 'chengguang', '程光快递'),
	(70, 'fengda', '丰达快递'),
	(71, 'feihang', '原飞航物流'),
	(72, 'jinyue', '晋越快递'),
	(73, 'yuefeng', '越丰快递'),
	(74, 'anjie', '安捷快递'),
	(75, 'aae', 'AAE快递'),
	(76, 'yuntong', '运通中港快递'),
	(77, 'dpex', 'DPEX快递'),
	(78, 'yuancheng', '远成物流'),
	(79, 'gdyz', '广东邮政物流'),
	(80, 'aramex', 'Aramex国际快递'),
	(81, 'intmail', '国际邮政快递'),
	(82, 'ytfh', '北京一统飞鸿快递'),
	(83, 'lejiedi', '乐捷递快递'),
	(84, 'santai', '三态速递'),
	(85, 'chuanzhi', '传志快递'),
	(86, 'gongsuda', '共速达物流|快递'),
	(87, 'ees', '百福东方物流'),
	(88, 'scs', '伟邦(SCS)快递'),
	(89, 'pinganda', '平安达'),
	(90, 'yad', '源安达快递'),
	(91, 'disifang', '递四方速递'),
	(92, 'yinjie', '顺捷丰达快递'),
	(93, 'jldt', '嘉里大通物流'),
	(94, 'coe', '东方快递'),
	(95, 'chuanxi', '传喜快递'),
	(96, 'feibao', '飞豹快递'),
	(97, 'jingguang', '京广快递'),
	(98, 'feiyuan', '飞远物流'),
	(99, 'cszx', '城市之星'),
	(100, 'rpx', 'RPX保时达'),
	(101, 'citylink', 'CityLinkExpress'),
	(102, 'chengshi100', '城市100'),
	(103, 'lijisong', '成都立即送快递'),
	(104, 'balunzhi', '巴伦支'),
	(105, 'dayang', '大洋物流快递'),
	(106, 'diantong', '店通快递'),
	(107, 'fanyu', '凡宇快递'),
	(108, 'haosheng', '昊盛物流'),
	(109, 'hebeijianhua', '河北建华快递'),
	(110, 'jixianda', '急先达物流'),
	(111, 'suijia', '穗佳物流'),
	(112, 'shengan', '圣安物流'),
	(113, 'saiaodi', '赛澳递'),
	(114, 'haihong', '山东海红快递'),
	(115, 'weitepai', '微特派'),
	(116, 'chengji', '城际快递'),
	(117, 'fardar', 'Fardar'),
	(118, 'henglu', '恒路物流'),
	(119, 'hwhq', '海外环球快递'),
	(120, 'jinda', '金大物流'),
	(121, 'kuayue', '跨越快递'),
	(122, 'kcs', '顺鑫(KCS)快递'),
	(123, 'mingliang', '明亮物流'),
	(124, 'minbang', '民邦快递'),
	(125, 'minsheng', '闽盛快递'),
	(126, 'xiyoute', '希优特快递'),
	(127, 'xianglong', '祥龙运通快递'),
	(128, 'yishunhang', '亿顺航快递'),
	(129, 'benteng', '成都奔腾国际快递'),
	(130, 'zhongtian', '济南中天万运'),
	(131, 'zhengzhoujianhua', '郑州建华快递'),
	(132, 'feite', '飞特物流'),
	(133, 'huahan', '华翰物流'),
	(134, 'baotongda', '宝通达'),
	(135, 'chukouyi', '出口易物流'),
	(136, 'yumeijie', '誉美捷快递'),
	(137, 'kuanrong', '宽容物流'),
	(138, 'nanbei', '南北快递'),
	(139, 'wanbo', '万博快递'),
	(140, 'suchengzhaipei', '速呈宅配'),
	(141, 'shengbang', '晟邦物流'),
	(142, 'baiqian', '百千诚国际物流'),
	(143, 'gaotie', '高铁快递'),
	(144, 'guanda', '冠达快递'),
	(145, 'haolaiyun', '好来运快递'),
	(146, 'hutong', '户通物流'),
	(147, 'huahang', '华航快递'),
	(148, 'huangmajia', '黄马甲快递'),
	(149, 'ucs', '合众速递'),
	(150, 'jiete', '捷特快递'),
	(151, 'jiuyi', '久易快递'),
	(152, 'kuaiyouda', '快优达速递'),
	(153, 'lanhu', '蓝弧快递'),
	(154, 'menduimen', '门对门快递'),
	(155, 'peixing', '陪行物流'),
	(156, 'riyu', '日昱物流'),
	(157, 'lindao', '上海林道货运'),
	(158, 'shiyun', '世运快递'),
	(159, 'aoshuo', '奥硕物流'),
	(160, 'nsf', '新顺丰（NSF）快递'),
	(161, 'dajin', '大金物流'),
	(162, 'coscon', '中国远洋运输(COSCON)'),
	(163, 'yuhong', '宇宏物流'),
	(164, 'jiayu', '佳宇物流'),
	(165, 'gangkuai', '港快速递'),
	(166, 'kuaitao', '快淘速递'),
	(167, 'sutong', '速通物流'),
	(168, 'anxun', '安迅物流'),
	(169, 'hkpost', '香港邮政'),
	(170, 'jppost', '日本邮政'),
	(171, 'singpost', '新加坡邮政'),
	(172, 'ztwl', '中铁物流'),
	(173, 'ppbyb', '贝邮宝'),
	(174, 'yanwen', '燕文物流'),
	(175, 'feiyang', '飞洋快递'),
	(176, 'zuochuan', '佐川急便'),
	(177, 'hengyu', '恒宇运通'),
	(178, 'mengsu', '蒙速快递'),
	(179, 'wuhuan', '五环速递'),
	(180, 'simai', '思迈快递'),
	(181, 'jiahuier', '佳惠尔快递'),
	(182, 'ande', '安得物流'),
	(183, 'rongqing', '荣庆物流'),
	(184, 'dashun', '大顺物流'),
	(185, 'fangfangda', '方方达物流'),
	(186, 'huiwen', '汇文快递'),
	(187, 'sujie', '速捷快递'),
	(188, 'dhlde', '德国DHL快递'),
	(189, 'baiteng', '百腾物流'),
	(190, 'dcs', 'DCS快递'),
	(191, 'dpd', 'DPD快递'),
	(192, 'tengxunda', '腾迅达物流'),
	(193, 'pinjun', '品骏快递'),
	(194, 'bse', '蓝天快递'),
	(195, 'nengda', '能达快递'),
	(196, 'ruifeng', '瑞丰速递'),
	(197, 'suteng', '速腾快递'),
	(198, 'zongxing', '纵行物流'),
	(199, 'jingshi', '京世物流'),
	(200, 'huacheng', '华诚物流'),
	(201, 'bsht', '百世汇通');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


-- 导出  表 express.company_point 结构
DROP TABLE IF EXISTS `company_point`;
CREATE TABLE IF NOT EXISTS `company_point` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `company_code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `contacts` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK522FE34E5A16EDD0` (`company_code`),
  CONSTRAINT `FK522FE34E5A16EDD0` FOREIGN KEY (`company_code`) REFERENCES `company` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  express.company_point 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `company_point` DISABLE KEYS */;
REPLACE INTO `company_point` (`id`, `company_code`, `name`, `address`, `phone`, `contacts`) VALUES
	(1, 'shunfeng', '长河分部', '浙江省杭州市滨江区滨江区长河路长河镇351号拓森科技园４号楼一层', '0571-86074760', '叶竞文'),
	(2, 'shentong', '浙江杭州公司', '杭州市萧山区靖江镇', '0571-28081888', '奚春阳'),
	(3, 'tiantian', '滨江分公司', '浙江省杭州市滨江区滨江区', '0571-12345678', '张三');
/*!40000 ALTER TABLE `company_point` ENABLE KEYS */;


-- 导出  表 express.express_log 结构
DROP TABLE IF EXISTS `express_log`;
CREATE TABLE IF NOT EXISTS `express_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '寄件人姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '寄件人性别',
  `nation` varchar(20) DEFAULT NULL COMMENT '寄件人民族',
  `birthday` varchar(30) DEFAULT NULL COMMENT '寄件人出生日期',
  `address` varchar(255) DEFAULT NULL COMMENT '寄件人身份证地址',
  `idCard` varchar(18) DEFAULT NULL COMMENT '寄件人身份证',
  `signDepart` varchar(255) DEFAULT NULL COMMENT '寄件人身份证签证公安局',
  `validTime` varchar(50) DEFAULT NULL COMMENT '寄件人身份证有效日期',
  `shapeCode` varchar(255) DEFAULT NULL COMMENT '寄件快递单号',
  `DN` varchar(255) DEFAULT NULL COMMENT '寄件人身份证DN码',
  `bitmap` longblob COMMENT '寄件人身份证图像',
  `face_img` longblob COMMENT '面单照',
  `sender_img` longblob COMMENT '寄件人照',
  `unpack_img` longblob COMMENT '开箱照',
  `longitude` varchar(50) DEFAULT NULL COMMENT '寄件经度',
  `latitude` varchar(50) DEFAULT NULL COMMENT '寄件纬度',
  `sendTime` datetime DEFAULT NULL COMMENT '寄件时间',
  `phone` varchar(15) DEFAULT NULL COMMENT '注册手机号',
  `type` varchar(10) DEFAULT NULL COMMENT '上报类型',
  `contact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA7229F5AC3632D5` (`phone`),
  CONSTRAINT `FKA7229F5AC3632D5` FOREIGN KEY (`phone`) REFERENCES `user` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  express.express_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `express_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `express_log` ENABLE KEYS */;


-- 导出  表 express.permission 结构
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  express.permission 的数据：~35 rows (大约)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
REPLACE INTO `permission` (`ID`, `CODE`, `NAME`, `DESCRIPTION`, `PARENT_ID`, `SEQ`) VALUES
	(100, 'TOP_QXGL', '权限管理', NULL, 0, 0),
	(101, 'SECOND_YHGL', '用户管理', NULL, 100, 1),
	(102, 'SECOND_JSGL', '角色管理', NULL, 100, 2),
	(103, 'SECOND_AQCL', '安全策略', NULL, 100, 3),
	(104, 'SECOND_WDYH', '网点用户', NULL, 100, 4),
	(110, 'TOP_WLGL', '网络管理', NULL, 0, 0),
	(111, 'SECOND_JKGL', '接口管理', NULL, 110, 1),
	(112, 'SECOND_WLCS', '网络测试', NULL, 110, 2),
	(113, 'SECOND_LYGL', '路由管理', NULL, 110, 4),
	(114, 'SECOND_PZGL', '安全配置', NULL, 110, 5),
	(120, 'TOP_XTGL', '系统管理', NULL, 0, 0),
	(121, 'SECOND_PTSM', '平台说明', NULL, 120, 1),
	(122, 'SECOND_PTGL', '平台管理', NULL, 120, 2),
	(123, 'SECOND_ZSGL', '证书管理', NULL, 120, 3),
	(124, 'SECOND_RZXZ', '日志下载', NULL, 120, 4),
	(125, 'SECOND_BBSJ', '版本升级', NULL, 120, 5),
	(130, 'TOP_SJGL', '审计管理', NULL, 0, 0),
	(131, 'SECOND_GLRZ', '管理日志', NULL, 130, 1),
	(140, 'TOP_KDGN', '快递功能', NULL, 0, 0),
	(141, 'SECOND_KDSM', '快递实名', NULL, 140, 1),
	(142, 'SECOND_KDRY', '快递人员', NULL, 140, 2),
	(143, 'SECOND_WDGL', '网点管理', NULL, 140, 3),
	(144, 'SECOND_OCR', 'OCR管理', NULL, 140, 4),
	(150, 'TOP_XTPZ', '系统配置', NULL, 0, 0),
	(151, 'SECOND_RZZJ', '日志主机', NULL, 150, 1),
	(160, 'TOP_JKGL', '监控管理', NULL, 0, 0),
	(161, 'SECOND_ZJJK', '主机监控', NULL, 160, 1),
	(170, 'TOP_WDGL', '网点管理', NULL, 0, 0),
	(171, 'SECOND_KDXX', '快递信息', NULL, 170, 1),
	(172, 'SECOND_YHXX', '用户信息', NULL, 170, 2),
	(180, 'TOP_BBGL', '版本管理', NULL, 0, 0),
	(181, 'SECOND_ZDBB', '终端版本', NULL, 180, 1),
	(182, 'SECOND_FWBB', '服务版本', NULL, 180, 2),
	(190, 'TOP_GAGL', '公安管理', NULL, 0, 0),
	(191, 'SECOND_PZBD', '碰撞比对', NULL, 190, 1);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


-- 导出  表 express.role 结构
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  express.role 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
REPLACE INTO `role` (`id`, `name`, `description`, `createdTime`, `modifiedTime`) VALUES
	(1, '初始化管理员', '初始化管理员', '2010-07-04 15:07:08', '2015-11-16 13:24:10'),
	(2, '授权管理员', '授权管理员', '2012-03-14 12:33:05', '2012-11-10 14:50:04'),
	(3, '审计管理员', '审计管理员', '2012-11-02 22:38:09', '2014-08-26 13:11:12'),
	(4, '配置管理员', '配置管理员', '2012-11-26 15:20:37', '2013-05-07 18:04:15'),
	(5, '网点管理员', '网点管理员', '2016-02-24 16:10:26', '2016-03-16 17:51:11'),
	(6, '测试管理员', '测试管理员', '2016-02-24 17:33:25', '2016-03-24 15:54:13'),
	(7, 'OCR管理员', 'OCR管理员', '2016-03-15 19:53:54', '2016-03-24 15:54:28'),
	(8, '公安管理员', '公安管理员', '2016-03-16 11:16:54', '2016-03-24 15:54:36');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- 导出  表 express.role_permission 结构
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `permission_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FKBD40D53851BABF58` (`role_id`),
  KEY `FKBD40D53852A81638` (`permission_id`),
  KEY `FK9C6EC93851BABF58` (`role_id`),
  KEY `FK9C6EC93852A81638` (`permission_id`),
  KEY `FKBD40D53880878851` (`role_id`),
  KEY `FKBD40D5388AAE8071` (`permission_id`),
  KEY `FKBD40D5381FCE46BD` (`role_id`),
  KEY `FKBD40D5384E8FBDDD` (`permission_id`),
  KEY `FKBD40D5388A556D64` (`role_id`),
  KEY `FKBD40D53826D30B44` (`permission_id`),
  KEY `FKBD40D538D063FAAC` (`role_id`),
  KEY `FKBD40D538D9C4828C` (`permission_id`),
  KEY `FKBD40D538F7EB1F96` (`role_id`),
  KEY `FKBD40D538BAD2CFF6` (`permission_id`),
  CONSTRAINT `FK9C6EC93851BABF58` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK9C6EC93852A81638` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D5381FCE46BD` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D53826D30B44` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D5384E8FBDDD` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D53851BABF58` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D53852A81638` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D53880878851` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D5388A556D64` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D5388AAE8071` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D538BAD2CFF6` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D538D063FAAC` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D538D9C4828C` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D538F7EB1F96` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  express.role_permission 的数据：~45 rows (大约)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
REPLACE INTO `role_permission` (`permission_id`, `role_id`) VALUES
	(100, 1),
	(101, 1),
	(102, 1),
	(103, 1),
	(140, 1),
	(141, 1),
	(170, 5),
	(171, 5),
	(172, 5),
	(100, 6),
	(101, 6),
	(102, 6),
	(103, 6),
	(104, 6),
	(110, 6),
	(111, 6),
	(112, 6),
	(113, 6),
	(114, 6),
	(120, 6),
	(121, 6),
	(122, 6),
	(123, 6),
	(124, 6),
	(125, 6),
	(130, 6),
	(131, 6),
	(140, 6),
	(141, 6),
	(142, 6),
	(143, 6),
	(144, 6),
	(150, 6),
	(151, 6),
	(160, 6),
	(161, 6),
	(180, 6),
	(181, 6),
	(182, 6),
	(190, 6),
	(191, 6),
	(140, 7),
	(144, 7),
	(190, 8),
	(191, 8);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;


-- 导出  表 express.safe_policy 结构
DROP TABLE IF EXISTS `safe_policy`;
CREATE TABLE IF NOT EXISTS `safe_policy` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `timeout` int(11) DEFAULT NULL,
  `passwordLength` int(11) DEFAULT NULL,
  `errorLimit` int(11) DEFAULT NULL,
  `remoteDisabled` tinyint(1) DEFAULT NULL,
  `macDisabled` tinyint(1) DEFAULT NULL,
  `passwordRules` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `lockTime` int(10) NOT NULL DEFAULT '24' COMMENT '锁定时间(小时)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='安全策略表';

-- 正在导出表  express.safe_policy 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `safe_policy` DISABLE KEYS */;
REPLACE INTO `safe_policy` (`id`, `timeout`, `passwordLength`, `errorLimit`, `remoteDisabled`, `macDisabled`, `passwordRules`, `lockTime`) VALUES
	(1, 600, 0, 3, 0, 0, '^[0-9a-zA-Z!$#%@^&amp;amp;amp;amp;amp;amp;amp;*()~_+]{8,20}$', 1);
/*!40000 ALTER TABLE `safe_policy` ENABLE KEYS */;


-- 导出  表 express.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `idcard` varchar(20) DEFAULT NULL,
  `express_name` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `register_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `company_point_id` bigint(11) DEFAULT NULL,
  `express_number` varchar(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `idcard` (`idcard`),
  KEY `FK36EBCBA6C405B` (`company_point_id`),
  CONSTRAINT `FK_user_company_point` FOREIGN KEY (`company_point_id`) REFERENCES `company_point` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  express.user 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `idcard`, `express_name`, `password`, `phone`, `register_time`, `modify_time`, `company_point_id`, `express_number`, `status`) VALUES
	(2, '222222222222222222', '22222233', 'E3CEB5881A0A1FDAAD01296D7554868D', '12222222222', '2016-02-24 15:49:09', '2016-03-16 21:20:59', 1, '22222233', 1),
	(3, '333333333333333333', '333333', '25D55AD283AA400AF464C76D713C07AD', '13333333333', '2016-02-25 15:40:52', '2016-03-16 20:01:04', 2, '333333', 1),
	(5, '555555555555555555', '555555', '5B1B68A9ABF4D2CD155C81A9225FD158', '15555555555', '2016-03-09 19:30:45', '2016-03-09 19:30:45', 1, '555555', 1),
	(6, '339001199301014567', '快递一号', '30E535568DE1F9231E7D9DF0F4A5A44D', '13777777777', '2016-03-16 16:41:52', '2016-03-16 16:41:52', 1, '001', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- 导出  表 express.user_oper_log 结构
DROP TABLE IF EXISTS `user_oper_log`;
CREATE TABLE IF NOT EXISTS `user_oper_log` (
  `id` bigint(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `phone` varchar(15) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `log_info` varchar(15) DEFAULT NULL,
  `logInfo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  express.user_oper_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_oper_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_oper_log` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
