package com.epam.note.services.interafaces;

import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import java.util.List;
import java.util.Set;

public interface LabelService {

  void createLabel(LabelEntity noteBook);

  void updateLabel(LabelEntity noteBook);

  void deleteLabel(LabelEntity noteBook);

  void deleteLabelById(Long id);

  LabelEntity getLabelById(Long id);

  List<LabelEntity> getAllLabels();

  Set<LabelEntity> findAllByNoteNotebookUserId(int userId);

  List<NoteEntity> findNotesById(long labelId);
}
