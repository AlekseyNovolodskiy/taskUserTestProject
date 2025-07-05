create table if not exists comments_info
(

    id      int8 primary key NOT NULL,
    comment varchar,
    user_id BIGINT           NOT NULL,
    task_id BIGINT           not null
);
create sequence comments_info_sequence start 5 increment 1;

insert into comments_info(id, comment, user_id, task_id)
values (1, 'comment1', 2, 1),
       (2, 'comments2', 3, 2),
       (3, 'comments3', 2, 1),
       (4, 'comments4', 3, 2)