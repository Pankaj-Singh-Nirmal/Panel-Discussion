package pl.panel.discussion.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import pl.panel.discussion.model.ForumPostCommentsDTO;

public interface ForumPostCommentsService
{
	Map<Integer, List<String>> getForumPostCommentMap(String titleId);
	String getForumPostCommentCount(String titleId);
	void insertForumPostComment(ForumPostCommentsDTO forumPostCommentsDTO);
	void forumPostAddAttributes(String titleId, Model model);
}
