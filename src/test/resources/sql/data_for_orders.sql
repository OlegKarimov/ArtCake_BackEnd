insert into account (first_name, last_name, email, hash_password,house_number, phone_number, role, zip_code, state, street, town)
values ('Jim','Carrey','manager@mail.com','$2a$10$pzso4fXl0Lod4xofbi49Ueoa7aFO3KgNQbzAYW4iJ3zCPgcGdLvkm', 55, '+4917611223344', 'MANAGER', '22339', 'CONFIRMED','Norbert-Schmid-Platz', 'Hamburg');
insert into account (first_name, last_name, email, hash_password,house_number, phone_number, role, zip_code, state, street, town)
values ('Konditer', 'Konditerow', 'confectioner@mail.com', '$2a$10$XhZqBs2ID5aIey7WJJiPAexSGXcfuB6NFSb/ZC/S3GMkO/ouhmTdG', 78, '+4917688359755', 'CONFECTIONER', '32341', 'CONFIRMED', 'StrandStrasse', 'Lubeck');
insert into account (first_name, last_name, email, hash_password,house_number, phone_number, role, zip_code, state, street, town)
values ('Client', 'Clientovich', 'client@mail.com', '$2a$10$jtLDeFSt7C1jNGtxpzh6kurTi.rej21OBRqjMexaC9jbW4JI967bC', 12, '+4917612359755', 'CLIENT', '22119', 'CONFIRMED', 'NeuStr.', 'Kiel');

insert into cake (category, image_path, state, ingredients, name, price)
values ('MOUSSE', 'https://github.com/OlegKarimov/ArtCake_Front_End/blob/main/public/Images/mousse/raspberrymousse.jpg', 'CREATED','water, sugar, egg','raspberry-mousse',33.33);

insert into orders (count, client_wishes, deadline, confectioner_id, state, client_id, cake_id, total_price, creation_date)
values (1,'For birthday(30 years)', '2023-10-10', 2, 'CREATED', 1, 1, 200.50, '2023-09-01');
insert into orders (count, client_wishes, deadline, confectioner_id, state, client_id, cake_id, total_price, creation_date)
values (1,'Make in blue and white colors', '2023-10-10', 2, 'CREATED', 3, 1, 250.50, '2023-09-01');

