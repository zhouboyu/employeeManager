create table Company(
        `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
        `name` varchar(64) NOT NULL DEFAULT '',
        `address` varchar(256) NOT NULL DEFAULT '',
        `phone` varchar(32) DEFAULT '',
        `detail` varchar(128) DEFAULT '',
        `isDeleted` TINYINT(4) NOT NULL DEFAULT 0,
        PRIMARY KEY (`id`)
)ENGINE=InnoDB CHARSET=utf8;


create table Employee(
        `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
        `name` varchar(64) NOT NULL DEFAULT '',
        `idCard` varchar(32) NOT NULL,
        `sex` TINYINT(1) NOT NULL,
        `isDeleted` TINYINT(4) NOT NULL DEFAULT 0,
        `companyId`bigint(20) NOT NULL DEFAULT 0,
        `phone` varchar(32) DEFAULT '',
        PRIMARY KEY (`id`)
)ENGINE=InnoDB CHARSET=utf8;


create table EmployeeLog(
        `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
        `employeeId` bigint(20) NOT NULL,
        `companyId` bigint(20) NOT NULL,
        `employeeName` varchar(64) NOT NULL,
        `companyName` varchar(64) NOT NULL,
        `entryTime` INT(11) NOT NULL DEFAULT 0,
        `leaveTime` INT(11) NOT NULL DEFAULT 0,
        PRIMARY KEY (`id`)
)ENGINE=InnoDB CHARSET=utf8;


create table Admin(
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB CHARSET=utf8;



insert into Admin ('account','password')  values('admin','admin');