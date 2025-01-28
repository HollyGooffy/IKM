-- Создание таблицы Prison
CREATE TABLE Prison (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50) UNIQUE NOT NULL,
                        city VARCHAR(50) UNIQUE NOT NULL
);

-- Создание таблицы Gang
CREATE TABLE Gang (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(50) UNIQUE NOT NULL,
                      leader VARCHAR(50) UNIQUE NOT NULL,
                      prison_id INTEGER UNIQUE,
                      active BOOLEAN,
                      FOREIGN KEY (prison_id) REFERENCES Prison(id)
);

-- Создание таблицы Caste
CREATE TABLE Caste (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL,
                       description TEXT,
                       gang_id INTEGER,
                       FOREIGN KEY (gang_id) REFERENCES Gang(id)
);

-- Создание таблицы Article
CREATE TABLE Article (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(50) UNIQUE NOT NULL,
                         description TEXT,
                         caste_id INTEGER,
                         FOREIGN KEY (caste_id) REFERENCES Caste(id)
);

-- Создание таблицы Member
CREATE TABLE Member (
                        id SERIAL PRIMARY KEY,
                        gang_id INTEGER,
                        caste_id INTEGER,
                        member_name varchar(50) not null,
                        member_second_name varchar(50) not null,
                        member_last_name varchar(50) not null,
                        joined_date DATE,
                        active BOOLEAN,
                        article_number varchar(50),
                        article_description varchar,
                        FOREIGN KEY (gang_id) REFERENCES Gang(id),
                        FOREIGN KEY (caste_id) REFERENCES Caste(id)
);
