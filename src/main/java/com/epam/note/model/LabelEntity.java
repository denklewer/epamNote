package com.epam.note.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "label")
@Data
public class LabelEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "id", nullable = false)
    private NoteEntity note;
}
