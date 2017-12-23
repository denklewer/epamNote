package com.epam.note.web;

import com.epam.note.model.NotebookEntity;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.NotebookService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.RequiredArgsConstructor;

@SpringUI
@RequiredArgsConstructor
public class NotebookUI extends UI {
    private final NotebookService notebookService;
    private final Grid<NotebookEntity> grid = new Grid<>(NotebookEntity.class);
    private final NotebookEditor editor;
    private final NoteList noteList;
    private final NoteEditor noteEditor;
    private final Button addNewBtn = new Button("New", VaadinIcons.PLUS);
    private final Button editBtn = new Button("Edit", VaadinIcons.PLUS);
    CssLayout actions = new CssLayout(addNewBtn, editBtn);
    private UserEntity user;


    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout notebookLayout = new VerticalLayout(actions, grid);
        HorizontalLayout mainLayout = new HorizontalLayout(notebookLayout, noteList, noteEditor);
        setContent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth(200, Unit.PIXELS);
        grid.setColumns("caption");

        noteEditor.setWidth(600, Unit.PIXELS);

        editBtn.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(e -> {
            noteList.showNotes(e.getValue());

            if (e.getValue() != null) {
                editBtn.setVisible(true);
            } else {
                editBtn.setVisible(false);
            }
        });

        addNewBtn.addClickListener(e -> editor.editNotebook(new NotebookEntity()));
        editBtn.addClickListener(e -> editor.editNotebook(grid.asSingleSelect().getValue()));

        UI.getCurrent().addWindow(editor);

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            grid.setItems(notebookService.getAllNotebooks());
        });

        grid.setItems(notebookService.getAllNotebooks());
    }
}


