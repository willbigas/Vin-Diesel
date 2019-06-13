package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.interfaces.AcoesTableModel;
import br.com.vindiesel.model.ItemVenda;
import br.com.vindiesel.uteis.UtilDecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author william.mauro
 */
public class ItemVendaTableModel extends AbstractTableModel implements AcoesTableModel<ItemVenda> {

    private static final int EAN13 = 0;
    private static final int NOME_PRODUTO = 1;
    private static final int QUANTIDADE = 2;
    private static final int VALOR_PRODUTO = 3;

    private List<ItemVenda> linhas;
    private String[] COLUNAS = {"CÓDIGO", "NOME", "QUANTIDADE", "VALOR"};

    public ItemVendaTableModel() {
        linhas = new ArrayList<>();
    }

    public ItemVendaTableModel(List<ItemVenda> listItemVendas) {
        linhas = new ArrayList<>(listItemVendas);
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
            case EAN13:
                return Integer.class;
            case NOME_PRODUTO:
                return String.class;
            case QUANTIDADE:
                return Integer.class;
            case VALOR_PRODUTO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        ItemVenda itemVenda = linhas.get(linha);
        switch (coluna) {
            case EAN13:
                return itemVenda.getId();
            case NOME_PRODUTO:
                return itemVenda.getProduto().getNome();
            case QUANTIDADE:
                return itemVenda.getQuantidade();
            case VALOR_PRODUTO:
                return UtilDecimalFormat.decimalFormatR$(itemVenda.getValorProduto());
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        ItemVenda itemVenda = linhas.get(linha);
        switch (coluna) {
            case EAN13:
                itemVenda.setId(Integer.valueOf((String) valor));
                break;
            case NOME_PRODUTO:
                itemVenda.getProduto().setNome((String) valor);
                break;
            case QUANTIDADE:
                itemVenda.setQuantidade((Integer) valor);
                break;
            case VALOR_PRODUTO:
                itemVenda.setValorProduto((Double) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public ItemVenda pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(ItemVenda itemVenda) {
        linhas.add(itemVenda);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<ItemVenda> itemVenda) {
        int indice = getRowCount();
        linhas.addAll(itemVenda);
        fireTableRowsInserted(indice, indice + itemVenda.size());
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
    public void atualizar(int indiceLinha, ItemVenda itemVenda) {
        linhas.set(indiceLinha, itemVenda);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
