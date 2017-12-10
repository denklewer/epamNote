package com.epam.note.services.interafaces;

import com.epam.note.model.NoteEntity;
import java.util.List;

public interface NoteService {

  void createNote(NoteEntity noteEntity);

  void updateNote(NoteEntity noteEntity);

  void deleteNote(NoteEntity noteEntity);

  void deleteNoteById(Long id);

  NoteEntity getNoteById(Long id);

  List<NoteEntity> getAllNotes();

  List<NoteEntity> findAllByNotebookId(long notebookId);
}
