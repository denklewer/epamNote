package com.epam.note.services.impl;

import com.epam.note.dao.NoteRepository;
import com.epam.note.model.NoteEntity;
import com.epam.note.services.interafaces.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
    if (noteRepository.exists(noteEntity.getId())) {
      noteRepository.save(noteEntity);
    }
  }

  @Override
  public void deleteNote(NoteEntity noteEntity) {
    noteRepository.delete(noteEntity);
  }

  @Override
  public void deleteNoteById(Long id) {
    noteRepository.delete(id);
  }

  @Override
  public NoteEntity getNoteById(Long id) {
    return noteRepository.findOne(id);
  }

  @Override
  public List<NoteEntity> getAllNotes() {
    return noteRepository.findAll();
  }

  public List<NoteEntity> findAllByNotebookId(long notebookId){
   return noteRepository.findAllByNotebookId(notebookId);
  }
}
