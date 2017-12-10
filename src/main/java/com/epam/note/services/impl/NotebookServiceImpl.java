package com.epam.note.services.impl;

import com.epam.note.dao.NotebookRepository;
import com.epam.note.model.NotebookEntity;
import com.epam.note.services.interafaces.NotebookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotebookServiceImpl implements NotebookService {

  private final NotebookRepository notebookRepository;

  @Override
  public void createNotebook(NotebookEntity noteBook) {
    notebookRepository.save(noteBook);
  }

  @Override
  public void updateNotebook(NotebookEntity noteBook) {
    if (notebookRepository.existsById(noteBook.getId())) {
      notebookRepository.save(noteBook);
    }
  }

  @Override
  public void deleteNotebook(NotebookEntity noteBook) {
    notebookRepository.delete(noteBook);
  }

  @Override
  public void deleteNotebookById(Long id) {
    notebookRepository.deleteById(id);
  }

  @Override
  public NotebookEntity getNotebookById(Long id) {
    return notebookRepository.findById(id).get();
  }

  @Override
  public List<NotebookEntity> getAllNotebooks() {
    return notebookRepository.findAll();
  }

  @Override
  public List<NotebookEntity> findAllByUserId(long userId) {
    return notebookRepository.findAllByUserId(userId);
  }
}
