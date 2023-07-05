-- ----------------------------
-- Table structure for items
-- ----------------------------
USE jpwm_db;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `userId` int(10) NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` varchar(50) NOT NULL,
    `email` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of items
-- ----------------------------
BEGIN;
INSERT INTO `users` (`userId`, `username`, `password`, `email`) VALUES (1, 'manololillo', 'mypass1234', 'manolo_manolillo@gmail.com');
INSERT INTO `users` (`userId`, `username`, `password`, `email`) VALUES (2, 'ursulaCliente', 'hardpassword889', 'ursulente96@gmail.com');
INSERT INTO `users` (`userId`, `username`, `password`, `email`) VALUES (3, 'ikerdeath_stared', '1625trEKS8', 'ikerde4th_2004@gmail.com');
COMMIT;