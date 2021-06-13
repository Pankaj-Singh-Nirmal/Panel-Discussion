package pl.panel.discussion;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class PanelDiscussionApplication implements CommandLineRunner
{
	private static final Logger logger = Logger.getLogger(PanelDiscussionApplication.class.getName());

    @Autowired
    JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(PanelDiscussionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		logger.info("StartApplication...");
        runJDBC();
	}

	void runJDBC() 
    {
		logger.info("Creating tables...");
    	
    	jdbcTemplate.execute
    	(
			"CREATE TABLE IF NOT EXISTS user_details" 
			   + "(" 
			   + "		user_id VARCHAR(50) NOT NULL," 
			   + "		first_name VARCHAR(50) NOT NULL," 
			   + "		last_name VARCHAR(50) NOT NULL," 
			   + "		password VARCHAR(70) NOT NULL," 
			   + "		PRIMARY KEY(user_id)" 
			   + ");"
    	);
    	
    	jdbcTemplate.execute
    	(
			"CREATE TABLE IF NOT EXISTS forum_post " 
			   + "(" 
			   + "		title_id INT AUTO_INCREMENT," 
			   + "		title TEXT," 
			   + "		content TEXT,"
			   + "		comment_count VARCHAR(50) DEFAULT '0',"
			   + "		creator_id VARCHAR(50),"
			   + "		creator_name VARCHAR(50),"
			   + "		created_on timestamp,"
			   + "		PRIMARY KEY(title_id)" 
			   + ");"
    	);
    	
    	jdbcTemplate.execute
    	(
			"CREATE TABLE IF NOT EXISTS forum_post_comments " 
			   + "(" 
			   + "		title_id VARCHAR(50)," 
			   + "		comment TEXT," 
			   + "		creator_id VARCHAR(50),"
			   + "		creator_name VARCHAR(50),"
			   + "		created_on timestamp,"
			   + "		PRIMARY KEY(created_on)" 
			   + ");"
    	);
    	
    	jdbcTemplate.execute
    	(
			"CREATE TABLE IF NOT EXISTS users " 
			   + "(" 
			   + "		username VARCHAR(50) NOT NULL, " 
			   + "		password VARCHAR(70) NOT NULL, " 
			   + "		enabled TINYINT NOT NULL DEFAULT 1, " 
			   + "  	PRIMARY KEY(username)" 
			   + ");"
    	);
    	
    	jdbcTemplate.execute
    	(
			"CREATE TABLE IF NOT EXISTS authorities " 
			   + "(" 
			   + "		username VARCHAR(50) NOT NULL, " 
			   + "		authority VARCHAR(50) NOT NULL, " 
			   + "  	CONSTRAINT FK_AUTHORITIES_USERS FOREIGN KEY(username) REFERENCES users(username)" 
			   + "		ON UPDATE CASCADE"
			   + ");"
    	);
    }
}
