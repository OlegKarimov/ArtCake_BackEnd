insert into account(first_name, last_name, email, hash_password,house_number, phone_number, role, zip_code, state, street, town)
values ('Jim', 'Carrey','test@mail.com','$2a$10$pzso4fXl0Lod4xofbi49Ueoa7aFO3KgNQbzAYW4iJ3zCPgcGdLvkm','55', '+4917611223344', 'MANAGER', '22339', 'CONFIRMED','Norbert-Schmid-Platz 11', 'Hamburg');

INSERT INTO public.account (id, email, first_name, hash_password, house_number, last_name, phone_number, role, state, street, town, zip_code)
VALUES (6, 'client@gmail.com', 'Client', '$2a$10$YdArHpvx6U8A6cFgmrv4GO46E6EJM9Pq2Xniz9QfHc0NsveV7oUdm', 7, 'Clientowitsch', '+4917688777755', 'CLIENT', 'CONFIRMED', 'StrandStrasse', 'Kiel', '22336');

insert into cake (category, state, ingredients, name, price)
values ('CHEESECAKES', 'CREATED', 'egg,milk,salt','strawberry-cheesecake',200.00);
insert into cake (category, state, ingredients, name, price)
values ('MACARONS', 'CREATED','milk,egg','strawberry-macarons',88.88);

INSERT INTO public.orders (id, client_wishes, confectioner_id, count, creation_date, deadline, state, total_price, cake_id, client_id)
VALUES (1, 'Make in blue and white colours', 0, 2, '2023-09-01', '2023-11-10', 'CREATED', 340, 2, 6);
INSERT INTO public.orders (id, client_wishes, confectioner_id, count, creation_date, deadline, state, total_price, cake_id, client_id)
VALUES (3, 'Make in blue and white colours', 0, 1, '2023-09-01', '2023-11-10', 'CREATED', 210, 1, 6);
