package br.com.alura.forum.service.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic_domain.Topic;

@Service
public class ForumMailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private NewReplyMailFactory newreplyMailFactory;
	
	@Async
	public void sendNewReplyMail(Answer answer) {

		
		
		Topic answeredTopic = answer.getTopic();

		MimeMessagePreparator messagePreparator = (mimeMessage) ->{
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			
			messageHelper.setTo(answeredTopic.getOwnerEmail());
			messageHelper.setSubject("Novo comentario em " + answeredTopic.getShortDescription());
			
			String messageContent = this.newreplyMailFactory.generateNewReplyMailContent(answer);
			messageHelper.setText(messageContent,true);
			
		};
		
		/*SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(answeredTopic.getOwnerEmail());
		message.setSubject("Novo comentario em " + answeredTopic.getShortDescription());
		message.setText("Ola " + answeredTopic.getContent() + "\n\n" + "Há uma nova mensagem no fórum "
				+ answer.getOwnerName() + "comentou no tópico: " + answeredTopic.getShortDescription());*/

		try {
			mailSender.send(messagePreparator);
		} catch (MailSendException e) {
			throw new ForumMailServiceException("Nao foi possivel enviar email", e);
		}

	}

}
