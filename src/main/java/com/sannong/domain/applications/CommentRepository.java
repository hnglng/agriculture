package com.sannong.domain.applications;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sannong.domain.applications.Answer;

/**
 * comment reporitory
 * @author william zhang
 */
@Repository
@Transactional
public interface CommentRepository {
	
	void addComment(Answer answer);
	
	String getCommentByCondition(Map<String,Object> map);
}
