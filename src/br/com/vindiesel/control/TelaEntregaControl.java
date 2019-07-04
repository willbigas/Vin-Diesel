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
import br.com.vindiesel.model.Tramite;
import br.com.vindiesel.model.tablemodel.DestinatarioTableModel;
import br.com.vindiesel.model.tablemodel.EncomendaTableModel;
import br.com.vindiesel.model.tablemodel.EntregaTableModel;
import br.com.vindiesel.model.tablemodel.RemetenteTableModel;
import br.com.vindiesel.model.tablemodel.TramiteTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.DecimalFormat;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaDestinatarioPesquisaAvancada;
import br.com.vindiesel.view.TelaEncomendaPesquisaAvancada;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaEntrega;
import br.com.vindiesel.view.TelaEntregaFicha;
import br.com.vindiesel.view.TelaEntregaReceitaDialog;
import br.com.vindiesel.view.TelaEntregaFreteNaoEncontrado;
import br.com.vindiesel.view.TelaRemetentePesquisaAvancada;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Will
 */
public class TelaEntregaControl {

    private static final int CODIGO_RASTREIO = 0;
    private static final int CADASTRO = 1;
    private static final int ENTREGA = 2;
    private static final int FRETE = 3;
    private static final int REMETENTE = 4;
    private static final int DESTINATARIO = 5;

    TelaEntrega telaEntrega;
    TelaEntregaFicha telaEntregaFicha;
    TelaEntregaReceitaDialog telaEntregaReceita;
    TelaDestinatarioPesquisaAvancada telaDestinatarioPesquisaAvancada;
    TelaRemetentePesquisaAvancada telaRemetentePesquisaAvancada;
    TelaEncomendaPesquisaAvancada telaEncomendaPesquisaAvancada;
    TelaEntregaFreteNaoEncontrado telaFreteNaoEncontrado;
    TelaReceitaGerenciarControl receitaGerenciarControl;
    TramiteControl tramiteControl;
    CalculadoraDeFrete calculoDeDistancia;
    TramiteTableModel tramiteTableModel;
    EntregaTableModel entregaTableModel;
    DestinatarioTableModel destinatarioTableModel;
    RemetenteTableModel remetenteTableModel;
    EncomendaTableModel encomendaTableModel;
    DestinatarioDao destinatarioDao;
    EncomendaDao encomendaDao;
    RemetenteDao remetenteDao;
    EntregaDao entregaDao;
    TramiteDao tramiteDao;
    EnderecoDao enderecoDao;
    List<Encomenda> listEncomendas;
    List<Remetente> listRemetentes;
    Double valorFreteManual = 0.0;
    Entrega entrega;
    Destinatario destinatario;
    Boolean novoDestinatario = false;
    Remetente remetente;
    Encomenda encomenda;
    Tramite tramite;
    Endereco endereco;
    MaskFormatter mascaraFormatadoraCPF;
    MaskFormatter mascaraFormatadoraCNPJ;

    public TelaEntregaControl() {
        calculoDeDistancia = new CalculadoraDeFrete();
        tramiteControl = new TramiteControl();
        entregaTableModel = new EntregaTableModel();
        tramiteTableModel = new TramiteTableModel();
        destinatarioTableModel = new DestinatarioTableModel();
        remetenteTableModel = new RemetenteTableModel();
        encomendaTableModel = new EncomendaTableModel();
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
        telaEntrega.getTblTramite().setModel(tramiteTableModel);
        atualizarTabelaEntregaAction();
        atualizaTotaisDeFrete(entregaDao.pesquisar());
        redimensionarTabelaEntregas();
        telaEntrega.getTpEntrega().setEnabledAt(1, false);
        telaEntrega.getTfPesquisarEntrega().requestFocus();
        criaInstanciasDeMascarasFormatadas();
    }

    public void atualizarTabelaEntregaAction() {
        entregaTableModel.limpar();
        entregaTableModel.adicionar(entregaDao.pesquisar());
    }

