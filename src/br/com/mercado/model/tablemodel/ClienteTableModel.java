package br.com.mercado.model.tablemodel;

import br.com.mercado.model.Cliente;
import br.com.mercado.interfaces.AcoesTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author william.mauro
 */
public class ClienteTableModel extends AbstractTableModel implements AcoesTableModel<Cliente> {

    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int TELEFONE = 2;
    private static final int EMAIL = 3;
    private static final int ENDERECO = 4;
    private static final int ATIVO = 5;

    private List<Cliente> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Telefone", "Email", "Cidade", "Ativo"};

    public ClienteTableModel() {
        linhas = new ArrayList<>();
    }

    public ClienteTableModel(List<Cliente> listClientes) {
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
            case TELEFONE:
                return String.class;
            case EMAIL:
                return String.class;
            case ENDERECO:
                return String.class;
            case ATIVO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Cliente cliente = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return cliente.getId();
            case NOME:
                return cliente.getNome();
            case TELEFONE:
                return cliente.getTelefone();
            case EMAIL:
                return cliente.getEmail();
            case ENDERECO:
                return cliente.getEndereco().getCidade();
            case ATIVO:
                if (cliente.getAtivo() == true) {
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
        Cliente cliente = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                cliente.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                cliente.setNome((String) valor);
                break;
            case TELEFONE:
                cliente.setTelefone((String) valor);
                break;
            case EMAIL:
                cliente.setEmail((String) valor);
                break;
            case ENDERECO:
                cliente.getEndereco().setCidade((String) valor);
                break;
            case ATIVO:
                if (valor.equals("Ativado")) {
                    cliente.setAtivo(true);
                } else {
                    cliente.setAtivo(false);
                }
                ;
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public Cliente pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Cliente cliente) {
        linhas.add(cliente);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Cliente> clientes) {
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
    public void atualizar(int indiceLinha, Cliente cliente) {
        linhas.set(indiceLinha, cliente);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
