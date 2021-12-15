package com.assignment.leaderboard.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.assignment.leaderboard.domain.UserDetail;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailDTO
{
	@Id
	@JsonView(UserDetailDTO.UserView.class)
	private int id;

	@NotNull
	@JsonView(UserDetailDTO.UserView.class)
	private String name;

	@JsonView(UserDetailDTO.UserView.class)
	@NotNull
	private Long age;

	@JsonView(UserDetailDTO.UserView.class)
	@NotNull
	private Integer points;

	@JsonView(UserDetailDTO.UserView.class)
	private String address;

	/**
	 * Default constructor
	 */
	public UserDetailDTO()
	{
		
	}
	
	/**
	 * Returning the object in json format
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param points
	 * @param address
	 */
	@JsonCreator
	public UserDetailDTO(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("age") long age,
			@JsonProperty("points") int points, @JsonProperty("address") String address)
	{
		this.id = id;
		this.name = name;
		this.age = age;
		this.points = points;
		this.address = address;
	}

	/**
	 * Sets userDetailDTO from UserDetail
	 * 
	 * @param userDetail
	 */
	public UserDetailDTO(UserDetail userDetail)
	{
		this.id = userDetail.getId();
		this.name = userDetail.getName();
		this.age = userDetail.getAge();
		this.points = userDetail.getPoints();
		this.address = userDetail.getAddress();
	}

	/**
	 * Json View on Click of User
	 * 
	 * @author arivarul.arivazhagan
	 *
	 */
	public interface UserView
	{

	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public Long getAge()
	{
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Long age)
	{
		this.age = age;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints()
	{
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points)
	{
		this.points = points;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

}
