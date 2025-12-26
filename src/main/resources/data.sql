TRUNCATE TABLE tasks RESTART IDENTITY CASCADE;

INSERT INTO tasks (title, category, due_date) VALUES
('タイトル１', 'SPRING', CURRENT_DATE),
('タイトル2', 'JAVA', CURRENT_DATE);