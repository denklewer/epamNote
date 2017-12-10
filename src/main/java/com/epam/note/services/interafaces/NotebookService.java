package com.epam.note.services.interafaces;

import com.epam.note.model.NotebookEntity;
import java.util.List;

public interface NotebookService {

  void createNotebook(NotebookEntity noteBook);

  void updateNotebook(NotebookEntity noteBook);

  void deleteNotebook(NotebookEntity noteBook);

  void deleteNotebookById(Long id);

  NotebookEntity getNotebookById(Long id);

  List<NotebookEntity> getAllNotebooks();

  List<NotebookEntity> findAllByUserId(long userId);
}
