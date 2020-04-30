create table file_store
(
    id           int auto_increment
        primary key,
    file_name    varchar(30)                           not null,
    file         longblob                              not null,
    updated_date timestamp default current_timestamp() not null
);


