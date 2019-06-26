package br.com.vindiesel.control;

import br.com.vindiesel.exceptions.BuscaCepException;
import br.com.vindiesel.interfaces.BuscaCepEventos;
import br.com.vindiesel.interfaces.BuscaCepEventosImpl;
import br.com.vindiesel.model.EnderecoSigla;
import br.com.vindiesel.dao.EnderecoDao;
import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.model.tablemodel.RemetenteTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaRemetenteGerenciar;
import br.com.vindiesel.view.TelaPrincipal;
import java.text.ParseException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Will
 */
public class TelaRemetenteGerenciarControl {
    
    TelaRemetenteGerenciar telaRemetenteGerenciar;
    Remetente remetente;
    Endereco endereco;
    RemetenteDao remetenteDao;
    RemetenteTableModel remetenteTableModel;
    EnderecoDao enderecoDao;
    Integer linhaSelecionada;
    MaskFormatter mascaraFormatadoraCPF;
    MaskFormatter mascaraFormatadoraCNPJ;
    
    public TelaRemetenteGerenciarControl() {
        remetenteDao = new RemetenteDao();
        enderecoDao = new EnderecoDao();
        remetenteTableModel = new RemetenteTableModel();
    }
    
    public void carregarEstadosNaComboBox() {
        telaRemetenteGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(EnderecoSigla.ESTADOS_BRASILEIROS));
        
    }
    
    public void chamarTelaRemetenteGerenciar() {
        if (telaRemetenteGerenciar == null) { // se tiver nulo chama janela normalmente
            telaRemetenteGerenciar = new TelaRemetenteGerenciar(this);
            TelaPrincipal.desktopPane.add(telaRemetenteGerenciar);
            telaRemetenteGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaRemetenteGerenciar.isVisible()) {
                telaRemetenteGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaRemetenteGerenciar);
                telaRemetenteGerenciar.setVisible(true);
            }
        }
        telaRemetenteGerenciar.getTblRemetente().setModel(remetenteTableModel);
        carregarEstadosNaComboBox();
        remetenteTableModel.limpar();
        remetenteTableModel.adicionar(remetenteDao.pesquisar());
        atualizaTotalDosRemetentes(remetenteDao.pesquisar());
        redimensionarTela();
        telaRemetenteGerenciar.getTpRemetente().setEnabledAt(1, false);
        criaInstanciasDeMascarasFormatadas();
        
    }
    
    private void redimensionarTela() {
        UtilTable.centralizarCabecalho(telaRemetenteGerenciar.getTblRemetente());
        UtilTable.redimensionar(telaRemetenteGerenciar.getTblRemetente(), 0, 150);
        UtilTable.redimensionar(telaRemetenteGerenciar.getTblRemetente(), 1, 420);
        UtilTable.redimensionar(telaRemetenteGerenciar.getTblRemetente(), 2, 130);
        UtilTable.redimensionar(telaRemetenteGerenciar.getTblRemetente(), 3, 160);
    }
    
    public void novoRemetenteAction() {
        limparCampos();
        telaRemetenteGerenciar.getTpRemetente().setEnabledAt(1, true);
        UtilTable.limparSelecaoDaTabela(telaRemetenteGerenciar.getTblRemetente());
        remetente = null;
        telaRemetenteGerenciar.getTpRemetente().setSelectedIndex(1);
        
    }
    
    private void cadastrarRemetente() {
        remetente = new Remetente();
        remetente.setNome(telaRemetenteGerenciar.getTfNome().getText());
        remetente.setTelefone(telaRemetenteGerenciar.getTfTelefone().getText());
        remetente.setCodigoPessoa(telaRemetenteGerenciar.getTfCodigoPessoa().getText());
        
        endereco = new Endereco();
        
        try {
            endereco.setCep(Integer.valueOf(telaRemetenteGerenciar.getTfCep().getText()));
        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_CEP);
            remetente = null;
            return;
        }
        
        endereco.setCidade(telaRemetenteGerenciar.getTfCidade().getText());
        endereco.setBairro(telaRemetenteGerenciar.getTfBairro().getText());
        endereco.setComplemento(telaRemetenteGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaRemetenteGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaRemetenteGerenciar.getTfNumero().getText());
        endereco.setRua(telaRemetenteGerenciar.getTfRua().getText());
        Integer idEndereco = enderecoDao.inserir(endereco);
        endereco.setId(idEndereco);
        
        remetente.setEndereco(endereco);
        
        if (Validacao.validaEntidade(remetente) != null) {
            Mensagem.info(Validacao.validaEntidade(remetente));
            remetente = null;
            endereco = null;
            return;
        }
        
        Integer idInserido = remetenteDao.inserir(remetente);
        if (idInserido != 0) {
            remetente.setId(idInserido);
            remetenteTableModel.adicionar(remetente);
            atualizaTotalDosRemetentes(remetenteDao.pesquisar());
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        remetente = null;
        endereco = null;
    }
    
    private void alterarRemetente() {
        remetente = remetenteTableModel.pegaObjeto(telaRemetenteGerenciar.getTblRemetente().getSelectedRow());
        remetente.setNome(telaRemetenteGerenciar.getTfNome().getText());
        remetente.setTelefone(telaRemetenteGerenciar.getTfTelefone().getText());
        remetente.setCodigoPessoa(telaRemetenteGerenciar.getTfCodigoPessoa().getText());
        
        endereco = remetente.getEndereco();
        endereco.setBairro(telaRemetenteGerenciar.getTfBairro().getText());
        
        try {
            endereco.setCep(Integer.valueOf(telaRemetenteGerenciar.getTfCep().getText()));
        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_CEP);
        }
        
        endereco.setCidade(telaRemetenteGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaRemetenteGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaRemetenteGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaRemetenteGerenciar.getTfNumero().getText());
        endereco.setRua(telaRemetenteGerenciar.getTfRua().getText());
        boolean enderecoAlterado = enderecoDao.alterar(endereco);
        if (!enderecoAlterado) {
            Mensagem.erro(Texto.ERRO_EDITAR);
            return;
        }
        
        if (Validacao.validaEntidade(remetente) != null) {
            Mensagem.info(Validacao.validaEntidade(remetente));
            return;
        }
        remetente.setEndereco(endereco);
        boolean alterado = remetenteDao.alterar(remetente);
        linhaSelecionada = telaRemetenteGerenciar.getTblRemetente().getSelectedRow();
        if (alterado) {
            remetenteTableModel.atualizar(linhaSelecionada, remetente);
            atualizaTotalDosRemetentes(remetenteDao.pesquisar());
            limparCampos();
            Mensagem.info(Texto.SUCESSO_EDITAR);
            
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        remetente = null;
        endereco = null;
    }
    
    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscadorDeCepControl buscadorDeCep = new BuscadorDeCepControl();
        try {
            buscadorDeCep.buscar(telaRemetenteGerenciar.getTfCep().getText());
            endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());

            // mostra na tela o cep pesquisado
            telaRemetenteGerenciar.getTfBairro().setText(endereco.getBairro());
            telaRemetenteGerenciar.getTfCidade().setText(endereco.getCidade());
            telaRemetenteGerenciar.getTfComplemento().setText(endereco.getComplemento());
            telaRemetenteGerenciar.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaRemetenteGerenciar.getTfRua().setText(endereco.getRua());
            telaRemetenteGerenciar.getTfCep().setText(telaRemetenteGerenciar.getTfCep().getText());
            telaRemetenteGerenciar.getTfNumero().requestFocus();
        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }
    
    public void gravarRemetenteAction() {
        if (remetente == null) {
            cadastrarRemetente();
        } else {
            alterarRemetente();
        }
    }
    
    public void excluirRemententeAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);
        
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.YES_OPTION) {
            remetente = remetenteTableModel.pegaObjeto(telaRemetenteGerenciar.getTblRemetente().getSelectedRow());
            boolean deletado = remetenteDao.deletar(remetente.getId());
            if (deletado) {
                remetenteTableModel.remover(telaRemetenteGerenciar.getTblRemetente().getSelectedRow());
                telaRemetenteGerenciar.getTblRemetente().clearSelection();
                Mensagem.info(Texto.SUCESSO_DELETAR);
            } else {
                Mensagem.erro(Texto.ERRO_DELETAR);
            }
        }
        remetente = null;
    }
    
    public void pesquisarRemetenteAction() {
        List<Remetente> remetentesPesquisados = remetenteDao.pesquisar(telaRemetenteGerenciar.getTfPesquisar().getText());
        if (remetentesPesquisados == null) {
            remetenteTableModel.limpar();
            remetentesPesquisados = remetenteDao.pesquisar();
            atualizaTotalDosRemetentes(remetentesPesquisados);
        } else {
            remetenteTableModel.limpar();
            remetenteTableModel.adicionar(remetentesPesquisados);
            atualizaTotalDosRemetentes(remetentesPesquisados);
        }
    }
    
    public void carregarRemetenteAction() {
        remetente = remetenteTableModel.pegaObjeto(telaRemetenteGerenciar.getTblRemetente().getSelectedRow());
        telaRemetenteGerenciar.getTfNome().setText(remetente.getNome());
        telaRemetenteGerenciar.getTfTelefone().setText(remetente.getTelefone());
        String codigoPessoa = remetente.getCodigoPessoa();
        if (codigoPessoa.length() > 16) {
            formataTfCodigoPessoaParaCNPJ();
            telaRemetenteGerenciar.getCheckCnpj().setSelected(true);
        } else {
            formataTfCodigoPessoaParaCPF();
            telaRemetenteGerenciar.getCheckCpf().setSelected(true);
            
        }
        telaRemetenteGerenciar.getTfCodigoPessoa().setText(remetente.getCodigoPessoa());
        
        telaRemetenteGerenciar.getTfBairro().setText(remetente.getEndereco().getBairro());
        telaRemetenteGerenciar.getTfCidade().setText(remetente.getEndereco().getCidade());
        telaRemetenteGerenciar.getTfComplemento().setText(remetente.getEndereco().getComplemento());
        telaRemetenteGerenciar.getCbEstado().getModel().setSelectedItem(remetente.getEndereco().getEstado());
        telaRemetenteGerenciar.getTfNumero().setText(remetente.getEndereco().getNumero());
        telaRemetenteGerenciar.getTfRua().setText(remetente.getEndereco().getRua());
        telaRemetenteGerenciar.getTfCep().setText(String.valueOf(remetente.getEndereco().getCep()));
        
        telaRemetenteGerenciar.getTpRemetente().setEnabledAt(1, true); // disabilita o tabbed pane
        telaRemetenteGerenciar.getTpRemetente().setSelectedIndex(1); // seleciona o tabbed pane
        telaRemetenteGerenciar.getTfNome().requestFocus();
        
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
        mascaraFormatadoraCNPJ.install(telaRemetenteGerenciar.getTfCodigoPessoa());
    }
    
    public void formataTfCodigoPessoaParaCPF() {
        mascaraFormatadoraCPF.install(telaRemetenteGerenciar.getTfCodigoPessoa());
        
    }
    
    private void limparCampos() {
        telaRemetenteGerenciar.getTfNome().setText("");
        telaRemetenteGerenciar.getTfCodigoPessoa().setText("");
        telaRemetenteGerenciar.getTfBairro().setText("");
        telaRemetenteGerenciar.getTfCep().setText("");
        telaRemetenteGerenciar.getTfCidade().setText("");
        telaRemetenteGerenciar.getTfComplemento().setText("");
        telaRemetenteGerenciar.getCbEstado().setSelectedIndex(0);
        telaRemetenteGerenciar.getTfNumero().setText("");
        telaRemetenteGerenciar.getTfTelefone().setText("");
        telaRemetenteGerenciar.getTfPesquisar().setText("");
        telaRemetenteGerenciar.getTfRua().setText("");
        telaRemetenteGerenciar.getTfNome().requestFocus();
        UtilTable.limparSelecaoDaTabela(telaRemetenteGerenciar.getTblRemetente());
    }
    
    public void atualizaTotalDosRemetentes(List<Remetente> remetentes) {
        Integer totalRemetentesBanco = 0;
        Integer totalRemetentesFiltrados = 0;
        List<Remetente> remetentesDoBanco = remetenteDao.pesquisar();
        
        totalRemetentesBanco = remetentesDoBanco.size();
        totalRemetentesFiltrados = remetentes.size();
        
        telaRemetenteGerenciar.getLblTotalRemetentes().setText(String.valueOf(totalRemetentesBanco));
        telaRemetenteGerenciar.getLblTotalFiltrados().setText(String.valueOf(totalRemetentesFiltrados));
    }
}
