CREATE TABLE IF NOT EXISTS venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(40) NOT NULL,
    location   VARCHAR(60) NOT NULL
);