package dao;

import model.NotebookEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class NotebookRepositoryTest {
    @Autowired
    private NotebookRepository notebookRepository;

    @Test
    public void  getAll() {
        assertTrue(notebookRepository.findAll().size() > 0);
    }

}
