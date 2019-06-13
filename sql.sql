create database mercado;

use mercado;

/** Criando Tabelas **/

CREATE TABLE enderecos(
id INT(11) NOT NULL auto_increment,
cep int(8) NOT NULL,
estado varchar(2) NOT NULL,
cidade varchar(50) NOT NULL,
bairro varchar(100) NOT NULL,
rua varchar(120) NOT NULL,
complemento varchar(120),
numero varchar(8),
PRIMARY KEY (id));

CREATE TABLE clientes(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
telefone varchar(11) NOT NULL,
email varchar(50) NOT NULL,
ativo boolean,
fk_endereco int NOT NULL,
FOREIGN KEY (fk_endereco) references enderecos(id),
PRIMARY KEY (id));


CREATE TABLE fornecedores(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
telefone varchar(11) NOT NULL,
ativo boolean,
fk_endereco int NOT NULL,
FOREIGN KEY (fk_endereco) references enderecos(id),
PRIMARY KEY (id));

CREATE TABLE categorias(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
ativo boolean,
PRIMARY KEY (id));

CREATE TABLE produtos(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
codigoBarras integer(13) NOT NULL,
valor decimal(10,2) NOT NULL,
quantidade integer(11) NOT NULL,
ativo boolean,
fk_categoria int NOT NULL,
fk_fornecedor int NOT NULL,
PRIMARY KEY (id),
foreign key(fk_categoria) references categorias(id),
foreign key(fk_fornecedor) references fornecedores(id));

CREATE TABLE tipoUsuario(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
ativo boolean,
tipoPermissao int,
PRIMARY KEY (id));

CREATE TABLE usuarios(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
pis INT(11) NOT NULL,
salario decimal(10,2) NOT NULL,
telefone varchar(11) NOT NULL,
email varchar(50) NOT NULL,
senha varchar(25) NOT NULL,
ativo boolean,
fk_endereco int NOT NULL,
fk_tipoUsuario int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (fk_endereco) references enderecos(id),
FOREIGN KEY(fk_tipoUsuario) references tipoUsuario(id));


CREATE TABLE vendas(
id INT(11) not null auto_increment,
fk_cliente int,
fk_usuario int NOT NULL,
dataVenda datetime NOT NULL,
PRIMARY KEY (id),
foreign key(fk_cliente) references clientes(id),
foreign key(fk_usuario) references usuarios(id));


CREATE TABLE itemVenda(
id INT(11) not null auto_increment,
valorProduto decimal(10,2),
quantidade int(11),
fk_venda int NOT NULL,
fk_produto int NOT NULL,
PRIMARY KEY (id),
foreign key(fk_venda) references vendas(id),
foreign key(fk_produto) references produtos(id));

CREATE TABLE entradas(
id INT(11) not null auto_increment,
dataEntrada datetime NOT NULL,
fk_fornecedor int NOT NULL,
PRIMARY KEY (id),
foreign key(fk_fornecedor) references fornecedores(id));


CREATE TABLE itemEntrada(
id INT(11) not null auto_increment,
valorProduto decimal(10,2),
quantidade int(11),
numeroLote int(11),
fk_entrada int NOT NULL,
fk_produto int NOT NULL,
PRIMARY KEY (id),
foreign key(fk_entrada) references entradas(id),
foreign key(fk_produto) references produtos(id));


CREATE TABLE receitas(
id INT(11) not null auto_increment,
dataCadastro datetime NOT NULL,
dataPagamento datetime,
dataVencimento date NOT NULL,
valorTotal decimal(10,2) NOT NULL,
valorRecebido decimal(10,2),
codVenda int(9),
PRIMARY KEY (id));

CREATE TABLE tipoDespesa(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE despesas(
id INT(11) not null auto_increment,
dataCadastro datetime NOT NULL,
dataPagamento datetime,
dataVencimento date NOT NULL,
valorPago decimal(10,2),
valorPagoRestante decimal(10,2) NOT NULL,
pago boolean not null,
codEntrada int(9),
fk_tipoDespesa int,
PRIMARY KEY (id),
foreign key(fk_tipoDespesa) references tipoDespesa(id));

INSERT INTO `mercado`.`tipoUsuario` (`id`, `nome`, `ativo`, `tipoPermissao`) VALUES ('1', 'Administradores', '1', '1');
INSERT INTO `mercado`.`tipoUsuario` (`id`, `nome`, `ativo`, `tipoPermissao`) VALUES ('2', 'Caixas', '1', '2');

INSERT INTO `mercado`.`enderecos` (`id`, `cep`, `estado`, `cidade`, `bairro`, `rua`, `complemento`, `numero`) VALUES ('1', '88133810', 'SC', 'Palhoça', 'Jardim das Palmeiras', 'Dália', 'Casa marrom areia', '538');
INSERT INTO `mercado`.`enderecos` (`id`, `cep`, `estado`, `cidade`, `bairro`, `rua`, `complemento`, `numero`) VALUES ('2', '88130800', 'SC', 'Palhoça', 'Jardim Eldorado', 'Não sei o nome', 'Casa Grande', '111');

INSERT INTO `mercado`.`usuarios` (`id`, `nome`, `pis`, `salario`, `telefone`, `email`, `senha`, `ativo`, `fk_endereco`, `fk_tipoUsuario`) VALUES ('1', 'William Bigas Mauro', '12345789', '4500.00', '30337442', 'admin', '1234', '1', '1', '1');
INSERT INTO `mercado`.`usuarios` (`id`, `nome`, `pis`, `salario`, `telefone`, `email`, `senha`, `ativo`, `fk_endereco`, `fk_tipoUsuario`) VALUES ('2', 'Agostinho Detofano Junior', '123456789', '3500.00', '996822475', 'caixa', '1234', '1', '2', '2');

INSERT INTO `mercado`.`tipodespesa` (`id`, `nome`) VALUES ('1', 'Fornecedor');
INSERT INTO `mercado`.`tipodespesa` (`id`, `nome`) VALUES ('2', 'Funcionário');


select * from categorias;

select * from produtos;

select * from fornecedores;

select * from tipoUsuario;

select * from clientes;

select * from enderecos;

select * from tipoDespesa;

select * from despesas;

select * from enderecos;

select * from usuarios;