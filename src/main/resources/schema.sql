CREATE TABLE answersSlow (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    question    VARCHAR(64) NOT NULL,
    answer      VARCHAR(64) NOT NULL);

CREATE TABLE answers (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    question    VARCHAR(64) NOT NULL,
    answer      VARCHAR(64) NOT NULL);

CREATE UNIQUE INDEX idx_question ON answers(question);
