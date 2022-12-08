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

-- All 55 cards --
INSERT INTO cards(id) VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),
(33),(34),(35),(36),(37),(38),(39),(40),(41),(42),(43),(44),(45),(46),(47),(48),(49),(50),(51),(52),(53),(54),(55);

-- All 57 symbols --
INSERT INTO symbols(id) VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),
(33),(34),(35),(36),(37),(38),(39),(40),(41),(42),(43),(44),(45),(46),(47),(48),(49),(50),(51),(52),(53),(54),(55),(56),(57);

-- symbolsets, at least 'original' must be set --
INSERT INTO symbolsets(id,name,path) VALUES (1,'original', 'resources/images/symbols/original');

-- symbolvariants for 'original' set --
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (1, 'Anchor','anchor.png',1,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (2, 'Apple','apple.png',2,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (3, 'Baby Bottle','baby-bottle.png',3,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (4, 'Bomb','bomb.png',4,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (5, 'cactus','cactus.png',5,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (6, 'Candle','candle.png',6,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (7, 'Taxi','taxi.png',7,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (8, 'Carrot','carrot.png',8,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (9, 'Chess Knight','chess-knight',9,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (10, 'Clock','clock.png',10,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (11, 'Clown','clown.png',11,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (12, 'Daisy flower','daisy-flower.png',12,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (13, 'Dinosaur','dinosaur.png',13,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (14, 'Dolphin','dolphin.png',14,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (15, 'Dragon','dragon.png',15,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (16, 'Exclamation Point','exclamation-point.png',16,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (17,'Eye','eye.png',17,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (18, 'Fire','fire.png',18,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (19, 'Four Leaf Clover','four-leaf-clover.png',19,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (20, 'Fantasy','fantasy.png',20,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (21, 'Green Splats','green-splats.png',21,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (22, 'Hammer','hammer.png',22,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (23, 'Heart','heart.png',23,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (24, 'Ice Cube','ice-cube.png',24,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (25, 'Igloo','igloo.png',25,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (26, 'Key','key.png',26,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (27, 'Ladybug','ladybug.png',27,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (28, 'Light Bulb','light-bulb.png',28,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (29, 'Lightning Bolt','lightning-bolt.png',29,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (30, 'Lock','lock.png',30,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (31, 'Mapple Leaf','mapple-leaf.png',31,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (32, 'Moon','moon.png',32,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (33, 'No Entry Sign','no-entry-sign.png',33,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (34, 'Orange Scarecrow Man','orange-scarecrow-man.png',34,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (35, 'Pencil','pencil.png',35,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (36, 'Purple Bird','bird.png',36,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (37, 'Purple Cat','cat.png',37,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (38, 'Purple Dobble Hand','dobble-hand.png',38,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (39, 'Red Lips','red-rips.png',39,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (40, 'Scissors','scissors.png',40,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (41, 'Skull and Crossbones','skull-and-crossbones.png',41,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (42, 'Snowflake','snowflake.png',42,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (43, 'Snowman','snowman.png',43,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (44, 'Spider','spider.png',44,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (45, 'Spider Web','spider-web.png',45,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (46, '​​Sun','sun.png',46,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (47, 'Sunglasses','sunglasses.png',47,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (48, 'Target','target.png',48,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (49, 'Tortoise','tortoise.png',49,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (50, 'Treble Clef','treble-clef.png',50,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (51, 'Tree','tree.png',51,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (52, 'Water Drip','water-drip.png',52,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (53, 'Dog','dog.png',53,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (54, 'Yin and Yang','yin-and-yang.png',54,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (55, 'Zebra','zebra.png',55,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (56, 'Question Mark','question-mark.png',56,1);
INSERT INTO symbolvariants(id, name, file_name, symbol_id,symbol_set_id) VALUES (57, 'cheese','cheese.png',57,1);

-- Gamemodes --

INSERT INTO gamemodes(id, name) VALUES (1, 'The Well');
INSERT INTO gamemodes(id, name) VALUES (2, 'The Tower');
INSERT INTO gamemodes(id, name) VALUES (3, 'The Poisoned Gift');

-- Initial players, not needed but useful for testing --

INSERT INTO players(id, name) VALUES (1, 'edurobrus');
INSERT INTO players(id, name) VALUES (2, 'miguel');
INSERT INTO players(id, name) VALUES (3, 'florencio');
INSERT INTO players(id, name) VALUES (4, 'esurobrus');
INSERT INTO players(id, name) VALUES (5, 'atci');
INSERT INTO players(id, name) VALUES (6, 'edus');


-- Initial users, not needed but useful for testing --

INSERT INTO USERS(username, password, enabled) VALUES ('User0','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User1','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User2','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User3','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User4','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User5','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User6','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User7','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User8','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User9','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User10','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User11','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User12','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User13','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User14','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User15','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User16','p4ssw0rd',TRUE);
INSERT INTO USERS(username, password, enabled) VALUES ('User17','p4ssw0rd',TRUE);

-- Initial games, not needed but useful for testing --

INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (1, null, 4, 'LOBBY', 1, 'User17', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (2, null, 6, 'LOBBY', 1, 'User16', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (3, 1234, 2, 'LOBBY', 2, 'User15', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (4, null, 6, 'LOBBY', 3, 'User14', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (5, null, 6, 'LOBBY', 3, 'User13', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (6, null, 3, 'LOBBY', 1, 'User12', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (7, 1234, 4, 'LOBBY', 1, 'User9', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (8, null, 6, 'LOBBY', 2, 'User11', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (9, 1234, 6, 'LOBBY', 2, 'User10', null);
INSERT INTO games(id, access_code, max_players, state, gamemode_Id, owner_Id, winner_Id) VALUES (10, 1234, 2, 'LOBBY', 1, 'User8', null);


-- Initial users into games --
INSERT INTO usergames(user_id,game_id) VALUES ('User17',1);
INSERT INTO usergames(user_id,game_id) VALUES ('User16',2);
INSERT INTO usergames(user_id,game_id) VALUES ('User15',3);
INSERT INTO usergames(user_id,game_id) VALUES ('User14',4);
INSERT INTO usergames(user_id,game_id) VALUES ('User13',5);
INSERT INTO usergames(user_id,game_id) VALUES ('User12',6);
INSERT INTO usergames(user_id,game_id) VALUES ('User11',7);
INSERT INTO usergames(user_id,game_id) VALUES ('User10',8);
INSERT INTO usergames(user_id,game_id) VALUES ('User9',9);
INSERT INTO usergames(user_id,game_id) VALUES ('User8',10);
INSERT INTO usergames(user_id,game_id) VALUES ('User7',1);
INSERT INTO usergames(user_id,game_id) VALUES ('User6',1);
INSERT INTO usergames(user_id,game_id) VALUES ('User5',1);
INSERT INTO usergames(user_id,game_id) VALUES ('User4',2);
INSERT INTO usergames(user_id,game_id) VALUES ('User3',2);
INSERT INTO usergames(user_id,game_id) VALUES ('User2',8);
INSERT INTO usergames(user_id,game_id) VALUES ('User1',9);
INSERT INTO usergames(user_id,game_id) VALUES ('User0',10);