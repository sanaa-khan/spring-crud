-- DROP TABLES
DROP TABLE if exists USER_SESSION;
DROP TABLE if exists USER;

/*
-- CREATE TABLE USER
*/

CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(30) NOT NULL,
                        `password` varchar(45) NOT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `username_UNIQUE` (`username`)
);

/*
-- INSERT INTO USER
*/

insert into user(user_id, username, password) values (1, 'sana', '1234');
insert into user(user_id, username, password) values (2, 'admin', '1234');
insert into user(user_id, username, password) values (3, 'hassan', '1234');

/*
-- CREATE TABLE USER SESSION
*/

CREATE TABLE `user_session` (
                                `id` varchar(50) NOT NULL,
                                `username` varchar(45) NOT NULL,
                                `login_time` datetime NOT NULL,
                                `login_ip` varchar(45) NOT NULL,
                                `session_id` varchar(45) NOT NULL,
                                `logout_time` datetime DEFAULT NULL,
                                `user_id` int NOT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `id_UNIQUE` (`id`),
                                KEY `user_id_idx` (`user_id`),
                                CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION
);