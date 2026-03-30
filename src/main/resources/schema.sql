CREATE TABLE IF NOT EXISTS venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(100) NOT NULL,
    location   VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS attendees
(
    attendee_id   SERIAL PRIMARY KEY,
    attendee_name VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS events
(
    event_id   SERIAL PRIMARY KEY,
    event_name VARCHAR(100)  NOT NULL,
    event_date VARCHAR(100) NOT NULL,
    venue_id   INT          NOT NULL,
    CONSTRAINT fk_venue FOREIGN KEY (venue_id) REFERENCES venues (venue_id) ON DELETE CASCADE ON UPDATE CASCADE
);