package com.myhome.kafka;

import java.io.Serializable;

public class MyHome implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	public MyHome() {
		
	}
	public MyHome(Integer id,String name, String age, String job) {
		this.id=id;
		this.name = name;
		this.age = age;
		this.job = job;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;
	private  String name;
	private  String age;
	private  String job;
	@Override
	public String toString() {
		return "MyHome [id=" + id + ", name=" + name + ", age=" + age + ", job=" + job + "]";
	}
	
	
	
}
