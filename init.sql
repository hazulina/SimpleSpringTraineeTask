
create table if not exists performers
(
	performer_id uuid default gen_random_uuid() not null
		constraint performers_pk
			primary key,
	performer_name varchar(100),
	performer_email varchar(100),
	performer_role varchar(100)
);

create table if not exists projects
(
	project_id uuid default gen_random_uuid() not null
		constraint projects_pk
			primary key,
	project_name varchar(200),
	project_start_date timestamp,
	project_deadline_date timestamp
);

create table if not exists tasks
(
	task_id uuid default gen_random_uuid() not null
		constraint tasks_pk
			primary key,
	task_name varchar(200),
	task_description varchar(500),
	task_priority varchar(50),
	task_status varchar(50),
	task_deadline timestamp,
	task_performer uuid
		constraint tasks_performers_performer_id_fk
			references performers,
	task_project uuid
		constraint tasks_projects_project_id_fk
			references projects
);

create table if not exists project_performers
(
	project_performers_id uuid default gen_random_uuid() not null
		constraint project_performers_pk
			primary key,
	project_id uuid
		constraint project_performers_projects_project_id_fk
			references projects,
	performer_id uuid
		constraint project_performers_performers_performer_id_fk
			references performers
);


-- Добавление тестовых данных в таблицу performers
INSERT INTO performers (performer_name, performer_email, performer_role)
VALUES
  ('Имя1', 'email1@example.com', 'MANAGEMENT'),
  ('Имя2', 'email2@example.com', 'DEVELOPMENT'),
  ('Имя3', 'email3@example.com', 'DEVELOPMENT'),
  ('Имя4', 'email4@example.com', 'TESTING'),
  ('Имя5', 'email5@example.com', 'TESTING');

-- Добавление тестовых данных в таблицу projects
INSERT INTO projects (project_name, project_start_date, project_deadline_date)
VALUES
  ('Проект1', '2023-01-01', '2023-01-01'),
  ('Проект2', '2023-02-01', '2023-02-01'),
  ('Проект3', '2023-03-01', '2023-03-01'),
  ('Проект4', '2023-04-01', '2023-04-01'),
  ('Проект5', '2023-05-01', '2023-05-01');

-- Добавление тестовых данных в таблицу tasks
INSERT INTO tasks (task_name, task_description, task_priority, task_status, task_deadline, task_performer, task_project)
VALUES
  ('Задача1', 'Описание1', 'MEDIUM', 'AT WORK', '2023-01-10', (SELECT performer_id FROM performers WHERE performer_name = 'Имя1'), (SELECT project_id FROM projects WHERE project_name = 'Проект1')),
  ('Задача2', 'Описание2', 'LOW', 'COMPLETE', '2023-02-15', (SELECT performer_id FROM performers WHERE performer_name = 'Имя2'), (SELECT project_id FROM projects WHERE project_name = 'Проект2')),
  ('Задача3', 'Описание3', 'MEDIUM', 'COMPLETE', '2023-03-15', (SELECT performer_id FROM performers WHERE performer_name = 'Имя3'), (SELECT project_id FROM projects WHERE project_name = 'Проект3')),
  ('Задача4', 'Описание4', 'HIGH', 'COMPLETE', '2023-04-15', (SELECT performer_id FROM performers WHERE performer_name = 'Имя4'), (SELECT project_id FROM projects WHERE project_name = 'Проект4')),
  ('Задача5', 'Описание5', 'LOW', 'COMPLETE', '2023-05-15', (SELECT performer_id FROM performers WHERE performer_name = 'Имя5'), (SELECT project_id FROM projects WHERE project_name = 'Проект5'));

-- Добавление тестовых данных в таблицу project_performers
INSERT INTO project_performers (project_id, performer_id)
VALUES
  ((SELECT project_id FROM projects WHERE project_name = 'Проект1'), (SELECT performer_id FROM performers WHERE performer_name = 'Имя1')),
  ((SELECT project_id FROM projects WHERE project_name = 'Проект2'), (SELECT performer_id FROM performers WHERE performer_name = 'Имя2')),
  ((SELECT project_id FROM projects WHERE project_name = 'Проект3'), (SELECT performer_id FROM performers WHERE performer_name = 'Имя3')),
  ((SELECT project_id FROM projects WHERE project_name = 'Проект4'), (SELECT performer_id FROM performers WHERE performer_name = 'Имя4')),
  ((SELECT project_id FROM projects WHERE project_name = 'Проект5'), (SELECT performer_id FROM performers WHERE performer_name = 'Имя5'));