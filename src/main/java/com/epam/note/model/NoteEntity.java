package com.epam.note.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "note")
@Data
public class NoteEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "notebook_id", referencedColumnName = "id", nullable = false)
    private NotebookEntity notebook;

    @Basic
    @Column(name = "caption", nullable = false)
    private String caption;

    @Basic
    @Column(name = "text", nullable = false)
    @Type(type="text")
    private String text;
}
