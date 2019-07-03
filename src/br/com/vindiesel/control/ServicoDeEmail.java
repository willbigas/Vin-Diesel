package br.com.vindiesel.control;

import br.com.vindiesel.uteis.Mensagem;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author William
 */
public class ServicoDeEmail {

    private String emailRemetente = "vindieseltransportes@gmail.com";
    private String senhaRemetente = "Wpadmin321";

    public ServicoDeEmail(String enderecoDestinatario, String assunto, String corpo) {
        Properties propertiesGmail = definePropertiesDoGmail();
        Session novaSessao = criaSessao(propertiesGmail);
        enviarEmail(novaSessao, enderecoDestinatario, assunto, corpo);
    }

    public boolean enviarEmail(Session sessao, String enderecoDestinatario, String assunto, String corpoEmail) {

        try {
            Address[] toUser = InternetAddress.parse(enderecoDestinatario);

            Message mensagem = new MimeMessage(sessao);
            mensagem.setFrom(new InternetAddress(emailRemetente)); //quem esta enviando
            mensagem.setRecipients(Message.RecipientType.TO, toUser); //emails de destino
            mensagem.setSubject(assunto); //assunto do email
            mensagem.setText(corpoEmail); //corpo do email
            Transport.send(mensagem);
            return true;
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
            Mensagem.erro("Erro ao Enviar email , "
                    + "Verifique os padrões de configuração de e-mail," + messagingException.getMessage());
            return false;
        }

    }

    private Session criaSessao(Properties properties) {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailRemetente, senhaRemetente);
            }

        });
        return session;
    }

    private Properties definePropertiesDoGmail() {
        //configurando as propriedades
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true"); //autorizacao
        properties.put("mail.smtp.starttls", "true"); //autenticacao
        properties.put("mail.smtp.host", "smtp.gmail.com"); // servidor gmail google
        properties.put("mail.smtp.port", "465"); //porta do servidor
        properties.put("mail.smtp.socketFactory.port", "465"); //expecifica a porta a ser conectada pelo socket
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //classe socket de conexao ao SMTP
        return properties;
    }

    private Properties definePropertiesDoHotmail() {
        //configurando as propriedades
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.live.com");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

}
