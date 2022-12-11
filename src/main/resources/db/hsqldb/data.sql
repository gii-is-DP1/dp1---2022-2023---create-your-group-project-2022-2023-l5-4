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



INSERT INTO gamemodes(id, name) VALUES (1, 'The Well');
INSERT INTO gamemodes(id, name) VALUES (2, 'The Tower');
INSERT INTO gamemodes(id, name) VALUES (3, 'The Poisoned Gift');


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


INSERT INTO tournamentModes(id, name) VALUES (1, 'The Well');
INSERT INTO tournamentModes(id, name) VALUES (2, 'The Tower');
INSERT INTO tournamentModes(id, name) VALUES (3, 'The Poisoned Gift');

INSERT INTO tournamentModes(id, name) VALUES (4, 'The Well');
INSERT INTO tournamentModes(id, name) VALUES (5, 'The Tower');
INSERT INTO tournamentModes(id, name) VALUES (6, 'The Poisoned Gift');

INSERT INTO tournamentModes(id, name) VALUES (7, 'The Well');
INSERT INTO tournamentModes(id, name) VALUES (8, 'The Tower');
INSERT INTO tournamentModes(id, name) VALUES (9, 'The Poisoned Gift');

INSERT INTO tournamentModes(id, name) VALUES (10, 'The Well');
INSERT INTO tournamentModes(id, name) VALUES (11, 'The Tower');
INSERT INTO tournamentModes(id, name) VALUES (12, 'The Poisoned Gift');


INSERT INTO tournaments(id, access_code, max_players, state,  owner_Id, winner_Id) VALUES (1, null, 4, 'LOBBY', 'User17', null);
INSERT INTO tournaments(id, access_code, max_players, state, owner_Id, winner_Id) VALUES (2, null, 6, 'LOBBY', 'User16', null);
INSERT INTO tournaments(id, access_code, max_players, state,  owner_Id, winner_Id) VALUES (3, 1234, 2, 'LOBBY',  'User15', null);
INSERT INTO tournaments(id, access_code, max_players, state,  owner_Id, winner_Id) VALUES (4, null, 6, 'LOBBY', 'User14', null);
INSERT INTO tournaments(id, access_code, max_players, state, owner_Id, winner_Id) VALUES (5, null, 6, 'LOBBY', 'User13', null);
INSERT INTO tournaments(id, access_code, max_players, state,  owner_Id, winner_Id) VALUES (6, null, 3, 'LOBBY', 'User12', null);
INSERT INTO tournaments(id, access_code, max_players, state, owner_Id, winner_Id) VALUES (7, 1234, 4, 'LOBBY', 'User9', null);
INSERT INTO tournaments(id, access_code, max_players, state, owner_Id, winner_Id) VALUES (8, null, 6, 'LOBBY', 'User11', null);
INSERT INTO tournaments(id, access_code, max_players, state,  owner_Id, winner_Id) VALUES (9, 1234, 6, 'LOBBY', 'User10', null);
INSERT INTO tournaments(id, access_code, max_players, state,  owner_Id, winner_Id) VALUES (10, 1234, 2, 'LOBBY', 'User8', null);

INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User17',1);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User16',2);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User15',3);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User14',4);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User13',5);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User12',6);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User11',7);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User10',8);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User9',9);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User8',10);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User7',1);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User6',1);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User5',1);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User4',2);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User3',2);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User2',8);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User1',9);
INSERT INTO usertournaments(user_id,tournament_id) VALUES ('User0',10);

