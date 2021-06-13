package pl.panel.discussion.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pl.panel.discussion.dao.ForumPostDao;
import pl.panel.discussion.model.ForumPostDTO;

@Repository
public class ForumPostDaoImpl implements ForumPostDao 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	RowMapper<ForumPostDTO> rowMapper;
	
	@Override
	public ForumPostDTO selectForumPost(String titleId) 
	{
		String sql = "SELECT * FROM forum_post WHERE title_id=?";
		String args = titleId;
		
		List<ForumPostDTO> forumPost = this.jdbcTemplate.query(sql, rowMapper, args);
		
		return forumPost.get(0);
	}
	
	@Override
	public List<ForumPostDTO> getForumPostList() 
	{
		String sql = "SELECT * FROM forum_post";
		List<ForumPostDTO> forumPostList = this.jdbcTemplate.query(sql, rowMapper);
		
		return forumPostList;
	}
	
	@Override
	public void createForumPost(ForumPostDTO forumPostDTO) 
	{
		String sql = "INSERT INTO forum_post(title, content, creator_id, creator_name, created_on) "
				   + "VALUES(?, ?, ?, ?, now())";
		Object[] args = {
							forumPostDTO.getTitle().trim(), 
							forumPostDTO.getContent(), 
							forumPostDTO.getCreatorId(),
							forumPostDTO.getCreatorName()
						};
		
		int numberOfRowsInserted = jdbcTemplate.update(sql, args);
		
		System.out.println(numberOfRowsInserted + " row(s) updated");
	}
	
	@Override
	public void updateForumPostComment(String commentCount, String titleId) 
	{
		String sql = "UPDATE forum_post SET comment_count=? WHERE title_id=?";
		Object[] args = {commentCount, titleId};
		
		int numberOfRowsUpdated = jdbcTemplate.update(sql, args);
		
		System.out.println(numberOfRowsUpdated + " row(s) updated");
	}
}
