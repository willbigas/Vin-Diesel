package br.com.mercado.control;

import br.com.mercado.dao.EntradaDao;
import br.com.mercado.dao.FornecedorDao;
import br.com.mercado.dao.ItemEntradaDao;
import br.com.mercado.dao.ProdutoDao;
import br.com.mercado.model.Entrada;
import br.com.mercado.model.Fornecedor;
import br.com.mercado.model.ItemEntrada;
import br.com.mercado.model.Produto;
import br.com.mercado.model.tablemodel.EntradaProdutoTableModel;
import br.com.mercado.model.tablemodel.ItemEntradaTableModel;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;
import br.com.mercado.uteis.UtilDate;
import br.com.mercado.uteis.Validacao;
import br.com.mercado.view.TelaEntradaDespesa;
import br.com.mercado.view.TelaEntradaGerenciar;
import br.com.mercado.view.TelaPrincipal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaEntradaGerenciarControl {

    private TelaEntradaGerenciar telaEntradaGerenciar = null;
    private TelaEntradaDespesa telaEntradaDespesa;
    private EntradaProdutoTableModel entradaProdutoTableModel;
    private ItemEntradaTableModel itemEntradaTableModel;
    private List<Fornecedor> listFornecedores;
    private List<ItemEntrada> listItemEntradas;
    private FornecedorDao fornecedorDao;
    private ProdutoDao produtoDao;
    private EntradaDao entradaDao;
    private ItemEntradaDao itemEntradaDao;
    private ItemEntrada itemEntrada;
    private Entrada entrada;
    private TelaDespesaGerenciarControl despesaGerenciarControl;

    public TelaEntradaGerenciarControl() {
        fornecedorDao = new FornecedorDao();
        entradaDao = new EntradaDao();
        produtoDao = new ProdutoDao();
        itemEntradaDao = new ItemEntradaDao();
        entradaProdutoTableModel = new EntradaProdutoTableModel();
        itemEntradaTableModel = new ItemEntradaTableModel();
        listItemEntradas = new ArrayList<>();
    }

    public void chamarTelaEntradaGerenciar() {
        if (telaEntradaGerenciar == null) { // se tiver nulo chama janela normalmente
            telaEntradaGerenciar = new TelaEntradaGerenciar(this);
            TelaPrincipal.desktopPane.add(telaEntradaGerenciar);
            telaEntradaGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaEntradaGerenciar.isVisible()) {
                telaEntradaGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaEntradaGerenciar);
                telaEntradaGerenciar.setVisible(true);
            }
        }
        carregarFornecedoresNaCombo();
        telaEntradaGerenciar.getTblProduto().setModel(entradaProdutoTableModel);
        entradaProdutoTableModel.limpar();
        entradaProdutoTableModel.adicionar(produtoDao.pesquisar());
        telaEntradaGerenciar.getTblEntrada().setModel(itemEntradaTableModel);
    }

    private void carregarFornecedoresNaCombo() {
        listFornecedores = fornecedorDao.pesquisar();
        DefaultComboBoxModel<Fornecedor> model = new DefaultComboBoxModel(listFornecedores.toArray());
        telaEntradaGerenciar.getCbFornecedor().setModel(model);
    }

    /**
     * Adiciona os itens de entrada somente em memoria e na interface do usuário
     */
    public void adicionarItemEntradaAction() {

        itemEntrada = new ItemEntrada();
        itemEntrada.setId(1);
        itemEntrada.setNumeroLote(Integer.valueOf(telaEntradaGerenciar.getTfLote().getText()));
        itemEntrada.setQuantidade(Integer.valueOf(telaEntradaGerenciar.getTfQuantidade().getText()));
        itemEntrada.setValorProduto(Double.valueOf(telaEntradaGerenciar.getTfValor().getText()));
        itemEntrada.setProduto(entradaProdutoTableModel.pegaObjeto(telaEntradaGerenciar.getTblProduto().getSelectedRow()));
        listItemEntradas.add(itemEntrada);
        itemEntradaTableModel.adicionar(itemEntrada);

        if (Validacao.validaEntidade(itemEntrada) != null) {
            Mensagem.info(Validacao.validaEntidade(itemEntrada));
            itemEntrada = null;
            return;
        }

        itemEntrada = null;
    }

    public void removerItemEntradaAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_REMOVER_ITEM_ENTRADA);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }

        itemEntrada = itemEntradaTableModel.pegaObjeto(telaEntradaGerenciar.getTblEntrada().getSelectedRow());
        listItemEntradas.remove(itemEntrada);
        itemEntradaTableModel.remover(telaEntradaGerenciar.getTblEntrada().getSelectedRow());
        Mensagem.info(Texto.SUCESSO_REMOVER);
        itemEntrada = null;

    }

    public void chamarDialogEntradaDespesaAction() {
        telaEntradaDespesa = new TelaEntradaDespesa(telaEntradaGerenciar, true, this);
        telaEntradaDespesa.setVisible(true);
    }

    /**
     * Itens de Entrada em memória (Atenção!)
     *
     * Nesta função eu recupero os itens da tabela pré inseridos em memória e
     * insiro uma entrada no banco e adiciono aos itens o id da entrada inserida
     * logo depois eu pesquiso o produto do Item entrada no banco.
     *
     * Desta forma consigo recuperar pelos itens de entrada a entrada realizada
     * e o produto deste item , permitindo assim alterar qualquer coisa prevista
     * como quantidade do estoque e preço do item
     *
     */
    public void adicionarEntradaAction() {
        entrada = new Entrada();
        entrada.setDataEntrada(LocalDateTime.now());
        entrada.setFornecedor((Fornecedor) telaEntradaGerenciar.getCbFornecedor().getSelectedItem());
        
           if (Validacao.validaEntidade(entrada) != null) {
            Mensagem.info(Validacao.validaEntidade(entrada));
            entrada = null;
            return;
        }
        Integer idEntradaInserida = entradaDao.inserir(entrada);

        if (idEntradaInserida == 0) {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
            return;
        }

        entrada.setId(idEntradaInserida);
        for (int i = 0; i < listItemEntradas.size(); i++) {
            ItemEntrada umItemEntradaDaTabela = listItemEntradas.get(i);
            umItemEntradaDaTabela.setEntrada(entrada);

            Produto produtoDoItemEntrada = produtoDao.pesquisar(umItemEntradaDaTabela.getProduto().getId());

            produtoDoItemEntrada.setQuantidade(produtoDoItemEntrada.getQuantidade() + umItemEntradaDaTabela.getQuantidade()); // soma a quantidade do item
            produtoDoItemEntrada.setValor(umItemEntradaDaTabela.getValorProduto()); // modifica preço do produto pela entrada
            produtoDoItemEntrada.setFornecedor(entrada.getFornecedor()); // modifica fornecedor , se houver mudança de CNPJ (Viabilizar lista de fornecedores depois)
            produtoDao.alterar(produtoDoItemEntrada);

            umItemEntradaDaTabela.setProduto(produtoDoItemEntrada);
            itemEntradaDao.inserir(umItemEntradaDaTabela);

        }

        try {

            Date dataVencimento = UtilDate.data(telaEntradaDespesa.getTfDataVencimento().getText());
            Double valorTotalPagamento = Double.valueOf(telaEntradaDespesa.getTfValorPagamento().getText());
            System.out.println("Data de Vencimento " + dataVencimento);
            System.out.println("Valor total para pagamento" + valorTotalPagamento);

            despesaGerenciarControl = new TelaDespesaGerenciarControl();
            despesaGerenciarControl.criarDespesa(idEntradaInserida, dataVencimento, valorTotalPagamento);
        } catch (Exception exception) {
            Mensagem.erro(Texto.ERRO_COVERTER_CAMPO_DATA);
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Itens gravados com sucesso");

        entrada.setItensEntrada(listItemEntradas);
    }

}