INSERT INTO symbols(id, name) VALUES (1, 'ajedrez');
INSERT INTO symbols(id, name) VALUES (2, 'ancla');
INSERT INTO symbols(id, name) VALUES (3, 'arana');
INSERT INTO symbols(id, name) VALUES (4, 'arbol');
INSERT INTO symbols(id, name) VALUES (5, 'biberon');
INSERT INTO symbols(id, name) VALUES (6, 'bomba');
INSERT INTO symbols(id, name) VALUES (7, 'bombilla');
INSERT INTO symbols(id, name) VALUES (8, 'bullseye');
INSERT INTO symbols(id, name) VALUES (9, 'cactus');
INSERT INTO symbols(id, name) VALUES (10, 'calavera');
INSERT INTO symbols(id, name) VALUES (11, 'candado');
INSERT INTO symbols(id, name) VALUES (12, 'cebra');
INSERT INTO symbols(id, name) VALUES (13, 'clave');
INSERT INTO symbols(id, name) VALUES (14, 'copoNieve');
INSERT INTO symbols(id, name) VALUES (15, 'corazon');
INSERT INTO symbols(id, name) VALUES (16, 'delfin');
INSERT INTO symbols(id, name) VALUES (17, 'dragon');
INSERT INTO symbols(id, name) VALUES (18, 'exclamacion');
INSERT INTO symbols(id, name) VALUES (19, 'fantasma');
INSERT INTO symbols(id, name) VALUES (20, 'gafas');
INSERT INTO symbols(id, name) VALUES (21, 'gato');
INSERT INTO symbols(id, name) VALUES (22, 'gota');
INSERT INTO symbols(id, name) VALUES (23, 'hielo');
INSERT INTO symbols(id, name) VALUES (24, 'hoguera');
INSERT INTO symbols(id, name) VALUES (25, 'hoja');
INSERT INTO symbols(id, name) VALUES (26, 'iglu');
INSERT INTO symbols(id, name) VALUES (27, 'interrogacion');
INSERT INTO symbols(id, name) VALUES (28, 'labios');
INSERT INTO symbols(id, name) VALUES (29, 'lapiz');
INSERT INTO symbols(id, name) VALUES (30, 'llave');
INSERT INTO symbols(id, name) VALUES (31, 'luna');
INSERT INTO symbols(id, name) VALUES (32, 'manchas');
INSERT INTO symbols(id, name) VALUES (33, 'mano');
INSERT INTO symbols(id, name) VALUES (34, 'manzana');
INSERT INTO symbols(id, name) VALUES (35, 'margarita');
INSERT INTO symbols(id, name) VALUES (36, 'mariquita');
INSERT INTO symbols(id, name) VALUES (37, 'martillo');
INSERT INTO symbols(id, name) VALUES (38, 'muneco');
INSERT INTO symbols(id, name) VALUES (39, 'ojo');
INSERT INTO symbols(id, name) VALUES (40, 'pajaro');
INSERT INTO symbols(id, name) VALUES (41, 'payaso');
INSERT INTO symbols(id, name) VALUES (42, 'perro');
INSERT INTO symbols(id, name) VALUES (43, 'prohibido');
INSERT INTO symbols(id, name) VALUES (44, 'queso');
INSERT INTO symbols(id, name) VALUES (45, 'rayo');
INSERT INTO symbols(id, name) VALUES (46, 'relog');
INSERT INTO symbols(id, name) VALUES (47, 'sol');
INSERT INTO symbols(id, name) VALUES (48, 'taxi');
INSERT INTO symbols(id, name) VALUES (49, 'telarana');
INSERT INTO symbols(id, name) VALUES (50, 'tijeras');
INSERT INTO symbols(id, name) VALUES (51, 'tortuga');
INSERT INTO symbols(id, name) VALUES (52, 'trebol');
INSERT INTO symbols(id, name) VALUES (53, 'trex');
INSERT INTO symbols(id, name) VALUES (54, 'us');
INSERT INTO symbols(id, name) VALUES (55, 'vela');
INSERT INTO symbols(id, name) VALUES (56, 'yinyan');
INSERT INTO symbols(id, name) VALUES (57, 'zanahoria');

