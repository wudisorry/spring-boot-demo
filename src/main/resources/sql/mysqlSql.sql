create table T_USER(
	F_ID INT(11) NOT NULL AUTO_INCREMENT,
	F_NAME VARCHAR(50),
	F_BIRTHDAY DATETIME,
	F_EMAIL varchar(50),
	F_INFO VARCHAR(200),
	F_ADD_USER INT(11),
	F_ADD_DATE DATETIME,
	F_UPDATE_USER INT(11),
	F_UPDATE_DATE DATETIME,
	PRIMARY KEY (F_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table T_SCHEDULE_CRON(
	F_ID INT(11) NOT NULL AUTO_INCREMENT,
	F_NAME VARCHAR(50),
	F_CLASS_NAME VARCHAR(100) comment '任务类名',
	F_CRON_EXPRESSION varchar(30),
	F_TASK_EXPLAIN VARCHAR(200) comment '任务描述',
	F_STATUS INT(1),
	F_EXCEPTION_MSG VARCHAR(2000) comment '执行失败原因',
	F_ADD_USER INT(11),
	F_ADD_DATE DATETIME,
	F_UPDATE_USER INT(11),
	F_UPDATE_DATE DATETIME,
	PRIMARY KEY (F_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;