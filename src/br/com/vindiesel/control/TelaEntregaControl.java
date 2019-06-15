package br.com.vindiesel.control;

import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.dao.EncomendaDao;
import br.com.vindiesel.dao.UsuarioDao;
import br.com.vindiesel.dao.EntregaDao;
import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.dao.TramiteDao;
import br.com.vindiesel.exceptions.BuscaCepException;
import br.com.vindiesel.interfaces.BuscaCepEventos;
import br.com.vindiesel.interfaces.BuscaCepEventosImpl;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.model.EnderecoSigla;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.model.tablemodel.TramiteTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaDestinatarioDialogPesquisar;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaEntrega;
import br.com.vindiesel.view.TelaEntregaReceita;
import java.util.ArrayList;
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
    TramiteTableModel tramiteTableModel;
    DestinatarioDao destinatarioDao;
    EncomendaDao encomendaDao;
    RemetenteDao remetenteDao;
    EntregaDao entregaDao;
    TramiteDao tramiteDao;
    List<Encomenda> listEncomendas;
    List<Remetente> listRemetentes;
    Entrega entrega;
    Encomenda encomenda;

    public TelaEntregaControl() {
        destinatarioDao = new DestinatarioDao();
        encomendaDao = new EncomendaDao();
        remetenteDao = new RemetenteDao();
        entregaDao = new EntregaDao();
        tramiteDao = new TramiteDao();
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
        carregarEncomendasNaCombo();
        carregarRemetentesNaCombo();
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

    public void chamarDialogVendaReceitaAction() {
        telaEntregaReceita = new TelaEntregaReceita(telaEntrega, true, this);
        telaEntregaReceita.setVisible(true);
    }

    public void adicionarVendaAction() {
        entrega = new Entrega();

        // atributos de entrega 
        if (Validacao.validaEntidade(entrega) != null) {
            Mensagem.info(Validacao.validaEntidade(entrega));
            entrega = null;
            return;
        }

        Integer idVendaInserida = entregaDao.inserir(entrega);

        if (idVendaInserida == 0) {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
            return;
        }

        entrega.setId(idVendaInserida);

    }

    public void chamarDialogClienteAction() {
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

}
