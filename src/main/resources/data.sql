TRUNCATE TABLE categories, tasks RESTART IDENTITY CASCADE;

INSERT INTO categories (name) VALUES
('Java'),
('Spring'),
('その他');

INSERT INTO tasks (title, category_id, due_date) VALUES
('タイトル１', 2, CURRENT_DATE),
('タイトル2', 1, CURRENT_DATE),
('タイトル3', 1, CURRENT_DATE),
('タイトル4', 3, CURRENT_DATE),
('タイトル5', 1, CURRENT_DATE),
('タイトル6', 2, CURRENT_DATE),
('タイトル7', 3, CURRENT_DATE),
('タイトル8', 1, CURRENT_DATE),
('タイトル9', 2, CURRENT_DATE);