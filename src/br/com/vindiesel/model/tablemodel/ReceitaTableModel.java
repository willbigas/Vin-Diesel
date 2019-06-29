package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.interfaces.AcoesTableModel;
import br.com.vindiesel.model.Receita;
import br.com.vindiesel.model.Tramite;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.uteis.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author William
 */
public class ReceitaTableModel extends AbstractTableModel implements AcoesTableModel<Receita> {

    private static final int DATA_CADASTRO = 0;
    private static final int DATA_VENCIMENTO = 1;
    private static final int VALOR_TOTAL = 2;
    private static final int FORMA_PAGAMENTO = 3;
    private static final int ENTREGUE = 4;

    private List<Receita> linhas;
    private String[] COLUNAS = {"DATA", "VENCIMENTO", "VL TOTAL", "FORMA PAGAMENTO", "STATUS"};

    public ReceitaTableModel() {
        linhas = new ArrayList<>();
    }

    public ReceitaTableModel(List<Receita> listReceitas) {
        linhas = new ArrayList<>(listReceitas);
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
            case DATA_CADASTRO:
                return String.class;
            case DATA_VENCIMENTO:
                return String.class;
            case VALOR_TOTAL:
                return String.class;
            case FORMA_PAGAMENTO:
                return String.class;
            case ENTREGUE:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Receita receita = linhas.get(linha);
        switch (coluna) {
            case DATA_CADASTRO:
                return UtilDate.data(receita.getDataCadastro());
            case DATA_VENCIMENTO:
                return UtilDate.data(receita.getDataVencimento());
            case VALOR_TOTAL:
                return DecimalFormat.decimalFormat(receita.getValorTotal());
            case FORMA_PAGAMENTO:
                if (receita.getFormaPagamento() == null) {
                    return "Não informado";
                } else {
                    return receita.getFormaPagamento().getNome();
                }
            case ENTREGUE:
                if (receita.getEntrega().getEntregue()== true) {
                    return "Entregue";
                } else {
                    return "Pendente";
                }
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Receita receita = linhas.get(linha);
        switch (coluna) {
            case DATA_CADASTRO:
                receita.setDataCadastro((Date) valor);
                break;
            case DATA_VENCIMENTO:
                receita.setDataVencimento((Date) valor);
                break;
            case VALOR_TOTAL:
                receita.setValorTotal((Double) valor);
                break;
            case FORMA_PAGAMENTO:
                receita.getFormaPagamento().setNome((String) valor);
                break;
            case ENTREGUE:
                receita.getEntrega().setEntregue((Boolean) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Receita pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Receita receita) {
        linhas.add(receita);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Receita> receitas) {
        int indice = getRowCount();
        linhas.addAll(receitas);
        fireTableRowsInserted(indice, indice + receitas.size());
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
    public void atualizar(int indiceLinha, Receita receita) {
        linhas.set(indiceLinha, receita);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
