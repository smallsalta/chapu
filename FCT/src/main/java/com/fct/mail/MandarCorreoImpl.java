package com.fct.mail;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Servicio de envío de emails
 */
public class MandarCorreoImpl 
implements MandarCorreo 
{
	private String asunto;
	private String cuerpo;
	private JavaMailSenderImpl mailSender;

	/**
	 * {@inheritDoc}
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	public void send(String to, byte[] adjunto) 
	throws MessagingException, IOException 
	{
		PwdAuthenticator pwd	= new PwdAuthenticator( this.mailSender.getUsername(), this.mailSender.getPassword() );
		Session session			= Session.getInstance( this.mailSender.getJavaMailProperties(), pwd );
		MimeMessage message 	= new MimeMessage(session);
		MimeMessageHelper help	= new MimeMessageHelper(message, true);
			
		help.setTo(to);
		help.setSubject( this.asunto );
		help.setText( this.cuerpo );
		help.addAttachment( "documento.pdf", new ByteArrayResource(adjunto) );
		
		this.mailSender.send(message);
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
}