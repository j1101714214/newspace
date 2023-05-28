package edu.whu.newspace.service.impl;

import edu.whu.newspace.entity.User;
import edu.whu.newspace.entity.dto.UserRegisterDTO;
import edu.whu.newspace.exception.TouTiaoException;
import edu.whu.newspace.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired
    private IUserService userService;
    private UserRegisterDTO mockRegisterUser;

    @BeforeEach
    public void before() {
        mockRegisterUser = new UserRegisterDTO();
        mockRegisterUser.setName("TEST_USER_1");
        mockRegisterUser.setPassword("000000");
    }

    @AfterEach
    public void after() {
        mockRegisterUser = null;
    }

    @Test
    @Rollback
    public void testQueryUserByName() {
        userService.addUser(mockRegisterUser);
        User testUser1 = userService.queryUserByName("TEST_USER_1");
        // 测试通过用户名查询用户信息
        assertNotNull(testUser1);

        assertEquals("TEST_USER_1", testUser1.getName());
        assertEquals("000000", testUser1.getPassword());

        assertThrows(TouTiaoException.class, () -> userService.queryUserByName(""));
    }

    @Test
    @Rollback
    public void testAddUser() {
        userService.addUser(mockRegisterUser);
        User testUser1 = userService.queryUserByName("TEST_USER_1");
        // testUser1为一个mock数据, 仅供测试使用
        // 将user添加入数据库之后, 查询并且检测是否数据被真正插入
        assertNotNull(testUser1);
        // 检验插入的数据是否为插入的数据
        assertEquals("TEST_USER_1", testUser1.getName());
        assertEquals("000000", testUser1.getPassword());
        // 对于重复插入数据的检验
        assertThrows(TouTiaoException.class, () -> userService.addUser(mockRegisterUser));
    }

    @Test
    @Rollback
    public void testUpdateUserInfo() {
        userService.addUser(mockRegisterUser);
        User user = userService.queryUserByName(mockRegisterUser.getName());
        Long id = user.getId();

        user.setName("TEST_USER_2");
        userService.updateUserInfo(user);
        User newUser = userService.queryUserById(id);

        assertEquals("TEST_USER_2", newUser.getName());
        assertEquals(user.getPassword(), newUser.getPassword());

        assertThrows(TouTiaoException.class, () -> userService.updateUserInfo(null));
    }

    @Test
    @Rollback
    public void testDeleteUserById() {
        userService.addUser(mockRegisterUser);
        User user = userService.queryUserByName(mockRegisterUser.getName());
        Long id = user.getId();
        // 测试删除用户操作
        assertNotNull(user);

        userService.deleteUserById(id);
        User userDeleted = userService.queryUserById(id);

        assertNull(userDeleted);
    }

    @Test
    @Rollback
    public void testQueryUserList() {
        int pageNum = 1;
        int pageSize = 3;

        String username = mockRegisterUser.getName();
        for(int i = 0; i < 6; ++i) {
            mockRegisterUser.setName(username + i);
            userService.addUser(mockRegisterUser);
        }

        List<User> users = userService.queryUserList(pageSize, pageNum);
        assertNotNull(users);
        assertEquals(3, users.size());

        assertEquals("王五", users.get(0).getName());

        // 检测第二页
        users = userService.queryUserList(pageSize, (pageNum + 1));
        assertNotNull(users);
        assertEquals(3, users.size());
        System.out.println(users);
        assertEquals("赵六", users.get(0).getName());

        assertThrows(TouTiaoException.class, () -> userService.queryUserList(0, 1));
        assertThrows(TouTiaoException.class, () -> userService.queryUserList(-1, 1));
        assertThrows(TouTiaoException.class, () -> userService.queryUserList(1, -1));
        assertThrows(TouTiaoException.class, () -> userService.queryUserList(1, 1000));
    }
}