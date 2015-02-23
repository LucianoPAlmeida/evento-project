package br.ucb.projeto.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	private String assunto;
	private String texto;
	private String remetente;
	private String nomeRemetente;
	private String senhaRemetente;
	private String recebedor;
	public EmailSender() {
		
	}
	
	public EmailSender(String assunto, String texto, String remetente,
			String nomeRemetente,String senhaRemetente, String recebedor) {
		setAssunto(assunto);
		setTexto(texto);
		setRecebedor(recebedor);
		setNomeRemetente(nomeRemetente);
		setRemetente(remetente);
		setSenhaRemetente(senhaRemetente);
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public String getSenhaRemetente() {
		return senhaRemetente;
	}

	public void setSenhaRemetente(String senhaRemetente) {
		this.senhaRemetente = senhaRemetente;
	}

	public String getRecebedor() {
		return recebedor;
	}

	public void setRecebedor(String recebedor) {
		this.recebedor = recebedor;
	}

	public Authenticator getAuthenticator(){
		Authenticator autenticacao = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getRemetente(),getSenhaRemetente());
			}
		};
		return autenticacao;
	}
	public Properties getPropriedades(){
		Properties props = System.getProperties();
		props.put("mail.smtp.host","localhost");
		props.put("mail.smtp.port","25");
		props.put("mail.smtp.auth","true");
		return props;
	}
	
	public boolean enviar(){
		Session session = Session.getDefaultInstance(getPropriedades(), getAuthenticator());
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getRemetente(),getNomeRemetente())); 
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(getRecebedor())); 
			message.setSubject(getAssunto());
			message.setText(getTexto());
			Transport.send(message);
			return true;
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
