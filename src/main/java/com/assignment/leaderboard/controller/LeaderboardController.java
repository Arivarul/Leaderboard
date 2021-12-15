package com.assignment.leaderboard.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.leaderboard.domain.UserDetail;
import com.assignment.leaderboard.dto.UserDetailDTO;
import com.assignment.leaderboard.exception.LeaderboardException;
import com.assignment.leaderboard.service.LeaderboardUserService;

import lombok.extern.log4j.Log4j2;

/**
 * Controller class to handle RestApi calls from UI
 * 
 * @author arivarul.arivazhagan
 *
 */
@Log4j2
@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController
{
	@Autowired
	private LeaderboardUserService leaderboardUserService;
	
	private static final Logger log = LoggerFactory.getLogger(LeaderboardController.class);

	private static final String USER_ID = "userId";

	//private static final String POINT = "point";

	private static final int STEP_VALUE = 1;

	/**
	 * Loads the Home Page of the Leader Board
	 * 
	 * @return
	 */
	@GetMapping(value = "/home")
	public ResponseEntity<List<UserDetailDTO>> loadLeaderboard()
	{
		List<UserDetailDTO> userDetailSet = leaderboardUserService.getAllUserDetails();
		if (CollectionUtils.isEmpty(userDetailSet))
		{
			return new ResponseEntity<List<UserDetailDTO>>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<UserDetailDTO>>(userDetailSet, HttpStatus.OK);
		}
	}

	/**
	 * Increments points to a specific User
	 * 
	 * @param userId
	 * @return
	 * @throws LeaderboardException
	 */
	@PutMapping(value = "/increment")
	public ResponseEntity<UserDetailDTO> incrementUserPoint(@RequestParam(name = USER_ID, required = true) int userId)
			throws LeaderboardException
	{
		UserDetailDTO existingUser = leaderboardUserService.getUserDetail(userId);
		if (null != existingUser)
		{
			int newPoints = existingUser.getPoints() + STEP_VALUE;
			existingUser.setPoints(newPoints);
			UserDetail updatedUser = new UserDetail(existingUser);
			UserDetailDTO updatedUserDto = leaderboardUserService.updateUser(updatedUser);
			if (null != updatedUserDto && updatedUserDto.getPoints() == newPoints)
			{
				return new ResponseEntity<UserDetailDTO>(updatedUserDto, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<UserDetailDTO>(HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<UserDetailDTO>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Decrements points to a specific User
	 * 
	 * @param userId
	 * @return
	 * @throws LeaderboardException
	 */
	@PutMapping(value = "/decrement")
	public ResponseEntity<UserDetailDTO> decrementUserPoint(@RequestParam(name = USER_ID, required = true) int userId)
			throws LeaderboardException
	{
		UserDetailDTO existingUser = leaderboardUserService.getUserDetail(userId);
		if (null != existingUser)
		{
			int newPoints = existingUser.getPoints() - STEP_VALUE;
			if(newPoints < 0)
			{
				log.error("User Point is already at 0. Cannot decrement further");
				return new ResponseEntity<UserDetailDTO>(HttpStatus.EXPECTATION_FAILED);
			}
			existingUser.setPoints(newPoints);
			UserDetail updatedUser = new UserDetail(existingUser);
			UserDetailDTO updatedUserDto = leaderboardUserService.updateUser(updatedUser);
			if (null != updatedUserDto && updatedUserDto.getPoints() == newPoints)
			{
				return new ResponseEntity<UserDetailDTO>(updatedUserDto, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<UserDetailDTO>(HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<UserDetailDTO>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Loads a specific User information
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "/loadUser/{userId}")
	public ResponseEntity<UserDetailDTO> loadUserData(@PathVariable(USER_ID) int userId)
	{
		UserDetailDTO user = leaderboardUserService.getUserDetail(userId);
		if (null != user)
		{
			return new ResponseEntity<UserDetailDTO>(user, HttpStatus.FOUND);
		}
		else
		{
			return new ResponseEntity<UserDetailDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds a new User to the Leader Board
	 * 
	 * @param userDetailDto
	 * @return
	 * @throws LeaderboardException
	 */
	@PostMapping(value = "/addUser")
	public ResponseEntity<UserDetailDTO> saveUser(@Valid @RequestBody UserDetailDTO userDetailDto)
			throws LeaderboardException
	{
		if (null == userDetailDto)
			return new ResponseEntity<UserDetailDTO>(HttpStatus.BAD_REQUEST);

		UserDetailDTO user = leaderboardUserService.addNewUser(userDetailDto);
		if (null != user && null != leaderboardUserService.getUserDetail(user.getId()))
		{
			return new ResponseEntity<UserDetailDTO>(user, HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<UserDetailDTO>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Marks the User information from Leader Board
	 * 
	 * @return
	 * @throws LeaderboardException
	 */
	@DeleteMapping(value = "/deleteUser")
	public ResponseEntity<Boolean> deleteUser(@RequestParam(name = USER_ID, required = true) int userId)
	{
		boolean userDeleted = leaderboardUserService.deleteUser(userId);
		if (userDeleted)
		{
			return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * Helps set points to a specific User if manually set in the UI
	 * 
	 * @param userId
	 * @param point
	 * @return
	 */
//	@PutMapping(value = "/{userId}/setPoint/{point}")
//	public ResponseEntity<UserDetail> decrementUserPoint(@PathVariable(USER_ID) int userId,
//			@PathVariable(POINT) int point)
//	{
//		return null;
//	}
}
