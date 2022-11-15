create table livro(
    id bigint(20) not null auto_increment,
    nome varchar(200) not null ,
    autor varchar(200) not null ,
    data_cadastro date not null ,
    usuario_email varchar(200) not null ,
    constraint livro_pk primary key (id)

);

create table usuario (
                         id bigint(20) not null AUTO_INCREMENT,
                         nome varchar(150) not null,
                         perfil varchar(255) not null,
                         email varchar(200) not null,
                         senha varchar(100) not null,
                         CONSTRAINT usuario_pk PRIMARY KEY (id)
);

insert into usuario(id, nome, perfil, email, senha) VALUES (1, 'Master', 'ADMIN', 'admin@admin.com', 'senha');
insert into usuario(id, nome, perfil, email, senha) VALUES (2, 'Client', 'Client', 'client@client.com', 'senha');
INSERT INTO livro(id, nome, autor, data_cadastro, usuario_email) VALUES (1, 'Livro1', 'Autor1', TO_DATE('01/01/2001', 'dd/MM/YYYY'), 'admin@admin.com');
INSERT INTO livro(id, nome, autor, data_cadastro, usuario_email) VALUES (2, 'Livro2', 'Autor1', TO_DATE('01/01/2001', 'dd/MM/YYYY'), 'client@client.com');
INSERT INTO livro(id, nome, autor, data_cadastro, usuario_email) VALUES (3, 'Livro3', 'Autor2', TO_DATE('01/01/2001', 'dd/MM/YYYY'), 'client@client.com');