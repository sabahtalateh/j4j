package com.sabahtalateh.j4j.oop.tracker;

import java.util.Arrays;
import java.util.Date;

/**
 * Item.
 */
public class Item {
    /**
     * Max comments per one item.
     */
    public static final int DEFAULT_MAX_COMMENTS = 20;

    /**
     * Item ID.
     */
    private String id;

    /**
     * Item name.
     */
    private String name;

    /**
     * Item description.
     */
    private String description;

    /**
     * Comments.
     */
    private String[] comments;

    /**
     * Comments count.
     */
    private int totalComments = 0;

    /**
     * Max comments amount.
     */
    private int maxComments;

    /**
     * Item creation datetime.
     */
    private Date date;

    /**
     * @param name        of item.
     * @param description of item.
     * @param maxComments of item.
     */
    public Item(String name, String description, int maxComments) {
        this.name = name;
        this.description = description;
        date = new Date();
        this.comments = new String[maxComments];
        this.maxComments = maxComments;
    }

    /**
     * @param item to create another.
     */
    public Item(Item item) {
        this.name = item.name;
        this.description = item.description;
        this.date = item.date;
        this.maxComments = item.maxComments;
        this.totalComments = item.totalComments;
        this.comments  = new String[this.maxComments];
        System.arraycopy(item.comments, 0, this.comments, 0, this.maxComments - 1);
    }

    /**
     * @param name        of item.
     * @param description of item.
     */
    public Item(String name, String description) {
        this(name, description, DEFAULT_MAX_COMMENTS);
    }

    /**
     * @param id set id.
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return datetime.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param comment to add.
     */
    public void addComment(String comment) {
        if (totalComments < this.maxComments) {
            this.comments[totalComments++] = comment;
        } else {
            String[] comments = new String[this.maxComments];
            System.arraycopy(this.comments, 1, comments, 0, this.maxComments - 1);
            comments[this.maxComments - 1] = comment;
            this.comments = comments;
        }
    }

    /**
     * @return comments array.
     */
    public String[] getComments() {
        return Arrays.copyOfRange(this.comments, 0, totalComments);
    }

    /**
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
