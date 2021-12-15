package com.assignment.leaderboard.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.assignment.leaderboard.domain.UserDetail;
import com.assignment.leaderboard.dto.UserDetailDTO;
import com.assignment.leaderboard.exception.LeaderboardException;
import com.assignment.leaderboard.repository.UserDetailRepository;
import com.assignment.leaderboard.service.LeaderboardUserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LeaderboardUserServiceImpl implements LeaderboardUserService
{
	@Autowired
	private UserDetailRepository userDetailRepo;

	private static final Logger log = LoggerFactory.getLogger(LeaderboardUserService.class);

	@Override
	public List<UserDetailDTO> getAllUserDetails()
	{
		List<UserDetail> userDetailList = userDetailRepo.findAllActiveUsersOrderByPoints();
		if (!CollectionUtils.isEmpty(userDetailList))
		{
			return userDetailList.stream().map(userDetail -> new UserDetailDTO(userDetail)).collect(Collectors.toList());
		}
		log.info("LeaderboardUserService: No Users exist now");
		return null;
	}

	/**
	 * Fetches User Detail by User Id
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public UserDetailDTO getUserDetail(int userId)
	{
		UserDetail userDetail = userDetailRepo.findById(userId).orElse(null);
		if (null != userDetail)
		{
			return new UserDetailDTO(userDetail);
		}
		else
		{
			log.info("LeaderboardUserService: No Users exist for id: {}", userId);
		}
		return null;
	}

	/**
	 * Saves a new User Detail
	 * 
	 * @param userDetailDto
	 * @return
	 * @throws LeaderboardException
	 */
	@Override
	public UserDetailDTO addNewUser(UserDetailDTO userDetailDto) throws LeaderboardException
	{
		if (null == userDetailDto)
		{
			throw new LeaderboardException("Null User Object Passed");
		}
		
		if(!userAlreadyExists(userDetailDto.getName()))
		{
			UserDetail userDetail = new UserDetail(userDetailDto);
			userDetail = userDetailRepo.save(userDetail);
			if (null != userDetail)
			{
				log.info("LeaderboardUserService: User with Id: {} is saved.", userDetail.getId());
				return new UserDetailDTO(userDetail);
			}
			else
			{
				log.error("LeaderboardUserService: Cannot Save the User Detail for user: {}", userDetailDto);
				throw new LeaderboardException("Error while saving UserDetail for user: " + userDetailDto);
			}
		}
		else
		{
			log.error("LeaderboardUserService: User with name {} already exists.", userDetailDto.getName());
			return null;
		}
	}

	/**
	 * Verifies if the User is already present by Name
	 * 
	 * @param name
	 * @return
	 */
	private boolean userAlreadyExists(String name)
	{
		return (userDetailRepo.findActiveUserByName(name) != null);
	}

	/**
	 * Removes the User
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public boolean deleteUser(int userId)
	{
		Optional<UserDetail> userDetail = userDetailRepo.findById(userId);
		if (userDetail.isPresent())
		{
			userDetailRepo.delete(userDetail.get());
			if (userDetailRepo.findById(userId).isEmpty())
			{
				log.info("User id {} deleted.", userId);
				return true;
			}
			else
			{
				log.error("LeaderboardUserService: Cannot Delete the User Detail for User Id: {}", userId);
			}
		}
		log.info("LeaderboardUserService: No User with Id: {} exists.", userId);
		return false;
	}

	/**
	 * Updates User Information By User
	 * 
	 * @param updatedUser
	 * @return
	 */
	@Override
	public UserDetailDTO updateUser(UserDetail updatedUser) throws LeaderboardException
	{
		if (null == updatedUser)
		{
			log.error("LeaderboardUserService: Null User Object encountered");
			return null;
		}

		updatedUser = userDetailRepo.save(updatedUser);
		if (null != updatedUser)
		{
			log.info("LeaderboardUserService: Updated User {} ", updatedUser);
			return new UserDetailDTO(updatedUser);
		}
		else
		{
			log.error("LeaderboardUserService: Error while updating User {}", updatedUser);
			throw new LeaderboardException("Couldn't Update User with updatedUser: " + updatedUser);
		}
	}
}
