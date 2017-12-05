package com.epam.note.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "notebook")
@Data
public class NotebookEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "id", nullable = false)
    private NoteEntity note;
}
