INSERT INTO biblebot.plan (id, bible_key, name) VALUES (1, 'elb', 'Bibelleseplan');
INSERT INTO biblebot.plan_instance (id, channel, start_date, plan_id) VALUES (1, '#test-bibelleseplan', '2017-12-30 11:00:00', 1);
INSERT INTO biblebot.plan_instance (id, channel, start_date, plan_id) VALUES (2, '#test-bibelleseplan2', '2018-01-01 03:00:00', 1);

INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (1, true, 1, 0);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (2, true, 1, 1);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (3, true, 1, 2);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (4, true, 1, 3);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (5, true, 1, 4);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (6, true, 1, 5);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (7, false, 'Hallo Bibelleser, heute lesen wir die ersten Bibelstellen. Diese findet man immer direkt unter der Nachricht, mit einem Link auf bibelserver.com für andere Übersetzungen. Für diese Push-Nachrichten wurde Elberfelder ausgewählt. Wir wünschen eine gesegnete Zeit für das Lesen!', 1, 6);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 1',  7, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 2',  7, 1);

# KW2

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (8, false, null, 1, 7);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 1',  8, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 2',  8, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 1',  8, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (9, false, null, 1, 8);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 3',  9, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 4',  9, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 2',  9, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (10, false, null, 1, 9);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 5',  10, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 6',  10, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 3',  10, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (11, false, null, 1, 10);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 7',  11, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 8',  11, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 9',  11, 2);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 4',  11, 3);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (12, false, null, 1, 11);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 10',  12, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 5',  12, 1);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (13, false, null, 1, 12);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 11',  13, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 6',  13, 1);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (14, false, null, 1, 13);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 3',  14, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 4',  14, 1);

# KW 3

INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order)        VALUES (15, true, 1, 14);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order)        VALUES (16, true, 1, 15);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order)        VALUES (17, true, 1, 16);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order)        VALUES (18, true, 1, 17);
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order, text)  VALUES (19, true, 1, 18, 'Heute findet die Veranstaltung "Einleitungsfragen zu
Johannes" in der Gemeinde um 18:00 Uhr statt.');
INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order)        VALUES (20, true, 1, 19);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (21, false, null, 1, 20);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 5',  21, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 6',  21, 1);

# KW 4

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (22, false, null, 1, 21);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 12',  22, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 13',  22, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 7',  22, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (23, false, null, 1, 22);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 14',  23, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 15',  23, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 8',  23, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (24, false, null, 1, 23);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 16',  24, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 17',  24, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 9',  24, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (25, false, null, 1, 24);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 18',  25, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 19',  25, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 10',  25, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (26, false, null, 1, 25);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 20',  26, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 11',  26, 1);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (27, false, null, 1, 26);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 21',  27, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 22',  27, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 12',  27, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (28, false, null, 1, 27);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 7',  28, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 8',  28, 1);

# KW 5

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (29, false, null, 1, 28);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 23',  29, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 24',  29, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 13',  29, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (30, false, null, 1, 29);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 25',  30, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 14',  30, 1);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (31, false, null, 1, 30);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 26',  31, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 27',  31, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 28',  31, 2);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 15',  31, 3);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (32, false, null, 1, 31);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 29',  32, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 30',  32, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 16',  32, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (33, false, null, 1, 32);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 31',  33, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 32',  33, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 33',  33, 2);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 17',  33, 3);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (34, false, null, 1, 33);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 34',  34, 0);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 35',  34, 1);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 18',  34, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (35, false, null, 1, 34);
INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 9',  35, 0);

# KW 6

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (36, false, null, 1, 35);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 36',  36, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 19',  36, 1);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (37, false, null, 1, 36);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 37',  37, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 20',  37, 1);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (38, false, null, 1, 37);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 38',  38, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Johannes 21',  38, 1);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (39, false, null, 1, 38);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 39',  39, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 40',  39, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 41',  39, 2);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 1:1-17',  39, 3);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (40, false, null, 1, 39);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 42',  40, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 43',  40, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 1:18-999',  40, 2);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 2',  40, 3);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (41, false, null, 1, 40);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 44',  41, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 45',  41, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 46',  41, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (42, false, null, 1, 41);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 10',  42, 0);

# KW 7

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (43, false, null, 1, 42);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 47',  43, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 48',  43, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 3',  43, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (44, false, null, 1, 43);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 49',  44, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Mose 50',  44, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 4',  44, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (45, false, null, 1, 44);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 1',  45, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 2',  45, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 5',  45, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (46, false, null, 1, 45);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 3',  46, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 4',  46, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 6',  46, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (47, false, null, 1, 46);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 5',  47, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 6',  47, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 7',  47, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (48, false, null, 1, 47);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 7',  48, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 8',  48, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 9',  48, 2);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 10',  48, 3);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (49, false, null, 1, 48);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 11',  49, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 12',  49, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 13',  49, 2);

# KW 8

INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (50, true, 1, 49);

INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (51, true, 1, 50);

INSERT INTO biblebot.plan_day (id, is_free, plan_id, days_order) VALUES (52, true, 1, 51);


INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (53, false, null, 1, 52);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 11',  53, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 12',  53, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 8',  53, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (54, false, null, 1, 53);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 13',  54, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 14',  54, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 9',  54, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (55, false, null, 1, 54);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('2. Mose 15',  55, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 10',  55, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('1. Korinther 11',  55, 2);

INSERT INTO biblebot.plan_day (id, is_free, text, plan_id, days_order) VALUES (56, false, null, 1, 55);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 14',  56, 0);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 15',  56, 1);
    INSERT INTO biblebot.passage (title, day_id, passages_order) VALUES ('Psalm 16',  56, 2);

# KW 9
