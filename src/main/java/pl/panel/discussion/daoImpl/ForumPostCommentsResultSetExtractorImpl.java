package pl.panel.discussion.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import pl.panel.discussion.service.CustomDateTimeFormatService;
import pl.panel.discussion.serviceImpl.CustomDateTimeFormatServiceImpl;

@Component
public class ForumPostCommentsResultSetExtractorImpl implements ResultSetExtractor<Map<Integer, List<String>>>
{
	CustomDateTimeFormatService customDateTimeFormatService = new CustomDateTimeFormatServiceImpl();
	
	@Override
	public Map<Integer, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException 
	{
		Map<Integer, List<String>> forumPostCommentsMap = new LinkedHashMap<>();
		int index = 0;
		
		while(rs.next()) 
		{
			List<String> forumPostCommentsList = new ArrayList<>();
			
			for(int i=2; i<=5; i++) 
			{
				if(i==5) 
				{
					String timestamp = rs.getString(i);
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
					
					forumPostCommentsList.add(customizedDateTime);
				}
				else
					forumPostCommentsList.add(rs.getString(i));
			}
			
			forumPostCommentsMap.put(index++, forumPostCommentsList);
		}
		
		return forumPostCommentsMap;
	}
}
