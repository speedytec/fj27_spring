package br.com.alura.forum.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

@Profile("dev")
@Configuration
public class GreenMailLocalSmtpConfiguration {
	
	
	private static final Logger logger = LoggerFactory.getLogger(GreenMailLocalSmtpConfiguration.class);
	
	private GreenMail smtpServer;
	
	@Value("${spring.mail.host}")
	private String hostAddress;
	
	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@PostConstruct
	public void setup(){
		ServerSetup serverSetup = new ServerSetup(port, hostAddress, "smtp");
		this.smtpServer = new GreenMail(serverSetup);
		this.smtpServer.setUser(username, username,password);
		this.smtpServer.start();
		
	}
	@PreDestroy
	public void Destroy(){
		this.smtpServer.stop();
	}

}
