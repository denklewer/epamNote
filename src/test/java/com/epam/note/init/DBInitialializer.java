package com.epam.note.init;

import com.epam.note.dao.LabelRepository;
import com.epam.note.dao.NoteRepository;
import com.epam.note.dao.NotebookRepository;
import com.epam.note.dao.UserRepository;
import com.epam.note.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
//@ComponentScan("resources/DataBeans.xml")
public class DBInitialializer {
    private final UserRepository userRepository;
    private final NotebookRepository notebookRepository;
    private final NoteRepository noteRepository;
    private final LabelRepository labelRepository;

    private ConfigurableApplicationContext ctx;

    @PostConstruct
    public void init() {
        ctx = new ClassPathXmlApplicationContext("classpath:DataBeans.xml");

        userRepository.save(ctx.getBean(UserEntity.class));
    }
}
