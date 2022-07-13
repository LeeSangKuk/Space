package com.space.app.dao;

import com.space.app.domain.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SqlSession session;
    private static String user = "com.space.app.dao.UserMapper.";

    public int insert(UserDTO userDTO) {
        return session.insert(user + "insert", userDTO);
    }
}
