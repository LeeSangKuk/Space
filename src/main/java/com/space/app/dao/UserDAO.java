package com.space.app.dao;

import com.space.app.domain.UserDTO;

public interface UserDAO {
    UserDTO select(String id);
    int insert(UserDTO userDTO);
}
