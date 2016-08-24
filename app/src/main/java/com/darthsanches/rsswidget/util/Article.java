package com.darthsanches.rsswidget.util;

import java.net.URL;

/**
 * @author alex
 * 
 * This class is used to store all the information about a given article
 * that is returned from an RSS feed. It is based on the RSS2.0 definition and
 * contains the variables for select core attributes in the schema
 *
 */
public class Article {
	
	private long articleId;
	private long feedId;
	private String title;
	private String description;
	/**
	 * @return the articleId
	 */
	public long getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the feedId
	 */
	public long getFeedId() {
		return feedId;
	}
	/**
	 * @param feedId the feedId to set
	 */
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


}
