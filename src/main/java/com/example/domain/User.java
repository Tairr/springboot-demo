package com.example.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.lang.String;

@Configuration
@PropertySource(value="classpath:test.properties")
@ConfigurationProperties(prefix = "com.example")
public class User {

	 private Long id; 
	 private String name; 
	 private Integer age;

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public User(Long id,String name,Integer age){
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}public User() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	} 
	 
	public  void foo(String name){
		System.out.println(this.name+" Hello "+name);
	}
}
