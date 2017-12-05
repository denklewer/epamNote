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

    @ManyToOne
    @JoinColumn(name = "label_id", referencedColumnName = "id", nullable = false)
    private LabelEntity label;

    @Basic
    @Column(name = "caption", nullable = false, length = -1)
    private String caption;

    @Basic
    @Column(name = "text", nullable = false, length = -1)
    private String text;
}
