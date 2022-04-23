DROP TABLE resume_user_profile IF EXISTS;
DROP TABLE security_user IF EXISTS;


CREATE TABLE security_user
(
    id        INTEGER IDENTITY PRIMARY KEY,
    user_name VARCHAR(30),
    password  VARCHAR(30),
    active    BOOLEAN,
    roles     VARCHAR(20)
);
CREATE INDEX security_user_user_name ON security_user (user_name);
