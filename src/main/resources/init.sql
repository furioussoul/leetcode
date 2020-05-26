DROP database IF EXISTS `miaosha`;
create database `miaosha` default character set utf8mb4;

use miaosha;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `GOODS`;
create table GOODS
(
    ID         decimal(8) primary key  auto_increment,
    NAME       varchar(50),
    GOOD_ID    decimal(8),
    LEFT_COUNT decimal(8)
)
    comment '商品库存';


create index ID
    on GOODS (NAME);


