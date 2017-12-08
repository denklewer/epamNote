package com.epam.note.dao;

import com.epam.note.model.NotebookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookRepository extends JpaRepository<NotebookEntity,Integer> {
    List<NotebookEntity> findAllByUserId(int userId);
}
