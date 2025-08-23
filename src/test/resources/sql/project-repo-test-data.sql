-- Users
INSERT INTO users (user_id, name, login_id, password, email, phone_number)
VALUES (1, 'Alice', 'alice123', 'password1', 'alice@example.com', '010-1111-1111'),
       (2, 'Bob', 'bob123', 'password2', 'bob@example.com', '010-2222-2222');

-- Thumbnails
INSERT INTO thumbnails (thumbnail_id, directory, filename)
VALUES (1, '/images', 'thumb1.png'),
       (2, '/images', 'thumb2.png'),
       (3, '/images', 'thumb3.png');

-- Projects
INSERT INTO projects (project_id, project_title, user_id, thumbnail_id)
VALUES (1, 'Project A', 1, 1),
       (2, 'Project B', 2, 2),
       (3, 'Project C', 1, 3);

-- Project Categories
INSERT INTO project_categories (project_id, category)
VALUES (1, 'Academia'),
       (1, 'Scholastic'),
       (2, 'Academia');
