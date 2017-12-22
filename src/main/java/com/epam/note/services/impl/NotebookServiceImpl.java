package com.epam.note.services.impl;

import com.epam.note.dao.NotebookRepository;
import com.epam.note.model.NotebookEntity;
import com.epam.note.services.interafaces.NotebookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    if (notebookRepository.exists(noteBook.getId())) {
      notebookRepository.save(noteBook);
    }
  }

  @Override
  public void deleteNotebook(NotebookEntity noteBook) {
    notebookRepository.delete(noteBook);
  }

  @Override
  public void deleteNotebookById(Long id) {
    notebookRepository.delete(id);
  }

  @Override
  public NotebookEntity getNotebookById(Long id) {
    return notebookRepository.findOne(id);
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
