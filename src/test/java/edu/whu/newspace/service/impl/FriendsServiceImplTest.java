package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.Friends;
import edu.whu.newspace.service.IFriendsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FriendsServiceImplTest {
    private Friends friends;

    @Autowired
    private IFriendsService friendsService;

    @BeforeEach
    public void init() {
        friends = new Friends(1L, 2L);
    }

    @AfterEach
    public void destroy() {
        friends = null;
    }

    @Rollback
    @Test
    public void testFollow() {
        friendsService.follow(1L, 2L);
    }

    @Rollback
    @Test
    public void testUnfollow() {
        friendsService.unfollow(1L, 2L);
    }
}