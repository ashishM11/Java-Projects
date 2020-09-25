drop database if exists `inventory_management`;
create database if not exists `inventory_management`;
use `inventory_management`;

drop table if exists `tblBrand`;
create table if not exists `tblBrand`(
	brandID bigint primary key auto_increment,
    brandName varchar(50) not null unique,
    brandDesc varchar(500) not null,
    brandLogo text
);

drop table if exists `tblStock`;
create table if not exists `tblStock`(
	stockID bigint primary key auto_increment,
    brandName varchar(50) not null unique,
    brandDesc varchar(500) not null,
    brandLogo text
);