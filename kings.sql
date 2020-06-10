/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : kings

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 10/05/2020 13:53:35
 conson: 带测试数据的 SQL
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (90);
INSERT INTO `hibernate_sequence` VALUES (90);
INSERT INTO `hibernate_sequence` VALUES (90);
INSERT INTO `hibernate_sequence` VALUES (90);
INSERT INTO `hibernate_sequence` VALUES (90);

-- ----------------------------
-- Table structure for t_all_heroes
-- ----------------------------
DROP TABLE IF EXISTS `t_all_heroes`;
CREATE TABLE `t_all_heroes`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_all_heroes
-- ----------------------------
INSERT INTO `t_all_heroes` VALUES (40, '辅助');
INSERT INTO `t_all_heroes` VALUES (41, '射手');
INSERT INTO `t_all_heroes` VALUES (42, '法师');
INSERT INTO `t_all_heroes` VALUES (70, '刺客');
INSERT INTO `t_all_heroes` VALUES (75, '战士');
INSERT INTO `t_all_heroes` VALUES (76, '坦克');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `kings_id` bigint(20) NULL DEFAULT NULL,
  `parent_comment_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKs4g74miwfsofvl47rf1qferyx`(`kings_id`) USING BTREE,
  INDEX `FK4jj284r3pb7japogvo6h72q95`(`parent_comment_id`) USING BTREE,
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKs4g74miwfsofvl47rf1qferyx` FOREIGN KEY (`kings_id`) REFERENCES `t_kings` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_kings
-- ----------------------------
DROP TABLE IF EXISTS `t_kings`;
CREATE TABLE `t_kings`  (
  `id` bigint(20) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `speak_content` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `type_id` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `heroes_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKecwqb9nybbt15l190defee21t`(`type_id`) USING BTREE,
  INDEX `FKawcvft8nfh0k7bvb509a5e2h6`(`user_id`) USING BTREE,
  CONSTRAINT `FKawcvft8nfh0k7bvb509a5e2h6` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKecwqb9nybbt15l190defee21t` FOREIGN KEY (`type_id`) REFERENCES `t_all_heroes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_kings
-- ----------------------------
INSERT INTO `t_kings` VALUES (49, b'1', '## 一、说明：\r\n\r\n◷ 使用JDBC技术连接MySQL数据库\r\n\r\n<!-- more -->\r\n\r\n 主要用途：\r\n1. 与数据库建立连接\r\n2. 发送SQL语句\r\n3. 处理结果\r\n\r\n## 二、提取工具类\r\n\r\n▷ 先提取出需要经常使用的代码\r\n\r\n``` Java\r\npackage JDBC;\r\n\r\nimport java.sql.*;\r\nimport java.util.ArrayList;\r\n\r\npublic class JDBCUnits {\r\n\r\n    public static final String user = \"root\";\r\n    public static final String password = \"root\";\r\n    public static final String url = \"jdbc:mysql://localhost:3306/mygamedb?useUnicode=true&characterEncoding=UTF8&useSSL=false\";\r\n\r\n    //使用集合创建简易连接池 ↓\r\n    private static ArrayList<Connection> connectionArrayList = new ArrayList<>();\r\n\r\n    static {\r\n        for (int i = 0 ;i<=5; i++){\r\n            Connection connection = CreateConnection();\r\n            connectionArrayList.add(connection);\r\n        }\r\n    }\r\n\r\n    public static Connection getConnection(){\r\n\r\n        if (connectionArrayList.isEmpty()==false){\r\n            Connection connection = connectionArrayList.get(0);\r\n            connectionArrayList.remove(connection);\r\n            return connection ;\r\n        }else{\r\n            return CreateConnection();\r\n        }\r\n    }\r\n\r\n    public static Connection CreateConnection(){\r\n        try {\r\n            Class.forName(\"com.mysql.jdbc.Driver\");\r\n            return  DriverManager.getConnection(url, user, password);\r\n        } catch (Exception e) {\r\n            e.printStackTrace();\r\n        }\r\n        return null;\r\n    }\r\n\r\n    public static void close(ResultSet resultSet , Statement statement , Connection connection) {\r\n        closeResultSet(resultSet);\r\n        closeStatement(statement);\r\n        closeConnection(connection);\r\n    }\r\n\r\n    public static void close(Statement statement1 , Statement statement2 , Connection connection){\r\n        closeStatement(statement1);\r\n        closeStatement(statement2);\r\n        closeConnection(connection);\r\n    }\r\n\r\n        private static void closeResultSet (ResultSet resultSet){\r\n            if (resultSet != null) {\r\n                try {\r\n                    resultSet.close();\r\n                } catch (SQLException e) {\r\n                    e.printStackTrace();\r\n                }\r\n            }\r\n        }\r\n\r\n        private static void closeStatement (Statement statement){\r\n            if (statement != null) {\r\n                try {\r\n                    statement.close();\r\n                } catch (SQLException e) {\r\n                    e.printStackTrace();\r\n                }\r\n            }\r\n        }\r\n\r\n        private static void closeConnection(Connection connection){\r\n            if (connection != null) {\r\n                try {\r\n                    connection.close();\r\n                } catch (SQLException e) {\r\n                    e.printStackTrace();\r\n                }\r\n            }\r\n        }\r\n\r\n}\r\n\r\n```\r\n\r\n## 三、查询\r\n\r\n```Java\r\npublic class JDBC {\r\n\r\n    public static void main(String[] args) {\r\n\r\n        selectAll();\r\n    \r\n    }\r\n\r\n    //查询全部\r\n    public static void selectAll() {\r\n\r\n        Connection connection = null;\r\n        Statement statement = null;\r\n        ResultSet resultSet = null;\r\n\r\n        try {\r\n            connection = JDBCUnits.getConnection();\r\n\r\n            statement = connection.createStatement();\r\n\r\n            resultSet = statement.executeQuery(\"select * from goods\");\r\n\r\n            while (resultSet.next()) {\r\n                System.out.println(\"ID:\"+resultSet.getInt(1)+\",\"+\"道具:\"+resultSet.getString(2)+\",\"+\"属性:\"+resultSet.getString(3)+\"\\n\");\r\n            }\r\n\r\n        } catch (Exception e) {\r\n            e.printStackTrace();\r\n        } finally {\r\n            JDBCUnits.close(resultSet, statement, connection);\r\n        }\r\n    }\r\n}\r\n```\r\n## 四、分页查询\r\n\r\n▷ 分页查询  pageNunber页数  pageCount每页查询条数\r\n\r\n```JAVA\r\n    public static void pagingQuery ( int pageNunber, int pageCount){\r\n\r\n        Connection connection = null;\r\n        PreparedStatement statement = null;\r\n        ResultSet resultSet = null;\r\n\r\n        try {\r\n\r\n            connection = JDBCUnits.getConnection();\r\n\r\n            statement = connection.prepareStatement(\"select * from user limit ?,?\");\r\n\r\n            statement.setInt(1, (pageNunber - 1) * pageCount);\r\n            statement.setInt(2, pageCount);\r\n\r\n            resultSet = statement.executeQuery();\r\n\r\n            while (resultSet.next()) {\r\n                \r\n                System.out.println(resultSet.getInt(\"id\") + resultSet.getString(\"username\") + resultSet.getString(\"password\") + resultSet.getString(\"registerdate\"));\r\n\r\n            }\r\n\r\n        } catch (Exception e) {\r\n            e.printStackTrace();\r\n        } finally {\r\n            JDBCUnits.close(resultSet,statement,connection);\r\n        }\r\n    }\r\n```\r\n## 五、插入\r\n\r\n```java\r\n //插入操作\r\n    public static void insert(String username , String password){\r\n\r\n        Connection connection = null;\r\n        PreparedStatement statement = null;\r\n        ResultSet resultSet = null;\r\n\r\n        try {\r\n            connection = JDBCUnits.getConnection();\r\n\r\n            String sql = \"insert into user (username,password) values(?,?)\";\r\n\r\n            statement = connection.prepareStatement(sql);\r\n            statement.setString(1,username);\r\n            statement.setString(2,password);\r\n            int result = statement.executeUpdate();\r\n\r\n        } catch (Exception e) {\r\n            e.printStackTrace();\r\n        } finally {\r\n            JDBCUnits.close(resultSet, statement, connection);\r\n        }\r\n    }\r\n```\r\n\r\n## 六、更新\r\n\r\n```java\r\n //更新操作\r\n    public static void update(int id ,String NewPassWord){\r\n\r\n        Connection connection = null;\r\n        PreparedStatement statement = null;\r\n        ResultSet resultSet = null;\r\n\r\n        try {\r\n            connection = JDBCUnits.getConnection();\r\n\r\n            String sql = \"update user set password = ? where id = ? \";\r\n\r\n            statement = connection.prepareStatement(sql);\r\n            statement.setString(1,NewPassWord);\r\n            statement.setInt(2,id);\r\n\r\n            int result = statement.executeUpdate();\r\n            if (result>0){\r\n                System.out.println(\"修改成功\");\r\n            }else{\r\n                System.out.println(\"修改失败\");\r\n            }\r\n\r\n        } catch (Exception e) {\r\n            e.printStackTrace();\r\n        } finally {\r\n            JDBCUnits.close(resultSet, statement, connection);\r\n        }\r\n    }\r\n```\r\n\r\n## 七、删除\r\n```java\r\n //删除操作(根据 id 进行删除)\r\n    public static void delete(int id){\r\n\r\n        Connection connection = null;\r\n        PreparedStatement statement = null;\r\n        ResultSet resultSet = null;\r\n\r\n        try {\r\n            connection = JDBCUnits.getConnection();\r\n\r\n            String sql = \"delete from user where id = ? \";\r\n\r\n            statement = connection.prepareStatement(sql);\r\n            statement.setInt(1,id);\r\n            int result = statement.executeUpdate();\r\n            if (result>0){\r\n                System.out.println(\"删除成功\");\r\n            }else{\r\n                System.out.println(\"删除失败\");\r\n            }\r\n\r\n        } catch (Exception e) {\r\n            e.printStackTrace();\r\n        } finally {\r\n            JDBCUnits.close(resultSet, statement, connection);\r\n        }\r\n    }\r\n```\r\n\r\n## 八、测试\r\n\r\n```java\r\npackage JDBC;\r\n\r\nimport java.sql.*;\r\n\r\npublic class JDBC01 {\r\n\r\n    public static void main(String[] args) {\r\n\r\n        //查询全部数据\r\n        selectAll();\r\n        \r\n        //插入数据\r\n        insert(\"我的世界\",\"468\");\r\n\r\n        //更新ID为29的密码\r\n        update(29,\"720\");\r\n\r\n        //删除ID为30的账户\r\n        delete(30);\r\n        \r\n    }\r\n}\r\n```\r\n\r\n## 九、结果\r\n\r\n![](https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E6%96%87%E7%AB%A0%E7%94%A8%E5%9B%BE/JDBC%E8%BF%9E%E6%8E%A5/lall.jpg.png)\r\n', NULL, NULL, 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg', b'1', 'JDBC连接MySQL数据库', '2020-04-08 15:48:30.184000', 40, 1, b'1', '使用JDBC技术连接MySQL数据库', 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg');
INSERT INTO `t_kings` VALUES (85, b'1', '## 1、李元芳的技能解析：\r\n&emsp;&emsp;李元芳的被动是密探谛听，只要李元芳靠近草丛，里面的情况都会显现出来，没有任何事物可以逃得过李元芳的眼睛，这样就不用担心敌方英雄偷袭。就连阿珂和兰陵王的隐身技能都逃不过他的眼睛。\r\n\r\n- ① 技能谍影重重，向敌方英雄发出四个小飞碟，几秒钟后同时打向敌方英雄，伤害很高。\r\n- ② 二技能刃盾穿透性很强，留下的刀痕也可以烧伤铁。\r\n- ③ 大招无间刃风，向指定方向发出一个大的飞碟，不断旋转灼伤敌人，伤害很高。\r\n\r\n\r\n## 2、铭文推荐：\r\n&emsp;&emsp;红色十异变，蓝色十狩猎，绿色十鹰眼。李元芳的爆发伤害虽然高，但他并不是很依靠攻速和暴击，所以一般情况下玩的都是百穿流。在实战中配合上装备一起使用，即使在面对坦克英雄时也能打出不俗的伤害。\r\n\r\n## 3、出装推荐：\r\n&emsp;&emsp;急速战靴，泣血之刃，暗影战斧，破晓，破军，名刀司命。因为李元芳并不是很依靠攻速，一技能释放之后就会有一个短暂的攻速加成，加上是一个非常需要爆发的英雄，所以这套出装还是非常不错的选择。\r\n\r\n## 4、对抗技巧：\r\n&emsp;&emsp;第一：对抗李元芳的第一点就是要注意他的二技能，李元芳的二技能是一个位移技能，但同时还有另外一个非常重要的效果，那就是会产生一个短暂的无法选中状态。李元芳没有二技能的时候，作战能力会大大下降，所以我们一定要看准时机，他的二技能一旦进入cd是击杀他的最好时机。\r\n\r\n&emsp;&emsp;第二：第二点笔者来给大家推荐几位比较克制李元芳的英雄，笔者在这里给大家推荐的是百里玄策和李白。百里玄策的留人能力自然不用多说，而且具有多段控制效果和突进能力。而李白的一技能具有两段位移，而且第三段可以回到原地，二技能和三技能都具有短暂的无法选中效果，在技能机制方面上对李元芳有很明显的克制作用。\r\n\r\n## 5、团战注意事项：\r\n&emsp;&emsp;中期和后期团战时，主要看大招的释放时期。李元芳的大招很吃队友控制的，如果队友有控制最好。没有控制就把大招往人堆里面丢，别想着往对面后排丢，还没丢过去就会被对面集火了。大招的伤害很可观，而且自带减速效果，可以让对面短时间打不到你，放大招记得把一技能也丢出去，然后就在后排普攻输出就行了。\r\n\r\n## 6、打法技巧：\r\n&emsp;&emsp;李元芳不仅推塔快，而且对线也强势，尤其对到战士，李元芳的一技能消耗+二技能位移，让敌人很头疼，摸不到而且要被频繁耗血，稍不甚就被击杀。不仅如此，元芳的被动能显示近范围的蹲草的敌人，而且隐身的技能对元芳也无效，比如版本强势的兰陵王，碰到元芳就比较头疼了。元芳目前还是前中期英雄，因此要尽量加快推塔节奏，有元芳的阵容，辅助尽量选择协助他推塔将节奏带起来。\r\n\r\n&emsp;&emsp;二技能是保命技能，不要轻易使用，除非是在追击的时候，否则尽量留着放这被突脸，二技能位移很长，路径上有伤害有减速，而且有不可选中状态，能躲避很多关键伤害，防御能力甚佳。大招除了伤害，更大的作用是用来分割战场，比如将敌人压在塔下，或者断掉敌人的逃跑路线，是一个非常好用的技能。\r\n\r\n(侵删)信息来源于：[百度体育](https://tiyu.baidu.com/news?id=ac85d4abf59d999bfc1de6787c9a983f)', NULL, NULL, 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/Kings/indexBigPicture/liyuanfang.jpeg', b'0', '一直在看着你的李元芳小朋友', '2020-04-10 16:07:51.713000', 41, 1, b'1', '李元芳的一些小技巧与玩法', 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/Kings/heroes/lyf.jpg');
INSERT INTO `t_kings` VALUES (86, b'0', '150150150150150150150150150150150150150150150150150150150150150150', NULL, NULL, 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg', b'0', '发生的会发布喀什觉得很费', '2020-04-10 21:11:20.167000', 41, 1, b'1', '发生的会发布喀什觉得很费发生的会发布喀什', 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg');
INSERT INTO `t_kings` VALUES (87, b'1', 'f不发烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦', '2020-04-10 21:33:19.174000', NULL, 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg', b'1', 'v不发达v不得不东风标致备份', '2020-04-10 21:33:19.174000', 41, 1, b'1', '吧烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦八', 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/Kings/heroes/ak.jpg');
INSERT INTO `t_kings` VALUES (88, b'1', '发生的会发布喀什觉得很费发生的会发布喀什觉得很费发生的会发布喀什觉得很费发生的会发布喀什觉得很费发生的会发布喀什觉得很费发生的会发布喀什觉得很费发生的会发布喀什觉得很费发生的会发布喀什觉得很费发生的会发布喀什觉得很费', NULL, NULL, 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E5%90%89%E4%BB%96.jpg', b'1', '生气的安其拉', '2020-04-10 21:42:58.455000', 41, 1, b'1', '发生的会发布喀什觉得很费发生的会发布喀什', 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/Kings/heroes/lyf.jpg');
INSERT INTO `t_kings` VALUES (89, b'1', '你你你你你你你你你你你你你你你你你你你你', '2020-04-10 21:36:35.371000', NULL, 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg', b'1', '你你你你你你你你你你你你你你你你你你你你', '2020-04-10 21:36:35.371000', 41, 1, b'1', '你你你你你你你你你你你你你你你你你你你你', 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg');

-- ----------------------------
-- Table structure for t_kings_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_kings_tags`;
CREATE TABLE `t_kings_tags`  (
  `kings_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  INDEX `FK2u70hxy7p0s10e5xfkpa9opi9`(`tags_id`) USING BTREE,
  INDEX `FKg2nvy6d74176pho06volsidta`(`kings_id`) USING BTREE,
  CONSTRAINT `FK2u70hxy7p0s10e5xfkpa9opi9` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKg2nvy6d74176pho06volsidta` FOREIGN KEY (`kings_id`) REFERENCES `t_kings` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_kings_tags
-- ----------------------------
INSERT INTO `t_kings_tags` VALUES (49, 73);
INSERT INTO `t_kings_tags` VALUES (49, 74);
INSERT INTO `t_kings_tags` VALUES (85, 69);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (43, '999');
INSERT INTO `t_tag` VALUES (44, '888');
INSERT INTO `t_tag` VALUES (45, 'sss');
INSERT INTO `t_tag` VALUES (47, 'coco');
INSERT INTO `t_tag` VALUES (69, 'Docker');
INSERT INTO `t_tag` VALUES (73, 'Java');
INSERT INTO `t_tag` VALUES (74, 'MySQL');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'https://consonblog-1257792125.cos.ap-chengdu.myqcloud.com/%E5%8D%9A%E5%AE%A2%E8%83%8C%E6%99%AF/%E6%A0%87%E7%AD%BE.jpg', '2020-03-31 00:00:00.000000', '康森', 'c2d5698d51a19d8a121ce581499d7b701668', 1, '2020-03-31 00:00:00.000000', 'conson');

SET FOREIGN_KEY_CHECKS = 1;
