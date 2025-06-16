CREATE TABLE reservation (
     id UUID PRIMARY KEY,
     user_id UUID NOT NULL,
     room_id UUID NOT NULL,
     start_time TIMESTAMP NOT NULL,
     end_time TIMESTAMP NOT NULL,
     status VARCHAR(255) NOT NULL
);