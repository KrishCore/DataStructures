-- Pruges all tables
DELETE FROM game;
DELETE FROM player;
DELETE FROM team;

-- Reset auto-increment values.
ALTER TABLE team AUTO_INCREMENT = 1;
ALTER TABLE player AUTO_INCREMENT = 1;
ALTER TABLE game AUTO_INCREMENT = 1;

-- Teams
INSERT INTO team (team_name, coach_name) VALUES
('Tigers', 'Ms. Lee'),
('Eagles', 'Mr. Green'),
('Bears', 'Coach Carter'),
('Sharks', 'Ms. Rivera');

-- Players: 4 players per team
INSERT INTO player (team_id, first_name, last_name, jersey_number) VALUES
(1, 'Ava', 'Smith', 12),
(1, 'Noah', 'Brown', 18),
(1, 'Mia', 'Davis', 23),
(1, 'Liam', 'Wilson', 31),

(2, 'Mark', 'Jones', 7),
(2, 'Emma', 'Miller', 14),
(2, 'Ethan', 'Taylor', 21),
(2, 'Sofia', 'Moore', 33),

(3, 'Lucas', 'Anderson', 5),
(3, 'Olivia', 'Thomas', 11),
(3, 'Mason', 'Jackson', 24),
(3, 'Isabella', 'White', 30),

(4, 'James', 'Harris', 9),
(4, 'Amelia', 'Martin', 16),
(4, 'Benjamin', 'Thompson', 25),
(4, 'Charlotte', 'Garcia', 34);

-- Games
INSERT INTO game (team1_id, team2_id, team1_score, team2_score) VALUES
(1, 2, 35, 28),
(3, 1, 22, 30),
(4, 2, 18, 24),
(1, 4, 27, 21),
(2, 3, 31, 34),
(4, 1, 26, 20),
(3, 4, 29, 29),
(2, 1, 17, 25),
(4, 3, 33, 28),
(2, 4, 40, 37);
