package pl.panel.discussion.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import pl.panel.discussion.model.ForumPostDTO;
import pl.panel.discussion.service.CustomDateTimeFormatService;
import pl.panel.discussion.serviceImpl.CustomDateTimeFormatServiceImpl;

@Component
public class ForumPostRowMapperImpl implements RowMapper<ForumPostDTO>
{	
	CustomDateTimeFormatService customDateTimeFormatService = new CustomDateTimeFormatServiceImpl();
	
	@Override
	public ForumPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		ForumPostDTO forumPostDTO = new ForumPostDTO();
		
		forumPostDTO.setTitleId(rs.getString(1));
		forumPostDTO.setTitle(rs.getString(2));
		forumPostDTO.setContent(rs.getString(3));
		forumPostDTO.setCommentCount(rs.getString(4));
		forumPostDTO.setCreatorId(rs.getString(5));
		forumPostDTO.setCreatorName(rs.getString(6));
		
		String timestamp = rs.getString(7);
		timestamp = timestamp.substring(0, timestamp.length()-2); //remove .0 from the timestamp
		
		String[] splitTimestamp = timestamp.split(" ");
		String dateAsString = splitTimestamp[0];
		String timeAsString = splitTimestamp[1];
		
		String formattedDate = customDateTimeFormatService.getFormattedDate(dateAsString);
		String formattedTime = customDateTimeFormatService.getFormattedTime(timeAsString);
		
		if(dateAsString.equals(LocalDate.now().toString()))
			formattedDate = "Today";
		else if(dateAsString.equals(LocalDate.now().minusDays(1).toString()))
			formattedDate = "Yesterday";
		
		String customizedDateTime = formattedDate + " at " + formattedTime;
		
		forumPostDTO.setCreatedOn(customizedDateTime);
		
		return forumPostDTO;
	}
}
