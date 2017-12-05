package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "note")
@Data
public class NoteEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
