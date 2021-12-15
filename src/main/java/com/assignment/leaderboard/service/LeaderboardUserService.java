package com.assignment.leaderboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.leaderboard.domain.UserDetail;
import com.assignment.leaderboard.dto.UserDetailDTO;
import com.assignment.leaderboard.exception.LeaderboardException;

@Service
public interface LeaderboardUserService
{

	/**
	 * Fetches all the User details
	 * 
	 * @return
	 */
	public List<UserDetailDTO> getAllUserDetails();

	/**
	 * Gets the specific User detail
	 * 
	 * @param userId
	 * @return
	 */
	public UserDetailDTO getUserDetail(int userId);

	/**
	 * Adds a new User to the Leader Board
	 * 
	 * @param userDetailDto
	 * @return
	 * @throws LeaderboardException
	 */
	public UserDetailDTO addNewUser(UserDetailDTO userDetailDto) throws LeaderboardException;

	/**
	 * Removes the specific User that matches the userId
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(int userId);

	/**
	 * Updates the existing User information by User Id
	 * 
	 * @param userId
	 * @return
	 */
	public UserDetailDTO updateUser(UserDetail newUser) throws LeaderboardException;
}
