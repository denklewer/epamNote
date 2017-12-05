package model;

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

}
