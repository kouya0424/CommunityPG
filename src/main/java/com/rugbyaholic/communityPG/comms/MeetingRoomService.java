package com.rugbyaholic.communityPG.comms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rugbyaholic.communityPG.auth.AuthenticatedUser;
import com.rugbyaholic.communityPG.common.aspects.LogRequired;
import com.rugbyaholic.communityPG.common.repositories.NumberingRepository;
import com.rugbyaholic.communityPG.comms.forms.TopicCreationForm;
import com.rugbyaholic.communityPG.comms.repositories.MeetingRoomRepository;

@Service
public class MeetingRoomService {

	@Autowired
	private NumberingRepository numberingRepository;

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	@LogRequired
	public List<Topic> loadTopics() {
		return meetingRoomRepository.searchAllTopics();
	}

	@Transactional(rollbackFor = Throwable.class)
	@LogRequired
	public void registerNewTopic(TopicCreationForm form, AuthenticatedUser user) {
		// トピック番号の発番
		String availYear = new SimpleDateFormat("yyyy").format(new Date());
		form.setTopicNo(
				availYear + numberingRepository.issueNumber(NumberingRepository.NUMBERING_CODE_TOPICNO, availYear));
		// 番号管理台帳の更新
		numberingRepository.next(NumberingRepository.NUMBERING_CODE_TOPICNO, availYear, user);
		// トピックテーブルへの新規登録
		meetingRoomRepository.registerTopic(form, user);
		// 投稿テーブルへの新規登録
		meetingRoomRepository.registerPost(form, user);
	}

	@Transactional(rollbackFor = Throwable.class)
	@LogRequired
	public Topic appendPost(TopicCreationForm form, AuthenticatedUser user) {
		meetingRoomRepository.registerPost(form, user);
		return meetingRoomRepository.findTopic(form.getTopicNo()).orElse(new Topic());
	}

	/**
	 * @メソッド説明：		投稿を編集するメソッドを呼び出します。
	 * 
	 * @param postText	投稿内容
	 * @param topicNo	トピック番号
	 * @param postNo	ポスト番号
	 * @return			投稿内容変更後の投稿一覧データを検索して返します。
	 */
	@Transactional(rollbackFor = Throwable.class)
	@LogRequired
	public Topic editPost(String postText, String topicNo, int postNo) {
		meetingRoomRepository.editerPost(postText, topicNo, postNo);
		return meetingRoomRepository.findTopic(topicNo).orElse(new Topic());
	}

	/**
	 * @メソッド説明：		投稿を削除するメソッドを呼び出します。
	 * 
	 * @param postText	投稿内容
	 * @param topicNo	トピック番号
	 * @param postNo	ポスト番号
	 * @return			投稿内容削除後の投稿一覧データを検索して返します。
	 */
	@Transactional(rollbackFor = Throwable.class)
	@LogRequired
	public Topic deletePost(String topicNo, int postNo) {
		meetingRoomRepository.deleterPost(topicNo, postNo);
		return meetingRoomRepository.findTopic(topicNo).orElse(new Topic());
	}
	
	/**
	 * @メソッド説明：		投稿トピックを削除するメソッドを呼び出します。
	 * 
	 * @param topicNo	トピック番号
	 * @return			投稿トピック削除後の投稿一覧データを検索して返します。
	 */
	@Transactional(rollbackFor = Throwable.class)
	@LogRequired
	public List<Topic> deleteTopic(String topicNo) {
		meetingRoomRepository.deleterTopic(topicNo);
		return meetingRoomRepository.searchAllTopics();
	}
	
	@Transactional(rollbackFor = Throwable.class)
	@LogRequired
	public Topic reloadTopic(String topicNo) {
		return meetingRoomRepository.findTopic(topicNo).orElse(new Topic());
	}

	@Transactional(rollbackFor = Throwable.class)
	@LogRequired
	public void postRating(String topicNo, int postNo, int rating, AuthenticatedUser user) {
		int currentRating = meetingRoomRepository.currentRating(topicNo, postNo, user).orElse(0);
		int ratingForUpdate;
		if (rating == currentRating) {
			ratingForUpdate = 0;
		} else {
			ratingForUpdate = rating;
		}
		meetingRoomRepository.updateRating(topicNo, postNo, user, ratingForUpdate);
	}
}