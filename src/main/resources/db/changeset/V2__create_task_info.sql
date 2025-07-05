create table if not exists task_info
(
    id               BIGINT PRIMARY KEY,
    task_name        VARCHAR(255) NOT NULL,
    task_description VARCHAR(255) NOT NULL,
    status           varchar      NOT NULL,
    priority         varchar      NOT NULL,
    creationat       TIMESTAMP    NOT NULL,
    expiredat        TIMESTAMP    NOT NULL,
    user_id          BIGINT       NOT NULL,
    author           VARCHAR      NOT NULL
);

create sequence task_info_sequence start 3 increment 1;

insert into task_info(id, task_name, task_description, status, priority, creationat, expiredat, user_id, author)

values (1, 'task1', 'asda', 'ATSTART', 'MEDIUM', '2027-07-10', '2027-07-12', 2, 'adminuser'),
       (2, 'task2', 'asdasd', 'INPROGRESS', 'HI', '2030-07-10', '2030-07-12', 2, 'adminuser')
