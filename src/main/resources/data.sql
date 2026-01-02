TRUNCATE TABLE tasks RESTART IDENTITY CASCADE;

INSERT INTO tasks (title, category, due_date) VALUES
('タイトル１', 'SPRING', CURRENT_DATE),
('タイトル2', 'JAVA', CURRENT_DATE),
('タイトル3', 'JAVA', CURRENT_DATE),
('タイトル4', 'JAVA', CURRENT_DATE),
('タイトル5', 'JAVA', CURRENT_DATE),
('タイトル6', 'JAVA', CURRENT_DATE),
('タイトル7', 'JAVA', CURRENT_DATE),
('タイトル8', 'JAVA', CURRENT_DATE),
('タイトル9', 'JAVA', CURRENT_DATE);