package com.epam.note.dao;

import com.epam.note.model.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LabelRepository extends JpaRepository<LabelEntity, Integer> {
    Set<LabelEntity> findAllByNoteNotebookUserId(int userId);
}
