package com.space.app.dao;

import com.space.app.domain.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSession session;
    private static String board="com.space.app.dao.BoardMapper.";

    @Override
    public List<BoardDTO> selectAll() { return session.selectList(board+"selectAll"); }

    @Override
    public int insert(BoardDTO boardDTO) { return session.insert(board+"insert", boardDTO); }
}
