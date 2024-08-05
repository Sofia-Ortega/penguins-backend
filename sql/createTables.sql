CREATE TABLE Penguins (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    love INTEGER,
    hunger INTEGER
);

CREATE TYPE Relationship_Level AS ENUM ('Mortal Enemies', 'Enemies', 'Strangers', 'Acquaintances', 'Friends', 'BFF');


CREATE TABLE Relationships(
    penguin_id_1 INTEGER REFERENCES Penguins,
    penguin_id_2 INTEGER REFERENCES  Penguins,
    relationship Relationship_Level
);