/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.interfaces.AcoesTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author william.mauro
 */
public class RemetenteTableModel extends AbstractTableModel implements AcoesTableModel<Remetente> {

    private static final int CODIGO_PESSOA = 0;
    private static final int NOME = 1;
    private static final int TELEFONE = 2;
    private static final int CIDADE = 3;

    private List<Remetente> linhas;
    private String[] COLUNAS = {"CPF/CNPJ", "NOME", "TELEFONE","CIDADE"};

    public RemetenteTableModel() {
        linhas = new ArrayList<>();
    }

    public RemetenteTableModel(List<Remetente> listFornecedores) {
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
            case CODIGO_PESSOA:
                return String.class;
            case NOME:
                return String.class;
            case TELEFONE:
                return String.class;
            case CIDADE:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Remetente remetente = linhas.get(linha);
        switch (coluna) {
            case CODIGO_PESSOA:
                return remetente.getCodigoPessoa();
            case NOME:
                return remetente.getNome();
            case TELEFONE:
                return remetente.getTelefone();
            case CIDADE:
                return remetente.getEndereco().getCidade();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Remetente remetente = linhas.get(linha);
        switch (coluna) {
            case CODIGO_PESSOA:
                remetente.setCodigoPessoa((String) valor);
                break;
            case NOME:
                remetente.setNome((String) valor);
                break;
            case TELEFONE:
                remetente.setTelefone((String) valor);
                break;
            case CIDADE:
                remetente.getEndereco().setCidade((String) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Remetente pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Remetente remetente) {
        linhas.add(remetente);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Remetente> remetentes) {
        int indice = getRowCount();
        linhas.addAll(remetentes);
        fireTableRowsInserted(indice, indice + remetentes.size());
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
    public void atualizar(int indiceLinha, Remetente remetente) {
        linhas.set(indiceLinha, remetente);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
