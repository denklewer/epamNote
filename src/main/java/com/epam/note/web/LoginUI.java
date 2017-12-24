package com.epam.note.web;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@SpringUI(path = "login")
@Theme("valo")
@RequiredArgsConstructor
public class LoginUI extends UI {
    private VerticalLayout mainLayout = new VerticalLayout();
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    protected void init(VaadinRequest request) {
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        mainLayout.addComponent(panel);
        setContent(mainLayout);

        FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        content.addComponent(username);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);

        Button send = new Button("Enter");
        send.addClickListener(h -> {
            Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(),password.getValue());
            Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            getPage().setLocation("/");
        });

        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        mainLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }


}
