package com.epam.note.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "note")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "notebook_id", referencedColumnName = "id", nullable = false)
    private NotebookEntity notebook;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LabelEntity> labels;

    @Basic
    @Column(name = "caption", nullable = false)
    private String caption;

    @Basic
    @Column(name = "text", nullable = false)
    @Type(type="text")
    private String text;
}
