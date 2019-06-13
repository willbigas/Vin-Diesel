/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.model.Fornecedor;
import br.com.vindiesel.interfaces.AcoesTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author william.mauro
 */
public class FornecedorTableModel extends AbstractTableModel implements AcoesTableModel<Fornecedor> {

    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int TELEFONE = 2;
    private static final int CIDADE = 3;
    private static final int ATIVO = 4;

    private List<Fornecedor> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Telefone","Cidade", "Ativo"};

    public FornecedorTableModel() {
        linhas = new ArrayList<>();
    }

    public FornecedorTableModel(List<Fornecedor> listFornecedores) {
        linhas = new ArrayList<>(listFornecedores);
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
            case CODIGO:
                return Integer.class;
            case NOME:
                return String.class;
            case TELEFONE:
                return String.class;
            case CIDADE:
                return String.class;
            case ATIVO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Fornecedor fornecedor = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return fornecedor.getId();
            case NOME:
                return fornecedor.getNome();
            case TELEFONE:
                return fornecedor.getTelefone();
            case CIDADE:
                return fornecedor.getEndereco().getCidade();
            case ATIVO:
                if (fornecedor.getAtivo() == true) {
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
        Fornecedor fornecedor = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                fornecedor.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                fornecedor.setNome((String) valor);
                break;
            case TELEFONE:
                fornecedor.setTelefone((String) valor);
                break;
            case CIDADE:
                fornecedor.getEndereco().setCidade((String) valor);
                break;
            case ATIVO:
                if (valor.equals("Ativado")) {
                    fornecedor.setAtivo(true);
                } else {
                    fornecedor.setAtivo(false);
                };
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Fornecedor pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Fornecedor fornecedor) {
        linhas.add(fornecedor);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Fornecedor> fornecedores) {
        int indice = getRowCount();
        linhas.addAll(fornecedores);
        fireTableRowsInserted(indice, indice + fornecedores.size());
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
    public void atualizar(int indiceLinha, Fornecedor fornecedor) {
        linhas.set(indiceLinha, fornecedor);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
