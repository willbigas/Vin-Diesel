package br.com.mercado.interfaces;

import java.util.List;

/**
 *
 * @author William
 */
public interface AcoesTableModel<E> {

    public E pegaObjeto(int indiceLinha);

    public void adicionar(E obj);

    public void adicionar(List<E> obj);

    public void remover(int indiceLinha);

    public void remover(int linhaInicio, int linhaFim);

    public void atualizar(int indiceLinha, E obj);

    public void limpar();

}
