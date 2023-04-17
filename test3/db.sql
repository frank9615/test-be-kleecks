create schema test3;

create table if not exists test3.vendite
(
    user   varchar(1) charset utf8mb4 not null,
    anno   int                        not null,
    totale bigint                     not null
);

insert into test3.vendite (user, anno, totale)
values  ('A', 2021, 500),
        ('B', 2021, 1000),
        ('C', 2022, 900),
        ('A', 2022, 1200),
        ('B', 2021, 600),
        ('A', 2022, 900),
        ('B', 2021, 500),
        ('C', 2021, 1000),
        ('C', 2022, 700),
        ('B', 2021, 500);