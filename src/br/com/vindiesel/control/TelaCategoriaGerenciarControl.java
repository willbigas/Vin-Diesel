package br.com.vindiesel.control;

import br.com.vindiesel.dao.CategoriaDao;
import br.com.vindiesel.model.Categoria;
import br.com.vindiesel.model.tablemodel.CategoriaTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaCategoriaGerenciar;
import br.com.vindiesel.view.TelaPrincipal;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaCategoriaGerenciarControl {

    private TelaCategoriaGerenciar telaCategoriaGerenciar;
    private Categoria categoria;
    private CategoriaDao categoriaDao;
    private CategoriaTableModel categoriaTableModel;
    private Integer linhaSelecionada;

    public TelaCategoriaGerenciarControl() {
        categoriaDao = new CategoriaDao();
        categoriaTableModel = new CategoriaTableModel();
        categoriaTableModel.limpar();
        categoriaTableModel.adicionar(categoriaDao.pesquisar());
    }

    public void chamarTelaCategoriaGerenciar() {
        if (telaCategoriaGerenciar == null) {
            telaCategoriaGerenciar = new TelaCategoriaGerenciar(this);
            TelaPrincipal.desktopPane.add(telaCategoriaGerenciar);
            telaCategoriaGerenciar.setVisible(true);
        } else {
            if (telaCategoriaGerenciar.isVisible()) {
                telaCategoriaGerenciar.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaCategoriaGerenciar);
                telaCategoriaGerenciar.setVisible(true);
            }
        }
        telaCategoriaGerenciar.getTblCategoria().setModel(categoriaTableModel);
        redimensionarTabelaProduto();
        centralizarCabecalhoEConteudoTabelaProduto();
    }

    public void redimensionarTabelaProduto() {
        UtilTable.redimensionar(telaCategoriaGerenciar.getTblCategoria(), 0, 90);
        UtilTable.redimensionar(telaCategoriaGerenciar.getTblCategoria(), 1, 280);
        UtilTable.redimensionar(telaCategoriaGerenciar.getTblCategoria(), 2, 90);
    }

    public void centralizarCabecalhoEConteudoTabelaProduto() {
        UtilTable.centralizarCabecalho(telaCategoriaGerenciar.getTblCategoria());
        UtilTable.centralizarConteudo(telaCategoriaGerenciar.getTblCategoria(), 0);
        UtilTable.centralizarConteudo(telaCategoriaGerenciar.getTblCategoria(), 1);
        UtilTable.centralizarConteudo(telaCategoriaGerenciar.getTblCategoria(), 2);
    }

    private void cadastrarCategoria() {

        categoria = new Categoria();
        categoria.setId(Integer.MAX_VALUE);
        categoria.setNome(telaCategoriaGerenciar.getTfNome().getText());
        if (telaCategoriaGerenciar.getCheckAtivo().isSelected()) {
            categoria.setAtivo(true);
        } else {
            categoria.setAtivo(false);
        }

        if (Validacao.validaEntidade(categoria) != null) {
            Mensagem.info(Validacao.validaEntidade(categoria));
            categoria = null;
            return;
        }

        Integer idInserido = categoriaDao.inserir(categoria);
        if (idInserido != 0) {
            categoria.setId(idInserido);
            categoriaTableModel.adicionar(categoria);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        categoria = null;
    }

    public void carregarCategoriaAction() {
        categoria = categoriaTableModel.pegaObjeto(telaCategoriaGerenciar.getTblCategoria().getSelectedRow());
        telaCategoriaGerenciar.getTfNome().setText(categoria.getNome());
        if (categoria.getAtivo() == true) {
            telaCategoriaGerenciar.getCheckAtivo().setSelected(true);
        } else {
            telaCategoriaGerenciar.getCheckAtivo().setSelected(false);
        }
    }

    private void alterarCategoria() {
        categoria.setNome(telaCategoriaGerenciar.getTfNome().getText());
        if (telaCategoriaGerenciar.getCheckAtivo().isSelected()) {
            categoria.setAtivo(true);
        } else {
            categoria.setAtivo(false);
        }

        if (Validacao.validaEntidade(categoria) != null) {
            Mensagem.info(Validacao.validaEntidade(categoria));
            categoria = null;
            return;
        }

        boolean alterado = categoriaDao.alterar(categoria);
        linhaSelecionada = telaCategoriaGerenciar.getTblCategoria().getSelectedRow();
        if (alterado) {
            categoriaTableModel.atualizar(linhaSelecionada, categoria);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        categoria = null;
    }

    public void desativarCategoriaAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }

        if (retorno == JOptionPane.YES_OPTION) {
            categoria = categoriaTableModel.pegaObjeto(telaCategoriaGerenciar.getTblCategoria().getSelectedRow());
            if (categoriaDao.desativar(categoria)) {
                categoriaTableModel.remover(telaCategoriaGerenciar.getTblCategoria().getSelectedRow());
                telaCategoriaGerenciar.getTblCategoria().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        categoria = null;
    }

    public void gravarCategoriaAction() {
        if (categoria == null) {
            cadastrarCategoria();
        } else {
            alterarCategoria();
        }
    }

    public void pesquisarCategoriaAction() {
        List<Categoria> categoriasPesquisadas = categoriaDao.pesquisar(telaCategoriaGerenciar.getTfPesquisar().getText());
        if (categoriasPesquisadas == null) {
            categoriaTableModel.limpar();
            categoriasPesquisadas = categoriaDao.pesquisar();
        } else {
            categoriaTableModel.limpar();
            categoriaTableModel.adicionar(categoriasPesquisadas);
        }
    }

    private void limparCampos() {
        telaCategoriaGerenciar.getTfNome().setText("");
        telaCategoriaGerenciar.getTfPesquisar().setText("");
        telaCategoriaGerenciar.getCheckAtivo().setSelected(false);
        telaCategoriaGerenciar.getTfNome().requestFocus();
    }

//    private boolean validarCampos() {
//        if (telaCategoriaGerenciar.getTfNome().getText().isEmpty()) {
//            telaCategoriaGerenciar.getTfNome().requestFocus();
//            return true;
//        }
//        return false;
//    }
}
