package br.com.mercado.control;

import br.com.mercado.dao.ClienteDao;
import br.com.mercado.dao.ItemVendaDao;
import br.com.mercado.dao.ProdutoDao;
import br.com.mercado.dao.UsuarioDao;
import br.com.mercado.dao.VendaDao;
import br.com.mercado.model.Cliente;
import br.com.mercado.model.ItemVenda;
import br.com.mercado.model.Produto;
import br.com.mercado.model.Usuario;
import br.com.mercado.model.Venda;
import br.com.mercado.model.tablemodel.ItemVendaTableModel;
import br.com.mercado.model.tablemodel.VendaProdutoTableModel;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;
import br.com.mercado.uteis.UtilDate;
import br.com.mercado.uteis.UtilTable;
import br.com.mercado.uteis.Validacao;
import br.com.mercado.view.TelaClienteDialogPesquisar;
import br.com.mercado.view.TelaPrincipal;
import br.com.mercado.view.TelaVenda;
import br.com.mercado.view.TelaVendaReceita;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaVendaControl {

    TelaVenda telaVenda;
    TelaVendaReceita telaVendaReceita;
    TelaClienteDialogPesquisar telaClienteDialogPesquisar;
    TelaReceitaGerenciarControl receitaGerenciarControl;
    ClienteDao clienteDao;
    UsuarioDao usuarioDao;
    ProdutoDao produtoDao;
    VendaDao vendaDao;
    ItemVendaDao itemVendaDao;
    List<Cliente> listClientes;
    List<Usuario> listUsuarios;
    List<ItemVenda> listItemVendas;
    VendaProdutoTableModel vendaProdutoTableModel;
    ItemVendaTableModel itemVendaTableModel;
    ItemVenda itemVenda;
    Venda venda;
    Produto produto;

    public TelaVendaControl() {
        clienteDao = new ClienteDao();
        usuarioDao = new UsuarioDao();
        produtoDao = new ProdutoDao();
        vendaDao = new VendaDao();
        itemVendaDao = new ItemVendaDao();
        listClientes = new ArrayList<>();
        listUsuarios = new ArrayList<>();
        vendaProdutoTableModel = new VendaProdutoTableModel();
        itemVendaTableModel = new ItemVendaTableModel();
        listItemVendas = new ArrayList<>();

    }

    public void chamarTelaVenda() {
        if (telaVenda == null) {
            telaVenda = new TelaVenda(this);
            TelaPrincipal.desktopPane.add(telaVenda);
            telaVenda.setVisible(true);
        } else {
            if (telaVenda.isVisible()) {
                telaVenda.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaVenda);
                telaVenda.setVisible(true);
            }
        }
        carregarClientesNaCombo();
        carregarUsuariosNaCombo();
        telaVenda.getTblProduto().setModel(vendaProdutoTableModel);
        vendaProdutoTableModel.limpar();
        vendaProdutoTableModel.adicionar(produtoDao.pesquisar());
        telaVenda.getTblVenda().setModel(itemVendaTableModel);
        redimensionarTabelaProduto();
        redimensionarTabelaItemVenda();
        centralizarCabecalhoEConteudoTabelaProduto();
        centralizarCabecalhoEConteudoTabelaItemVenda();

    }

    public void redimensionarTabelaProduto() {

        UtilTable.redimensionar(telaVenda.getTblProduto(), 0, 90);
        UtilTable.redimensionar(telaVenda.getTblProduto(), 1, 223);
        UtilTable.redimensionar(telaVenda.getTblProduto(), 2, 90);
        UtilTable.redimensionar(telaVenda.getTblProduto(), 3, 95);
    }
    
     public void redimensionarTabelaItemVenda() {

        UtilTable.redimensionar(telaVenda.getTblVenda(), 0, 90);
        UtilTable.redimensionar(telaVenda.getTblVenda(), 1, 280);
        UtilTable.redimensionar(telaVenda.getTblVenda(), 2, 90);
        UtilTable.redimensionar(telaVenda.getTblVenda(), 3, 113);
    }

    public void centralizarCabecalhoEConteudoTabelaProduto() {
        UtilTable.centralizarCabecalho(telaVenda.getTblProduto());
        UtilTable.centralizarConteudo(telaVenda.getTblProduto(), 0);
        UtilTable.centralizarConteudo(telaVenda.getTblProduto(), 1);
        UtilTable.centralizarConteudo(telaVenda.getTblProduto(), 2);
        UtilTable.centralizarConteudo(telaVenda.getTblProduto(), 3);
        UtilTable.centralizarConteudo(telaVenda.getTblProduto(), 4);
    }
    
    public void centralizarCabecalhoEConteudoTabelaItemVenda() {
        UtilTable.centralizarCabecalho(telaVenda.getTblVenda());
        UtilTable.centralizarConteudo(telaVenda.getTblVenda(), 0);
        UtilTable.centralizarConteudo(telaVenda.getTblVenda(), 1);
        UtilTable.centralizarConteudo(telaVenda.getTblVenda(), 2);
        UtilTable.centralizarConteudo(telaVenda.getTblVenda(), 3);
    }
    
    private void carregarClientesNaCombo() {
        listClientes = clienteDao.pesquisar();
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel(listClientes.toArray());
        telaVenda.getCbCliente().setModel(model);
    }

    private void carregarUsuariosNaCombo() {
        listUsuarios = usuarioDao.pesquisar();
        DefaultComboBoxModel<Usuario> model = new DefaultComboBoxModel(listUsuarios.toArray());
        telaVenda.getCbUsuario().setModel(model);
    }

    public void adicionarItemVendaAction() {
        itemVenda = new ItemVenda();
        itemVenda.setId(1);
        itemVenda.setQuantidade(Integer.valueOf(telaVenda.getTfQuantidade().getText()));
        itemVenda.setValorProduto(Double.valueOf(telaVenda.getTfValor().getText()));
        itemVenda.setProduto(vendaProdutoTableModel.pegaObjeto(telaVenda.getTblProduto().getSelectedRow()));
        listItemVendas.add(itemVenda);
        itemVendaTableModel.adicionar(itemVenda);
        itemVenda = null;
    }

    public void removerItemVendaAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_REMOVER_ITEM_VENDA);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        itemVenda = itemVendaTableModel.pegaObjeto(telaVenda.getTblVenda().getSelectedRow());
        listItemVendas.remove(itemVenda);
        itemVendaTableModel.remover(telaVenda.getTblVenda().getSelectedRow());
        Mensagem.info(Texto.SUCESSO_REMOVER);
        itemVenda = null;
    }

    public void chamarDialogVendaReceitaAction() {
        telaVendaReceita = new TelaVendaReceita(telaVenda, true, this);
        telaVendaReceita.setVisible(true);
    }

    public void adicionarVendaAction() {
        venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        venda.setCliente((Cliente) telaVenda.getCbCliente().getSelectedItem());
        venda.setUsuario((Usuario) telaVenda.getCbUsuario().getSelectedItem());

        if (Validacao.validaEntidade(venda) != null) {
            Mensagem.info(Validacao.validaEntidade(venda));
            venda = null;
            return;
        }

        Integer idVendaInserida = vendaDao.inserir(venda);

        if (idVendaInserida == 0) {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
            return;
        }

        venda.setId(idVendaInserida);
        for (int i = 0; i < listItemVendas.size(); i++) {
            ItemVenda umItemVendaDaTabela = listItemVendas.get(i);
            umItemVendaDaTabela.setVenda(venda);

            Produto produtoDoItemVenda = produtoDao.pesquisar(umItemVendaDaTabela.getProduto().getId());

            produtoDoItemVenda.setQuantidade(produtoDoItemVenda.getQuantidade() - umItemVendaDaTabela.getQuantidade());
            produtoDao.alterar(produtoDoItemVenda);
            produtoDoItemVenda.setValor(umItemVendaDaTabela.getValorProduto());

            umItemVendaDaTabela.setProduto(produtoDoItemVenda);
            itemVendaDao.inserir(umItemVendaDaTabela);
        }

        try {

            Date dataVencimento = UtilDate.data(telaVendaReceita.getTfDataVencimento().getText());
            Double valorTotalRecebimento = Double.valueOf(telaVendaReceita.getTfValorRecebimento().getText());

            receitaGerenciarControl = new TelaReceitaGerenciarControl();
            receitaGerenciarControl.criarReceita(idVendaInserida, dataVencimento, valorTotalRecebimento);
        } catch (Exception exception) {
            Mensagem.erro(Texto.ERRO_COVERTER_CAMPO_DATA);
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Itens gravados com sucesso");
        venda.setItemVendas(listItemVendas);
    }

    public void pesquisarProdutoAction() {
        List<Produto> produtosPesquisados = produtoDao.pesquisar(telaVenda.getTfPesquisaProduto().getText());
        if (produtosPesquisados == null) {
            vendaProdutoTableModel.limpar();
            produtosPesquisados = produtoDao.pesquisar();
        } else {
            vendaProdutoTableModel.limpar();
            vendaProdutoTableModel.adicionar(produtosPesquisados);
        }
    }

    public void carregaProdutoSelecionadoAction(int row) {
        produto = vendaProdutoTableModel.pegaObjeto(row);
        telaVenda.getTfQuantidade().setText(String.valueOf(produto.getQuantidade()));
        telaVenda.getTfValor().setText(String.valueOf(produto.getValor()));
    }

    public void chamarDialogClienteAction() {
        telaClienteDialogPesquisar = new TelaClienteDialogPesquisar(telaVenda, true, this);
        telaClienteDialogPesquisar.setVisible(true);
    }

}
