package com.epam.note.web;

import com.epam.note.model.NoteEntity;
import com.epam.note.model.NotebookEntity;
import com.epam.note.services.interafaces.NoteService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class NoteList extends VerticalLayout {
    private final NoteService noteService;
    private NotebookEntity notebook;
    private final TextField caption = new TextField("Caption");
    private final Grid<NoteEntity> grid = new Grid<>(NoteEntity.class);
    private final Button addNewBtn = new Button("New note", VaadinIcons.PLUS);

    @Autowired
    public NoteList(NoteService service, NoteEditor noteEditor) {
        this.noteService = service;
        setVisible(false);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth(200, Unit.PIXELS);
        grid.setColumns("caption");

        addComponents(addNewBtn, grid);

        noteEditor.setChangeHandler(() -> {
            noteEditor.setVisible(false);
            grid.setItems(noteService.findAllByNotebookId(notebook.getId()));
        });


        grid.asSingleSelect().addValueChangeListener(e -> {
            noteEditor.editNote(e.getValue());
        });

        addNewBtn.setWidth(200, Unit.PIXELS);
        addNewBtn.addClickListener(e -> {
            addNewNote();
            grid.setItems(noteService.findAllByNotebookId(notebook.getId()));
        });

        setVisible(false);
    }

    void showNotes(NotebookEntity notebook) {
        if (notebook == null) {
            setVisible(false);
            return;
        }

        this.notebook = notebook;
        grid.setCaption(notebook.getCaption());
        grid.setItems(noteService.findAllByNotebookId(notebook.getId()));
        setVisible(true);
    }

    private void addNewNote() {
        noteService.createNote(NoteEntity.builder()
                .caption("New Note")
                .text("")
                .notebook(notebook)
                .build());
    }

}
