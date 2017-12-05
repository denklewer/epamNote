package com.epam.note.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "notebook_id", referencedColumnName = "id", nullable = false)
    private NotebookEntity notebook;

    @Basic
    @Column(name = "login", nullable = false, length = -1)
    private String login;

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    private String password;
}
