package com.epam.note.services.interafaces;

import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;

import java.util.List;
import java.util.Set;

public interface NoteService {
    List<NotebookEntity> getNotebooks(int userId);

    void saveNotebook(NotebookEntity noteBook);

    List<NoteEntity> getNotes(int notebookId);

    void saveNote(NoteEntity noteEntity);

    Set<LabelEntity> getAllLabels(int userId);

    List<NoteEntity> getNotesByLabelId(int labelId);
}
