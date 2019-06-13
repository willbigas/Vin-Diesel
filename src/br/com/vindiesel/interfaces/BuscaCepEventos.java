package br.com.vindiesel.interfaces;

import br.com.vindiesel.control.BuscaCepControl;

public interface BuscaCepEventos {
    /**
     * Quando o CEP for encontrado com sucesso
     * @param cep retorna o objeto BuscaCep
     */
    public void sucessoAoEncontrar(BuscaCepControl cep);
    
    /**
     * Quando ocorrer qualquer erro ao encontrar o CEP
     * @param cep retorna o CEP que foi requisitado
     */
    public void erroAoEncontrar(String cep);
}
