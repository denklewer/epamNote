package com.epam.note.services.interafaces;

import com.epam.note.model.UserEntity;

public interface AuthorizationService {
    void save(UserEntity user);

    void delete(UserEntity user);

    void update(UserEntity user);
}
