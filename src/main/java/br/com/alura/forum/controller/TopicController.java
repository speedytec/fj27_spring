package br.com.alura.forum.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.controller.dto.input.TopicsearchInputDto;
import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.controller.repository.TopicRepository;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;



@Controller
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;
	
	@ResponseBody
	@GetMapping(value ="/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<TopicBriefOutputDto> listTopics(TopicsearchInputDto topicSearch, 
			@PageableDefault (sort="creationInstant",direction=Sort.Direction.DESC)Pageable pageRequest){
		
			
		Category subcategory = new Category("Java",new Category("Programacao"));
		Course course = new Course("Java e JFS", subcategory);
		Topic topic = new Topic("Problemas com JSF 1", "JSF", new User("Fulano", "fulano@fulano.com", "123456"), course);
		 
 		Specification<Topic> topicSearchSpecification = topicSearch.build();
 		
 		Page<Topic> topics = this.topicRepository.findAll(topicSearchSpecification, pageRequest);
 		
		//List<Topic> topics = topicRepository.findAll(topicSearchSpecification,pageRequest);
		return TopicBriefOutputDto.listfromTopics(topics);	
	}
	
	
	
	
}
