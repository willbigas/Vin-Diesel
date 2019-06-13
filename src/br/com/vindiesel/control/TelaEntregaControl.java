package br.com.vindiesel.control;

import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.dao.EncomendaDao;
import br.com.vindiesel.dao.UsuarioDao;
import br.com.vindiesel.dao.EntregaDao;
import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.model.Usuario;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.model.tablemodel.EncomendaTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.uteis.UtilTable;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaEntregaControl {

    TelaEntrega telaEntrega;
    TelaEntregaReceita telaEntregaReceita;
    TelaDestinatarioDialogPesquisar telaDestinatarioDialogPesquisar;
    TelaReceitaGerenciarControl receitaGerenciarControl;
    DestinatarioDao destinatarioDao;
    UsuarioDao usuarioDao;
    EncomendaDao encomendaDao;
    EntregaDao entregaDao;
    List<Destinatario> listDestinatarios;
    List<Usuario> listUsuarios;
    EncomendaTableModel encomendaTableModel;
    Entrega entrega;
    Encomenda encomenda;

    public TelaEntregaControl() {
        destinatarioDao = new DestinatarioDao();
        usuarioDao = new UsuarioDao();
        encomendaDao = new EncomendaDao();
        entregaDao = new EntregaDao();
        listDestinatarios = new ArrayList<>();
        listUsuarios = new ArrayList<>();
        encomendaTableModel = new EncomendaTableModel();

    }

    public void chamarTelaVenda() {
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
        carregarClientesNaCombo();
        carregarUsuariosNaCombo();
        telaEntrega.getTblProduto().setModel(encomendaTableModel);
        encomendaTableModel.limpar();
        encomendaTableModel.adicionar(encomendaDao.pesquisar());
        redimensionarTabelaProduto();
        redimensionarTabelaItemVenda();
        centralizarCabecalhoEConteudoTabelaProduto();
        centralizarCabecalhoEConteudoTabelaItemVenda();

    }

    public void redimensionarTabelaProduto() {

        UtilTable.redimensionar(telaEntrega.getTblProduto(), 0, 90);
        UtilTable.redimensionar(telaEntrega.getTblProduto(), 1, 223);
        UtilTable.redimensionar(telaEntrega.getTblProduto(), 2, 90);
        UtilTable.redimensionar(telaEntrega.getTblProduto(), 3, 95);
    }
    
     public void redimensionarTabelaItemVenda() {

        UtilTable.redimensionar(telaEntrega.getTblVenda(), 0, 90);
        UtilTable.redimensionar(telaEntrega.getTblVenda(), 1, 280);
        UtilTable.redimensionar(telaEntrega.getTblVenda(), 2, 90);
        UtilTable.redimensionar(telaEntrega.getTblVenda(), 3, 113);
    }

    public void centralizarCabecalhoEConteudoTabelaProduto() {
        UtilTable.centralizarCabecalho(telaEntrega.getTblProduto());
        UtilTable.centralizarConteudo(telaEntrega.getTblProduto(), 0);
        UtilTable.centralizarConteudo(telaEntrega.getTblProduto(), 1);
        UtilTable.centralizarConteudo(telaEntrega.getTblProduto(), 2);
        UtilTable.centralizarConteudo(telaEntrega.getTblProduto(), 3);
        UtilTable.centralizarConteudo(telaEntrega.getTblProduto(), 4);
    }
    
    public void centralizarCabecalhoEConteudoTabelaItemVenda() {
        UtilTable.centralizarCabecalho(telaEntrega.getTblVenda());
        UtilTable.centralizarConteudo(telaEntrega.getTblVenda(), 0);
        UtilTable.centralizarConteudo(telaEntrega.getTblVenda(), 1);
        UtilTable.centralizarConteudo(telaEntrega.getTblVenda(), 2);
        UtilTable.centralizarConteudo(telaEntrega.getTblVenda(), 3);
    }
    
    private void carregarClientesNaCombo() {
        listDestinatarios = destinatarioDao.pesquisar();
        DefaultComboBoxModel<Destinatario> model = new DefaultComboBoxModel(listDestinatarios.toArray());
        telaEntrega.getCbCliente().setModel(model);
    }

    private void carregarUsuariosNaCombo() {
        listUsuarios = usuarioDao.pesquisar();
        DefaultComboBoxModel<Usuario> model = new DefaultComboBoxModel(listUsuarios.toArray());
        telaEntrega.getCbUsuario().setModel(model);
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

    public void pesquisarEncomendaAction() {
        List<Encomenda> produtosPesquisados = encomendaDao.pesquisar(telaEntrega.getTfPesquisaProduto().getText());
        if (produtosPesquisados == null) {
            encomendaTableModel.limpar();
            produtosPesquisados = encomendaDao.pesquisar();
        } else {
            encomendaTableModel.limpar();
            encomendaTableModel.adicionar(produtosPesquisados);
        }
    }


    public void chamarDialogClienteAction() {
        telaDestinatarioDialogPesquisar = new TelaDestinatarioDialogPesquisar(telaEntrega, true, this);
        telaDestinatarioDialogPesquisar.setVisible(true);
    }

}
