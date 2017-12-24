package com.epam.note.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.dao.UserRepository;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.NoteService;
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
public class IntegrationNoteTest {

  private ConfigurableApplicationContext ctx;

  @Autowired
  private NotebookRepository notebookRepository;

  @Autowired
  private NoteRepository noteRepository;

  @Autowired
  private NoteService noteService;

  @Autowired
  private UserRepository userRepository;

  private NotebookEntity saveNotebookEntity;
  private NoteEntity saveNoteEntity;


  @Before
  public void initAndSave() {
    ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
    userRepository.save(ctx.getBean(UserEntity.class));
    saveNotebookEntity = notebookRepository.save(ctx.getBean(NotebookEntity.class));
    saveNoteEntity = noteRepository.save(ctx.getBean(NoteEntity.class));
  }

  @Test
  public void createNote() {
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
  public void updateNote() {
    NoteEntity noteEntity = NoteEntity.builder()
                                      .caption("test")
                                      .text("Test text")
                                      .notebook(saveNotebookEntity)
                                      .build();
    noteService.createNote(noteEntity);
    long id = noteEntity.getId();
    NoteEntity expected = noteService.getNoteById(id);
    expected.setText("test text ajahahahah");
    noteService.updateNote(expected);

    NoteEntity current = noteService.getNoteById(id);

    assertThat(expected).isEqualToComparingFieldByField(current);
  }

  @Test
  public void deleteNote() {
    NoteEntity noteEntity = NoteEntity.builder()
                                      .caption("test")
                                      .text("Test text")
                                      .notebook(saveNotebookEntity)
                                      .build();
    noteService.createNote(noteEntity);
    long id = noteEntity.getId();
    assertNotNull(noteService.getNoteById(id));
    noteService.deleteNoteById(id);
    assertNull(noteService.getNoteById(id));

    NoteEntity noteEntity2 = NoteEntity.builder()
                                       .caption("test")
                                       .text("Test text")
                                       .notebook(saveNotebookEntity)
                                       .build();

    noteService.createNote(noteEntity2);
    long id2 = noteEntity2.getId();
    assertNotNull(noteService.getNoteById(id2));
    noteService.deleteNote(noteEntity2);
    assertNull(noteService.getNoteById(id2));
  }

  @Test
  public void getNotes() {

    assertThat(noteService.getAllNotes()
                          .size() > 0).isTrue();
    assertThat(noteService.getNoteById(saveNoteEntity.getId())).isNotEqualTo(null);
  }

  @Test
  public void getNotesByLabelId() {
    assertTrue(noteService.findAllByNotebookId(saveNotebookEntity.getId())
                          .size() > 0);
  }
}
