create table tr_category(
    category_id         int not null AUTO_INCREMENT,
    category_name       varchar(50) not null,
    category_color      varchar(10) not null default '#000000',
    primary key (category_id)
);

insert into tr_category(category_id, category_name) values
                                                                        (1, 'Uncategorized'),
                                                                        (2, 'Gaming'),
                                                                        (3, 'Tech'),
                                                                        (99, 'Other');

create table tr_article(
    article_id          int not null AUTO_INCREMENT,
    article_title       varchar(1000) not null,
    article_content     text,
    article_date        timestamp default CURRENT_TIMESTAMP,
    article_user        int not null default 1,
    article_category    int not null default 1,
    article_thumbnail   varchar(1000) null,
    article_banner      varchar(1000) null,
    article_published   bool,
	primary key (article_id),
	foreign key article_user_fk (article_user) references ama_user(user_id),
	foreign key article_category_fk (article_category) references tr_category(category_id)
);

create table tr_temp(
    temp_id             int not null AUTO_INCREMENT,
    temp_user           int not null,
    temp_key            varchar(100) not null default 'temp',
    temp_content        text not null,
    primary key (temp_id),
    foreign key temp_user_fk (temp_user) references ama_user(user_id)
);