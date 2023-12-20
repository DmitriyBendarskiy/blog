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

CREATE TABLE `article_category` (
  `id` varchar(40) PRIMARY KEY,
  `article_id` varchar(40),
  `category_id` varchar(40),
  UNIQUE (article_id, category_id)
);

CREATE TABLE `article_tag` (
  `id` varchar(40) PRIMARY KEY,
  `article_id` varchar(40),
  `tag` varchar(40),
  UNIQUE (article_id, tag)
);

ALTER TABLE `article_category` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

ALTER TABLE `article_category` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);

ALTER TABLE `article_tag` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);
