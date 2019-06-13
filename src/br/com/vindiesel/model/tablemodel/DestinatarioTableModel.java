package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.interfaces.AcoesTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author william.mauro
 */
public class DestinatarioTableModel extends AbstractTableModel implements AcoesTableModel<Destinatario> {

    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int CODIGO_PESSOA = 2;
    private static final int ENDERECO = 4;

    private List<Destinatario> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Codigo Pessoa", "Email", "Cidade"};

    public DestinatarioTableModel() {
        linhas = new ArrayList<>();
    }

    public DestinatarioTableModel(List<Destinatario> listClientes) {
        linhas = new ArrayList<>(listClientes);
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
                return String.class;
            case NOME:
                return String.class;
            case CODIGO_PESSOA:
                return String.class;
            case ENDERECO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Destinatario destinatario = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return destinatario.getId();
            case NOME:
                return destinatario.getNome();
            case CODIGO_PESSOA:
                return destinatario.getCodigoPessoa();
            case ENDERECO:
                return destinatario.getEndereco().getCidade();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Destinatario cliente = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                cliente.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                cliente.setNome((String) valor);
                break;
            case CODIGO_PESSOA:
                cliente.setCodigoPessoa((String) valor);
                break;
            case ENDERECO:
                cliente.getEndereco().setCidade((String) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Destinatario pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Destinatario cliente) {
        linhas.add(cliente);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Destinatario> clientes) {
        int indice = getRowCount();
        linhas.addAll(clientes);
        fireTableRowsInserted(indice, indice + clientes.size());
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
    public void atualizar(int indiceLinha, Destinatario cliente) {
        linhas.set(indiceLinha, cliente);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
