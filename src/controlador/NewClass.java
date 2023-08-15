/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *   
 * 
Fazer um CRUD em Java Desktop para cada uma das entidades abaixo e entregar junto com o projeto já realizado em sala (CRUD de usuário + entidades abaixo).

0. Entidade: usuario (ATUALIZAR)
- Adicionar coluna status int(1) default '1'
- Essa coluna vai mostrar se o usuário está ativo ou inativo (combobox)

1. Entidade: usuariogrupo
id int PK AI
nome varchar(100) not null

2. Entidade: produtocategoria
id int PK AI
nome varchar(100) not null

3. Entidade: produto
id int PK AI
nome varchar(100) not null
unidadeDeMedida varchar(15)
datacadastro datetime default current_timestamp

4. Entidade: cliente
id int PK AI
nome varchar(100) not null
tipocliente int(1) not null (Pessoa física ou Pessoa Jurídica)
cpfcnpj varchar(15) not null
telefone varchar(20)
email varchar(100)
observacao text
datacadastro datetime default current_timestamp

OBSERVAÇÃO
- Validar campos obrigatórios (not null)
- Formatar visualmente: CPF/CNPJ e telefone
- Validar no cadastro de cliente: cpfcnpj e email
 * @author Ostroski
 */
public class NewClass {
    
}
