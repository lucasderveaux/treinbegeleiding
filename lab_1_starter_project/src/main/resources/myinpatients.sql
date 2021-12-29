INSERT INTO TRAIN(train_id, status) VALUES
('1','ON_TIME');
INSERT INTO TRAIN_STOP(train_stop_id, train_id, station, track, arrival_time, departure_time) values
('0', '1', 'Gent', '5', LOCALTIME(), LOCALTIME()),
('1', '1', 'Antwerpen', '12', LOCALTIME(), LOCALTIME()),
('2', '1', 'Schilde', '1', LOCALTIME(), LOCALTIME())