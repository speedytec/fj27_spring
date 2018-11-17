package br.com.alura.forum.controller.repository;


import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.OpenTopicsByCategory;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;

public interface TopicRepository extends Repository<Topic, Long> ,JpaSpecificationExecutor<Topic>{
	
	@Query("select t from Topic t")
	List<Topic> list();
	
	List <Topic> findAll();
	
	Page<Topic> findAll(Pageable pageRequest);

	Page<Topic> findAll(Specification<Topic> topicSearchSpecification, Pageable pageRequest);

	List<Topic> findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(User loggedUser, Instant oneHourAgo);
	
	Topic findById(Long id);
	
	Topic save(Topic topic);
	
	@Query("select new br.com.alura.forum.model.OpenTopicsByCategory(" +
			"t.course.subcategory.category.name as categoryName, " +
			"count(t) as topicCount, " +
			"now() as instant) from Topic t " +
			"where t.status = 'NOT_ANSWERED' " +
			"group by t.course.subcategory.category")
	List<OpenTopicsByCategory> findOpenTopicsByCategory();
	
	
	
}
