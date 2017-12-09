package com.epam.note.services.impl;

import com.epam.note.dao.LabelRepository;
import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.services.interafaces.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NotebookRepository notebookRepository;
    private final LabelRepository labelRepository;

    @Override
    public List<NotebookEntity> getNotebooks(int userId) {
        return notebookRepository.findAllByUserId(userId);
    }

    @Override
    public void saveNotebook(NotebookEntity noteBook) {
        notebookRepository.save(noteBook);
    }

    @Override
    public List<NoteEntity> getNotes(int notebookId) {
        return noteRepository.findAllByNotebookId(notebookId);
    }

    @Override
    public void saveNote(NoteEntity noteEntity) {
        noteRepository.save(noteEntity);
    }

    @Override
    public Set<LabelEntity> getAllLabels(int userId) {
        return labelRepository.findAllByNoteNotebookUserId(userId);
    }

    @Override
    public List<NoteEntity> getNotesByLabelId(int labelId) {
        return noteRepository.findAllByLabelsId(labelId);
    }
}
