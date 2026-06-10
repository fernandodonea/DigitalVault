CREATE TABLE IF NOT EXISTS users (
                                     id          VARCHAR(36) PRIMARY KEY,
    username    VARCHAR(100) UNIQUE NOT NULL,
    email       VARCHAR(200) NOT NULL,
    password    VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS vault_items (
                                           id              VARCHAR(36) PRIMARY KEY,
    type            VARCHAR(20) NOT NULL,
    title           VARCHAR(200) NOT NULL,
    category        VARCHAR(50)  NOT NULL,
    username_owner  VARCHAR(100) NOT NULL REFERENCES users(username),
    content         TEXT,
    url             VARCHAR(500),
    cred_username   VARCHAR(200),
    password        VARCHAR(255),
    card_number     VARCHAR(50),
    card_holder     VARCHAR(200),
    cvv             INTEGER
    );

CREATE TABLE IF NOT EXISTS audit_log (
                                         id          SERIAL PRIMARY KEY,
                                         action_name VARCHAR(100) NOT NULL,
    timestamp   TIMESTAMP NOT NULL
    );