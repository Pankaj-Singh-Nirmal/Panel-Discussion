package pl.panel.discussion.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.panel.discussion.dao.ForumPostCommentsDao;
import pl.panel.discussion.model.ForumPostCommentsDTO;
import pl.panel.discussion.model.ForumPostDTO;
import pl.panel.discussion.service.ForumPostCommentsService;
import pl.panel.discussion.service.ForumPostService;

@Service
public class ForumPostCommentsServiceImpl  implements ForumPostCommentsService
{
	@Autowired
	ForumPostCommentsDao forumPostCommentsDao;
	
	@Autowired
	ForumPostService forumPostService;
	
	@Autowired
	ForumPostCommentsService forumPostCommentsService;

	@Override
	public Map<Integer, List<String>> getForumPostCommentMap(String titleId)
	{
		return forumPostCommentsDao.getForumPostCommentMap(titleId);
	}
	
	@Override
	public String getForumPostCommentCount(String titleId) 
	{
		return forumPostCommentsDao.getForumPostCommentCount(titleId);
	}
	
	@Override
	public void insertForumPostComment(ForumPostCommentsDTO forumPostCommentsDTO) 
	{
		forumPostCommentsDao.insertForumPostComment(forumPostCommentsDTO);
	}
	
	@Override
	public void forumPostAddAttributes(String titleId, Model model) 
	{
		ForumPostDTO forumPost = forumPostService.selectForumPost(titleId);
		model.addAttribute("forumPostDetails", forumPost);
		
		Map<Integer, List<String>> forumPostCommentMap = forumPostCommentsService
				.getForumPostCommentMap(titleId);
		model.addAttribute("commentMap", forumPostCommentMap);
		
		String forumPostCommentCount = forumPostCommentsService.getForumPostCommentCount(titleId);
		model.addAttribute("commentCount", forumPostCommentCount);
	}
}
