package pl.panel.discussion.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class ForumPostDTO
{
	private String titleId;
	@NotBlank(message="* Title can't be empty !")
	private String title;
	@NotBlank(message="* Content can't be empty !")
	private String content;
	private String commentCount;
	private String creatorId;
	private String creatorName;
	private String createdOn;
	
	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	@Override
	public String toString() {
		return "ForumPostDTO ["
				+ "titleId=" + titleId 
				+ ", title=" + title 
				+ ", content=" + content 
				+ ", commentCount=" + commentCount 
				+ ", creatorId=" + creatorId 
				+ ", creatorName=" + creatorName 
				+ ", createdOn=" + createdOn
				+ "]";
	}
}
