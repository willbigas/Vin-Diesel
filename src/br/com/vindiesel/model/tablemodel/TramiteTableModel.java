
package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.interfaces.AcoesTableModel;
import br.com.vindiesel.model.Tramite;
import br.com.vindiesel.uteis.UtilDecimalFormat;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author William
 */
public class TramiteTableModel extends AbstractTableModel implements AcoesTableModel<Tramite> {

    private static final int DATA_HORA = 0;
    private static final int NOME = 1;
    private static final int OBSERVACAO = 2;
    private static final int CODIGO_ENCOMENDA = 3;

    private List<Tramite> linhas;
    private String[] COLUNAS = {"DATA E HORA", "NOME", "OBSERVACAO", "CODIGO DA ENCOMENDA"};

    public TramiteTableModel() {
        linhas = new ArrayList<>();
    }

    public TramiteTableModel(List<Tramite> listTramites) {
        linhas = new ArrayList<>(listTramites);
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
            case DATA_HORA:
                return LocalDateTime.class;
            case NOME:
                return String.class;
            case OBSERVACAO:
                return String.class;
            case CODIGO_ENCOMENDA:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Tramite tramite = linhas.get(linha);
        switch (coluna) {
            case DATA_HORA:
                return tramite.getId();
            case NOME:
                return tramite.getNome();
            case OBSERVACAO:
                return tramite.getObservacao();
            case CODIGO_ENCOMENDA:
                return tramite.getEntrega().getEncomenda().getCodigoRastreio();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Tramite tramite = linhas.get(linha);
        switch (coluna) {
            case DATA_HORA:
                tramite.setDataHora((LocalDateTime) valor);
                break;
            case NOME:
                tramite.setNome((String) valor);
                break;
            case OBSERVACAO:
                tramite.setObservacao((String) valor);
                break;
            case CODIGO_ENCOMENDA:
                tramite.getEntrega().getEncomenda().setCodigoRastreio((String) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Tramite pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Tramite tramite) {
        linhas.add(tramite);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Tramite> tramites) {
        int indice = getRowCount();
        linhas.addAll(tramites);
        fireTableRowsInserted(indice, indice + tramites.size());
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
    public void atualizar(int indiceLinha, Tramite tramite) {
        linhas.set(indiceLinha, tramite);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
