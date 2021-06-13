package pl.panel.discussion.dao;

import java.util.List;
import java.util.Map;

import pl.panel.discussion.model.ForumPostCommentsDTO;

public interface ForumPostCommentsDao
{
	Map<Integer, List<String>> getForumPostCommentMap(String titleId);
	String getForumPostCommentCount(String titleId);
	void insertForumPostComment(ForumPostCommentsDTO forumPostCommentsDTO);
}
