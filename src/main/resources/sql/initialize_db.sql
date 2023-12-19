CREATE DATABASE IF NOT EXISTS blogdb;
USE blogdb;
CREATE TABLE `article` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `text` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `category` (
  `id` integer PRIMARY KEY,
  `category` varchar(255)
);

CREATE TABLE `tag` (
  `id` integer PRIMARY KEY,
  `tag` varchar(255)
);

CREATE TABLE `articleCategory` (
  `article_id` integer,
  `category_id` integer,
  PRIMARY KEY (article_id, category_id)
);

CREATE TABLE `articleTag` (
  `article_id` integer,
  `tag_id` integer,
  PRIMARY KEY (article_id, tag_id)
);

ALTER TABLE `articleCategory` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

ALTER TABLE `articleCategory` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);

ALTER TABLE `articleTag` ADD FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`);

ALTER TABLE `articleTag` ADD FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);
