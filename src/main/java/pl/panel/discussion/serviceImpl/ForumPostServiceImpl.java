package pl.panel.discussion.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.panel.discussion.dao.ForumPostDao;
import pl.panel.discussion.model.ForumPostDTO;
import pl.panel.discussion.service.ForumPostService;

@Service
public class ForumPostServiceImpl  implements ForumPostService
{
	@Autowired
	ForumPostDao forumPostDao;

	@Override
	public ForumPostDTO selectForumPost(String titleId) 
	{
		return forumPostDao.selectForumPost(titleId);
	}
	
	@Override
	public List<ForumPostDTO> getForumPostList() 
	{
		return forumPostDao.getForumPostList();
	}
	
	@Override
	public void createForumPost(ForumPostDTO forumPostDTO) 
	{
		forumPostDao.createForumPost(forumPostDTO);
	}

	@Override
	public void updateForumPostComment(String commentCount, String titleId) 
	{
		forumPostDao.updateForumPostComment(commentCount, titleId);
	}
}
