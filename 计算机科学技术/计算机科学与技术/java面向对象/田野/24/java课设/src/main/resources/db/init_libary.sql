-- 1) 创建数据库（如果不存在）并切换到该库
CREATE DATABASE IF NOT EXISTS `libary` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `libary`;

-- 2) 创建表 books（如果不存在）
CREATE TABLE IF NOT EXISTS `books` (
  `bookid` INT NOT NULL AUTO_INCREMENT,
  `bookname` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) 创建借阅记录表 borrow_records（如果不存在）
CREATE TABLE IF NOT EXISTS `borrow_records` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bookid` INT NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `borrow_date` DATETIME NOT NULL,
  `due_date` DATETIME NOT NULL,
  `return_date` DATETIME NULL,
  `status` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) 创建预约表 reservations（如果不存在）
CREATE TABLE IF NOT EXISTS `reservations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bookid` INT NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `reserve_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5) 插入一些示例数据（如果表为空）
INSERT INTO `books` (bookname)
SELECT * FROM (SELECT 'Java编程思想' AS bookname UNION ALL
                SELECT '深入理解Java虚拟机' UNION ALL
                SELECT 'MyBatis深入解析' UNION ALL
                SELECT 'Spring实战' UNION ALL
                SELECT '算法导论') AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `books` LIMIT 1);
