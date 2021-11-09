-- NEED TO CREATE PROJECT ---------------

INSERT INTO roles (id, name) VALUES
(1, 'ROLE_USER'),
(6, 'ROLE_ADMIN');

-----------------------------------------------

INSERT INTO users (id, first_name, second_name, last_name, username, email, password, phone_number, role_id, user_profile_image_url) VALUES
(1, 'John', 'Frederick', 'Smith', 'john_the_admin', '1664286@gmail.com', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO', '+79165874526', 1, 'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/b561dc28-505f-426f-8a59-abec46161744/dbscvr2-c1a5e06a-2375-4dca-b7a0-8700279f9cbb.png/v1/fill/w_894,h_894,strp/sefsef_x_unnamed_by_cyacol_dbscvr2-pre.png'),
(2, 'Денис', 'Даниилович', 'Воробьев', 'vorobiev.d.d', 'vorobievd.d@rosatom.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO', '+75874569874', 1, ''),
(3, 'Александр', 'Михайлович', 'Богданов', 'bogdanov.a.m', 'bogdanov.a.m@rosatom.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO', '+7000', 1, ''),
(4, 'Владимир ', 'Даниилович', 'Блинов', 'blinov.v.d', 'vorobievdd@rosatom.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO', '+7000', 1, ''),
(5, 'Ксения', 'Владимировна', 'Вдовина', 'vdovina.k.v', 'vorobievdd@rosatom.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO', '+7000', 1, 'https://iconorbit.com/icons/256-watermark/1611201511385554301-Girl%20User.jpg'),
(6, 'Михаил', 'Владиславович', 'Голованов', 'golovanov', 'golovanov.m.v@rosatom.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO', '+7000', 1, ''),
(7, 'Иван', 'Иванович', 'Ивванов', 'ivanov', 'ivanov.m.v@rosatom.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO', '+7000', 1, '');
