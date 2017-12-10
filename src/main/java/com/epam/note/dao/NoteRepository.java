package com.epam.note.dao;

import com.epam.note.model.NoteEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Integer> {
    List<NoteEntity> findAllByNotebookId(int notebookId);
}
