

# Vin Diesel ![delivery-truck_64x64](https://user-images.githubusercontent.com/40570053/59400622-6f9d2780-8d6e-11e9-946c-3b57c41cdea6.png)

# Sistema de Transportadora | Projeto Acadêmico SENAC


Este projeto é um mini sistema de gerenciamento para transportadora de encomendas feito em JavaSE, sua finalidade é para fins educativos e não lucrativos, o sistema conta com diversas apis para busca de localização e calculo de frete , relatórios com o framework jasper reports, validações genéricas com Hibernate validator e Buscador de Endereço do VIACEP API.


### Objetivo:
Criar um sistema Escavável, Performatico e de facil manutenção para fins educativos e aprendizado do JAVASE e suas Libs e Frameworks.

### Público-alvo:
Sistema inicialmente para fins educativos.
Destinado a todo cliente de transportadora que deseja automatizar seu processo de entrega de encomendas.


### Requisitos Funcionais
##### O Acesso ao sistema deverá ser através de login, com dois tipos de perfis: administrador, que terá acesso total a todas as funcionalidades do sistema e funcionário, que terá acesso ao cadastro e edição de clientes e envios e recebimentos de encomendas.
##### Para realizar o envio de uma encomenda será necessário o cadastro do remetente, fornecimento do nome, cpf ou cnpj e endereço do destinatário.
##### Controle sobre a entrada e saída de encomendas.
##### Relatórios para obter melhor controle financeiro, e futuramente melhorar eficiência
de entregas.
##### O frete será calculado de acordo com o peso, as dimensões da encomenda e distância. E deverá ser possível rastreá-la por um código único.
##### Fórmula para cálculo do frete: (((C x L x A) / 6.000) * 1,5)+ D / 20 + 0.015:
#### ● D = Distância em Km
#### ● C: comprimento do pacote em centímetros;
#### ● L: largura do pacote em centímetros;
#### ● A: altura do pacote em centímetros;




### Requisitos não funcionais
##### Interface com multi telas e atalhos de teclado
##### Relatórios dinamicos e com parametros em jasper



### Linguagens, Bibliotecas e Apis:
- Java
- Consumo de API ViACEP
- Consumo de Google DirectionsAPI
- Consumo de Google GeocodingApi
- MySQL com Conexão JDBC
- Hibernate Validator
- JasperSoft Studio
- Jasper Report
- Swing

### Carga horária Até agora:
Aproximadamente 25hrs 


### License:
none;

