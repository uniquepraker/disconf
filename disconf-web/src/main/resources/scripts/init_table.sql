CREATE DATABASE IF NOT EXISTS disconf;
USE disconf;

CREATE TABLE IF NOT EXISTS user (
  user_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  name varchar(50) NOT NULL COMMENT '姓名',
  password varchar(255) NOT NULL COMMENT '密码',
  token varchar(255) NOT NULL COMMENT 'token',
  ownapps varchar(255) NOT NULL DEFAULT '' COMMENT '能操作的APPID,逗号分隔',
  role_id bigint(20) NOT NULL DEFAULT '1' COMMENT '角色ID',
  PRIMARY KEY (user_id)
)
  ENGINE=InnoDB
  DEFAULT CHARSET=utf8
  COMMENT '用户表';
