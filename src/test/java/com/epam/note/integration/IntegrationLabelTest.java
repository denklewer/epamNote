package com.epam.note.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.epam.note.dao.LabelRepository;
import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.dao.UserRepository;
import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.LabelService;
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
public class IntegrationLabelTest {

  private ConfigurableApplicationContext ctx;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private NoteRepository noteRepository;

  @Autowired
  private LabelRepository labelRepository;

  @Autowired
  private LabelService labelService;

  @Autowired
  private NotebookRepository notebookRepository;

  private NoteEntity saveNoteEntity;
  private LabelEntity saveLabelEntity;

  @Before
  public void initAndSave() {
    ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");

    userRepository.save(ctx.getBean(UserEntity.class));
    notebookRepository.save(ctx.getBean(NotebookEntity.class));
    saveNoteEntity = noteRepository.save(ctx.getBean(NoteEntity.class));
    saveLabelEntity = labelRepository.save(ctx.getBean(LabelEntity.class));
  }

  @Test
  public void getAllLabels() {
    assertThat(labelService.getAllLabels()
                           .size() > 0).isTrue();
    assertThat(labelService.getLabelById(saveLabelEntity.getId())).isNotEqualTo(null);
  }

  @Test
  public void updateLabel() {
    long labelEntityId = saveLabelEntity.getId();
    LabelEntity current = labelService.getLabelById(labelEntityId);
    saveLabelEntity.setValue("new test string");
    labelService.updateLabel(current);
    LabelEntity expected = labelService.getLabelById(labelEntityId);
    assertThat(expected).isEqualToComparingFieldByField(current);
  }

  @Test
  public void deleteLabelById() {
    LabelEntity labelEntity2 = LabelEntity.builder()
                                          .note(saveNoteEntity)
                                          .value("test")
                                          .build();
    labelService.createLabel(labelEntity2);
    long id = labelEntity2.getId();

    assertNotNull(labelService.getLabelById(id));
    labelService.deleteLabelById(id);
    assertNull(labelService.getLabelById(id));
  }

}
