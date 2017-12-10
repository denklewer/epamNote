package com.epam.note.services.impl;

import com.epam.note.dao.LabelRepository;
import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.services.interafaces.LabelService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LabelServiceImpl implements LabelService {

  private final LabelRepository labelRepository;

  @Override
  public void createLabel(LabelEntity labelEntity) {
    labelRepository.save(labelEntity);
  }

  @Override
  public void updateLabel(LabelEntity labelEntity) {
    if (labelRepository.existsById(labelEntity.getId())) {
      labelRepository.save(labelEntity);
    }
  }

  @Override
  public void deleteLabel(LabelEntity labelEntity) {
    labelRepository.delete(labelEntity);
  }

  @Override
  public void deleteLabelById(Long id) {
    labelRepository.deleteById(id);
  }

  @Override
  public LabelEntity getLabelById(Long id) {
    return labelRepository.findById(id).get();
  }

  @Override
  public List<LabelEntity> getAllLabels() {
    return labelRepository.findAll();
  }

  public Set<LabelEntity> findAllByNoteNotebookUserId(int userId) {
    return labelRepository.findAllByNoteNotebookUserId(userId);
  }

  public List<NoteEntity> findNotesById(long labelId) {
   return labelRepository.findNotesById(labelId);
  }
}
