package com.assignment.leaderboard.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.assignment.leaderboard.dto.UserDetailDTO;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * User Domain Object to store User Information
 * 
 * @author arivarul.arivazhagan
 *
 */

@Log4j2
@Data
@Entity(name = "UserDetail")
@Table(name = "User_Detail")
public class UserDetail implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@NonNull
	@Column(name = "NAME")
	private String name;

	@NonNull
	@Column(name = "AGE")
	private Long age;

	@NonNull
	@Column(name = "POINTS")
	private Integer points;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "LAST_UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;

	/**
	 * Default constructor
	 */
	public UserDetail()
	{
		
	}
	
	/**
	 * Generating User Detail from Constructor
	 * 
	 * @param userDetailDTO
	 */
	public UserDetail(UserDetailDTO userDetailDTO)
	{
		this.id = userDetailDTO.getId();
		this.name = userDetailDTO.getName();
		this.age = userDetailDTO.getAge();
		this.points = userDetailDTO.getPoints();
		this.address = userDetailDTO.getAddress();
	}

	/**
	 * Gets invoked before an insert
	 */
	@PrePersist
	void preInsert()
	{
		this.createdDate = new Date();
		this.lastUpdatedDate = new Date();
	}

	/**
	 * Gets invoked before an udpate
	 */
	@PreUpdate
	void preUpdate()
	{
		this.lastUpdatedDate = new Date();
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