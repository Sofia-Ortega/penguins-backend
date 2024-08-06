DROP TABLE IF EXISTS Relationships;
DROP TYPE IF EXISTS Relationship_Level;
DROP TABLE IF EXISTS Penguins;
DROP TABLE IF EXISTS Species;

CREATE TABLE Species (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE Penguins (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    love INTEGER,
    hunger INTEGER,
    species INTEGER REFERENCES Species
);

CREATE TYPE Relationship_Level AS ENUM ('Mortal Enemies', 'Enemies', 'Strangers', 'Acquaintances', 'Friends', 'BFF');


CREATE TABLE Relationships(
    penguin_id_1 INTEGER REFERENCES Penguins,
    penguin_id_2 INTEGER REFERENCES  Penguins,
    relationship Relationship_Level
);
