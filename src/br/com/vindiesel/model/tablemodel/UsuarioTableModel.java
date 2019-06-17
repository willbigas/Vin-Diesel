/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.model.Usuario;
import br.com.vindiesel.interfaces.AcoesTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author william.mauro
 */
public class UsuarioTableModel extends AbstractTableModel implements AcoesTableModel<Usuario> {

    private static final int CPF = 0;
    private static final int NOME = 1;
    private static final int EMAIL = 2;
    private static final int TELEFONE = 3;
    private static final int ATIVO = 4;

    private List<Usuario> linhas;
    private String[] COLUNAS = {"CPF", "NOME" ,"EMAIL", "TELEFONE", "ATIVO"};

    public UsuarioTableModel() {
        linhas = new ArrayList<>();
    }

    public UsuarioTableModel(List<Usuario> listFuncionarios) {
        linhas = new ArrayList<>(listFuncionarios);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUNAS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CPF:
                return String.class;
            case NOME:
                return String.class;
            case EMAIL:
                return String.class;
            case TELEFONE:
                return String.class;
            case ATIVO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Usuario funcionario = linhas.get(linha);
        switch (coluna) {
            case CPF:
                return funcionario.getCpf();
            case NOME:
                return funcionario.getNome();
            case EMAIL:
                return funcionario.getEmail();
            case TELEFONE:
                return funcionario.getTelefone();
            case ATIVO:
                if (funcionario.getAtivo() == true) {
                    return "Ativado";
                } else {
                    return "Desativado";
                }
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Usuario funcionario = linhas.get(linha);
        switch (coluna) {
            case CPF:
                funcionario.setCpf((String) valor);
                break;
            case NOME:
                funcionario.setNome((String) valor);
                break;
            case EMAIL:
                funcionario.setEmail((String) valor);
                break;
            case TELEFONE:
                funcionario.setTelefone((String) valor);
                break;
            case ATIVO:
                if (valor.equals("Ativado")) {
                    funcionario.setAtivo(true);
                } else {
                    funcionario.setAtivo(false);
                }
                ;
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Usuario pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Usuario funcionario) {
        linhas.add(funcionario);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Usuario> funcionarios) {
        int indice = getRowCount();
        linhas.addAll(funcionarios);
        fireTableRowsInserted(indice, indice + funcionarios.size());
        fireTableDataChanged();
    }

    @Override
    public void remover(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void remover(int linhaInicio, int linhaFim) {
        for (int i = linhaInicio; i <= linhaFim; i++) {
            linhas.remove(i);
            fireTableRowsDeleted(linhaInicio, linhaFim); // atualiza delete
        }
    }

    @Override
    public void atualizar(int indiceLinha, Usuario funcionario) {
        linhas.set(indiceLinha, funcionario);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
