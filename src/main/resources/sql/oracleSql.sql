create table t_user(
       id number(4) primary key,
       name varchar2(20),
       email varchar2(50),
       birthday date,
       remark varchar2(500)
);

create sequence seq_t_user start with 1 increment by 1;