    private void redimensionarTabelaEntregas() {
        UtilTable.centralizarCabecalho(telaEntrega.getTblEntrega());
        UtilTable.redimensionar(telaEntrega.getTblEntrega(), CODIGO_RASTREIO, 100);
        UtilTable.redimensionar(telaEntrega.getTblEntrega(), CADASTRO, 90);
        UtilTable.redimensionar(telaEntrega.getTblEntrega(), ENTREGA, 100);
        UtilTable.redimensionar(telaEntrega.getTblEntrega(), FRETE, 95);
        UtilTable.redimensionar(telaEntrega.getTblEntrega(), REMETENTE, 290);
        UtilTable.redimensionar(telaEntrega.getTblEntrega(), DESTINATARIO, 290);
    }

    public void chamarDialogEntregaFichaAction() {
        telaEntregaFicha = new TelaEntregaFicha(telaEntrega, true, this);
        carregarDestinatarioJdialogFicha();
        carregarRemetenteJdialogFicha();
        carregarEncomendaJdialogFicha();
        telaEntregaFicha.setVisible(true);
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
        telaEntregaReceita = new TelaEntregaReceitaDialog(telaEntrega, true, this);
        telaEntregaReceita.setVisible(true);
    }

    public void adicionarEntregaAction() {
        entrega = new Entrega();
        setarAtributosDeEntrega();
        verificarSeEhUmNovoDestinatario();
        setarAtributosDeDestinatario();

        try {
            setarAtributosDeEndereco();

        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_CEP);
        }

        if (Validacao.validaEntidade(destinatario) != null) {
            Mensagem.info(Validacao.validaEntidade(destinatario));
            return;
        }

        if (novoDestinatario) {
            if (inserirDestinatarioNoBanco()) {
                return;
            }
        } else {
            if (alterarDestinatarioNoBanco()) {
                return;
            }
        }

        entrega.setDestinatario(destinatario);

        Double valorFrete = calcularFrete(entrega.getRemetente(), entrega.getDestinatario(), entrega);

        if (valorFrete == null) {
            chamarDialogFreteNaoEncontrado(entrega);
            valorFrete = valorFreteManual;
        }

        entrega.setValorTotal(valorFrete);

        // atributos de entrega 
        if (Validacao.validaEntidade(entrega) != null) {
            Mensagem.info(Validacao.validaEntidade(entrega));
            return;
        }

        Integer idEntregaInserida = entregaDao.inserir(entrega);

        if (idEntregaInserida != 0) {
            entrega.setId(idEntregaInserida);
            entregaTableModel.adicionar(entrega);
            atualizaTotaisDeFrete(entregaDao.pesquisar());
            Mensagem.info(Texto.SUCESSO_CADASTRAR_ENTREGA);

        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }

        int idTramiteInserido = tramiteControl.adicionarTramite(entrega, "VIN DIESEL", "Encomenda foi registrada para entrega na transportadora", 3);

        List<Tramite> tramites = new ArrayList<>();
        tramites.add(tramiteDao.pesquisar(idTramiteInserido));
        entrega.setTramites(tramites);

