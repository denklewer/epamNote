package com.epam.note.integration;

import com.epam.note.config.ApplicationConfig;
import com.epam.note.dao.LabelRepository;
import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.dao.UserRepository;
import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("dev")
public class IntegrationTest {
    ConfigurableApplicationContext ctx;

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

    private int userId;

    @Before
    public void initAndSave(){
        ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
        userId = ctx.getBean(UserEntity.class).getId();

        userRepository.save(ctx.getBean(UserEntity.class));
        notebookRepository.save(ctx.getBean(NotebookEntity.class));
        noteRepository.save(ctx.getBean(NoteEntity.class));
        labelRepository.save(ctx.getBean(LabelEntity.class));
    }

    @Test
    public void getAllLabels() {
        assertTrue(noteService.getAllLabels(userId).size() > 0);
    }

    @Test
    public void getNotebooks() {
        assertTrue(noteService.getNotebooks(userId).size() > 0);
    }

    @Test
    public void getNotes() {
        assertTrue(noteService.getNotes(ctx.getBean(NotebookEntity.class).getId()).size() > 0);
    }

    @Test
    public void getNotesByLabelId() {
        assertTrue(noteService.getNotesByLabelId(ctx.getBean(LabelEntity.class).getId()).size() > 0);
    }

    @Test
    public void saveNote() {
        NoteEntity note = new NoteEntity();
        note.setCaption("TestNote");
        note.setNotebook(ctx.getBean(NotebookEntity.class));
        note.setText("");
        noteService.saveNote(note);
        assertTrue(noteRepository.findAll()
                .stream()
                .anyMatch(n -> n.getCaption().equals(note.getCaption())));
    }

    @Test
    public void saveNotebook() {
        NotebookEntity notebook = new NotebookEntity();
        notebook.setCaption("TestNotebook");
        notebook.setUser(ctx.getBean(UserEntity.class));
        noteService.saveNotebook(notebook);
        assertTrue(notebookRepository.findAll()
                .stream()
                .anyMatch(n -> n.getCaption().equals(notebook.getCaption())));
    }

}
