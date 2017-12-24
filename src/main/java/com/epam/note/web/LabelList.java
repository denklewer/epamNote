package com.epam.note.web;

import com.epam.note.model.LabelEntity;
import com.epam.note.model.NoteEntity;
import com.epam.note.services.interafaces.LabelService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import lombok.RequiredArgsConstructor;


@Theme("valo")
@UIScope
@SpringComponent
@RequiredArgsConstructor
public class LabelList extends VerticalLayout {
    private TextField text = new TextField();
    private Button add = new Button("Add", VaadinIcons.PLUS);
    private CssLayout adding = new CssLayout(text, add);
    private GridLayout labelsLayout = new GridLayout();
    private VerticalLayout layout = new VerticalLayout(adding, labelsLayout);
    private final LabelService labelService;
    private NoteEntity noteEntity;


    public void init(NoteEntity noteEntity) {
        this.noteEntity = noteEntity;
        layout.setSpacing(true);
        labelsLayout.setSpacing(true);
        adding.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        add.setStyleName(ValoTheme.BUTTON_PRIMARY);

        LabelEntity newLabel = LabelEntity.builder().note(noteEntity).value(text.getValue()).build();

        add.addClickListener(e -> {
            labelService.createLabel(newLabel);
        });

        loadLabelsList();
        setVisible(true);
    }

    private void loadLabelsList() {
        for (LabelEntity label : noteEntity.getLabels()) {
            Button labelButton = new Button(label.getValue(), VaadinIcons.TRASH);
            labelButton.addClickListener(e -> {
                labelService.deleteLabel(label);
                loadLabelsList();
            });

            labelsLayout.addComponent(labelButton);
        }
    }
}
