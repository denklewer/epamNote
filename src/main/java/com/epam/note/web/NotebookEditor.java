package com.epam.note.web;

import com.epam.note.model.NotebookEntity;
import com.epam.note.services.interafaces.NotebookService;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class NotebookEditor extends Window {
    private final NotebookService notebookService;
    NotebookEntity notebookEntity;

    TextField caption = new TextField("Caption");

    Button save = new Button("Save", VaadinIcons.ARROW_CIRCLE_DOWN);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcons.TRASH);
    CssLayout actions = new CssLayout(save, cancel, delete);



    Binder<NotebookEntity> binder = new Binder<>(NotebookEntity.class);

    @Autowired
    public NotebookEditor(NotebookService notebookService) {
        this.notebookService = notebookService;

        VerticalLayout layout = new VerticalLayout(caption, actions);
        layout.setSpacing(true);

        setContent(layout);
        setPosition(600, 300);

        binder.bindInstanceFields(this);

        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> notebookService.createNotebook(notebookEntity));
        delete.addClickListener(e -> notebookService.deleteNotebookById(notebookEntity.getId()));
        cancel.addClickListener(e -> editNotebook(notebookEntity));
        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editNotebook(NotebookEntity c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = notebookService.getNotebookById(c.getId()) != null;
        if (persisted) {
            notebookEntity = notebookService.getNotebookById(c.getId());
        }
        else {
            notebookEntity = c;
        }
        cancel.setVisible(persisted);

        binder.setBean(notebookEntity);

        setVisible(true);

        save.focus();
        caption.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }
}
