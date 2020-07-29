create table t_user(
	id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50),
	birthday DATETIME,
	email varchar(50),
	info VARCHAR(200),
	addUser INT(11),
	addDate DATETIME,
	updateUser INT(11),
	updateDate DATETIME,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table t_schedule_cron(
	id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50),
	className VARCHAR(100) comment '任务类名',
	cronExpression varchar(30),
	taskExplain VARCHAR(200) comment '任务描述',
	status INT(1),
	exceptionMsg VARCHAR(2000) comment '执行失败原因',
	addUser INT(11),
	addDate DATETIME,
	updateUser INT(11),
	updateDate DATETIME,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_schedule_cron(id, name, className, cronExpression, taskExplain) VALUES (1, '第一个定时任务', 'com.arh.springbootdemo.schedule.PrintTextTaskServiceImpl', '*/10 * * * * ?', '第一个还能描述啥');