create table if not exists user_info
(
    id int8 primary key,

    email varchar NOT NULL  UNIQUE,
    password varchar NOT NULL,
    role varchar NOT NULL

);

create sequence user_info_sequence start 3 increment 1;

insert into user_info(id, email, password, role)
values(1,'adminemail','$2a$10$1fnktPcyCD8aqmWaan/qz.VK0eZbZpR5LisdpMnW5PzHWF1agx2wy','ADMIN'),
    (2,'useremail','$2a$10$1fnktPcyCD8aqmWaan/qz.VK0eZbZpR5LisdpMnW5PzHWF1agx2wy','USER')

