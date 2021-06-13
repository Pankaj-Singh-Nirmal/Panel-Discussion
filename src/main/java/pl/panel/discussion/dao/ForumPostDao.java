package pl.panel.discussion.dao;

import java.util.List;

import pl.panel.discussion.model.ForumPostDTO;

public interface ForumPostDao 
{
	ForumPostDTO selectForumPost(String titleId);
	List<ForumPostDTO> getForumPostList();
	void createForumPost(ForumPostDTO forumPostDTO);
	void updateForumPostComment(String titleId, String commentCount);
}
