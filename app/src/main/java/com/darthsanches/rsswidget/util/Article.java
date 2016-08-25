package com.darthsanches.rsswidget.util;

/**
 * @author alexandroid
 *         <p>
 *         This class is used to store all the information about a given article
 *         that is returned from an RSS feed. It is based on the RSS2.0 definition and
 *         contains the variables for select core attributes in the schema
 */
public class Article {

    private String title;
    private String description;

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
