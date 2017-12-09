package com.epam.note.service;

import com.epam.note.config.ApplicationConfig;
import com.epam.note.dao.LabelRepository;
import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.services.impl.NoteServiceImpl;
import com.epam.note.services.interafaces.NoteService;
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
    private int someInt = 1;

    @Mock
    private LabelRepository labelRepository;

    @Mock
    private NotebookRepository notebookRepository;

    @Mock
    private NoteRepository noteRepository;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
        noteService = new NoteServiceImpl(noteRepository, notebookRepository, labelRepository);
    }

    @Test
    public void getAllLabels() {
        noteService.getAllLabels(someInt);
        verify(labelRepository, times(1)).findAllByNoteNotebookUserId(anyInt());
    }

    @Test
    public void getNotebooks() {
        noteService.getNotebooks(someInt);
        verify(notebookRepository, times(1)).findAllByUserId(anyInt());
    }

    @Test
    public void getNotes() {
        noteService.getNotes(someInt);
        verify(noteRepository, times(1)).findAllByNotebookId(anyInt());
    }

    @Test
    public void getNotesByLabelId() {
        noteService.getNotesByLabelId(someInt);
        //verify(noteRepository, times(1)).findAllByLabelsId(anyInt());
    }

    @Test
    public void saveNote() {
        noteService.saveNote(ctx.getBean(NoteEntity.class));
        verify(noteRepository, times(1)).save(anyObject());
    }

    @Test
    public void saveNotebook() {
        noteService.saveNotebook(ctx.getBean(NotebookEntity.class));
        verify(notebookRepository, times(1)).save(anyObject());
    }


}
