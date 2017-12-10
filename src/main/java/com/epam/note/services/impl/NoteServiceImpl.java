package com.epam.note.services.impl;

import com.epam.note.dao.NoteRepository;
import com.epam.note.model.NoteEntity;
import com.epam.note.services.interafaces.NoteService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  @Override
  public void createNote(NoteEntity noteEntity) {
    noteRepository.save(noteEntity);
  }

  @Override
  public void updateNote(NoteEntity noteEntity) {
    if (noteRepository.existsById(noteEntity.getId())) {
      noteRepository.save(noteEntity);
    }
  }

  @Override
  public void deleteNote(NoteEntity noteEntity) {
    noteRepository.delete(noteEntity);
  }

  @Override
  public void deleteNoteById(Long id) {
    noteRepository.deleteById(id);
  }

  @Override
  public NoteEntity getNoteById(Long id) {
    return noteRepository.findById(id).get();
  }

  @Override
  public List<NoteEntity> getAllNotes() {
    return noteRepository.findAll();
  }

  public List<NoteEntity> findAllByNotebookId(long notebookId){
   return noteRepository.findAllByNotebookId(notebookId);
  }
}
