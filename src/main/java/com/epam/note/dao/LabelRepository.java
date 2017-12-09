package com.epam.note.dao;

import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LabelRepository extends JpaRepository<LabelEntity, Integer> {
    Set<LabelEntity> findAllByNoteNotebookUserId(int userId);

    @Query("select L.note from LabelEntity L where L.id = ?1")
    List<NoteEntity> findNotesById(int labelId);
}
