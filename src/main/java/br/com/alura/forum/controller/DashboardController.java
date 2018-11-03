package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.controller.dto.input.TopicsearchInputDto;
import br.com.alura.forum.controller.dto.output.DashboardOutputDto;
import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.controller.repository.TopicRepository;
import br.com.alura.forum.model.topic_domain.Topic;

@Controller
public class DashboardController {

	
	@Autowired
	private TopicRepository topicRepository;
	
	
		@ResponseBody
		@GetMapping(value ="/api/topics/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
		public Page<DashboardOutputDto> listTopics(TopicsearchInputDto topicSearch, 
				@PageableDefault (sort="creationInstant",direction=Sort.Direction.DESC)Pageable pageRequest){
			
		Page<Topic> topics = this.topicRepository.findAll(pageRequest);	
		//List <Topic> topicsList= topicRepository.findAll();
		
		return DashboardOutputDto.dashboardReturn(topics);	
	}
}
