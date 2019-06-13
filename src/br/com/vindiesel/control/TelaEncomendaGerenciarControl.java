package br.com.vindiesel.control;

import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.dao.EncomendaDao;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.model.tablemodel.EncomendaTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaEncomendaGerenciar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaEncomendaGerenciarControl {

    TelaEncomendaGerenciar telaProdutoGerenciar;
    List<Remetente> listFornecedores;
    EncomendaTableModel encomendaTableModel;
    RemetenteDao remetenteDao;
    EncomendaDao encomendaDao;
    Encomenda encomenda;
    Integer linhaSelecionada;

    public TelaEncomendaGerenciarControl() {
        remetenteDao = new RemetenteDao();
        encomendaDao = new EncomendaDao();
        encomendaTableModel = new EncomendaTableModel();
    }

    public void chamarTelaProdutoGerenciar() {
        if (telaProdutoGerenciar == null) {
            telaProdutoGerenciar = new TelaEncomendaGerenciar(this);
            TelaPrincipal.desktopPane.add(telaProdutoGerenciar);
            telaProdutoGerenciar.setVisible(true);
        } else {
            if (telaProdutoGerenciar.isVisible()) {
                telaProdutoGerenciar.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaProdutoGerenciar);
                telaProdutoGerenciar.setVisible(true);
            }
        }

        telaProdutoGerenciar.getTblProduto().setModel(encomendaTableModel);
        carregarFornecedoresNaCombo();
        encomendaTableModel.limpar();
        encomendaTableModel.adicionar(encomendaDao.pesquisar());
        telaProdutoGerenciar.getTfNome().requestFocus();
        redimensionarTabelaDeProdutos();
        centralizarCabecalhoEConteudoTabelaProduto();
    }

    private void redimensionarTabelaDeProdutos() {
        UtilTable.redimensionar(telaProdutoGerenciar.getTblProduto(), 0, 70);
        UtilTable.redimensionar(telaProdutoGerenciar.getTblProduto(), 1, 280);
        UtilTable.redimensionar(telaProdutoGerenciar.getTblProduto(), 2, 100);
        UtilTable.redimensionar(telaProdutoGerenciar.getTblProduto(), 3, 80);
        UtilTable.redimensionar(telaProdutoGerenciar.getTblProduto(), 4, 80);
        UtilTable.redimensionar(telaProdutoGerenciar.getTblProduto(), 5, 100);
        UtilTable.redimensionar(telaProdutoGerenciar.getTblProduto(), 6, 66);
    }

    public void centralizarCabecalhoEConteudoTabelaProduto() {
        UtilTable.centralizarCabecalho(telaProdutoGerenciar.getTblProduto());
        UtilTable.centralizarConteudo(telaProdutoGerenciar.getTblProduto(), 0);
        UtilTable.centralizarConteudo(telaProdutoGerenciar.getTblProduto(), 1);
        UtilTable.centralizarConteudo(telaProdutoGerenciar.getTblProduto(), 2);
        UtilTable.centralizarConteudo(telaProdutoGerenciar.getTblProduto(), 3);
        UtilTable.centralizarConteudo(telaProdutoGerenciar.getTblProduto(), 4);
        UtilTable.centralizarConteudo(telaProdutoGerenciar.getTblProduto(), 5);
        UtilTable.centralizarConteudo(telaProdutoGerenciar.getTblProduto(), 6);
    }

    private void carregarFornecedoresNaCombo() {
        listFornecedores = remetenteDao.pesquisar();
        DefaultComboBoxModel<Remetente> model = new DefaultComboBoxModel(listFornecedores.toArray());
        TelaEncomendaGerenciar.cbFornecedor.setModel(model);
    }

    private void cadastrarProduto() {
        encomenda = new Encomenda();
        
        /// pegar atributos de encomenda aqui.


        if (Validacao.validaEntidade(encomenda) != null) {
            Mensagem.info(Validacao.validaEntidade(encomenda));
            encomenda = null;
            return;
        }

        Integer idInserido = encomendaDao.inserir(encomenda);
        if (idInserido != 0) {
            encomenda.setId(idInserido);
            encomendaTableModel.adicionar(encomenda);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
        }
        encomenda = null;
    }

    private void alterarProduto() {
        encomenda = encomendaTableModel.pegaObjeto(telaProdutoGerenciar.getTblProduto().getSelectedRow());
        
        
        // atributos de encomenda

        linhaSelecionada = telaProdutoGerenciar.getTblProduto().getSelectedRow();

        if (Validacao.validaEntidade(encomenda) != null) {
            Mensagem.info(Validacao.validaEntidade(encomenda));
            encomenda = null;
            return;
        }

        boolean alterado = encomendaDao.alterar(encomenda);

        if (alterado) {
            encomendaTableModel.atualizar(linhaSelecionada, encomenda);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        encomenda = null;
    }

    public void gravarProdutoAction() {
        if (encomenda == null) {
            cadastrarProduto();
        } else {
            alterarProduto();
        }
    }

    public void carregarProdutoAction() {
        encomenda = encomendaTableModel.pegaObjeto(telaProdutoGerenciar.getTblProduto().getSelectedRow());
        
        // Atributos de encomenda

    }

    public void desativarProdutoAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }

        if (retorno == JOptionPane.YES_OPTION) {
            encomenda = encomendaTableModel.pegaObjeto(telaProdutoGerenciar.getTblProduto().getSelectedRow());
            if (remetenteDao.desativar(encomenda.getId())) {
                encomendaTableModel.remover(telaProdutoGerenciar.getTblProduto().getSelectedRow());
                telaProdutoGerenciar.getTblProduto().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        encomenda = null;
    }

    public void pesquisarProdutoAction() {
        List<Encomenda> produtosPesquisados = encomendaDao.pesquisar(telaProdutoGerenciar.getTfPesquisar().getText());
        if (produtosPesquisados == null) {
            encomendaTableModel.limpar();
            produtosPesquisados = encomendaDao.pesquisar();
        } else {
            encomendaTableModel.limpar();
            encomendaTableModel.adicionar(produtosPesquisados);
        }

    }

    public void limparCampos() {
        telaProdutoGerenciar.getTfNome().setText("");
        telaProdutoGerenciar.getTfPesquisar().setText("");
        telaProdutoGerenciar.getTfQuantidade().setText("");
        telaProdutoGerenciar.getTfCodigoBarras().setText("");
        telaProdutoGerenciar.getTfValor().setText("");
        telaProdutoGerenciar.getCbFornecedor().setSelectedIndex(0);
        telaProdutoGerenciar.getCheckAtivo().setSelected(false);
        telaProdutoGerenciar.getTblProduto().clearSelection();
        telaProdutoGerenciar.getTfNome().requestFocus();
    }
}
