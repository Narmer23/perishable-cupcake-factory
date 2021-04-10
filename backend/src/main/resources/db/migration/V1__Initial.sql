--
-- Table structure for table `cake`
--

DROP TABLE IF EXISTS `cake`;
CREATE TABLE `cake`
(
    `id`            binary(16) NOT NULL,
    `default_price` decimal(19, 2) DEFAULT NULL,
    `name`          varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`id`)
);

--
-- Table structure for table `batch`
--

DROP TABLE IF EXISTS `batch`;
CREATE TABLE `batch`
(
    `id`              binary(16) NOT NULL,
    `batch_price`     decimal(19, 2) DEFAULT NULL,
    `production_date` datetime(6)    DEFAULT NULL,
    `quantity`        int(11)    NOT NULL,
    `cake_id`         binary(16)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKfvfgxdy79qe5y1adq7nqu8us0` (`cake_id`),
    CONSTRAINT `FKfvfgxdy79qe5y1adq7nqu8us0` FOREIGN KEY (`cake_id`) REFERENCES `cake` (`id`)
);

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient`
(
    `id`   binary(16) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

--
-- Table structure for table `uom`
--

DROP TABLE IF EXISTS `uom`;
CREATE TABLE `uom`
(
    `id`    binary(16) NOT NULL,
    `name`  varchar(255) DEFAULT NULL,
    `label` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

--
-- Table structure for table `cake_ingredient`
--

DROP TABLE IF EXISTS `cake_ingredient`;
CREATE TABLE `cake_ingredient`
(
    `id`            binary(16) NOT NULL,
    `quantity`      decimal(19, 2) DEFAULT NULL,
    `cake_id`       binary(16)     DEFAULT NULL,
    `ingredient_id` binary(16)     DEFAULT NULL,
    `uom_id`        binary(16)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKpq3o0cgveshj7s5wlnl8ube48` (`cake_id`),
    KEY `FK1lvfxkwdgyudl85hem2g8rd56` (`ingredient_id`),
    KEY `FKe8h1d7l80cxlrbr6o5nhqytkn` (`uom_id`),
    CONSTRAINT `FK1lvfxkwdgyudl85hem2g8rd56` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`),
    CONSTRAINT `FKe8h1d7l80cxlrbr6o5nhqytkn` FOREIGN KEY (`uom_id`) REFERENCES `uom` (`id`),
    CONSTRAINT `FKpq3o0cgveshj7s5wlnl8ube48` FOREIGN KEY (`cake_id`) REFERENCES `cake` (`id`)
);

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                binary(16) NOT NULL,
    `email`             varchar(255) DEFAULT NULL,
    `first_name`        varchar(255) DEFAULT NULL,
    `last_name`         varchar(255) DEFAULT NULL,
    `password`          varchar(255) DEFAULT NULL,
    `registration_date` datetime(6)  DEFAULT NULL,
    PRIMARY KEY (`id`)
);