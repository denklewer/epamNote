package com.epam.note.dao;

import com.epam.note.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Integer> {
    List<NoteEntity> findAllByNotebookId(int notebookId);

    List<NoteEntity> findAllByLabelsId(int labelId);
}
