create table t_user(
	id INT(11) NOT NULL,
	name VARCHAR(20),
	birthday DATETIME,
	info VARCHAR(200),
	updateTime DATETIME,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;