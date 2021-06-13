package pl.panel.discussion.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class ForumPostCommentsDTO
{
	private String titleId;
	@NotBlank(message="* Comment can't be empty !")
	private String comment;
	private String creatorId;
	private String creatorName;
	private String createdOn;
	
	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		return "ForumPostCommentsDTO ["
				+ "titleId=" + titleId 
				+ ", comment=" + comment 
				+ ", creatorId=" + creatorId 
				+ ", creatorName=" + creatorName 
				+ ", createdOn=" + createdOn
				+ "]";
	}
}
