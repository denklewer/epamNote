package com.epam.note.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "label")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "value")
    @NonNull
    private String value;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private NoteEntity note;
}
