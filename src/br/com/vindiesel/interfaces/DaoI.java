package br.com.vindiesel.interfaces;

import java.util.List;

/**
 *
 * @author William
 */
public interface DaoI<E> {

    public int inserir(E obj);

    public boolean alterar(E obj);

    public boolean deletar(E obj);

    public boolean deletar(int id);

    public boolean desativar(E obj);

    public boolean desativar(int id);

    public List<E> pesquisar();

    public List<E> pesquisar(String termo);

    public E pesquisar(int id);

}
