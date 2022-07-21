package com.space.app.dao;

import com.space.app.domain.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSession session;
    private static String board="com.space.app.dao.BoardMapper.";

    @Override
    public List<BoardDTO> selectAll() { return session.selectList(board+"selectAll"); }

    @Override
    public int insert(BoardDTO boardDTO) { return session.insert(board+"insert", boardDTO); }

    @Override
    public BoardDTO select(Integer bno){
        return session.selectOne(board+"select", bno);
    }

    @Override
    public int increaseViewCnt(Integer bno) { return session.update(board+"increaseViewCnt"); }

    @Override
    public int delete(Integer bno, String writer) {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(board+"delete", map);
    }
}
