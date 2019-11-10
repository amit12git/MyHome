package com.myhome.cassandra;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Data
@Table
public class MyHome {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public MyHome(String id, String name, String age, String job) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.job = job;
	}

	@PrimaryKey
	private @NonNull String id;
	private @NonNull String name;
	private @NonNull String age;
	private @NonNull String job;
	
}
