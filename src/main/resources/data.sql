delete from  user_role;
delete from  roles;
delete from  users;


INSERT INTO roles (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_LIBRARIAN'),
(3, 'ROLE_USER')
;

INSERT INTO users (id, username, password) VALUES
(1, 'Admin', 'admin'),
(2, 'Librarian', 'lib'),
(3, 'User', 'user' )
;

INSERT INTO user_role(user_id, role_id) VALUES
(1,1),
(2,2),
(3,3)
;

INSERT INTO bookdescription (id, isbn, name, author) VALUES
(1, 1222, 'Сто лет одиночества', 'Г.Г. Маркес'),
(2, 1342, 'Горе от ума', 'А.С. Грибоедов'),
(3, 2333, 'Не время для драконов', 'C. Лукьяненко, Н.Перумов')
;

INSERT INTO book (id, deleted, description_id) VALUES
(1, 0, 1),
(2, 0, 2),
(3, 0, 3),
(4, 0, 1),
(5, 0, 1)
;

INSERT INTO holder (dtype, id, name, type, address, phone, description) VALUES
('placement', 1, 'Полка Верхняя', 1, null, null, 'Тяжело тянуться'),
('placement', 2, 'Полка Нижняя', 1, null, null, 'Неудобно нагибаться'),
('placement', 3, 'Полка Средняя', 1, null, null, 'Опция по дефолту'),
('client', 4, 'Иванов', 2, 'Москва', '223322', null),
('placement', 5, 'Петров', 2, 'Урюпинск', '123321', null)
;

INSERT INTO movement (id , from_id , to_id ) VALUES
(1, null, 1),
(2, null, 3),
(3, null, 2),
(4, null, 2),
(5, 1, 4),
(6, 4, 3),
(7, 3, 4),
(8, 3, 5)
;

INSERT INTO book_movements (book_id , movements_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(1, 5),
(1, 6),
(1, 7),
(2, 8)
;