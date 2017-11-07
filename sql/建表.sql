CREATE DATABASE IF NOT EXISTS immortal_blog DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE USER guest@'localhost' IDENTIFIED BY 'password';
#adminLy可以远程访问
CREATE USER ly@'%' IDENTIFIED BY 'password';
#修改user01密码
SET PASSWORD FOR 'ly'@'%' = PASSWORD('955125');
#授权
#adminLy管理immortal_blog全部权限
GRANT ALL PRIVILEGES ON immortal_blog.* TO ly;
#guest查看权限，并修改密码
GRANT SELECT  ON *.* TO 'guest'@'localhost' IDENTIFIED by 'password';
CREATE TABLE immortal_blog.bas_user
(
    pk_id INT PRIMARY KEY COMMENT '用户id，自增主键' AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL COMMENT '用户登录时的用户名',
    password VARCHAR(32) NOT NULL COMMENT '用户登录时的密码',
    sex TINYINT COMMENT '用户的性别，1：男，0：女'
) COMMENT '用户表，存储用户相关信息';
CREATE UNIQUE INDEX ui_bas_user_username ON immortal_blog.bas_user (username);
