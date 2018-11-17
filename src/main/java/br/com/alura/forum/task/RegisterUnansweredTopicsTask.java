package br.com.alura.forum.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.alura.forum.controller.repository.TopicRepository;
import br.com.alura.forum.model.OpenTopicsByCategory;
import br.com.alura.forum.repository.OpenTopicsByCategoryRepository;

@Component
public class RegisterUnansweredTopicsTask {

	
	@Autowired
	private TopicRepository topicRepository ;
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterUnansweredTopicsTask.class);
	
	@Autowired
	private OpenTopicsByCategoryRepository openTopicsByCategoryRepository;
	
	//@Scheduled(cron = "0 0 20 * * *")
	@Scheduled(fixedRate = 600000)
	public void execute(){
		
		logger.info("Atualizando topicos");
		List<OpenTopicsByCategory> topics = topicRepository.findOpenTopicsByCategory();
		this.openTopicsByCategoryRepository.saveAll(topics);
		
		
		
	}
}
