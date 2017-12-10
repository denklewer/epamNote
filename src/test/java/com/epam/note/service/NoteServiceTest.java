package com.epam.note.service;

import com.epam.note.config.ApplicationConfig;
import com.epam.note.dao.LabelRepository;
import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.services.impl.LabelServiceImpl;
import com.epam.note.services.impl.NoteServiceImpl;
import com.epam.note.services.impl.NotebookServiceImpl;
import com.epam.note.services.interafaces.LabelService;
import com.epam.note.services.interafaces.NoteService;
import com.epam.note.services.interafaces.NotebookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("dev")
public class NoteServiceTest {
    private ConfigurableApplicationContext ctx;
    private NoteService noteService;
    private NotebookService notebookService;
    private LabelService labelService;
    private long someLong = 1;

    @Mock
    private LabelRepository labelRepository;

    @Mock
    private NotebookRepository notebookRepository;

    @Mock
    private NoteRepository noteRepository;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
        noteService=new NoteServiceImpl(noteRepository);
        notebookService=new NotebookServiceImpl(notebookRepository);
        labelService=new LabelServiceImpl(labelRepository);
    }

    @Test
    public void getAllLabels() {
        labelService.getAllLabels();
        verify(labelRepository, times(1)).findAll();
    }

    @Test
    public void getNotebooks() {
        notebookService.getAllNotebooks();
        verify(notebookRepository, times(1)).findAll();
    }

    @Test
    public void getNotes() {
        noteService.getAllNotes();
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    public void getNotesByLabelId() {
        noteService.findAllByNotebookId(someLong);
        verify(noteRepository, times(1)).findAllByNotebookId(anyInt());
    }

    @Test
    public void saveNote() {
        noteService.createNote(ctx.getBean(NoteEntity.class));
        verify(noteRepository, times(1)).save(anyObject());
    }

    @Test
    public void saveNotebook() {
        notebookService.createNotebook(ctx.getBean(NotebookEntity.class));
        verify(notebookRepository, times(1)).save(anyObject());
    }


}
