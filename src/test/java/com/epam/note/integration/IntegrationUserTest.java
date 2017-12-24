package com.epam.note.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.epam.note.dao.UserRepository;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.UserService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class IntegrationUserTest {

  private ConfigurableApplicationContext ctx;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  private long userId;

  private UserEntity saveUserEntity;


  @Before
  public void initAndSave() {
    ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
    userId = ctx.getBean(UserEntity.class)
                .getId();

    saveUserEntity = userRepository.save(ctx.getBean(UserEntity.class));
  }

  @Test
  public void createUser() {
    UserEntity current = userService.getUserById(saveUserEntity.getId());
    UserEntity expected = ctx.getBean(UserEntity.class);
    assertThat(current).isEqualToIgnoringGivenFields(expected, "notebooks");
  }

  @Test
  public void updateUser() {
    UserEntity expected = UserEntity.builder()
                                    .password("lol")
                                    .username("name")
                                    .build();
    userService.createUser(expected);
    long id = expected.getId();

    assertNotNull(userService.getUserById(id));

    expected.setUsername("LOL new Name");
    userService.updateUser(expected);
    UserEntity current = userService.getUserById(id);
    assertThat(expected).isEqualToIgnoringGivenFields(current, "notebooks");
  }

  @Test
  public void deleteUser() {

    UserEntity expected = UserEntity.builder()
                                    .password("lol")
                                    .username("name")
                                    .build();
    userService.createUser(expected);
    long id = expected.getId();

    assertNotNull(userService.getUserById(id));
    userService.deleteUserlById(id);
    assertNull(userService.getUserById(id));

    UserEntity expected2 = UserEntity.builder()
                                     .password("lol2")
                                     .username("name2")
                                     .build();
    userService.createUser(expected2);
    long id2 = expected2.getId();

    assertNotNull(userService.getUserById(id2));
    userService.deleteUser(expected2);
    assertNull(userService.getUserById(id2));
  }

  @Test
  public void getAllUsers() {
    UserEntity expected = UserEntity.builder()
                                    .password("lol")
                                    .username("name")
                                    .build();
    UserEntity expected2 = UserEntity.builder()
                                     .password("lol2")
                                     .username("name2")
                                     .build();
    userService.createUser(expected);
    userService.createUser(expected2);
    UserEntity user1 = userService.getUserById(expected.getId());
    UserEntity user2 = userService.getUserById(expected2.getId());
    List<UserEntity> allUsers = userService.getAllUsers();
    assertTrue(allUsers.contains(user1) && allUsers.contains(user2) && allUsers.size() > 2);
  }
}
