create table ama_folder(
    folder_id               int auto_increment primary key,
    folder_parent           int null,
    folder_name             varchar(255),
foreign key (folder_parent) references ama_folder(folder_id)
)engine=innodb;

insert into ama_folder(folder_id, folder_parent, folder_name) values (1,1,'ROOT');

create table ama_document(
    doc_id                  int auto_increment primary key,
    doc_folder              int not null default 1,
    doc_name                varchar(255) not null,
    doc_creation            timestamp default current_timestamp,
    doc_path                varchar(1000) not null,
    doc_project             int null,
    doc_content             text null,
    foreign key (doc_folder) references ama_folder(folder_id),
    foreign key (doc_project) references ama_project(project_id)
)engine=innodb;