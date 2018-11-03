package br.com.alura.forum.controller.dto.output;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.model.topic_domain.TopicStatus;

public class DashboardOutputDto {
	
	
	private TopicStatus status;
	private int numberOfResponses;
	private String subcategoryName;
	private String categoryName;
	private boolean solved;
	private int unansweredTopics;;
	
	
	
	
	public DashboardOutputDto(Topic topic) {
				
		this.subcategoryName = topic.getCourse().getSubcategory().getName();
		this.categoryName = topic.getCourse().getCategoryName();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
		this.unansweredTopics= topic.getNumberOfAnswers();
		
	
	}
	
	
	
	public static Page<DashboardOutputDto> dashboardReturn(Page<Topic> topicPage){
		
		
		return topicPage.map(DashboardOutputDto::new);
	}

}
