package com.epam.note.dao;

import com.epam.note.config.ApplicationConfig;
import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
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
public class DaoTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Before
    public void initAndSave(){
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");
        userRepository.save(ctx.getBean(UserEntity.class));
        notebookRepository.save(ctx.getBean(NotebookEntity.class));
        noteRepository.save(ctx.getBean(NoteEntity.class));
        labelRepository.save(ctx.getBean(LabelEntity.class));
    }

    @Test
    public void  getUsers() {
        assertTrue(userRepository.findAll().size() > 0);
    }

    @Test
    public void  getNotebooks() {
        assertTrue(notebookRepository.findAll().size() > 0);
    }

    @Test
    public void  getNotes() {
        assertTrue(noteRepository.findAll().size() > 0);
    }

    @Test
    public void  getLabels() {
        assertTrue(labelRepository.findAll().size() > 0);
    }
}
