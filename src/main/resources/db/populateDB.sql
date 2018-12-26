DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM dishes;
DELETE
FROM votes;
ALTER SEQUENCE global_seq
  RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin'),
       ('User1', 'user1@yandex.ru', 'password1'),
       ('User2', 'user2@yandex.ru', 'password2');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 100000),
       ('ROLE_USER', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_USER', 100002);

INSERT INTO restaurants (name, phone, address, date)
VALUES ('Ресторан 1', 222222, 'Улица, д.1', '2018-11-07'),
       ('Ресторан 2', 222223, 'Улица, д.2', '2018-11-06'),
       ('Ресторан 3', 222224, 'Улица, д.3', '2018-11-07');

INSERT INTO dishes (restaurant_id, date, description, price)
VALUES (100003, '2018-11-07', 'Бизнес-ланч 1 (Ресторан 1)', 500),
       (100003, '2018-11-07', 'Бизнес-ланч 2 (Ресторан 1)', 500),
       (100003, '2018-11-07', 'Бизнес-ланч 3 (Ресторан 1)', 500),
       (100003, '2018-11-06', 'Бизнес-ланч 4 (Ресторан 1)', 500),
       (100004, '2018-11-07', 'Бизнес-ланч 1 (Ресторан 2)', 500),
       (100004, '2018-11-07', 'Бизнес-ланч 2 (Ресторан 2)', 500),
       (100004, '2018-11-07', 'Бизнес-ланч 3 (Ресторан 2)', 500),
       (100005, '2018-11-07', 'Бизнес-ланч 1 (Ресторан 3)', 500),
       (100005, '2018-11-07', 'Бизнес-ланч 2 (Ресторан 3)', 500),
       (100005, '2018-11-07', 'Бизнес-ланч 3 (Ресторан 3)', 500);

INSERT INTO votes (date, time, user_id, restaurant_id)
VALUES ('2018-11-07', '10:00:00', 100000, 100005),
       ('2018-11-07', '10:30:00', 100001, 100005),
       ('2018-11-07', '10:40:00', 100002, 100003),
       ('2018-11-08', '10:00:00', 100000, 100003);