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

    @Basic
    @Column(name = "caption", nullable = false, length = -1)
    private String caption;

    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "id", nullable = false)
    private NoteEntity note;
}
