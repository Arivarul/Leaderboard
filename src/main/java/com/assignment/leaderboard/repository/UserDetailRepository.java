package com.assignment.leaderboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.leaderboard.domain.UserDetail;

@Repository
@Transactional
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>
{

	/**
	 * Query to fetch all the Users sorted by Points
	 * 
	 * @return
	 */
	@Query(value = "select distinct users.* from User_Detail users "
			+ "order by users.points desc, users.name asc", nativeQuery = true)
	List<UserDetail> findAllActiveUsersOrderByPoints();

	/**
	 * Query to fetch the User by name
	 * 
	 * @param name
	 * @return
	 */
	@Query(value = "select distinct user.* from User_Detail user where user.name =:name", nativeQuery = true)
	UserDetail findActiveUserByName(String name);
}
