CREATE DATABASE IF NOT EXISTS blogdb;
USE blogdb;
CREATE TABLE `article` (
  `id` varchar(40) PRIMARY KEY,
  `name` varchar(255),
  `text` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `category` (
  `id` varchar(40) PRIMARY KEY,
  `category` varchar(255)
);

CREATE TABLE `articleCategory` (
  `id` varchar(40) PRIMARY KEY,
  `article_id` varchar(40),
  `category_id` varchar(40),
  UNIQUE (article_id, category_id)
);

CREATE TABLE `articleTag` (
  `id` varchar(40) PRIMARY KEY,
  `article_id` varchar(40),
  `tag` varchar(40),
  UNIQUE (article_id, tag)
);

ALTER TABLE `articleCategory` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

ALTER TABLE `articleCategory` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);

ALTER TABLE `articleTag` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);
