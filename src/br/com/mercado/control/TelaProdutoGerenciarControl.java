package br.com.mercado.control;

import br.com.mercado.dao.CategoriaDao;
import br.com.mercado.dao.FornecedorDao;
import br.com.mercado.dao.ProdutoDao;
import br.com.mercado.model.Categoria;
import br.com.mercado.model.Fornecedor;
import br.com.mercado.model.Produto;
import br.com.mercado.model.tablemodel.ProdutoTableModel;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;
import br.com.mercado.uteis.UtilTable;
import br.com.mercado.uteis.Validacao;
import br.com.mercado.view.TelaPrincipal;
import br.com.mercado.view.TelaProdutoGerenciar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaProdutoGerenciarControl {

    TelaProdutoGerenciar telaProdutoGerenciar;
    List<Fornecedor> listFornecedores;
    List<Categoria> listCategorias;
    ProdutoTableModel produtoTableModel;
    FornecedorDao fornecedorDao;
    CategoriaDao categoriaDao;
    ProdutoDao produtoDao;
    Produto produto;
    Integer linhaSelecionada;

    public TelaProdutoGerenciarControl() {
        fornecedorDao = new FornecedorDao();
        categoriaDao = new CategoriaDao();
        produtoDao = new ProdutoDao();
        produtoTableModel = new ProdutoTableModel();
    }

    public void chamarTelaProdutoGerenciar() {
        if (telaProdutoGerenciar == null) {
            telaProdutoGerenciar = new TelaProdutoGerenciar(this);
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

        telaProdutoGerenciar.getTblProduto().setModel(produtoTableModel);
        carregarFornecedoresNaCombo();
        carregarCategoriasNaCombo();
        produtoTableModel.limpar();
        produtoTableModel.adicionar(produtoDao.pesquisar());
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
        listFornecedores = fornecedorDao.pesquisar();
        DefaultComboBoxModel<Fornecedor> model = new DefaultComboBoxModel(listFornecedores.toArray());
        TelaProdutoGerenciar.cbFornecedor.setModel(model);
    }

    private void carregarCategoriasNaCombo() {
        listCategorias = categoriaDao.pesquisar();
        DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel(listCategorias.toArray());
        TelaProdutoGerenciar.cbCategoria.setModel(model);
    }

    private void cadastrarProduto() {
        produto = new Produto();
        produto.setCodBarras(Integer.valueOf(telaProdutoGerenciar.getTfCodigoBarras().getText()));
        produto.setNome(telaProdutoGerenciar.getTfNome().getText());
        produto.setQuantidade(0);
        produto.setValor(Double.valueOf(telaProdutoGerenciar.getTfValor().getText()));
        produto.setCategoria((Categoria) telaProdutoGerenciar.getCbCategoria().getSelectedItem());
        produto.setFornecedor((Fornecedor) telaProdutoGerenciar.getCbFornecedor().getSelectedItem());

        if (telaProdutoGerenciar.getCheckAtivo().isSelected()) {
            produto.setAtivo(true);
        } else {
            produto.setAtivo(false);
        }

        if (Validacao.validaEntidade(produto) != null) {
            Mensagem.info(Validacao.validaEntidade(produto));
            produto = null;
            return;
        }

        Integer idInserido = produtoDao.inserir(produto);
        if (idInserido != 0) {
            produto.setId(idInserido);
            produtoTableModel.adicionar(produto);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
        }
        produto = null;
    }

    private void alterarProduto() {
        produto = produtoTableModel.pegaObjeto(telaProdutoGerenciar.getTblProduto().getSelectedRow());
        produto.setCodBarras(Integer.valueOf(telaProdutoGerenciar.getTfCodigoBarras().getText()));
        produto.setNome(telaProdutoGerenciar.getTfNome().getText());
        produto.setQuantidade(Integer.valueOf(telaProdutoGerenciar.getTfQuantidade().getText()));
        produto.setValor(Double.valueOf(telaProdutoGerenciar.getTfValor().getText()));
        produto.setCategoria((Categoria) telaProdutoGerenciar.getCbCategoria().getSelectedItem());
        produto.setFornecedor((Fornecedor) telaProdutoGerenciar.getCbFornecedor().getSelectedItem());

        linhaSelecionada = telaProdutoGerenciar.getTblProduto().getSelectedRow();

        if (Validacao.validaEntidade(produto) != null) {
            Mensagem.info(Validacao.validaEntidade(produto));
            produto = null;
            return;
        }

        boolean alterado = produtoDao.alterar(produto);

        if (alterado) {
            produtoTableModel.atualizar(linhaSelecionada, produto);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        produto = null;
    }

    public void gravarProdutoAction() {
        if (produto == null) {
            cadastrarProduto();
        } else {
            alterarProduto();
        }
    }

    public void carregarProdutoAction() {
        produto = produtoTableModel.pegaObjeto(telaProdutoGerenciar.getTblProduto().getSelectedRow());
        telaProdutoGerenciar.getTfNome().setText(produto.getNome());
        telaProdutoGerenciar.getTfCodigoBarras().setText(String.valueOf(produto.getCodBarras()));
        telaProdutoGerenciar.getTfQuantidade().setText(String.valueOf(produto.getQuantidade()));
        telaProdutoGerenciar.getTfValor().setText(String.valueOf(produto.getValor()));
        telaProdutoGerenciar.getCbCategoria().getModel().setSelectedItem(produto.getCategoria());
        telaProdutoGerenciar.getCbFornecedor().getModel().setSelectedItem(produto.getFornecedor());

        if (produto.getAtivo() == true) {
            telaProdutoGerenciar.getCheckAtivo().setSelected(true);
        } else {
            telaProdutoGerenciar.getCheckAtivo().setSelected(false);
        }
        telaProdutoGerenciar.getTpProduto().setSelectedIndex(1);
    }

    public void desativarProdutoAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }

        if (retorno == JOptionPane.YES_OPTION) {
            produto = produtoTableModel.pegaObjeto(telaProdutoGerenciar.getTblProduto().getSelectedRow());
            if (fornecedorDao.desativar(produto.getId())) {
                produtoTableModel.remover(telaProdutoGerenciar.getTblProduto().getSelectedRow());
                telaProdutoGerenciar.getTblProduto().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        produto = null;
    }

    public void pesquisarProdutoAction() {
        List<Produto> produtosPesquisados = produtoDao.pesquisar(telaProdutoGerenciar.getTfPesquisar().getText());
        if (produtosPesquisados == null) {
            produtoTableModel.limpar();
            produtosPesquisados = produtoDao.pesquisar();
        } else {
            produtoTableModel.limpar();
            produtoTableModel.adicionar(produtosPesquisados);
        }

    }

    public void limparCampos() {
        telaProdutoGerenciar.getTfNome().setText("");
        telaProdutoGerenciar.getTfPesquisar().setText("");
        telaProdutoGerenciar.getTfQuantidade().setText("");
        telaProdutoGerenciar.getTfCodigoBarras().setText("");
        telaProdutoGerenciar.getTfValor().setText("");
        telaProdutoGerenciar.getCbCategoria().setSelectedIndex(0);
        telaProdutoGerenciar.getCbFornecedor().setSelectedIndex(0);
        telaProdutoGerenciar.getCheckAtivo().setSelected(false);
        telaProdutoGerenciar.getTblProduto().clearSelection();
        telaProdutoGerenciar.getTfNome().requestFocus();
    }
}