        Date dataVencimento = telaEntregaReceita.getTfDataVencimento().getDate();
        receitaGerenciarControl = new TelaReceitaGerenciarControl();
        boolean receitaInserida = receitaGerenciarControl.criarReceita(entrega, dataVencimento, valorFrete);
        if (receitaInserida) {
            destinatario = null;
            entrega = null;
            endereco = null;
            limparCamposTabEntrega();
        }

    }

    private boolean alterarDestinatarioNoBanco() {
        enderecoDao.alterar(endereco);
        destinatario.setEndereco(endereco);
        Boolean destinatarioAlterado = destinatarioDao.alterar(destinatario);
        if (!destinatarioAlterado) {
            Mensagem.info(Texto.ERRO_CADASTRAR);
            return true;
        }
        return false;
    }

    private boolean inserirDestinatarioNoBanco() {
        Integer idEndereco = enderecoDao.inserir(endereco);
        endereco.setId(idEndereco);
        destinatario.setEndereco(endereco);
        Integer idInserido = destinatarioDao.inserir(destinatario);
        if (idInserido != 0) {
            destinatario.setId(idInserido);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
            return true;
        }
        return false;
    }

    private void setarAtributosDeDestinatario() {
        destinatario.setCodigoPessoa(telaEntrega.getTfCodigoPessoa().getText());
        destinatario.setNome(telaEntrega.getTfNome().getText());
    }

    private void verificarSeEhUmNovoDestinatario() {
        if (destinatario == null) {
            novoDestinatario = true;
            destinatario = new Destinatario();
            endereco = new Endereco();
        } else {
            endereco = destinatario.getEndereco();
        }
    }

    private void setarAtributosDeEntrega() {
        entrega.setDataCadastro(new Timestamp(System.currentTimeMillis()));
        entrega.setDataEntrega(null);
        entrega.setEntregue(false);
        entrega.setRemetente((Remetente) telaEntrega.getCbRemetente().getSelectedItem());
        entrega.setEncomenda((Encomenda) telaEntrega.getCbEncomenda().getSelectedItem());
    }

    private void setarAtributosDeEndereco() throws NumberFormatException {
        endereco.setBairro(telaEntrega.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaEntrega.getTfCep().getText()));
        endereco.setCidade(telaEntrega.getTfCidade().getText());
        endereco.setComplemento(telaEntrega.getTfComplemento().getText());
        endereco.setEstado((String) telaEntrega.getCbEstado().getSelectedItem());
        endereco.setNumero(telaEntrega.getTfNumero().getText());
        endereco.setRua(telaEntrega.getTfRua().getText());
    }

    public void chamarDialogPesquisaAvancadaDestinatarioAction() {
        telaDestinatarioPesquisaAvancada = new TelaDestinatarioPesquisaAvancada(telaEntrega, true, this);
        telaDestinatarioPesquisaAvancada.getTblDestinatario().setModel(destinatarioTableModel);
        destinatarioTableModel.limpar();
        destinatarioTableModel.adicionar(destinatarioDao.pesquisar());
        telaDestinatarioPesquisaAvancada.setVisible(true);
    }

    public void chamarDialogPesquisaAvancadaRemetenteAction() {
        telaRemetentePesquisaAvancada = new TelaRemetentePesquisaAvancada(telaEntrega, true, this);
        telaRemetentePesquisaAvancada.getTblRemetente().setModel(remetenteTableModel);
        remetenteTableModel.limpar();
        remetenteTableModel.adicionar(remetenteDao.pesquisar());
        telaRemetentePesquisaAvancada.setVisible(true);
    }

    public void chamarDialogPesquisaAvancadaEncomendaAction() {
        telaEncomendaPesquisaAvancada = new TelaEncomendaPesquisaAvancada(telaEntrega, true, this);
        telaEncomendaPesquisaAvancada.getTblEncomenda().setModel(encomendaTableModel);
        encomendaTableModel.limpar();
        encomendaTableModel.adicionar(encomendaDao.pesquisar());
        telaEncomendaPesquisaAvancada.setVisible(true);
    }

    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscadorDeCepControl buscadorDeCep = new BuscadorDeCepControl();
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
            telaEntrega.getTfNumero().requestFocus();
        } catch (NumberFormatException numberFormatException) {
            Mensagem.erro(Texto.ERRO_COVERTER_CAMPO_CEP);
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
            return;
        } catch (BuscaCepException buscaCepException) {
            Mensagem.erro(Texto.ERRO_CEP_NAO_ENCONTRADO);
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
            return;
        } catch (Exception exception) {
            Mensagem.erro(Texto.ERRO_CEP_GENERICO);
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return;
        }
    }

    private Double calcularFrete(Remetente remetente, Destinatario destinatario, Entrega entrega) {

        System.out.println("Cep do Remetente :" + remetente.getEndereco().getCep());
        System.out.println("Cep do Destinatario :" + destinatario.getEndereco().getCep());

        String distanciaEmKm = calculoDeDistancia.calculaDistanciaEmKm(String.valueOf(remetente.getEndereco().getCep()), String.valueOf(destinatario.getEndereco().getCep()));

        if (distanciaEmKm == null) {
            return null;
        }

        Double valorTotalFrete;
        Double valorDimensao;
        Double valorEntrega;

        Double comprimento = entrega.getEncomenda().getDimensao().getComprimento();
        Double altura = entrega.getEncomenda().getDimensao().getLargura();
        Double largura = entrega.getEncomenda().getDimensao().getAltura();

        valorDimensao = (comprimento * largura * altura) / 6000;
        valorDimensao = valorDimensao * 1.5;

        valorEntrega = Double.valueOf(DecimalFormat.paraPonto(distanciaEmKm)) / 20;

        valorTotalFrete = valorDimensao + valorEntrega + 0.015;
        System.out.println(valorTotalFrete);
        return valorTotalFrete;
    }

    public void listarTramitesDeUmaEntregaAction() {
        if (telaEntrega.getTblEntrega().getSelectedRow() == -1) {
            Mensagem.info(Texto.SELECIONADA_LINHA);
            return;
        }

        entrega = entregaTableModel.pegaObjeto(telaEntrega.getTblEntrega().getSelectedRow());
        tramiteTableModel.limpar();
        tramiteTableModel.adicionar(tramiteDao.pesquisarTramitesPorEntrega(entrega));
        telaEntrega.getTpEntrega().setEnabledAt(1, true);
        telaEntrega.getTpEntrega().setSelectedIndex(1);
    }

    public void adicionarTramitesDeUmaEntregaAction() {
        int cbSelecionada = telaEntrega.getCbTipoTramite().getSelectedIndex();

        if (cbSelecionada == 2) {
            int retorno = Mensagem.confirmacao(Texto.ATENCAO_FINALIZAR_ENTREGA);

            if (retorno == JOptionPane.NO_OPTION) {
                return;
            }

            if (retorno == JOptionPane.CLOSED_OPTION) {
                return;
            }

            entrega.setEntregue(true);
            entrega.setDataEntrega(new Date(System.currentTimeMillis()));
            entregaDao.alterar(entrega);
        }

        int idTramiteInserido = tramiteControl.adicionarTramite(entrega, telaEntrega.getTfNomeTramite().getText(),
                telaEntrega.getTfObservacaoTramite().getText(), cbSelecionada + 1);
        if (idTramiteInserido == 0) {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
            tramite = null;
            return;
        }

        tramite = tramiteDao.pesquisar(idTramiteInserido);
        tramiteTableModel.adicionar(tramite);
        Mensagem.info(Texto.SUCESSO_CADASTRAR);
        tramite = null;

    }

    public void removerTramitesDeUmaEntregaAction() {
        int linhaSelecionada = telaEntrega.getTblTramite().getSelectedRow();
        tramite = tramiteTableModel.pegaObjeto(telaEntrega.getTblTramite().getSelectedRow());
        boolean removido = tramiteControl.removerTramite(tramite);
        if (!removido) {
            tramite = null;
            Mensagem.erro(Texto.ERRO_DELETAR);
            return;
        }
        tramiteTableModel.remover(linhaSelecionada);
        Mensagem.info(Texto.SUCESSO_DELETAR);
        tramite = null;

    }

    public void resetarEntregaETramitesAction() {
        entrega = null;
        tramite = null;
        telaEntrega.getTpEntrega().setEnabledAt(1, false);
    }

    public void limparCamposTabEntrega() {
        telaEntrega.getCbRemetente().setSelectedIndex(0);
        telaEntrega.getCbEncomenda().setSelectedIndex(0);
        telaEntrega.getTfPesquisarEntrega().setText("");
        telaEntrega.getTfCodigoPessoa().setText("");
        telaEntrega.getTfNome().setText("");
        telaEntrega.getTfCep().setText("");
        telaEntrega.getTfCidade().setText("");
        telaEntrega.getTfBairro().setText("");
        telaEntrega.getTfComplemento().setText("");
        telaEntrega.getCbEstado().setSelectedIndex(0);
        telaEntrega.getTfNumero().setText("");
        telaEntrega.getTfRua().setText("");

        UtilTable.limparSelecaoDaTabela(telaEntrega.getTblEntrega());
    }

    private void criaInstanciasDeMascarasFormatadas() {
        try {
            mascaraFormatadoraCPF = new javax.swing.text.MaskFormatter("###.###.###-##");
            mascaraFormatadoraCNPJ = new javax.swing.text.MaskFormatter("##.###.###/####-##");
        } catch (ParseException parseException) {
            Mensagem.erro(Texto.ERRO_CONVERTER_CAMPO_MASCARA_CNPJ);
        }

    }

    public void formataTfCodigoPessoaParaCNPJ() {
        mascaraFormatadoraCNPJ.install(telaEntrega.getTfCodigoPessoa());
    }

    public void formataTfCodigoPessoaParaCPF() {
        mascaraFormatadoraCPF.install(telaEntrega.getTfCodigoPessoa());

    }

    public void carregaDadosDestinatarioDoDialogPesquisaAvancadaAction() {
        destinatario = destinatarioTableModel.pegaObjeto(telaDestinatarioPesquisaAvancada.getTblDestinatario().getSelectedRow());
        if (destinatario.getCodigoPessoa().length() > 15) {
            formataTfCodigoPessoaParaCNPJ();
            telaEntrega.getCheckCnpj().setSelected(true);
        } else {
            formataTfCodigoPessoaParaCPF();
            telaEntrega.getCheckCpf().setSelected(true);
        }
        popularDadosDeDestinatario();
    }

    private void popularDadosDeDestinatario() {
        telaEntrega.getTfCodigoPessoa().setText(destinatario.getCodigoPessoa());
        telaEntrega.getTfNome().setText(destinatario.getNome());
        telaEntrega.getTfBairro().setText(destinatario.getEndereco().getBairro());
        telaEntrega.getTfCidade().setText(destinatario.getEndereco().getCidade());
        telaEntrega.getTfComplemento().setText(destinatario.getEndereco().getComplemento());
        telaEntrega.getCbEstado().getModel().setSelectedItem(destinatario.getEndereco().getEstado());
        telaEntrega.getTfRua().setText(destinatario.getEndereco().getRua());
        telaEntrega.getTfNumero().setText(destinatario.getEndereco().getNumero());
        telaEntrega.getTfCep().setText(String.valueOf(destinatario.getEndereco().getCep()));
    }

    public void pesquisarEntregasAction() {
        List<Entrega> entregasPesquisadas = entregaDao.pesquisar(telaEntrega.getTfPesquisarEntrega().getText());
        if (entregaTableModel == null) {
            entregaTableModel.limpar();
            atualizaTotaisDeFrete(entregasPesquisadas);
            entregasPesquisadas = entregaDao.pesquisar();
        } else {
            entregaTableModel.limpar();
            entregaTableModel.adicionar(entregasPesquisadas);
            atualizaTotaisDeFrete(entregasPesquisadas);
        }
    }

    public void pesquisarDestinatariosNoDialogPesquisaAvancadaAction() {
        List<Destinatario> destinatariosPesquisados = destinatarioDao.pesquisar(telaDestinatarioPesquisaAvancada.getTfCampoPesquisa().getText());
        if (destinatariosPesquisados == null) {
            destinatarioTableModel.limpar();
            destinatariosPesquisados = destinatarioDao.pesquisar();
        } else {
            destinatarioTableModel.limpar();
            destinatarioTableModel.adicionar(destinatariosPesquisados);
        }
    }

    public void carregaDadosRemetenteDoDialogPesquisaAvancadaAction() {
        remetente = remetenteTableModel.pegaObjeto(telaRemetentePesquisaAvancada.getTblRemetente().getSelectedRow());
        telaEntrega.getCbRemetente().getModel().setSelectedItem(remetente);
    }

    public void pesquisarRemetentesNoDialogPesquisaAvancadaAction() {
        List<Remetente> remetentesPesquisados = remetenteDao.pesquisar(telaRemetentePesquisaAvancada.getTfCampoPesquisa().getText());
        if (remetentesPesquisados == null) {
            remetenteTableModel.limpar();
            remetentesPesquisados = remetenteDao.pesquisar();
        } else {
            remetenteTableModel.limpar();
            remetenteTableModel.adicionar(remetentesPesquisados);
        }
    }

    public void carregaDadosEncomendaDoDialogPesquisaAvancadaAction() {
        encomenda = encomendaTableModel.pegaObjeto(telaEncomendaPesquisaAvancada.getTblEncomenda().getSelectedRow());
        telaEntrega.getCbEncomenda().getModel().setSelectedItem(encomenda);
    }

    public void pesquisarEncomendasNoDialogPesquisaAvancadaAction() {
        List<Encomenda> encomendasPesquisadas = encomendaDao.pesquisar(telaEncomendaPesquisaAvancada.getTfCampoPesquisa().getText());
        if (encomendasPesquisadas == null) {
            encomendaTableModel.limpar();
            encomendasPesquisadas = encomendaDao.pesquisar();
        } else {
            encomendaTableModel.limpar();
            encomendaTableModel.adicionar(encomendasPesquisadas);
        }
    }

    public void atualizaTotaisDeFrete(List<Entrega> entregas) {
        Double totalFreteBanco = 0.0;
        Double totalFreteFiltrado = 0.0;
        List<Entrega> entregasDoBanco = entregaDao.pesquisar();
        for (Entrega entregaDoBanco : entregasDoBanco) {
            totalFreteBanco += entregaDoBanco.getValorTotal();
        }
        for (Entrega entrega : entregas) {
            totalFreteFiltrado += entrega.getValorTotal();
        }
        telaEntrega.getLblFreteTotal().setText(DecimalFormat.decimalFormatR$(totalFreteBanco));
        telaEntrega.getLblFreteFiltrado().setText(DecimalFormat.decimalFormatR$(totalFreteFiltrado));
    }

    public void chamarDialogFreteNaoEncontrado(Entrega entrega) {
        telaFreteNaoEncontrado = new TelaEntregaFreteNaoEncontrado(telaEntrega, true, this);
        telaFreteNaoEncontrado.getLblCidadeRemetente().setText(entrega.getRemetente().getEndereco().getCidade());
        telaFreteNaoEncontrado.getLblCidadeDestinatario().setText(entrega.getDestinatario().getEndereco().getCidade());
        telaFreteNaoEncontrado.getLblPesoEncomenda().setText(DecimalFormat.paraVirgula(String.valueOf(entrega.getEncomenda().getPeso())));
        telaFreteNaoEncontrado.getLblValorEncomenda().setText(DecimalFormat.paraVirgula(String.valueOf(entrega.getEncomenda().getValorNotaFiscal())));
        telaFreteNaoEncontrado.setVisible(true);
    }

    public void atualizaValorFreteManualAction() {
        valorFreteManual = Double.valueOf(DecimalFormat.paraPonto(telaFreteNaoEncontrado.getTfValorFreteManual().getText()));
    }

    private void limparTabListarEntregaAction() {
        telaEntrega.getTfPesquisarEntrega().setText("");
    }

    private void limparTabEditarTramitesAction() {
        telaEntrega.getTfNomeTramite().setText("");
        telaEntrega.getTfObservacaoTramite().setText("");
        telaEntrega.getCbTipoTramite().setSelectedIndex(0);
    }

    public void limparCamposTabEfetivarEntregaAction() {
        telaEntrega.getTfCodigoPessoa().setText("");
        telaEntrega.getTfNome().setText("");
        telaEntrega.getTfCep().setText("");
        telaEntrega.getCbEstado().setSelectedIndex(0);
        telaEntrega.getTfCidade().setText("");
        telaEntrega.getTfBairro().setText("");
        telaEntrega.getTfNumero().setText("");
        telaEntrega.getTfRua().setText("");
        telaEntrega.getTfComplemento().setText("");
        telaEntrega.getTfCodigoPessoa().setText("");
        telaEntrega.getCbEncomenda().setSelectedIndex(0);
        telaEntrega.getCbRemetente().setSelectedIndex(0);
        destinatario = null;

    }

    private void carregarDestinatarioJdialogFicha() {
        entrega = entregaTableModel.pegaObjeto(telaEntrega.getTblEntrega().getSelectedRow());
        telaEntregaFicha.getLblNomeDestinatario().setText(entrega.getDestinatario().getNome());
        String codigoPessoa = entrega.getDestinatario().getCodigoPessoa();

        if (codigoPessoa.length() > 15) {
            formataTfCodigoPessoaParaCNPJ();
        } else {
            formataTfCodigoPessoaParaCPF();
        }
        telaEntregaFicha.getLblCodigoPessoaDestinatario().setText(entrega.getDestinatario().getCodigoPessoa());

        telaEntregaFicha.getLblBairroDestinatario().setText(entrega.getDestinatario().getEndereco().getBairro());
        telaEntregaFicha.getLblCidadeDestinatario().setText(entrega.getDestinatario().getEndereco().getCidade());
        telaEntregaFicha.getLblComplementoDestinatario().setText(entrega.getDestinatario().getEndereco().getComplemento());
        telaEntregaFicha.getLblEstadoDestinatario().setText(entrega.getDestinatario().getEndereco().getEstado());
        telaEntregaFicha.getLblNumeroDestinatario().setText(entrega.getDestinatario().getEndereco().getNumero());
        telaEntregaFicha.getLblRuaDestinatario().setText(entrega.getDestinatario().getEndereco().getRua());
        telaEntregaFicha.getLblCepDestinatario().setText(String.valueOf(entrega.getDestinatario().getEndereco().getCep()));
    }

    private void carregarRemetenteJdialogFicha() {
        entrega = entregaTableModel.pegaObjeto(telaEntrega.getTblEntrega().getSelectedRow());
        telaEntregaFicha.getLblNomeRemetente().setText(entrega.getRemetente().getNome());
        telaEntregaFicha.getLblTelefoneRemetente().setText(entrega.getRemetente().getTelefone());
        String codigoPessoa = entrega.getRemetente().getCodigoPessoa();

        if (codigoPessoa.length() > 15) {
            formataTfCodigoPessoaParaCNPJ();
        } else {
            formataTfCodigoPessoaParaCPF();
        }
        telaEntregaFicha.getLblCodigoPessoaRemetente().setText(entrega.getRemetente().getCodigoPessoa());

        telaEntregaFicha.getLblBairroRemetente().setText(entrega.getRemetente().getEndereco().getBairro());
        telaEntregaFicha.getLblCidadeRemetente().setText(entrega.getRemetente().getEndereco().getCidade());
        telaEntregaFicha.getLblComplementoRemetente().setText(entrega.getRemetente().getEndereco().getComplemento());
        telaEntregaFicha.getLblEstadoRemetente().setText(entrega.getRemetente().getEndereco().getEstado());
        telaEntregaFicha.getLblNumeroRemetente().setText(entrega.getRemetente().getEndereco().getNumero());
        telaEntregaFicha.getLblRuaRemetente().setText(entrega.getRemetente().getEndereco().getRua());
        telaEntregaFicha.getLblCepRemetente().setText(String.valueOf(entrega.getRemetente().getEndereco().getCep()));
    }

    private void carregarEncomendaJdialogFicha() {
        entrega = entregaTableModel.pegaObjeto(telaEntrega.getTblEntrega().getSelectedRow());
        telaEntregaFicha.getLblCodigoEncomenda().setText(entrega.getEncomenda().getCodigoRastreio());
        telaEntregaFicha.getLblValorEncomenda().setText(DecimalFormat.decimalFormatR$((entrega.getEncomenda().getValorNotaFiscal())));
        telaEntregaFicha.getLblPesoEncomenda().setText(String.valueOf(entrega.getEncomenda().getPeso()) + " KG");
        telaEntregaFicha.getLblLarguraEncomenda().setText(String.valueOf(entrega.getEncomenda().getDimensao().getLargura()) + " CM");
        telaEntregaFicha.getLblAlturaEncomenda().setText(String.valueOf(entrega.getEncomenda().getDimensao().getAltura()) + " CM");
        telaEntregaFicha.getLblProfundidadeEncomenda().setText(String.valueOf(entrega.getEncomenda().getDimensao().getComprimento()) + " CM");
        telaEntregaFicha.getLblDataCadastro().setText(UtilDate.data(entrega.getDataCadastro()));
        if (entrega.getDataEntrega() == null) {
            telaEntregaFicha.getLblDataEntrega().setVisible(false);

        } else {
            telaEntregaFicha.getLblDataEntrega().setText(UtilDate.data(entrega.getDataEntrega()));
        }
    }

}
