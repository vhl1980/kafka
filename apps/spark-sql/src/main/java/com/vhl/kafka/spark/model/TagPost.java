package com.vhl.kafka.spark.model;

import java.io.Serializable;

public class TagPost implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer site_id ;
	private String user_key;
	private Integer property_id;
	private String post_key;
	private String created;
	private Integer familly_id;
	private Integer value;
	private Integer score;
	private String context;
	
	
	public TagPost() {
	}
	
	
	public TagPost(Integer site_id, String user_key, Integer property_id, String post_key, String created,
			Integer familly_id, Integer value, Integer score, String context) {
		super();
		this.site_id = site_id;
		this.user_key = user_key;
		this.property_id = property_id;
		this.post_key = post_key;
		this.created = created;
		this.familly_id = familly_id;
		this.value = value;
		this.score = score;
		this.context = context;
	}


	public Integer getSite_id() {
		return site_id;
	}
	public void setSite_id(Integer site_id) {
		this.site_id = site_id;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public Integer getProperty_id() {
		return property_id;
	}
	public void setProperty_id(Integer property_id) {
		this.property_id = property_id;
	}
	public String getPost_key() {
		return post_key;
	}
	public void setPost_key(String post_key) {
		this.post_key = post_key;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public Integer getFamilly_id() {
		return familly_id;
	}
	public void setFamilly_id(Integer familly_id) {
		this.familly_id = familly_id;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
	

}
