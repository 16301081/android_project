package com.info;

public class Trainer_info {

	private String name;
	private String motto;
	private String introduce;
	
	public Trainer_info(String name, String motto, String introduce) {
		this.name = name;
		this.motto = motto;
		this.introduce = introduce;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	

}
