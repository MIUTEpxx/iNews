/*
 Navicat Premium Data Transfer

 Source Server         : pxx
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : inews

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 23/10/2024 15:40:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`  (
  `id` int(0) NOT NULL COMMENT '新闻id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '详情内容',
  `classid` int(0) NULL DEFAULT NULL COMMENT '新闻栏目id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES (1, '<p>\r\n	<span style=\"font-size:16px;line-height:2;\">据路透社报道，当地时间26日，2024年美国总统大选共和党候选人特朗普表示，他将重返宾夕法尼亚州巴特勒市参加一场集会。此前在7月13日，特朗普在该地的竞选集会现场发表演讲时，遭遇未遂刺杀，轰动世界。</span>\r\n</p>\r\n<p>\r\n	<span style=\"font-size:16px;\"><img src=\"http://localhost:9090/images/news/1_1.png\" alt=\"\" /><br />\r\n</span><span style=\"line-height:2;\"></span><br />\r\n<span style=\"font-size:16px;line-height:2;\">26日，特朗普在其自创社媒“真实社交”上发文称，“我将回到宾夕法尼亚州的巴特勒，参加一场盛大而美丽的集会”，但他没有透露具体地点和时间。</span><br />\r\n<br />\r\n<span style=\"font-size:16px;line-height:2;\">报道引述《华尔街日报》26日的民调结果称，目前特朗普的竞选支持率为49%，哈里斯的支持率则为47%，误差幅度为3个百分点。该报本月早些时候的一项民调显示，特朗普以48%对42%的优势领先于现任总统拜登。</span><br />\r\n<br />\r\n<span style=\"font-size:16px;line-height:2;\">由于受到民主党内高层及金主施压等因素，拜登于21日宣布退出2024年总统竞选，并推举哈里斯作为党内候选人继续竞选。</span><br />\r\n<br />\r\n<span style=\"font-size:16px;line-height:2;\"></span><span style=\"font-size:16px;line-height:2;\"></span>\r\n</p>', 51);
INSERT INTO `detail` VALUES (2, '<p>\r\n	<span style=\"font-size:16px;line-height:2;\">根据pxx社报道,2024年10月16日13点40分,国内新兴游戏工作室倪哈游(NinHoYo)正式宣布制作《苦力怕传说:狂野之息》!</span>\r\n</p>\r\n<p>\r\n	<span style=\"font-size:16px;\"><img src=\"http://localhost:9090/images/news/2_1.png\" alt=\"\" /><br />\r\n</span><span style=\"line-height:2;\"></span><br />\r\n<span style=\"font-size:16px;line-height:2;\">团队主创倪哥表示,《苦力怕传说:狂野之息》将会是一个半开放式幻想冒险RPG,但暂未透露计划发售日期。</span><br />\r\n<br />\r\n</p>', 50);

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `userid` int(0) NOT NULL,
  `newsid` int(0) NOT NULL,
  PRIMARY KEY (`userid`, `newsid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorites
-- ----------------------------
INSERT INTO `favorites` VALUES (10086, 1);

-- ----------------------------
-- Table structure for navlist
-- ----------------------------
DROP TABLE IF EXISTS `navlist`;
CREATE TABLE `navlist`  (
  `id` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新闻栏目id',
  `classname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻栏目名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of navlist
-- ----------------------------
INSERT INTO `navlist` VALUES ('50', '国内');
INSERT INTO `navlist` VALUES ('51', '国际');
INSERT INTO `navlist` VALUES ('52', '体育');
INSERT INTO `navlist` VALUES ('53', '科技');
INSERT INTO `navlist` VALUES ('54', '时尚');
INSERT INTO `navlist` VALUES ('55', '游戏');

-- ----------------------------
-- Table structure for newslist
-- ----------------------------
DROP TABLE IF EXISTS `newslist`;
CREATE TABLE `newslist`  (
  `id` int(0) NOT NULL COMMENT '新闻id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻标题',
  `picurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻封面在静态资源的路径',
  `posttime` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻发布时间',
  `hits` int(0) NULL DEFAULT NULL COMMENT '新闻点击量',
  `classid` int(0) NULL DEFAULT NULL COMMENT '新闻栏目id,对应navlist中的id',
  `author` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻作者或来源',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of newslist
-- ----------------------------
INSERT INTO `newslist` VALUES (1, '特朗普将重返刺杀小镇进行公开演讲!', '/images/news/1.jpg', '1722043200', 123456, 51, 'pxx');
INSERT INTO `newslist` VALUES (2, '又一个国产3A?苦力怕传说:狂野之息制作决定!', '/images/news/2.png', '1729073066', 114514, 50, 'pxx');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL COMMENT '用户账号(id)',
  `nickname` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `picurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10086, 'pxx', '123456', '123@email.com', '123456', '/images/user/10086.jpg');

SET FOREIGN_KEY_CHECKS = 1;
