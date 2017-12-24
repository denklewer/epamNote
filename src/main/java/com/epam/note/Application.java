package com.epam.note;

import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.dao.UserRepository;
import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    final private NotebookRepository notebookRepository;
    final private NoteRepository noteRepository;
    final private UserRepository userRepository;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        UserEntity user = UserEntity.builder().id(0).username("admin")
                .password(bCryptPasswordEncoder.encode("password")).build();
        userRepository.save(user);

        NotebookEntity notebook = NotebookEntity.builder().caption("Notebook").user(user).build();
        notebookRepository.save(notebook);

        NoteEntity note = NoteEntity.builder().caption("Note").text("text").notebook(notebook).build();
        noteRepository.save(note);
    }
}
