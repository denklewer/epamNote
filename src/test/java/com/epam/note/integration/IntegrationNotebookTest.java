package com.epam.note.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.epam.note.dao.NotebookRepository;
import com.epam.note.dao.UserRepository;
import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.NotebookService;
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
public class IntegrationNotebookTest {

  private ConfigurableApplicationContext ctx;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private NotebookRepository notebookRepository;

  @Autowired
  private NotebookService notebookService;

  private NotebookEntity saveNotebookEntity;
  private UserEntity saveUserEntity;


  @Before
  public void initAndSave() {
    ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");

    saveUserEntity = userRepository.save(ctx.getBean(UserEntity.class));
    saveNotebookEntity = notebookRepository.save(ctx.getBean(NotebookEntity.class));
  }


  @Test
  public void createNotebook() {
    assertNotNull(notebookService.getNotebookById(saveNotebookEntity.getId()));
  }

  @Test
  public void updateNotebook() {
    NotebookEntity notebookEntity = NotebookEntity.builder()
                                                  .caption("smth")
                                                  .user(saveUserEntity)
                                                  .build();
    notebookService.createNotebook(notebookEntity);

    long id = notebookEntity.getId();
    NotebookEntity expexted = notebookService.getNotebookById(id);

    expexted.setCaption("new");
    notebookService.updateNotebook(expexted);

    NotebookEntity current = notebookService.getNotebookById(id);
    assertThat(current).isEqualToComparingFieldByField(expexted);
  }

  @Test
  public void deleteNotebook() {
    NotebookEntity notebookEntity = NotebookEntity.builder()
                                                  .caption("smth")
                                                  .user(saveUserEntity)
                                                  .build();
    notebookService.createNotebook(notebookEntity);
    long id = notebookEntity.getId();

    assertNotNull(notebookService.getNotebookById(id));
    notebookService.deleteNotebookById(id);
    assertNull(notebookService.getNotebookById(id));

    NotebookEntity notebookEntity2 = NotebookEntity.builder()
                                                   .caption("smth2")
                                                   .user(saveUserEntity)
                                                   .build();
    notebookService.createNotebook(notebookEntity2);
    long id2 = notebookEntity2.getId();

    assertNotNull(notebookService.getNotebookById(id2));
    notebookService.deleteNotebook(notebookEntity2);
    assertNull(notebookService.getNotebookById(id2));
  }

  @Test
  public void getAllNotebooks() {
    NotebookEntity notebookEntity1 = NotebookEntity.builder()
                                                   .caption("smth")
                                                   .user(saveUserEntity)
                                                   .build();
    notebookService.createNotebook(notebookEntity1);
    NotebookEntity notebookEntity2 = NotebookEntity.builder()
                                                   .caption("smth")
                                                   .user(saveUserEntity)
                                                   .build();
    notebookService.createNotebook(notebookEntity2);
    long id1 = notebookEntity1.getId();
    long id2 = notebookEntity2.getId();
    NotebookEntity notebook1 = notebookService.getNotebookById(id1);
    NotebookEntity notebook2 = notebookService.getNotebookById(id2);

    List<NotebookEntity> allNotebooks = notebookService.getAllNotebooks();

    assertTrue(allNotebooks.contains(notebook1) && allNotebooks.contains(notebook2)
        && allNotebooks.size() > 2);
  }
}
