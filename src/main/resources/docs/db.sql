CREATE TABLE "sys_user" (
"user_id"  integer PRIMARY KEY AUTOINCREMENT NOT NULL,
"user_name"  varchar(50),
"password"  varchar(50),
"status"  integer,
"create_time"  varchar(50),
"login_time"  varchar(50)
);

CREATE TABLE "sys_userinfo" (
"id"  integer PRIMARY KEY AUTOINCREMENT NOT NULL,
"user_id"  integer NOT NULL,
"real_name"  varchar(50),
"nick_name"  varchar(50),
"phone"  varchar(11),
"email"  varchar(50),
"sex"  integer,
"birthday"  varchar(50)
);

CREATE TABLE "sys_dict_type" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"dict_name"  varchar(50),
"dict_code"  varchar(50),
"remark"  varchar(100)
);

CREATE TABLE "sys_dict_item" (
"id"  integer PRIMARY KEY AUTOINCREMENT NOT NULL,
"dict_name"  varchar(50),
"dict_code"  varchar(50),
"dict_type"  varchar(50),
"remark"  varchar(100)
);
