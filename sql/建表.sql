CREATE DATABASE IF NOT EXISTS springdemo DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE USER guest@'localhost' IDENTIFIED BY 'password';
#adminLy可以远程访问
CREATE USER adminLy@'%' IDENTIFIED BY 'password';
#修改user01密码
SET PASSWORD FOR 'adminLy'@'%' = PASSWORD('ly19955125');
#授权
#adminLy管理ly全部权限
GRANT ALL PRIVILEGES ON ly.* TO adminLy;
#guest查看权限，并修改密码
GRANT SELECT  ON *.* TO 'guest'@'localhost' IDENTIFIED by 'password';
CREATE TABLE ly.bas_user
(
    pk_id INT PRIMARY KEY COMMENT '用户id，自增主键' AUTO_INCREMENT,
    user_name VARCHAR(20) NOT NULL COMMENT '用户登录时的用户名',
    user_password CHAR(32) NOT NULL COMMENT '用户登录时的密码，用md5转换',
    sex TINYINT COMMENT '用户的性别，1：男，2：女'
);
CREATE UNIQUE INDEX bas_user_user_name_uindex ON ly.bas_user (user_name);
ALTER TABLE ly.bas_user COMMENT = '用户表，存储用户相关信息';