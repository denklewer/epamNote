package com.epam.note.web;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

@SpringUI(path = "error")
@Theme("valo")
public class ErrorUI extends UI {
    private VerticalLayout mainLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest request) {
        Panel panel = new Panel("Error");
        panel.setSizeUndefined();
        mainLayout.addComponent(panel);
        setContent(mainLayout);

        FormLayout content = new FormLayout();
        Label label = new Label("Something goes wrong.");
        content.addComponent(label);

        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        mainLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }
}
