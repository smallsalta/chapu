package com.fct.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PwdAuthenticator 
extends Authenticator
{
	private String username;
	private String password;

	public PwdAuthenticator(String username, String password)
	{
		this.username	= username;
		this.password	= password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() 
	{
		return new PasswordAuthentication( this.username, this.password );
	}
}
