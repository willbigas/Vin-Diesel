
package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.interfaces.AcoesTableModel;
import br.com.vindiesel.uteis.UtilDecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author William
 */
public class EncomendaTableModel extends AbstractTableModel implements AcoesTableModel<Encomenda> {

    private static final int CODIGO_RASTREIO = 0;
    private static final int VALOR_ENCOMENDA = 1;
    private static final int PESO = 2;
    private static final int LARGURA = 3;
    private static final int ALTURA = 4;
    private static final int COMPRIMENTO = 5;

    private List<Encomenda> linhas;
    private String[] COLUNAS = {"CÓDIGO RASTREIO", "VALOR_NF" , "PESO", "LARGURA", "ALTURA", "COMPRIMENTO"};

    public EncomendaTableModel() {
        linhas = new ArrayList<>();
    }

    public EncomendaTableModel(List<Encomenda> listProdutos) {
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
            case CODIGO_RASTREIO:
                return String.class;
            case VALOR_ENCOMENDA:
                return String.class;
            case PESO:
                return String.class;
            case LARGURA:
                return String.class;
            case ALTURA:
                return String.class;
            case COMPRIMENTO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Encomenda encomenda = linhas.get(linha);
        switch (coluna) {
            case CODIGO_RASTREIO:
                return encomenda.getCodigoRastreio();
            case VALOR_ENCOMENDA:
                return UtilDecimalFormat.decimalFormat(encomenda.getValorNotaFiscal());
            case PESO:
                return UtilDecimalFormat.decimalFormatR$(encomenda.getPeso());
            case LARGURA:
                return UtilDecimalFormat.decimalFormat(encomenda.getPeso());
            case ALTURA:
                return UtilDecimalFormat.decimalFormat(encomenda.getDimensao().getAltura());
            case COMPRIMENTO:
                return UtilDecimalFormat.decimalFormat(encomenda.getDimensao().getComprimento());
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Encomenda produto = linhas.get(linha);
        switch (coluna) {
            case CODIGO_RASTREIO:
                produto.setCodigoRastreio((String) valor);
                break;
            case VALOR_ENCOMENDA:
                produto.setValorNotaFiscal(Double.valueOf((String) valor));
                break;
            case PESO:
                produto.setPeso((Double) valor);
                break;
            case LARGURA:
                produto.getDimensao().setLargura((Double) valor);
                break;
            case ALTURA:
                produto.getDimensao().setAltura((Double) valor);
                break;
            case COMPRIMENTO:
                produto.getDimensao().setComprimento((Double) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Encomenda pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Encomenda encomenda) {
        linhas.add(encomenda);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Encomenda> encomendas) {
        int indice = getRowCount();
        linhas.addAll(encomendas);
        fireTableRowsInserted(indice, indice + encomendas.size());
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
    public void atualizar(int indiceLinha, Encomenda encomenda) {
        linhas.set(indiceLinha, encomenda);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
