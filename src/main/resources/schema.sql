CREATE TABLE IF NOT EXISTS venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(100) NOT NULL,
    location   VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS attendees (
                                         attendee_id SERIAL PRIMARY KEY,
                                         attendee_name VARCHAR(100) NOT NULL,
                                         email VARCHAR(100) NOT NULL
);