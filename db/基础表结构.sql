/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 127.0.0.1:3306
 Source Schema         : qishuihe

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 04/09/2022 21:55:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_t`;
CREATE TABLE `sys_permission_t`  (
  `id` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色Id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限点',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限描述',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `enable` int(11) NULL DEFAULT 0,
  `tenant` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_t
-- ----------------------------
INSERT INTO `sys_permission_t` VALUES (10001, '/admin/logout', '登出', 'qishuihe', '2022-07-21 00:00:00', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission_relation_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_relation_t`;
CREATE TABLE `sys_role_permission_relation_t`  (
  `id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联Id',
  `role_id` bigint(20) NULL DEFAULT 0 COMMENT '角色点',
  `permission_id` bigint(20) NULL DEFAULT 0 COMMENT '权限ID',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `enable` int(11) NULL DEFAULT NULL,
  `tenant` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统校色和权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission_relation_t
-- ----------------------------
INSERT INTO `sys_role_permission_relation_t` VALUES (10001, 10001, 10001, 'qishuihe', '2022-07-21 23:13:00', 'qishuihe', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_t`;
CREATE TABLE `sys_role_t`  (
  `id` bigint(20) NOT NULL COMMENT '角色Id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `enable` int(11) NULL DEFAULT NULL,
  `tenant` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_t
-- ----------------------------
INSERT INTO `sys_role_t` VALUES (10001, '管理员', 'admin', 'qishuihe', '2022-07-21 23:11:00', 'qishuihe', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_user_relation_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user_relation_t`;
CREATE TABLE `sys_role_user_relation_t`  (
  `id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联Id',
  `role_id` bigint(20) NULL DEFAULT 0 COMMENT '角色点',
  `user_id` bigint(20) NULL DEFAULT 0 COMMENT '用户ID',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `enable` int(11) NULL DEFAULT NULL,
  `tenant` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色和用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user_relation_t
-- ----------------------------
INSERT INTO `sys_role_user_relation_t` VALUES (10001, 10001, 10001, 'qishuihe', '2022-07-21 23:13:00', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_user_relation_t` VALUES (10002, 10001, 1551197898651254784, 'qishuihe', '2022-07-21 23:13:00', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_t`;
CREATE TABLE `sys_user_t`  (
  `id` bigint(20) NOT NULL DEFAULT 0 COMMENT '系统生成的唯一标识',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录密码',
  `sex` int(11) NULL DEFAULT 0 COMMENT '0:男,1女',
  `work_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '工号，作为登录的唯一标识',
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮件地址',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最后更新人',
  `update_date` datetime(0) NULL DEFAULT NULL,
  `tenant` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '租户编码',
  `enable` int(11) NULL DEFAULT 0 COMMENT '是否删除，0有效，1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_t
-- ----------------------------
INSERT INTO `sys_user_t` VALUES (10001, 'qishuihe', '$2a$10$/Sntcri.oeF7BE2//n2svOStLtRxLp6TqY900HFv60fBxGwNy.uiO', 1, '10001', '10001@qishuihe.com', '10001', '2022-08-15 22:15:00', '10001', '2022-08-15 22:16:00', '10001', 0);
INSERT INTO `sys_user_t` VALUES (1566422343020998657, 'string', 'string', 0, 'string', '123111', '10001', '2022-09-04 21:46:04', '10001', '2022-09-04 21:46:04', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
