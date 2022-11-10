drop table if exists issuer;
drop table if exists coin;
drop table if exists mintage;
drop table if exists api_key;

create table if not exists issuer (
    id int auto_increment primary key,
    `name` varchar(255) not null
);

create table if not exists coin (
    id int auto_increment primary key,
    title varchar(255) not null,
    issuer_id int not null,
    reference_number varchar(255) not null,
    year_min int default null,
    year_max int default null,
    demonetized bit default false,
    face_value float default null,
    composition varchar(255) default null,
    composition_comment varchar(127) default null,
    weight float default null,
    diameter float default null,
    shape varchar(255) default null,
    obverse_lettering text default null,
    reverse_lettering text default null,
    obverse_image text default null,
    reverse_image text default null,
    numista_id int not null,
    foreign key (issuer_id) references issuer (id)
);

create table if not exists mintage (
    id int auto_increment primary key,
    coin_id int not null,
    `year` int default null,
    `number` bigint default null,
    comment text default null,
    foreign key (coin_id) references coin (id)
);

create table if not exists api_key (
    id int auto_increment primary key,
    username varchar(127) not null,
    auth_key varchar(127) not null,
    status varchar(127) not null,
    role varchar(127) not null,
    quota int default null,
    used int default null
);