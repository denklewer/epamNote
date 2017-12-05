package com.epam.note.dao;

import com.epam.note.ApplicationConfig;
import com.epam.note.model.NotebookEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class NotebookRepositoryTest {
    @Autowired
    private NotebookRepository notebookRepository;

    @Test
    public void  getAll() {
        assertTrue(notebookRepository.findAll().size() > 0);
    }
}
