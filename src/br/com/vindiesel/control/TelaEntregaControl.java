package br.com.vindiesel.control;

import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.dao.EncomendaDao;
import br.com.vindiesel.dao.EnderecoDao;
import br.com.vindiesel.dao.EntregaDao;
import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.dao.TramiteDao;
import br.com.vindiesel.exceptions.BuscaCepException;
import br.com.vindiesel.interfaces.BuscaCepEventos;
import br.com.vindiesel.interfaces.BuscaCepEventosImpl;
import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.model.EnderecoSigla;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.model.tablemodel.EntregaTableModel;
import br.com.vindiesel.model.tablemodel.TramiteTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaDestinatarioDialogPesquisar;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaEntrega;
import br.com.vindiesel.view.TelaEntregaReceita;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Will
 */
public class TelaEntregaControl {

    TelaEntrega telaEntrega;
    TelaEntregaReceita telaEntregaReceita;
    TelaDestinatarioDialogPesquisar telaDestinatarioDialogPesquisar;
    TelaReceitaGerenciarControl receitaGerenciarControl;
    DistanciaCalculoControl calculoDeDistancia;
    TramiteTableModel tramiteTableModel;
    EntregaTableModel entregaTableModel;
    DestinatarioDao destinatarioDao;
    EncomendaDao encomendaDao;
    RemetenteDao remetenteDao;
    EntregaDao entregaDao;
    TramiteDao tramiteDao;
    EnderecoDao enderecoDao;
    List<Encomenda> listEncomendas;
    List<Remetente> listRemetentes;
    Entrega entrega;
    Destinatario destinatario;
    Encomenda encomenda;
    Endereco endereco;

    public TelaEntregaControl() {
        calculoDeDistancia = new DistanciaCalculoControl();
        entregaTableModel = new EntregaTableModel();
        destinatarioDao = new DestinatarioDao();
        encomendaDao = new EncomendaDao();
        remetenteDao = new RemetenteDao();
        entregaDao = new EntregaDao();
        tramiteDao = new TramiteDao();
        enderecoDao = new EnderecoDao();
        listEncomendas = new ArrayList<>();

    }

