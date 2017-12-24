package com.epam.note.web;

import com.epam.note.model.NoteEntity;
import com.epam.note.services.interafaces.NoteService;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
@RequiredArgsConstructor
public class NoteEditor  extends VerticalLayout{
    private final NoteService noteService;
    private NoteEntity noteEntity;

    private TextField caption = new TextField();
    private TextArea text = new TextArea();
    private LabelList labelList;

    /* Action buttons */
    private Button save = new Button("Save", VaadinIcons.ARROW_CIRCLE_DOWN);
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete", VaadinIcons.TRASH);
    private CssLayout actions = new CssLayout(save, cancel, delete);

    private Binder<NoteEntity> binder = new Binder<>(NoteEntity.class);

    @PostConstruct
    public void init() {
        addComponents(caption, text, actions);

        caption.setWidth(100, Unit.PERCENTAGE);
        text.setWidth(100, Unit.PERCENTAGE);

        binder.bindInstanceFields(this);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> noteService.updateNote(noteEntity));
        cancel.addClickListener(e -> editNote(noteEntity));
        delete.addClickListener(e -> {
            noteService.deleteNote(noteEntity);
            setVisible(false);
        });
        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editNote(NoteEntity note) {
        if (note == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = noteService.getNoteById(note.getId()) != null;
        if (persisted) {
            noteEntity = noteService.getNoteById(note.getId());
        }
        else {
            noteEntity = note;
        }
        cancel.setVisible(persisted);

        binder.setBean(noteEntity);

        setVisible(true);

        save.focus();
        text.selectAll();
    }

    public void setChangeHandler(NotebookEditor.ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }
}
