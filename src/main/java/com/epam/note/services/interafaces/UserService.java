package com.epam.note.services.interafaces;

import com.epam.note.model.UserEntity;
import java.util.List;

public interface UserService {

  void createUser(UserEntity noteBook);

  void updateUser(UserEntity noteBook);

  void deleteUser(UserEntity noteBook);

  void deleteUserlById(Long id);

  UserEntity getUserById(Long id);

  List<UserEntity> getAllUsers();
}
