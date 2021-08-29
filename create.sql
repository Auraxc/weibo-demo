CREATE DATABASE `weibo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE weibo;

CREATE TABLE `comment` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `content` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `weiboId` int(11) NOT NULL,
                           `userId` int(11) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `follow` (
                          `followerId` int(11) NOT NULL,
                          `followeeId` int(11) NOT NULL,
                          PRIMARY KEY (`followerId`,`followeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `message` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `content` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `user_id` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `session` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `session` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `salt` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `user_id` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `todo` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `content` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `userId` int(11) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `user_role` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `salt` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `avator` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `weibo` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `content` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `userId` int(11) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;