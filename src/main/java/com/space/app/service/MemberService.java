package com.space.app.service;

import com.space.app.domain.UserDTO;

public interface MemberService {
    UserDTO selectUser(String id);
    int register(UserDTO userDTO);
}
