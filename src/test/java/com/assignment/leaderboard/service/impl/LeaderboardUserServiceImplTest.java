package com.assignment.leaderboard.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.assignment.leaderboard.domain.UserDetail;
import com.assignment.leaderboard.dto.UserDetailDTO;
import com.assignment.leaderboard.repository.UserDetailRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeaderboardUserServiceImplTest
{
	@Mock
	private UserDetailRepository mockUserDetailRepo;

	private UserDetailDTO userDetailDto = new UserDetailDTO();

	UserDetail userDetail = new UserDetail();

	@BeforeEach
	public void setUpBefore()
	{
		userDetailDto.setId(1);
		userDetailDto.setName("Emma");
		userDetailDto.setPoints(1);
		userDetail = new UserDetail(userDetailDto);

		when(mockUserDetailRepo.save(userDetail)).thenReturn(userDetail);
	}

	@Test
	public void whenSavingUserPoint()
	{
		UserDetail userDetail1 = mockUserDetailRepo.save(userDetail);
		Assert.notNull(userDetail1, "The saved object must not be null");
	}

	@Test
	public void whenUpdatingUserPoint()
	{
		UserDetail userDetail1 = mockUserDetailRepo.save(userDetail);
		Assert.notNull(userDetail, "The saved object must not be null");

		userDetail1.setPoints(2);
		userDetail = mockUserDetailRepo.save(userDetail1);
		Assert.notNull(userDetail, "The saved object must not be null");
		assertEquals(userDetail.getPoints().intValue(), 2);

	}

	@Test
	public void whenUserDeleted()
	{
		int id = userDetail.getId();
		mockUserDetailRepo.delete(userDetail);
		List<UserDetail> finalUserList = mockUserDetailRepo.findAll();
		assertEquals(finalUserList.size(), 0);

		Optional<UserDetail> user = mockUserDetailRepo.findById(id);
		assertTrue(user.isEmpty());
	}
}
