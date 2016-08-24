package com.example.naresh.paseo;

/**
 * Created by VINUTHAMUTHYALA on 27/04/16.
 */

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class JavaEmail {

  private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_AUTH_USER = "vinutha.muthyala@gmail.com";
    private static final String SMTP_AUTH_PWD  = "chittu93";

    public static void main( String[] args )
    {
//        clientWindow cw=new clientWindow();
    	Properties props = System.getProperties();
    	System.out.println("Started");
    	props.put("mail.smtp.host", SMTP_HOST_NAME);
    	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.auth", "true");
    	  props.put("mail.smtp.user", SMTP_AUTH_USER);
    	    props.put("mail.smtp.password", SMTP_AUTH_PWD);
	props.put("mail.smtp.port", "465");
	props.put("mail.smtp.starttls.enable", "true");


	Session session = Session.getDefaultInstance(props,
	new javax.mail.Authenticator()
	{
		protected PasswordAuthentication getPasswordAuthentication()
		{ return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);	}
	});

    	try {

            Message message = new MimeMessage(session);
            //textField_1
            message.setFrom(new InternetAddress("vinutha.muthyala@gmail.com"));
           // message.setFrom(new InternetAddress(textField_1.getText()));
	    //textField_2
	    //message.setRecipients(Message.RecipientType.TO,
                        //InternetAddress.parse(textField_2.getText()));
            message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse("vinutha.muthyala@gmail.com"));
	    //textField_3
	    message.setSubject("sdkjfb");
	    //textArea
	    message.setText("Mail recieved from java application bult by "+ SMTP_AUTH_USER);

	    Transport.send(message);

	    System.out.println("YIPPEEEE..!!!");

    	} catch (MessagingException e) {
    	    throw new RuntimeException(e);
    	}



    }
}