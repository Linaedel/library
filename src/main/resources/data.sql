INSERT INTO role (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_LIBRARIAN'),
(3, 'ROLE_READER')
;

INSERT INTO holder (dtype, id, username, password, deleted, name, type, address, phone, description) VALUES
('placement', 1, null, null, 0, 'Полка Верхняя', 1, null, null, 'Тяжело тянуться'),
('placement', 2, null, null, 0, 'Полка Нижняя', 1, null, null, 'Неудобно нагибаться'),
('placement', 3, null, null, 0, 'Полка Средняя', 1, null, null, 'Опция по дефолту'),
('person', 4, 'User1', '1', 0, 'Андрей', 0, 'Москва', '223322', null),
('person', 5, 'User2', '2', 0, 'Сергей', 0, 'Урюпинск', '123321', null),
('person', 6, 'Admin', 'admin', 0, 'Admin', 0, 'Торонто', '01', null),
('person', 7, 'Librarian', 'lib', 0, 'Варфоломей', 0, 'Бобруйск', '555', null)
;

INSERT INTO person_role(person_id, role_id) VALUES
(4,3),
(5,3),
(6,1),
(7,2)
;

INSERT INTO bookdescription (id, isbn, name, author, available, requested) VALUES
(1, 1222, 'Сто лет одиночества', 'Г.Г. Маркес', 2, 0),
(2, 1342, 'Горе от ума', 'А.С. Грибоедов', 0, 0),
(3, 2333, 'Не время для драконов', 'C. Лукьяненко, Н.Перумов', 1, 0)
;

INSERT INTO book (id, deleted, description_id, on_holder, returned) VALUES
(1, 0, 1, 0, 0),
(2, 0, 2, 0, 0),
(3, 0, 3, 1, 0),
(4, 0, 1, 1, 0),
(5, 0, 1, 1, 0)
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