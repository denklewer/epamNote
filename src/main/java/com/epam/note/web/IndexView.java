package com.epam.note.web;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class IndexView {
    public String sayHello() {
        return "Hello from bean " + toString();
    }
}
