create table t_user(
	id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(30),
	birthday DATETIME,
	email varchar(30),
	info VARCHAR(200),
	addUser INT(11),
	addDate DATETIME,
	updateUser INT(11),
	updateDate DATETIME,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;