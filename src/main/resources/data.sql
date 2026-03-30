INSERT INTO venues (venue_name, location)
VALUES ('Grand Hall', 'New York'),
       ('Sunset Arena', 'Los Angeles'),
       ('Riverfront Center', 'Chicago'),
       ('Mountain View Stage', 'Denver'),
       ('Ocean Breeze Pavilion', 'Miami');

INSERT INTO attendees (attendee_name, email)
VALUES ('Alice Johnson', 'alice.johnson@example.com'),
       ('Bob Smith', 'bob.smith@example.com'),
       ('Charlie Brown', 'charlie.brown@example.com'),
       ('Diana Prince', 'diana.prince@example.com'),
       ('Ethan Hunt', 'ethan.hunt@example.com');

INSERT INTO events (event_name, event_date, venue_id)
VALUES ('Tech Conference 2026', '2026-05-15', 1),
       ('Music Festival', '2026-06-20', 2),
       ('Startup Pitch Day', '2026-04-10', 3),
       ('Art Expo', '2026-07-05', 4),
       ('Health & Wellness Summit', '2026-08-12', 5);