INSERT INTO cards(id, name) VALUES (1,'c01');
INSERT INTO cards(id, name) VALUES (10,'c10');
INSERT INTO cards(id, name) VALUES (11,'c11');
INSERT INTO cards(id, name) VALUES (12,'c12');
INSERT INTO cards(id, name) VALUES (13,'c13');
INSERT INTO cards(id, name) VALUES (14,'c14');
INSERT INTO cards(id, name) VALUES (15,'c15');
INSERT INTO cards(id, name) VALUES (16,'c16');
INSERT INTO cards(id, name) VALUES (17,'c17');
INSERT INTO cards(id, name) VALUES (18,'c18');
INSERT INTO cards(id, name) VALUES (19,'c19');
INSERT INTO cards(id, name) VALUES (2,'c02');
INSERT INTO cards(id, name) VALUES (20,'c20');
INSERT INTO cards(id, name) VALUES (21,'c21');
INSERT INTO cards(id, name) VALUES (22,'c22');
INSERT INTO cards(id, name) VALUES (23,'c23');
INSERT INTO cards(id, name) VALUES (24,'c24');
INSERT INTO cards(id, name) VALUES (25,'c25');
INSERT INTO cards(id, name) VALUES (26,'c26');
INSERT INTO cards(id, name) VALUES (27,'c27');
INSERT INTO cards(id, name) VALUES (28,'c28');
INSERT INTO cards(id, name) VALUES (29,'c29');
INSERT INTO cards(id, name) VALUES (3,'c03');
INSERT INTO cards(id, name) VALUES (30,'c30');
INSERT INTO cards(id, name) VALUES (31,'c31');
INSERT INTO cards(id, name) VALUES (32,'c32');
INSERT INTO cards(id, name) VALUES (33,'c33');
INSERT INTO cards(id, name) VALUES (34,'c34');
INSERT INTO cards(id, name) VALUES (35,'c35');
INSERT INTO cards(id, name) VALUES (36,'c36');
INSERT INTO cards(id, name) VALUES (37,'c37');
INSERT INTO cards(id, name) VALUES (38,'c38');
INSERT INTO cards(id, name) VALUES (39,'c39');
INSERT INTO cards(id, name) VALUES (4,'c04');
INSERT INTO cards(id, name) VALUES (40,'c40');
INSERT INTO cards(id, name) VALUES (41,'c41');
INSERT INTO cards(id, name) VALUES (42,'c42');
INSERT INTO cards(id, name) VALUES (43,'c43');
INSERT INTO cards(id, name) VALUES (44,'c44');
INSERT INTO cards(id, name) VALUES (45,'c45');
INSERT INTO cards(id, name) VALUES (46,'c46');
INSERT INTO cards(id, name) VALUES (47,'c47');
INSERT INTO cards(id, name) VALUES (48,'c48');
INSERT INTO cards(id, name) VALUES (49,'c49');
INSERT INTO cards(id, name) VALUES (5,'c05');
INSERT INTO cards(id, name) VALUES (50,'c50');
INSERT INTO cards(id, name) VALUES (51,'c51');
INSERT INTO cards(id, name) VALUES (52,'c52');
INSERT INTO cards(id, name) VALUES (53,'c53');
INSERT INTO cards(id, name) VALUES (54,'c54');
INSERT INTO cards(id, name) VALUES (55,'c55');
INSERT INTO cards(id, name) VALUES (6,'c06');
INSERT INTO cards(id, name) VALUES (7,'c07');
INSERT INTO cards(id, name) VALUES (8,'c08');
INSERT INTO cards(id, name) VALUES (9,'c09');


-- Carga de figuras de cartas

INSERT INTO symbolcard(symbol_id, card_id) VALUES (29,1);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,1);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,1);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,1);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (57,1);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (43,1);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,1);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,1);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,10);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (50,10);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,10);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,10);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (19,10);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,10);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,10);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,10);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,11);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,11);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,11);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,11);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (50,11);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,11);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,11);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,11);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,12);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (50,12);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,12);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,12);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,12);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,12);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (31,12);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (29,12);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,13);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,13);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,13);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (37,13);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (50,13);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,13);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,13);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,13);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,14);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (50,14);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,14);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,14);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,14);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,14);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,14);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (43,14);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,15);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,15);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,15);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,15);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (51,15);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,15);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,15);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,15);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,16);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (51,16);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (7,16);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,16);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,16);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,16);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,16);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,16);

INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,17);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,17);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (31,17);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,17);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (51,17);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,17);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,17);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,17);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,18);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (51,18);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,18);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (13,18);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,18);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,18);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,18);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,18);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,19);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,19);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,19);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (37,19);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (51,19);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,19);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,19);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (29,19);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,2);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (57,2);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,2);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (37,2);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,2);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,2);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,2);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (2,2);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,20);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (51,20);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,20);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,20);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (19,20);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,20);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,20);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,20);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,21);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,21);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,21);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (2,21);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (51,21);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,21);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,21);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (43,21);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,22);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (52,22);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,22);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,22);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,22);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,22);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (31,22);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,22);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (19,23);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,23);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,23);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,23);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (52,23);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,23);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,23);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,23);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,24);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (7,24);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,24);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,24);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (52,24);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,24);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,24);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,24);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,25);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,25);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,25);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (37,25);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (52,25);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,25);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,25);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,25);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,26);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (52,26);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,26);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,26);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,26);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,26);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,26);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (29,26);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,27);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,27);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,27);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (2,27);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (52,27);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,27);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (13,27);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,27);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,28);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (52,28);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,28);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,28);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,28);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,28);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,28);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (43,28);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,29);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,29);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,29);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,29);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (53,29);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,29);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,29);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (13,29);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (31,3);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,3);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,3);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,3);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (57,3);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,3);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,3);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,3);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,30);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (53,30);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,30);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,30);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,30);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,30);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,30);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,30);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,31);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,31);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,31);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (37,31);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (53,31);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,31);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,31);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,31);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,32);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (53,32);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (7,32);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,32);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,32);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,32);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,32);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,32);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,33);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (19,33);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,33);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,33);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (2,33);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (53,33);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,33);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,33);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,34);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (53,34);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,34);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,34);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,34);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,34);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (31,34);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,34);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,35);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,35);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,35);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,35);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (53,35);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,35);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,35);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (43,35);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,36);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (54,36);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,36);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,36);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (19,36);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,36);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,36);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,36);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,37);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,37);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,37);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (37,37);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (54,37);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,37);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,37);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,37);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,38);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (54,38);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,38);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (13,38);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,38);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,38);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,38);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,38);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,39);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,39);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (31,39);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (2,39);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (54,39);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,39);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,39);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,39);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,4);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (57,4);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,4);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,4);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,4);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,4);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,4);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,4);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,40);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (54,40);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (7,40);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,40);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,40);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,40);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,40);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (29,40);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,41);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,41);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,41);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,41);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (54,41);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,41);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,41);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,41);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,42);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (54,42);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,42);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,42);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,42);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,42);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,42);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (43,42);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,43);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,43);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,43);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (37,43);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (55,43);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,43);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,43);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,43);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,44);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (55,44);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,44);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,44);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,44);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,44);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (31,44);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,44);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,45);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (25,45);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,45);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (2,45);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (55,45);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,45);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,45);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,45);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,46);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (55,46);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,46);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,46);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (19,46);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,46);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,46);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,46);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,47);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,47);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,47);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,47);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (55,47);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,47);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (13,47);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (29,47);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,48);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (55,48);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (7,48);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,48);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,48);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,48);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,48);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,48);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,49);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,49);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,49);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,49);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (55,49);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,49);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,49);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (43,49);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,5);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,5);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,5);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (19,5);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,5);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (57,5);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,5);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,5);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (44,50);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (56,50);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,50);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,50);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (26,50);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (32,50);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (38,50);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,50);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (33,51);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (39,51);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (45,51);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,51);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (56,51);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,51);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,51);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,51);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,52);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (56,52);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,52);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,52);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (40,52);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (46,52);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,52);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (15,52);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,53);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (47,53);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,53);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,53);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (16,53);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (56,53);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,53);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (22,53);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (23,54);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (56,54);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,54);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,54);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,54);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,54);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (17,54);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (29,54);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,55);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,55);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (18,55);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (24,55);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (30,55);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (56,55);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,55);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (36,55);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (13,6);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (20,6);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (57,6);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (48,6);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,6);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (41,6);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (34,6);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (27,6);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,7);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (57,7);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (21,7);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (7,7);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (49,7);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (42,7);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (28,7);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (35,7);


INSERT INTO symbolcard(symbol_id, card_id) VALUES (6,8);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (5,8);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (7,8);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (4,8);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (1,8);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (50,8);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (3,8);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (2,8);

INSERT INTO symbolcard(symbol_id, card_id) VALUES (50,9);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (9,9);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (10,9);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (14,9);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (13,9);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (8,9);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (11,9);
INSERT INTO symbolcard(symbol_id, card_id) VALUES (12,9);