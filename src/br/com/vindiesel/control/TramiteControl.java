package br.com.vindiesel.control;

import br.com.vindiesel.dao.EntregaDao;
import br.com.vindiesel.dao.TipoTramiteDao;
import br.com.vindiesel.dao.TramiteDao;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.model.TipoTramite;
import br.com.vindiesel.model.Tramite;
import java.time.LocalDateTime;

/**
 *
 * @author Will
 */
public class TramiteControl {

    TramiteDao tramiteDao;
    EntregaDao entregaDao;
    TipoTramiteDao tipoTramiteDao;
    Tramite tramite;
    TipoTramite tipoTramite;

    public static final int CODIGO_SAIU_DE = 1;
    public static final int CODIGO_FOI_PARA = 2;
    public static final int CODIGO_CHEGOU_EM = 3;

    public TramiteControl() {
        tramiteDao = new TramiteDao();
        tipoTramiteDao = new TipoTramiteDao();
    }

    /**
     *
     * @param entrega
     * @param nome
     * @param observacao
     * @param codigoTipoTramite - Se for 1 = SAIU DE , 2 = FOI PARA , 3 = CHEGOU
     * EM
     */
    public int adicionarTramite(Entrega entrega, String nome, String observacao, Integer codigoTipoTramite) {
        tramite = new Tramite();
        tramite.setDataHora(LocalDateTime.now());
        tramite.setEntrega(entrega);
        tramite.setObservacao(observacao);

        if (codigoTipoTramite == CODIGO_SAIU_DE) {
            tramite.setNome(tipoTramiteDao.pesquisar(CODIGO_SAIU_DE).getNome() + " " + nome);
            tramite.setTipoTramite(tipoTramiteDao.pesquisar(CODIGO_SAIU_DE));
        }
        if (codigoTipoTramite == CODIGO_FOI_PARA) {
            tramite.setNome(tipoTramiteDao.pesquisar(CODIGO_FOI_PARA).getNome() + " " + nome);
            tramite.setTipoTramite(tipoTramiteDao.pesquisar(CODIGO_FOI_PARA));
        }
        if (codigoTipoTramite == CODIGO_CHEGOU_EM) {
            tramite.setNome(tipoTramiteDao.pesquisar(CODIGO_CHEGOU_EM).getNome() + " " + nome);
            tramite.setTipoTramite(tipoTramiteDao.pesquisar(CODIGO_CHEGOU_EM));
        }

        int idInserido = tramiteDao.inserir(tramite);
        if (idInserido != 0) {
            //enviarEmailDeTramite(entrega, tramite);
        }

        return idInserido;
    }

    public boolean removerTramite(Tramite tramiteRecebido) {
        tramite = tramiteDao.pesquisar(tramiteRecebido.getId());
        boolean deletado = tramiteDao.deletar(tramite.getId());
        return deletado;

    }

    /*Implementando mais nao funciona dentro da rede do senac*/
    public void enviarEmailDeTramite(Entrega entrega, Tramite tramite) {
        String enderecoDestino = entrega.getRemetente().getEmail(); // pegar email do remetente aki
        String assunto = "Status de Encomenda vindiesel :" + entrega.getEncomenda().getCodigoRastreio();
        String corpo = "Olá, \n sua encomenda foi alterada pela nossa tranportadora para o status de:" + tramite.getNome()
                + "\r Observação:" + tramite.getObservacao();
        ServicoDeEmail servicoDeEmail = new ServicoDeEmail(enderecoDestino, assunto, corpo);

    }

}
