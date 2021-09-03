package com.rugbyaholic.communityPG.comms.repositories;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rugbyaholic.communityPG.auth.AuthenticatedUser;
import com.rugbyaholic.communityPG.comms.Topic;
import com.rugbyaholic.communityPG.comms.forms.TopicCreationForm;

@Mapper
public interface MeetingRoomRepository {
	
	public List<Topic> searchAllTopics();
	
	public void registerTopic(@Param("form") TopicCreationForm form, @Param("user") AuthenticatedUser user);
	
	public void registerPost(@Param("form") TopicCreationForm form, @Param("user") AuthenticatedUser user);
	
	public Optional<Topic> findTopic(String topicNo);
	
	public Optional<Integer> currentRating(@Param("topicNo") String topicNo, 
											@Param("postNo") int postNo, 
											@Param("user") AuthenticatedUser user);

	public void updateRating(@Param("topicNo") String topicNo, 
							@Param("postNo") int postNo, 
							@Param("user") AuthenticatedUser user,
							@Param("rating") int rating);
}