    public void chamarTelaEntrega() {
        if (telaEntrega == null) {
            telaEntrega = new TelaEntrega(this);
            TelaPrincipal.desktopPane.add(telaEntrega);
            telaEntrega.setVisible(true);
        } else {
            if (telaEntrega.isVisible()) {
                telaEntrega.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaEntrega);
                telaEntrega.setVisible(true);
            }
        }
        carregarEstadosNaComboBox();
        carregarEncomendasNaCombo();
        carregarRemetentesNaCombo();
        telaEntrega.getTblEntrega().setModel(entregaTableModel);
        entregaTableModel.limpar();
        entregaTableModel.adicionar(entregaDao.pesquisar());

    }

    public void carregarEstadosNaComboBox() {
        telaEntrega.getCbEstado().setModel(new DefaultComboBoxModel<>(EnderecoSigla.ESTADOS_BRASILEIROS));
    }

    private void carregarEncomendasNaCombo() {
        listEncomendas = encomendaDao.pesquisar();
        DefaultComboBoxModel<Encomenda> model = new DefaultComboBoxModel(listEncomendas.toArray());
        telaEntrega.getCbEncomenda().setModel(model);
    }

    private void carregarRemetentesNaCombo() {
        listRemetentes = remetenteDao.pesquisar();
        DefaultComboBoxModel<Remetente> model = new DefaultComboBoxModel(listRemetentes.toArray());
        telaEntrega.getCbRemetente().setModel(model);
    }

    public void chamarDialogEntregaReceitaAction() {
        telaEntregaReceita = new TelaEntregaReceita(telaEntrega, true, this);
        telaEntregaReceita.setVisible(true);
    }

    public void adicionarEntregaAction() {
        entrega = new Entrega();
        entrega.setDataCadastro(LocalDateTime.now());
        entrega.setDataEntrega(null);
        entrega.setEntregue(false);
        entrega.setRemetente((Remetente) telaEntrega.getCbRemetente().getSelectedItem());
        entrega.setEncomenda((Encomenda) telaEntrega.getCbEncomenda().getSelectedItem());

        destinatario = new Destinatario();
        destinatario.setCodigoPessoa(telaEntrega.getTfCodigoPessoa().getText());
        destinatario.setNome(telaEntrega.getTfNome().getText());

        endereco = new Endereco();
        endereco.setBairro(telaEntrega.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaEntrega.getTfCep().getText()));
        endereco.setCidade(telaEntrega.getTfCidade().getText());
        endereco.setComplemento(telaEntrega.getTfComplemento().getText());
        endereco.setEstado((String) telaEntrega.getCbEstado().getSelectedItem());
        endereco.setNumero(telaEntrega.getTfNumero().getText());
        endereco.setRua(telaEntrega.getTfRua().getText());

        if (Validacao.validaEntidade(destinatario) != null) {
            Mensagem.info(Validacao.validaEntidade(destinatario));
            destinatario = null;
            endereco = null;
            return;
        }

        Integer idEndereco = enderecoDao.inserir(endereco);
        endereco.setId(idEndereco);

        destinatario.setEndereco(endereco);

        Integer idInserido = destinatarioDao.inserir(destinatario);
        if (idInserido != 0) {
            destinatario.setId(idInserido);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
            entrega = null;
            endereco = null;
            destinatario = null;
            return;
        }

        entrega.setDestinatario(destinatario);

        Double valorFrete = calcularFrete(entrega.getRemetente(), entrega.getDestinatario(), entrega);

        entrega.setValorTotal(valorFrete);

        // atributos de entrega 
        if (Validacao.validaEntidade(entrega) != null) {
            Mensagem.info(Validacao.validaEntidade(entrega));
            entrega = null;
            endereco = null;
            destinatario = null;
            return;
        }

        Integer idEntregaInserida = entregaDao.inserir(entrega);

        if (idEntregaInserida != 0) {
            entrega.setId(idEntregaInserida);
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }

        Date dataVencimento = UtilDate.data(telaEntregaReceita.getTfDataVencimento().getText());
        receitaGerenciarControl = new TelaReceitaGerenciarControl();
        receitaGerenciarControl.criarReceita(entrega, dataVencimento, valorFrete);

        destinatario = null;
        entrega = null;
        endereco = null;

    }

    public void chamarDialogRemetenteAction() {
        telaDestinatarioDialogPesquisar = new TelaDestinatarioDialogPesquisar(telaEntrega, true, this);
        telaDestinatarioDialogPesquisar.setVisible(true);
    }

    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscaCepControl buscadorDeCep = new BuscaCepControl();
        try {
            buscadorDeCep.buscar(telaEntrega.getTfCep().getText());
            Endereco endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());
            System.out.println("Endereco encontrado" + endereco);

            // mostra na tela o cep pesquisado
            telaEntrega.getTfBairro().setText(endereco.getBairro());
            telaEntrega.getTfCidade().setText(endereco.getCidade());
            telaEntrega.getTfComplemento().setText(endereco.getComplemento());
            telaEntrega.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaEntrega.getTfRua().setText(endereco.getRua());
            telaEntrega.getTfCep().setText(telaEntrega.getTfCep().getText());
        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }

    private Double calcularFrete(Remetente remetente, Destinatario destinatario, Entrega entrega) {
        String distanciaEmKm = calculoDeDistancia.calculaDistanciaEmKm(String.valueOf(remetente.getEndereco().getCep()), String.valueOf(destinatario.getEndereco().getCep()));
        System.out.println("Distancia em km :" + distanciaEmKm);
        Double valorTotalFrete;
        Double valorDimensao;
        Double valorEntrega;

        Double comprimento = entrega.getEncomenda().getDimensao().getComprimento();
        Double altura = entrega.getEncomenda().getDimensao().getLargura();
        Double largura = entrega.getEncomenda().getDimensao().getAltura();

        valorDimensao = (comprimento * largura * altura) / 6000;
        valorDimensao = valorDimensao * 1.5;

        valorEntrega = Double.valueOf(distanciaEmKm) / 20;

        valorTotalFrete = valorDimensao + valorEntrega + 0.015;
        System.out.println(valorTotalFrete);
        return valorTotalFrete;
    }

}
