create table if not exists task_info
(
    id               BIGINT PRIMARY KEY,
    task_name        VARCHAR(255) NOT NULL,
    task_description VARCHAR(255)  NOT NULL,
    creationat     TIMESTAMP    NOT NULL,
    expiredat       TIMESTAMP    NOT NULL,
    user_id          BIGINT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_info (id)
);

create sequence task_info_sequence start 3 increment 1;

insert into task_info(id, task_name, task_description, creationat, expiredat, user_id)

values (1, 'task1', 'asda', '2027-07-10', '2027-07-12', 1),
       (2, 'task2', 'asdasd', '2030-07-10', '2030-07-12', 1)
