package com.epam.note.integration;

import com.epam.note.dao.LabelRepository;
import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.dao.UserRepository;
import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.LabelService;
import com.epam.note.services.interafaces.NoteService;
import com.epam.note.services.interafaces.NotebookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class IntegrationTest {

  private ConfigurableApplicationContext ctx;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private NotebookRepository notebookRepository;

  @Autowired
  private NoteRepository noteRepository;

  @Autowired
  private LabelRepository labelRepository;

  @Autowired
  private NoteService noteService;

  @Autowired
  private LabelService labelService;

  @Autowired
  private NotebookService notebookService;

  private long userId;
  private NotebookEntity saveNotebookEntity;
  private UserEntity saveUserEntity;
  private NoteEntity saveNoteEntity;
  private LabelEntity saveLabelEntity;

  @Before
  public void initAndSave() {
    ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
    userId = ctx.getBean(UserEntity.class)
                .getId();

    saveUserEntity = userRepository.save(ctx.getBean(UserEntity.class));
    saveNotebookEntity = notebookRepository.save(ctx.getBean(NotebookEntity.class));
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
  public void getNotebooks() {
    assertThat(notebookService.getAllNotebooks()
                             .size() > 0).isTrue();
    assertThat(notebookService.getNotebookById(saveNotebookEntity.getId())).isNotEqualTo(null);
  }

  @Test
  public void getNotes() {

    assertThat(labelService.getAllLabels()
                           .size() > 0).isTrue();
    assertThat(labelService.getLabelById(saveLabelEntity.getId())).isNotEqualTo(null);
  }

  @Test
  public void getNotesByLabelId() {
    assertTrue(labelService.findNotesById(saveLabelEntity.getId())
                          .size() > 0);
  }

  @Test
  public void saveNote() {
    String caption = "TestNote";
    NotebookEntity notebookEntity = ctx.getBean(NotebookEntity.class);
    NoteEntity note = NoteEntity.builder()
                                .caption(caption)
                                .text("Test text")
                                .notebook(notebookEntity)
                                .build();
    noteService.createNote(note);
    assertThat(noteRepository.findAll()
                             .stream()
                             .anyMatch(n -> n.getCaption()
                                             .equals(note.getCaption()))).isTrue();
  }

  @Test
  public void saveNotebook() {
    String caption = "TestNotebook";
    UserEntity userEntity = ctx.getBean(UserEntity.class);
    NotebookEntity notebook = NotebookEntity.builder()
                                            .caption(caption)
                                            .user(userEntity)
                                            .build();
    notebookService.createNotebook(notebook);
    assertThat(notebookRepository.findAll()
                                 .stream()
                                 .anyMatch(n -> n.getCaption()
                                                 .equals(notebook.getCaption()))).isTrue();
  }

}
