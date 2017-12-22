package com.epam.note.controller;

import com.epam.note.dao.NotebookRepository;
import com.epam.note.model.NotebookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class NotebookController {
    private final NotebookRepository notebookRepository;

    @GetMapping("/notebooks")
    @ResponseBody
    public List<NotebookEntity> getNotebooks() {
        return notebookRepository.findAll();
    }

    @PostMapping("/notebook")
    @ResponseBody
    public NotebookEntity create(HttpServletRequest request) {
        return notebookRepository.save(new NotebookEntity());
    }

    @PutMapping("/notebook")
    @ResponseBody
    public NotebookEntity update(HttpServletRequest request, NotebookEntity notebookEntity) {
        return notebookRepository.save(notebookEntity);
    }
}
