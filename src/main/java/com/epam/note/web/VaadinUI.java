package com.epam.note.web;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import lombok.RequiredArgsConstructor;

@SpringUI
@Theme("valo")
@RequiredArgsConstructor
public class VaadinUI extends UI {
    private final IndexView indexView;

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Label(indexView.sayHello()));
    }
}
