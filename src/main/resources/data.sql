TRUNCATE TABLE categories, tasks RESTART IDENTITY CASCADE;

INSERT INTO categories (name) VALUES
('Java'),
('Spring'),
('その他');

INSERT INTO tasks (title, category_id, due_date, current_status) VALUES
('タイトル１', 2, CURRENT_DATE, 'TODO'),
('タイトル2', 1, CURRENT_DATE, 'DOING'),
('タイトル3', 1, CURRENT_DATE, 'DONE'),
('タイトル4', 2, CURRENT_DATE, 'TODO'),
('タイトル5', 1, CURRENT_DATE, 'DOING'),
('タイトル6', 1, CURRENT_DATE, 'DONE'),
('タイトル7', 2, CURRENT_DATE, 'TODO'),
('タイトル8', 1, CURRENT_DATE, 'DOING'),
('タイトル9', 1, CURRENT_DATE, 'DONE'),