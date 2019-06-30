/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.uteis;

/**
 *
 * @author william.mauro
 */
public class Texto {

    // Mensagens de Sucesso Padrões
    public static final String SUCESSO_CADASTRAR = "Cadastrado com Sucesso!";
    
    public static final String SUCESSO_CADASTRAR_USUARIO = "Usuário cadastrado com sucesso";
    
    public static final String SUCESSO_CADASTRAR_ENTREGA = "Entrega efetuada com sucesso!";
    
    

    public static final String SUCESSO_EDITAR = "Editado com Sucesso!";

    public static final String SUCESSO_DELETAR = "Excluido com Sucesso!";

    public static final String SUCESSO_DESATIVAR = "Desativado com Sucesso!";
    
    public static final String SUCESSO_REMOVER = "Sucesso ao remover!";
    
    
    

    // Mensagens de Erro Padrões
    public static final String ERRO_DELETAR = "Erro ao excluir";

    public static final String ERRO_CADASTRAR = "Erro ao Cadastrar!";
    
    public static final String ERRO_CADASTRAR_USUARIO = "Erro ao cadastrar um usuário";

    public static final String ERRO_EDITAR = "Erro ao Editar!";

    public static final String ERRO_DESATIVAR = "Erro ao desativar";

    // Mensagens de Campos Vazios Padrões
    public static final String VAZIO_CAMPO = "O Campo está vazio!";

    public static final String VAZIO_CAMPOS = "Algum dos campos estão vazios!";

    // Mensagem de Campos ou Componentes não selecionados.
    public static final String SELECIONADA_LINHA = "Nenhuma Linha Selecionada";

    public static final String SELECIONADA_COLUNA = "Nenhuma Coluna Selecionada";

    public static final String SELECIONADA_BOTAO = "Nenhum Botão Selecionado";

    // Perguntas de Confirmação Padrões.
    public static final String PERGUNTA_EXCLUIR = "Você deseja Realmente excluir ";

    public static final String PERGUNTA_DESATIVAR = "Você deseja Realmente desativar ";

    public static final String PERGUNTA_EDITAR = "Você deseja Realmente editar ";
    
    public static final String PERGUNTA_REMOVER_ITEM_ENTRADA = "Você deseja remover este item da lista de Entrada?";
    
    public static final String PERGUNTA_REMOVER_ITEM_VENDA = "Você deseja remover este item da lista de Venda?";
    
    
    public static final String ERRO_USUARIO = "Usuário não encontrado!";
    
    public static final String SENHA_USUARIO = "A senha não bate com o login de usuário encontrado!";
    
    
    public static final String ERRO_INTERFACE = "Atenção , Este sistema operacional "
            + "não suporta a mudança de interface pela JVM , "
            + "por favor contate o desenvolvedor.";
    
    public static final String ERRO_COVERTER_CAMPO_DATA = "Atenção, Erro ao converter o campo para data";
    
     public static final String ERRO_COVERTER_CAMPO_CEP = "Atenção, o campo de [CEP] deve conter 8 digitos Ex:[00000000]";
     
     public static final String ERRO_CEP_NAO_ENCONTRADO = "Atenção, Cep não encontrado na nossa base de dados , Verifique , caso seja um cep válido digite manualmente";
     
     
     public static final String ERRO_CEP_GENERICO = "Atenção, Aconteceu alguma coisa não prevista ao tentar digitar o cep, por favor entre em contato imediatamente com o administrador do sistema";
     
     
     public static final String ERRO_COVERTER_CAMPO_DECIMAL = "Atenção, os campos devem ser em formato decimal Ex: 00.00";
     
     public static final String ERRO_COVERTER_CAMPO_PIS_SALARIO = "Atenção, "
             + "os campo [PIS] deve ser somente em numeros Ex: 000000000 \r\n e o campo [Salário] deve ser somente em valores decimais Ex: 00.00";
    
    
    // Mensagens de Bean Categoria 
    
    public static final String CATEGORIA_NULL = "Para criar uma categoria, preencha todos os campos obrigatórios";
    
    
    public static final String CATEGORIA_NOME = "O campo [Nome] não pode conter ser vazio ou conter somente espaços";
    
    public static final String CATEGORIA_NOME_TAMANHO = "O campo [Nome] deve ter entre 5 e 50 caracteres";
    
    
    public static final String CATEGORIA_ATIVO_NULO = "O campo [Ativo] não pode ser nulo";
    
    
    
    public static final String CAMPO_CPF_CNPJ = "O campo [CPF/CNPJ] deve conter está "
            + "no formato correto \r\n para CPF : XXX.XXX.XXX-XX \r\n para CNPJ: XX.XXX.XXX/XXXX-XX";
    
    
    public static final String REGEX_CPF_AND_CNPJ = "^([0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}|[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2})$";
    
    
    public static final String ERRO_CONVERTER_CAMPO_MASCARA_CNPJ = "Atencao , Erro ao converter campo de mascara de CNPJ , contate o administrador do sistema.";
    
    
    public static final String ERRO_USUARIO_DUPLICADO = "Atenção ja existe um usuário com esse email vinculado no sistema.";
    
    
    
    public static final String ERRO_INTERFACE_SYNTHETICA = "Erro ao converter a Interface para a Synsthetica Look and Feel, Entre em contato com o desenvolvimento";
    
    
    public static final String ATENCAO_FINALIZAR_ENTREGA = "Atencao , Ao selecionar este tipo de tramite o sistema automaticamente finalizara a entrega , tem certeza dessa acao?";
    
    

}
