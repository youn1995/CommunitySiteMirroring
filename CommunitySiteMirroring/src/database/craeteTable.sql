conn hr/hr

create table communitysite
(id number(20) PRIMARY key,
division varchar2(20) not null,
title varchar2(1000) not null,
body varchar2(4000),
writer varchar2(300) not null,
hits number(10) default 0,
recommend number(10) default 0,
time date not null,
url varchar(1000) not null);