package br.com.alura.forum.controller.dto.input;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.repository.CourseRepository;

public class NewTopicInputDto {

	@NotBlank
	@Size(min=10)
	private String shortDescription;
	
	@NotBlank
	@Size(min=10)
	private String content;
	
	@NotEmpty
	private String coursename;
	
	
	public Topic build(User owner,CourseRepository courseRepository){
		
		Course course = courseRepository.findByName(this.coursename);
		return new Topic(this.shortDescription, this.content, owner, course);
		
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
}
