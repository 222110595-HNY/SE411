
insert into product(name, description, price) values ('3d printer', 'A beginner friendly 3d printer', 1840.25);
insert into product(name, description, price) values ('PC computer', 'A gaming laptop', 2300.01);
insert into product(name, description, price) values ('Mobile phone', 'A 5g smartphone', 850.0);
insert into product(name, description, price) values ('Scanner', 'A WIFI scanner', 260.45);
insert into product(name, description, price) values ('Mouse', 'An optical mouse', 65.99);
insert into product(name, description, price) values ('Keyboard', 'A simple keyboard', 105.99);

-- passwords are BCrypt hashes: user1 / pwd123 and user2 / pwd456
insert into users(username, password) values ('user1', '$2a$10$XPCSzPaizausOiD7QcBjjuKUjOf4PDNbottpb0QSFuv96EcMgsngC');
insert into users(username, password) values ('user2', '$2a$10$buWcY8O5P7C5TtE8bRkAF.7mrQogDahEqZ.IsAb5AM.AIJAbyy31K');
