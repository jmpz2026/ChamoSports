CREATE TABLE user (
    id LONG PRIMARY KEY AUTO_INCREMENT,
    rolId LONG,
    name VARCHAR(20),
    password VARCHAR(256),
    teamId LONG,
    FOREIGN KEY (teamId) REFERENCES team(id)
);

CREATE TABLE team (
    id LONG PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE
);

CREATE TABLE result (
    id LONG PRIMARY KEY,
    userId LONG,
    teamId LONG,
    powerShoot LONG,
    speedShoot LONG,
    effectiveShoot LONG,
    totalShoot LONG,
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (teamId) REFERENCES team(id)
)