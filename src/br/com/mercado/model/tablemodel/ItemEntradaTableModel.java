package br.com.mercado.model.tablemodel;

import br.com.mercado.model.Entrada;
import br.com.mercado.interfaces.AcoesTableModel;
import br.com.mercado.model.ItemEntrada;
import br.com.mercado.uteis.UtilDecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author william.mauro
 */
public class ItemEntradaTableModel extends AbstractTableModel implements AcoesTableModel<ItemEntrada> {

    private static final int EAN13 = 0;
    private static final int NOME_PRODUTO = 1;
    private static final int QUANTIDADE = 2;
    private static final int VALOR_PRODUTO = 3;
    private static final int NUMERO_LOTE = 4;

    private List<ItemEntrada> linhas;
    private String[] COLUNAS = {"Ean13", "Produto", "Quantidade", "Valor Unitário", "Numero Lote"};

    public ItemEntradaTableModel() {
        linhas = new ArrayList<>();
    }

    public ItemEntradaTableModel(List<ItemEntrada> listItemEntradas) {
        linhas = new ArrayList<>(listItemEntradas);
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
            case NUMERO_LOTE:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        ItemEntrada itemEntrada = linhas.get(linha);
        switch (coluna) {
            case EAN13:
                return itemEntrada.getProduto().getCodBarras();
            case NOME_PRODUTO:
                return itemEntrada.getProduto().getNome();
            case QUANTIDADE:
                return itemEntrada.getQuantidade();
            case VALOR_PRODUTO:
                return UtilDecimalFormat.decimalFormatR$(itemEntrada.getValorProduto());
            case NUMERO_LOTE:
                return itemEntrada.getNumeroLote();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        ItemEntrada itemEntrada = linhas.get(linha);
        switch (coluna) {
            case EAN13:
                itemEntrada.getProduto().setCodBarras(Integer.valueOf((String) valor));
                break;
            case NOME_PRODUTO:
                itemEntrada.getProduto().setNome((String) valor);
                break;
            case QUANTIDADE:
                itemEntrada.setQuantidade((Integer) valor);
                break;
            case VALOR_PRODUTO:
                itemEntrada.setValorProduto((Double) valor);
                break;
            case NUMERO_LOTE:
                itemEntrada.setNumeroLote((Integer) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public ItemEntrada pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(ItemEntrada itemEntrada) {
        linhas.add(itemEntrada);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<ItemEntrada> itemEntradas) {
        int indice = getRowCount();
        linhas.addAll(itemEntradas);
        fireTableRowsInserted(indice, indice + itemEntradas.size());
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
    public void atualizar(int indiceLinha, ItemEntrada itemEntrada) {
        linhas.set(indiceLinha, itemEntrada);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }
                    
    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
