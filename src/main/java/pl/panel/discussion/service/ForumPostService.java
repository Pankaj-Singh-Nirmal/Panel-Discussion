package pl.panel.discussion.service;

import java.util.List;

import pl.panel.discussion.model.ForumPostDTO;

public interface ForumPostService 
{
	ForumPostDTO selectForumPost(String titleId);
	List<ForumPostDTO> getForumPostList();
	void createForumPost(ForumPostDTO forumPostDTO);
	void updateForumPostComment(String commentCount, String titleId);
}
