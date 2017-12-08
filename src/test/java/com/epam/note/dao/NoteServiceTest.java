package com.epam.note.dao;

import com.epam.note.config.ApplicationConfig;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.services.impl.NoteServiceImpl;
import com.epam.note.services.interafaces.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("dev")
public class NoteServiceTest {
    private ConfigurableApplicationContext ctx;
    private NoteService noteService;
    private int someInt = 1;

    @Mock
    LabelRepository labelRepository;

    @Mock
    NotebookRepository notebookRepository;

    @Mock
    NoteRepository noteRepository;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
        labelRepository = mock(LabelRepository.class);
        //when(labelRepository.findAllByNoteNotebookUserId(anyInt())).thenReturn(ctx.getBean());
        notebookRepository = mock(NotebookRepository.class);
        noteRepository = mock(NoteRepository.class);
        noteService = new NoteServiceImpl(noteRepository, notebookRepository, labelRepository);
    }

    @Test
    public void getAllLabels() {
        verify(labelRepository, atLeastOnce()).findAllByNoteNotebookUserId(anyInt());
        noteService.getAllLabels(someInt);
        noteService.getAllLabels(someInt);
    }

    @Test
    public void getNotebooks() {
        noteService.getNotebooks(someInt);
    }

    @Test
    public void getNotes() {
        noteService.getNotebooks(someInt);
    }

    @Test
    public void getNotesByLabelId() {
        noteService.getNotes(someInt);
    }

    @Test
    public void saveNote() {
        noteService.saveNote(ctx.getBean(NoteEntity.class));
    }

    @Test
    public void saveNotebook() {
        noteService.saveNotebook(ctx.getBean(NotebookEntity.class));
    }



}
