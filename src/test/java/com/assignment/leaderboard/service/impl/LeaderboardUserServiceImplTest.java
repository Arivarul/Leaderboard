package com.assignment.leaderboard.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.assignment.leaderboard.domain.UserDetail;
import com.assignment.leaderboard.dto.UserDetailDTO;
import com.assignment.leaderboard.repository.UserDetailRepository;

@AutoConfigureTestDatabase(replace=Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LeaderboardUserServiceImplTest
{
	@Mock
	private UserDetailRepository mockUserDetailRepo;
	
	private UserDetailDTO userDetailDto1 = new UserDetailDTO();
	
	@Test
	public void whenUpdatingUserPoint()
	{
		userDetailDto1.setId(1);
		userDetailDto1.setName("Emma");
		userDetailDto1.setPoints(1);
		UserDetail userDetail1 = new UserDetail(userDetailDto1);

		when(mockUserDetailRepo.save(userDetail1)).thenReturn(userDetail1);
		UserDetail userDetail = mockUserDetailRepo.save(userDetail1);
		Assert.notNull(userDetail, "The saved object must not be null");
		
		userDetail1.setPoints(2);
		userDetail = mockUserDetailRepo.save(userDetail1);
		Assert.notNull(userDetail, "The saved object must not be null");
		assertEquals(userDetail.getPoints(), 2);
		
		mockUserDetailRepo.delete(userDetail1);
		List<UserDetail> finalUserList = mockUserDetailRepo.findAll();
		assertEquals(finalUserList.size(), 0);
		
	}
}
