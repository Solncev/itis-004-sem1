package com.solncev.net.service;

import com.solncev.net.dto.UserDTO;
import com.solncev.net.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
    UserDTO get(int id);
    void save(User user);
}
