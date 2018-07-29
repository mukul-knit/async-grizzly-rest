package com.pluralsight.async_rest_grizzly;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "age"})
@JsonInclude(Include.NON_NULL)
public class Author {
	
	@MatrixParam("name")
	private String name;
	
	@DefaultValue("No address.")
	private String address;
	
	@QueryParam("city")
	private String city;
	
	
	private int age = 45;
	
	@PathParam("authorId")
	private String id;
	
	public Author() {}
	
	public Author(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
