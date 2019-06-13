package br.com.mercado.model.tablemodel;

import br.com.mercado.model.Produto;
import br.com.mercado.interfaces.AcoesTableModel;
import br.com.mercado.uteis.UtilDecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author William
 */
public class VendaProdutoTableModel extends AbstractTableModel implements AcoesTableModel<Produto> {

    private static final int EAN13 = 0;
    private static final int NOME = 1;
    private static final int QUANTIDADE = 2;
    private static final int VALOR = 3;
    private static final int CATEGORIA = 4;

    private List<Produto> linhas;
    private String[] COLUNAS = {"EAN13", "NOME", "QUANTIDADE", "VALOR", "CATEGORIA"};

    public VendaProdutoTableModel() {
        linhas = new ArrayList<>();
    }

    public VendaProdutoTableModel(List<Produto> listProdutos) {
        linhas = new ArrayList<>(listProdutos);
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
            case NOME:
                return String.class;
            case QUANTIDADE:
                return Integer.class;
            case VALOR:
                return String.class;
            case CATEGORIA:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto produto = linhas.get(linha);
        switch (coluna) {
            case EAN13:
                return produto.getCodBarras();
            case NOME:
                return produto.getNome();
            case QUANTIDADE:
                return produto.getQuantidade();
            case VALOR:
                return UtilDecimalFormat.decimalFormatR$(produto.getValor());
            case CATEGORIA:
                return produto.getCategoria().getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Produto produto = linhas.get(linha);
        switch (coluna) {
            case EAN13:
                produto.setCodBarras((Integer) valor);
                break;
            case NOME:
                produto.setNome((String) valor);
                break;
            case QUANTIDADE:
                produto.setQuantidade((Integer) valor);
                break;
            case VALOR:
                produto.setValor(Double.valueOf((String) valor));
                break;
            case CATEGORIA:
                produto.getCategoria().setNome((String) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Produto pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Produto> produtos) {
        int indice = getRowCount();
        linhas.addAll(produtos);
        fireTableRowsInserted(indice, indice + produtos.size());
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
    public void atualizar(int indiceLinha, Produto produto) {
        linhas.set(indiceLinha, produto);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
