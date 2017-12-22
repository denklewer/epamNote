package com.epam.note.services.impl;

import com.epam.note.dao.UserRepository;
import com.epam.note.model.UserEntity;
import com.epam.note.services.interafaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public void createUser(UserEntity user) {
    userRepository.save(user);
  }

  @Override
  public void updateUser(UserEntity user) {
    if (userRepository.exists(user.getId())) {
      userRepository.save(user);
    }
  }

  @Override
  public void deleteUser(UserEntity user) {
    userRepository.delete(user);
  }

  @Override
  public void deleteUserlById(Long id) {
    userRepository.delete(id);
  }

  @Override
  public UserEntity getUserById(Long id) {
    return userRepository.findOne(id);
  }

  @Override
  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }
}
