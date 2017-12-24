package com.epam.note.services.impl;

import com.epam.note.dao.UserRepository;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public void update(UserEntity user) {
        UserEntity oldUser = userRepository.getOne(user.getId());
        if (!user.getUsername().isEmpty()) {
            oldUser.setUsername(user.getUsername());
        }
        if (!user.getPassword().isEmpty()) {
            oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(oldUser);
    }

}
