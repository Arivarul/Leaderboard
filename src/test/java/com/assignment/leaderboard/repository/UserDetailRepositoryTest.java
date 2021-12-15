package com.assignment.leaderboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.leaderboard.domain.UserDetail;
import com.assignment.leaderboard.dto.UserDetailDTO;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserDetailRepositoryTest
{

	@Autowired
	private UserDetailRepository userDetailRepository;

	private UserDetailDTO userDetailDto1 = new UserDetailDTO();
	private UserDetailDTO userDetailDto2 = new UserDetailDTO();
	private UserDetailDTO userDetailDto3 = new UserDetailDTO();

	@Test
	void test()
	{
		userDetailRepository.deleteAll();

		userDetailDto1.setName("Emma");
		userDetailDto1.setPoints(1);
		userDetailDto1.setAge(25L);
		userDetailDto1.setAddress("80-7515, Leslie Road, New York, USA");
		UserDetail userDetail1 = new UserDetail(userDetailDto1);
		userDetailRepository.save(userDetail1);

		userDetailDto2.setName("Sherlock");
		userDetailDto2.setPoints(8);
		userDetailDto2.setAge(28L);
		userDetailDto2.setAddress("No 21B, Baker Street, London");
		UserDetail userDetail2 = new UserDetail(userDetailDto2);
		userDetailRepository.save(userDetail2);

		userDetailDto3.setName("Harry Potter");
		userDetailDto3.setPoints(5);
		userDetailDto3.setAge(22L);
		userDetailDto3.setAddress("No 4 Pivet Drive, Little Whinging, Surrey");
		UserDetail userDetail3 = new UserDetail(userDetailDto3);
		userDetailRepository.save(userDetail3);

		List<UserDetail> initialUserList = userDetailRepository.findAll();
		assertEquals(initialUserList.size(), 3);

		List<UserDetail> homePageUserList = userDetailRepository.findAllActiveUsersOrderByPoints();
		assertEquals(homePageUserList.get(0).getName(), "Sherlock");
		assertEquals(homePageUserList.get(0).getPoints(), 8);
		assertEquals(homePageUserList.get(0).getAge(), 28);
		assertEquals(homePageUserList.get(0).getAddress(), "No 21B, Baker Street, London");

		assertEquals(homePageUserList.get(1).getName(), "Harry Potter");
		assertEquals(homePageUserList.get(1).getPoints(), 5);
		assertEquals(homePageUserList.get(1).getAge(), 22);
		assertEquals(homePageUserList.get(1).getAddress(), "No 4 Pivet Drive, Little Whinging, Surrey");

		assertEquals(homePageUserList.get(2).getName(), "Emma");
		assertEquals(homePageUserList.get(2).getPoints(), 1);
		assertEquals(homePageUserList.get(2).getAge(), 25);
		assertEquals(homePageUserList.get(2).getAddress(), "80-7515, Leslie Road, New York, USA");

		UserDetail userData = userDetailRepository.findActiveUserByName("Emma");
		assertEquals(userData.getName(), "Emma");
	}

}
