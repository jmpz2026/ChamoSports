CREATE TABLE IF NOT EXISTS team (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS app_user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rolId INT NOT NULL,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(256) NOT NULL,
    teamId INT NOT NULL,
    FOREIGN KEY (teamId) REFERENCES team(id)
);

CREATE TABLE IF NOT EXISTS training (
    id INT PRIMARY KEY AUTO_INCREMENT,
    teamId INT NOT NULL,
    date TIMESTAMP NOT NULL,
    FOREIGN KEY (teamId) REFERENCES team(id)
);

CREATE TABLE IF NOT EXISTS result (
    id INT PRIMARY KEY AUTO_INCREMENT,
    trainingId INT NOT NULL,
    userId INT NOT NULL,
    powerShoot INT NOT NULL,
    speedShoot INT NOT NULL,
    effectiveShoot INT NOT NULL,
    totalShoot REAL NOT NULL,
    FOREIGN KEY (trainingId) REFERENCES training(id),
    FOREIGN KEY (userId) REFERENCES app_user(id),
    UNIQUE(trainingId, userId)
);

MERGE INTO team (id,name) VALUES (1,'admin');
MERGE INTO app_user (id,rolId,name,password,teamId) VALUES (1,2,'admin','admin',1);

ALTER TABLE team ALTER COLUMN id RESTART WITH 2;
ALTER TABLE app_user ALTER COLUMN id RESTART WITH 2;