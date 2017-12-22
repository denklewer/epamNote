package com.epam.note.web;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SpringUI(path = "notebook")
public class NotebookUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        Label label = new Label("Notebooks");
    }
}
