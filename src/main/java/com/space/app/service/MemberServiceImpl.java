package com.space.app.service;

import com.space.app.dao.UserDAO;
import com.space.app.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    UserDAO userDAO;

    public int register(UserDTO userDTO) { return userDAO.insert(userDTO); }
}
