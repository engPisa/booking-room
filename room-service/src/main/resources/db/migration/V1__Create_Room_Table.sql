CREATE TABLE room (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,

    CONSTRAINT capacity_must_be_positive CHECK (capacity > 0)
);