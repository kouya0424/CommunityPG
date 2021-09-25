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
	
	/**
	 * @メソッド説明：		取得したトピック番号の投稿内容を変更します。
	 * 
	 * @param postText	投稿内容
	 * @param topicNo	トピック番号
	 * @param postNo	ポスト番号
	 */
	public void editerPost(@Param("postText") String postText,
						@Param("topicNo") String topicNo, 
						@Param("postNo") int postNo);

	/**
	 * @メソッド説明：		取得したトピック番号の投稿内容を削除します。
	 * 
	 * @param postText	投稿内容
	 * @param topicNo	トピック番号
	 * @param postNo	ポスト番号
	 */
	public void deleterPost(@Param("topicNo") String topicNo, 
			@Param("postNo") int postNo);
	
	/**
	 * @メソッド説明：		取得したトピックを削除します。
	 * 
	 * @param topicNo	トピック番号
	 */
	public void deleterTopic(@Param("topicNo") String topicNo);
	
	/**
	 * @メソッド説明：		TOPICSテーブルの検索結果を返します
	 * 
	 * @param topicNo	トピックナンバー
	 * @return			画面表示するトピックの検索結果
	 */
	public Optional<Topic> findTopic(String topicNo);
	
	public Optional<Integer> currentRating(@Param("topicNo") String topicNo, 
											@Param("postNo") int postNo, 
											@Param("user") AuthenticatedUser user);

	public void updateRating(@Param("topicNo") String topicNo, 
							@Param("postNo") int postNo, 
							@Param("user") AuthenticatedUser user,
							@Param("rating") int rating);
}