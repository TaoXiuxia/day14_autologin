其中使用的数据库是
create database day14;
use day14;
create table user(
		id int primary key auto_increment,
		name varchar(20),
		password varchar(40),
		role varchar(20)
	);
insert into user values(null,'a','123','user'),(null,'b','123','user'),(null,'c','123','user'),(null,'admin','123','admin');
update user set password=MD5(password);