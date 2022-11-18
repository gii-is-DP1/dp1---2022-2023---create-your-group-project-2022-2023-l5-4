-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
INSERT INTO users(username,password,enabled) VALUES ('antbarjim1','123abc',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'antbarjim1','owner');
INSERT INTO users(username,password,enabled) VALUES ('adrgarpor','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'adrgarpor','admin');
INSERT INTO users(username,password,enabled) VALUES ('edurobrus','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'edurobrus','owner');

-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Antonio', 'Barea', 'Tarfia St.', 'Sevilla', '662934416', 'antbarjim1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Firulais', '2011-07-10', 2, 11);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO symbols(id, name) VALUES (1, 'Anchor');
INSERT INTO symbols(id, name) VALUES (2, 'Apple');
INSERT INTO symbols(id, name) VALUES (3, 'Bottle');
INSERT INTO symbols(id, name) VALUES (4, 'pump');
INSERT INTO symbols(id, name) VALUES (5, 'cacti');
INSERT INTO symbols(id, name) VALUES (6, 'Candle');
INSERT INTO symbols(id, name) VALUES (7, 'Taxis');
INSERT INTO symbols(id, name) VALUES (8, 'Carrot');
INSERT INTO symbols(id, name) VALUES (9, 'Chess Knight');
INSERT INTO symbols(id, name) VALUES (10, 'Clock');
INSERT INTO symbols(id, name) VALUES (11, 'Clown');
INSERT INTO symbols(id, name) VALUES (12, 'daisy flower');
INSERT INTO symbols(id, name) VALUES (13, 'Dinosaurs');
INSERT INTO symbols(id, name) VALUES (14, 'Dolphin');
INSERT INTO symbols(id, name) VALUES (15, 'Dragon');
INSERT INTO symbols(id, name) VALUES (16, 'exclamation point');
INSERT INTO symbols(id, name) VALUES (17,'Eye');
INSERT INTO symbols(id, name) VALUES (18, 'Fire');
INSERT INTO symbols(id, name) VALUES (19, 'Four Leaf Clover');
INSERT INTO symbols(id, name) VALUES (20, 'Fantasy');
INSERT INTO symbols(id, name) VALUES (21, 'Green symbolss');
INSERT INTO symbols(id, name) VALUES (22, 'Hammer');
INSERT INTO symbols(id, name) VALUES (23, 'Heart');
INSERT INTO symbols(id, name) VALUES (24, 'ice cube');
INSERT INTO symbols(id, name) VALUES (25, 'Igloo');
INSERT INTO symbols(id, name) VALUES (26, 'Key');
INSERT INTO symbols(id, name) VALUES (27, 'Ladybug');
INSERT INTO symbols(id, name) VALUES (28, 'Lightbulb');
INSERT INTO symbols(id, name) VALUES (29, 'Bolt Lightning');
INSERT INTO symbols(id, name) VALUES (30, 'lock');
INSERT INTO symbols(id, name) VALUES (31, 'Arch Blade');
INSERT INTO symbols(id, name) VALUES (32, 'Moon');
INSERT INTO symbols(id, name) VALUES (33, 'Entry prohibited sign');
INSERT INTO symbols(id, name) VALUES (34, 'Orange Scarecrow Man');
INSERT INTO symbols(id, name) VALUES (35, 'Pencil');
INSERT INTO symbols(id, name) VALUES (36, 'purple bird');
INSERT INTO symbols(id, name) VALUES (37, 'cat purple ');
INSERT INTO symbols(id, name) VALUES (38, 'Purple double hand');
INSERT INTO symbols(id, name) VALUES (39, 'Red Lips');
INSERT INTO symbols(id, name) VALUES (40, 'Scissors');
INSERT INTO symbols(id, name) VALUES (41, 'Skull and Crossbones');
INSERT INTO symbols(id, name) VALUES (42, 'Snowflake');
INSERT INTO symbols(id, name) VALUES (43, 'Snowman');
INSERT INTO symbols(id, name) VALUES (44, 'Spider');
INSERT INTO symbols(id, name) VALUES (45, 'Cobweb');
INSERT INTO symbols(id, name) VALUES (46, '​​Sun');
INSERT INTO symbols(id, name) VALUES (47, 'Sunglasses');
INSERT INTO symbols(id, name) VALUES (48, 'Target/crosshair');
INSERT INTO symbols(id, name) VALUES (49, 'Turtle');
INSERT INTO symbols(id, name) VALUES (50, 'Treble Clef');
INSERT INTO symbols(id, name) VALUES (51, 'Tree');
INSERT INTO symbols(id, name) VALUES (52, 'Dripping water');
INSERT INTO symbols(id, name) VALUES (53, 'Dog');
INSERT INTO symbols(id, name) VALUES (54, 'Yin and Yang');
INSERT INTO symbols(id, name) VALUES (55, 'Zebras');
INSERT INTO symbols(id, name) VALUES (56, 'Question mark');
INSERT INTO symbols(id, name) VALUES (57, 'cheese');

INSERT INTO gamemodes(id, name) VALUES (1, 'The Well');
INSERT INTO gamemodes(id, name) VALUES (2, 'The Tower');
INSERT INTO gamemodes(id, name) VALUES (3, 'The Poisoned Gift');

INSERT INTO games(id, gamemode_Id, owner_Id, winner_Id) VALUES (1, 1, 'adrgarpor', null);
INSERT INTO games(id, gamemode_Id, owner_Id, winner_Id) VALUES (2, 2, 'adrgarpor', null);
INSERT INTO games(id, gamemode_Id, owner_Id, winner_Id) VALUES (3, 3, 'adrgarpor', null);


INSERT INTO players(id, name) VALUES (1, 'edurobrus');
INSERT INTO players(id, name) VALUES (2, 'miguel');
INSERT INTO players(id, name) VALUES (3, 'florencio');
INSERT INTO players(id, name) VALUES (4, 'esurobrus');
INSERT INTO players(id, name) VALUES (5, 'atci');
INSERT INTO players(id, name) VALUES (6, 'edus');
