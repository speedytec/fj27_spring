package br.com.alura.forum.model;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import br.com.alura.forum.model.topic_domain.Topic;

public class PossibleSpam {
	
	private List<Topic> topics;

	public PossibleSpam(List<Topic> topics) {
		this.topics = topics;
	}
	
	public boolean hasTopicLimitExceeded(){
		return this.topics.size() >=4;
	}
	
	public long minutesToNextTopic (Instant from){
		Instant instantOftheOldestTopic = topics.get(0).getCreationInstant();
		
		return Duration.between(from, instantOftheOldestTopic).getSeconds() / 60;
	}

}
