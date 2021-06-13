package pl.panel.discussion.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.panel.discussion.dao.ForumPostCommentsDao;
import pl.panel.discussion.model.ForumPostCommentsDTO;

@Repository
public class ForumPostCommentsDaoImpl implements ForumPostCommentsDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Map<Integer, List<String>> getForumPostCommentMap(String titleId) 
	{
		String sql = "SELECT * FROM forum_post_comments WHERE title_id=?";
		String args = titleId;
		
		Map<Integer, List<String>> forumPostCommentMap = this.jdbcTemplate.query(sql, 
																				new ForumPostCommentsResultSetExtractorImpl(), 
																				args);
		
		return forumPostCommentMap;
	}
	
	@Override
	public String getForumPostCommentCount(String titleId) 
	{
		String sql = "SELECT COUNT(*) FROM forum_post_comments WHERE title_id=?";
		String args = titleId;
		
		String forumPostCommentCount = this.jdbcTemplate.queryForObject(sql, String.class, args);
		
		return forumPostCommentCount;
	}
	
	@Override
	public void insertForumPostComment(ForumPostCommentsDTO forumPostCommentsDTO) 
	{
		String sql = "INSERT INTO forum_post_comments VALUES(?, ?, ?, ?, now())";
		Object[] args = {
							forumPostCommentsDTO.getTitleId(), 
							forumPostCommentsDTO.getComment(), 
							forumPostCommentsDTO.getCreatorId(),
							forumPostCommentsDTO.getCreatorName()
						};
		
		int numberOfRowsInserted = jdbcTemplate.update(sql, args);
		
		System.out.println(numberOfRowsInserted + " row(s) updated");
	}
}
