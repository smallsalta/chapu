package com.fct.mail;

import java.io.IOException;

import javax.mail.MessagingException;

public interface MandarCorreo 
{
	/**
	 * Método que manda un correo electrónico con la factura adjunta
	 * 
	 * @param to			Correo electrónico del remitente	
	 * @param adjunto		Factura adjunta
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	public void send(String to, byte[] adjunto) 
	throws MessagingException, IOException;	
}