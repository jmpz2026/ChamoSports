CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rolId INT NOT NULL,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(256) NOT NULL,
    teamId INT NOT NULL,
    FOREIGN KEY (teamId) REFERENCES team(id)
);

CREATE TABLE team (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE training (
    id INT PRIMARY KEY,
    teamId INT NOT NULL,
    date DATETIME NOT NULL,
    FOREIGN KEY (teamId) REFERENCES team(id)
);

CREATE TABLE result (
    id INT PRIMARY KEY AUTO_INCREMENT,
    trainingId INT NOT NULL,
    userId INT NOT NULL,
    powerShoot INT NOT NULL,
    speedShoot INT NOT NULL,
    effectiveShoot INT NOT NULL,
    totalShoot INT NOT NULL,
    FOREIGN KEY (trainingId) REFERENCES training(id),
    FOREIGN KEY (userId) REFERENCES user(id),
    UNIQUE(trainingId, userId)
);