package br.com.vindiesel.model.tablemodel;

import br.com.vindiesel.model.TipoUsuario;
import br.com.vindiesel.interfaces.AcoesTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Will
 */
public class TipoUsuarioTableModel extends AbstractTableModel implements AcoesTableModel<TipoUsuario> {

    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int PERMISSAO = 2;
    private static final int ATIVO = 3;

    private List<TipoUsuario> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Permissão", "Ativo"};

    public TipoUsuarioTableModel() {
        linhas = new ArrayList<>();
    }

    public TipoUsuarioTableModel(List<TipoUsuario> listTipoUsuarios) {
        linhas = new ArrayList<>(listTipoUsuarios);
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
            case PERMISSAO:
                return String.class;
            case ATIVO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        TipoUsuario tipoUsuario = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return tipoUsuario.getId();
            case NOME:
                return tipoUsuario.getNome();
            case PERMISSAO:
                if (tipoUsuario.getTipoPermissao() == 1) {
                    return "Administrador";
                } else {
                    return "Funcionário";
                }
            case ATIVO:
                if (tipoUsuario.getAtivo() == true) {
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
        TipoUsuario tipoUsuario = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                tipoUsuario.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                tipoUsuario.setNome((String) valor);
                break;
            case PERMISSAO:
                tipoUsuario.setTipoPermissao((Integer) valor);
                break;
            case ATIVO:
                if (valor.equals("Ativado")) {
                    tipoUsuario.setAtivo(true);
                } else {
                    tipoUsuario.setAtivo(false);
                }
                ;
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public TipoUsuario pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(TipoUsuario tipoUsuario) {
        linhas.add(tipoUsuario);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<TipoUsuario> tipoUsuarios) {
        int indice = getRowCount();
        linhas.addAll(tipoUsuarios);
        fireTableRowsInserted(indice, indice + tipoUsuarios.size());
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
    public void atualizar(int indiceLinha, TipoUsuario tipoUsuario) {
        linhas.set(indiceLinha, tipoUsuario);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
